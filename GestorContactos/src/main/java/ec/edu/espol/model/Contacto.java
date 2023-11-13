package ec.edu.espol.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class Contacto implements Serializable {
    
    private static final long serialVersionUID = 8799656478674716638L;
    protected String nombre;
    protected String correo;
    protected String perfil;
    protected LinkedList<String> fotos;
    protected String telefono;
    protected ArrayList<Contacto> contactosAsociados;

    public Contacto(String nombre, String correo, String perfil, LinkedList<String> fotos, String telefono, ArrayList<Contacto> contactosAsociados) {
        this.nombre = nombre;
        this.correo = correo;
   
        this.perfil = perfil;
        this.fotos = fotos;
        this.telefono = telefono;
        this.contactosAsociados = contactosAsociados;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getPerfil() {
        return perfil;
    }

    public LinkedList<String> getFotos() {
        return fotos;
    }

    public String getTelefono() {
        return telefono;
    }

    public ArrayList<Contacto> getContactosAsociados() {
        return contactosAsociados;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public void setFotos(LinkedList<String> fotos) {
        this.fotos = fotos;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setContactosAsociados(ArrayList<Contacto> contactosAsociados) {
        this.contactosAsociados = contactosAsociados;
    }
}    