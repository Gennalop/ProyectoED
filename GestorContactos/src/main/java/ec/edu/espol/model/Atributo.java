package ec.edu.espol.model;

import java.io.Serializable;

public class Atributo<E> implements Serializable{
    
    private String nombre;
    private E contenido;
    private String descripcion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public E getContenido() {
        return contenido;
    }

    public void setContenido(E contenido) {
        this.contenido = contenido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
