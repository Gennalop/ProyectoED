package ec.edu.espol.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Utilitaria {
    
    public static <E> void saveFile(E e, String nomfile) {
        System.out.println("Guardando: " + e.toString());
        File archivo = new File(nomfile);
        if (archivo.exists()) {
            try (myObjectOutputStream out = new myObjectOutputStream(new FileOutputStream(nomfile, true))) {
                out.writeObject(e);
            } catch (Exception ex) {
            }
        } else {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(nomfile))) {
                out.writeObject(e);
            } catch (IOException ie) {
                ie.printStackTrace(System.out);
            }
        }
    }
    
    public static <E> List<E> readFile(String nomfile){
        List<E> retorno = new ArrayList<>();
        E e;
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(nomfile))){
            while(true){
                e = (E) in.readObject();
                System.out.println("Leyendo: "+e.toString());
                retorno.add(e);
            }
        }catch(Exception ex){
        }
        return retorno;
    }
}
