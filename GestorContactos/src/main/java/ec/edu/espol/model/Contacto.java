package ec.edu.espol.model;

import java.io.Serializable;

public class Contacto implements Serializable {
    
    private static final long serialVersionUID = 8799656478674716638L;
    String nombre;
    String correo;
    String direccionPerfil;
    String telefono;

    public Contacto(String nombre, String correo, String perfil, String telefono) {
        this.nombre = nombre;
        this.correo = correo;
        this.direccionPerfil = perfil;
        this.telefono = telefono;
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

    public String getPerfil() {
        return direccionPerfil;
    }

    public void setPerfil(String perfil) {
        this.direccionPerfil = perfil;
    }
    
    public String toString(){
        return nombre+"-"+correo+"-"+telefono+"-"+direccionPerfil;
    }
}
