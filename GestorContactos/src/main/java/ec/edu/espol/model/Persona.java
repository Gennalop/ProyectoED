package ec.edu.espol.model;

import java.util.ArrayList;
import java.util.LinkedList;

public class Persona extends Contacto{
    private String apodo;
    private String apellido;
    //private ArrayList<String> telefonos;
    //private ArrayList<String> correos;

    public Persona(String departamento, String sitioWeb, String nombre, String perfil, LinkedList<String> fotos, LinkedList<Atributo<String>> telefono, ArrayList<Atributo<Contacto>> contactosAsociados) {
        super(nombre, perfil, fotos, telefono, contactosAsociados);
        this.apodo = apodo;
        this.apellido = apellido;
        //this.telefonos = telefonos;
        this.correos = correos;
    }

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
}
