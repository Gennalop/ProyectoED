package ec.edu.espol.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Comparator;
import util.ArrayList;

public class Utilitaria {
    
    public static <E> void saveFile(E e, String nomfile, boolean overwrite) {
        File archivo = new File(nomfile);
        if (!archivo.exists() || overwrite == true) {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomfile))) {
                out.writeObject(e);
            } catch (IOException ie) {
                ie.printStackTrace(System.out);
            }
        } else {
            try (myObjectOutputStream out = new myObjectOutputStream(new FileOutputStream(nomfile, true))) {
                out.writeObject(e);
            } catch (Exception ex) {
            }
        }
        //Lo que yo puedo leer
        File file = new File("Copy"+nomfile);
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write(e.toString()+"\n");
            writer.close();
        } catch (IOException ex) {
        }
        /*
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("Copy"+nomfile,true))){
            bw.write(this.grado + "|");
            int cont = 1;
            for(int c : this.coeficientes){
                if(cont == this.coeficientes.length)
                    bw.write(c + "\n");
                else
                    bw.write(c + ",");
                cont++;
            }
        }catch(IOException ie){  
        }*/
    }
    
    //Revisar para usar generics y return List
    public static ArrayList<Contacto> readFileContacto(String nomfile){
        ArrayList<Contacto> retorno = new ArrayList<>();
        Contacto e;
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(nomfile))){
            while(true){
                e = (Contacto) in.readObject();
                System.out.println("Leyendo: "+e.toString());
                retorno.addLast(e);
            }
        }catch(Exception ex){
        }
        return retorno;
    }
    
    public static <E> ArrayList<E> readFile(String nomfile){
        ArrayList<E> retorno = new ArrayList<>();
        E e;
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(nomfile))){
            while(true){
                e = (E) in.readObject();
                System.out.println("Leyendo: "+e.toString());
                retorno.addLast(e);
            }
        }catch(Exception ex){
        }
        return retorno;
    }
    
    public static void removeContact(Contacto contacto, String nomfile){
        
        ArrayList<Contacto> contactos = readFileContacto(nomfile);
        Comparator<Contacto> cmp = new Comparator<>(){
            @Override
            public int compare(Contacto o1, Contacto o2) {
                return o1.getNombre().compareTo(o2.getNombre());
            }
            
        };
        
        contactos.removeElement(cmp, contacto);
//removeElement(Comparator<E> cmp, E element)
        /*for(Contacto c:contactos){
            if(contacto.idContacto == c.idContacto){
                contactos.remove(c);
                break;
            }                  
        }*/
        
        if(contactos.size()==0)
            (new File(nomfile)).delete();  
        
        for(int i = 0; i<contactos.size(); i++){
            if(i == 0){
                saveFile(contactos.get(i), nomfile, true);
            } else {
                saveFile(contactos.get(i), nomfile, false);
            }                             
        }
        
    }
    
    
}
