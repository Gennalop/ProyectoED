package ec.edu.espol.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.scene.control.Alert;
import util.ArrayList;
import util.LinkedList;
import util.List;

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
    }
    
    //Revisar para usar generics y return List
    public static List<Contacto> readFileContacto(String nomfile){
        List<Contacto> retorno = new LinkedList<>();
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
    
    public static List<Contacto> clone(List<Contacto> original) {
        List<Contacto> contactos = new ArrayList<>();
        for (Contacto c:original) {
            contactos.addLast( c);
        }
        return contactos;
    }

    
    public static void removeContact(Contacto contacto, String nomfile){
        /*
        ArrayList<Contacto> contactos = readFileContacto(nomfile);
        
        contactos.removeElement(cmp, contacto)
//removeElement(Comparator<E> cmp, E element)
        for(Contacto c:contactos){
            if(contacto.idContacto == c.idContacto){
                contactos.remove(c);
                break;
            }                  
        }
        
        if(contactos.size()==0)
            (new File(nomfile)).delete();  
        
        for(int i = 0; i<contactos.size(); i++){
            if(i == 0){
                saveFile(contactos.get(i), nomfile, true);
            } else {
                saveFile(contactos.get(i), nomfile, false);
            }                             
        }*/
        
    }
    
    public static void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alerta");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
}
