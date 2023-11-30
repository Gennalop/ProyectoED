package ec.edu.espol.model;

import util.List;

public class Persona extends Contacto{
    private String apodo;
    private String apellido;

    public Persona(String apodo, String apellido, String nombre, String perfil, List<String> fotos, List<AtributoComplejo> ubicacion, List<AtributoComplejo> correos, List<AtributoComplejo> telefonos, List<Contacto> contactos) {
        super(nombre, perfil, fotos, ubicacion, correos, telefonos, contactos);
        this.apodo = apodo;
        this.apellido = apellido;
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
