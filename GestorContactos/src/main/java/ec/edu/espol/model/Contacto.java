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
    protected String perfil;
    protected LinkedList<String> fotos;
    protected LinkedList<Atributo<String>> correos;
    protected LinkedList<Atributo<String>> telefonos;
    protected ArrayList<Atributo<Contacto>> contactosAsociados;

    public Contacto(String nombre, String perfil, LinkedList<String> fotos, LinkedList<Atributo<String>> telefonos, ArrayList<Atributo<Contacto>> contactosAsociados) {
        this.nombre = nombre;
        this.perfil = perfil;
        this.fotos = fotos;
        this.telefonos = telefonos;
        this.contactosAsociados = contactosAsociados;
    }

    public Contacto(String nombre, String perfil, LinkedList<String> fotos) {
        this.nombre = nombre;
        this.perfil = perfil;
        this.fotos = fotos;
    }
    
    //Pasar a utilitaria y comparar con saveFile========================================================
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
    //==================================================================================================

    public String getNombre() {
        return nombre;
    }

    public LinkedList<Atributo<String>> getCorreos() {
        return correos;
    }

    public String getPerfil() {
        return perfil;
    }

    public LinkedList<String> getFotos() {
        return fotos;
    }

    public LinkedList<Atributo<String>> getTelefono() {
        return telefonos;
    }

    public ArrayList<Atributo<Contacto>> getContactosAsociados() {
        return contactosAsociados;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCorreos(LinkedList<Atributo<String>> correos) {
        this.correos = correos;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public void setFotos(LinkedList<String> fotos) {
        this.fotos = fotos;
    }

    public void setTelefono(LinkedList<Atributo<String>> telefono) {
        this.telefonos = telefono;
    }

    public void setContactosAsociados(ArrayList<Atributo<Contacto>> contactosAsociados) {
        this.contactosAsociados = contactosAsociados;
    }
    
    public String toString(){
        return nombre+"-"+perfil;
    }
    
}
