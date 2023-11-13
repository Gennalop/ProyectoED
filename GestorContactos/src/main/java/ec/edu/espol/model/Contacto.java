package ec.edu.espol.model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.image.Image;

public class Contacto implements Serializable {
    
    private static final long serialVersionUID = 8799656478674716638L;
    protected String nombre;
    protected String correo;
    protected ArrayList<String> correos;
    protected String perfil;
    protected LinkedList<String> fotos;
    protected String telefono;
    protected ArrayList<Integer> telefonos;
    protected ArrayList<Contacto> contactosAsociados;
    

    public Contacto(String nombre, String correo, String perfil, String telefono) {
        this.nombre = nombre;
        this.correo = correo;
        this.perfil = perfil;
        this.telefono = telefono;
    }
    
    public LinkedList<Contacto> leerContacto(){
        LinkedList<Contacto> lista = new LinkedList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("nombre del archivo"))) {
            lista = (LinkedList<Contacto>) in.readObject();
        } catch (FileNotFoundException ex) {
            System.out.println("No se encuentra el archivo" + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("IOException:" + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException:" + ex.getMessage());
        }

        return lista;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Image getPerfil() {
        return perfil;  
    }

    public void setPerfil(Image perfil) {
        this.perfil = perfil;
    }
    
    public String toString(){
        return nombre;
    }
}
