package ec.edu.espol.model;

import java.io.Serializable;

public class AtributoComplejo<E> implements Serializable{
    
    private static final long serialVersionUID = 8799656478674716638L;
    private String nombre;
    private E contenido;
    private String descripcion;

    public AtributoComplejo(String nombre, E contenido, String descripcion) {
        this.nombre = nombre;
        this.contenido = contenido;
        this.descripcion = descripcion;
    }

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
