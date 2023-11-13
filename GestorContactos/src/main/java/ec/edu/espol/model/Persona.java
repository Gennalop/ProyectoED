package ec.edu.espol.model;

import java.util.ArrayList;
import java.util.LinkedList;

public class Persona extends Contacto{
    private String apodo;
    private String apellido;
    private ArrayList<String> telefonos;
    private ArrayList<String> correos;

    public Persona(String nombre, String apellido, String telefono, ArrayList<String> telefonos, String correo, ArrayList<String> correos, String apodo, String perfil, LinkedList<String> fotos, ArrayList<Contacto> contactosAsociados) {
        super(nombre, correo, perfil, fotos, telefono, contactosAsociados);
        this.apodo = apodo;
        this.apellido = apellido;
        this.telefonos = telefonos;
        this.correos = correos;
    }

    public String getApodo() {
        return apodo;
    }

    public String getApellido() {
        return apellido;
    }

    public ArrayList<String> getTelefonos() {
        return telefonos;
    }

    public ArrayList<String> getCorreos() {
        return correos;
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

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setTelefonos(ArrayList<String> telefonos) {
        this.telefonos = telefonos;
    }

    public void setCorreos(ArrayList<String> correos) {
        this.correos = correos;
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
