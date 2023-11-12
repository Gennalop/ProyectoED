package ec.edu.espol.model;

import java.io.Serializable;
import javafx.scene.image.Image;

public class Contacto implements Serializable {
    
    private static final long serialVersionUID = 8799656478674716638L;
    String nombre;
    String correo;
    Image perfil;
    String telefono;

    public Contacto(String nombre, String correo, Image perfil, String telefono) {
        this.nombre = nombre;
        this.correo = correo;
        this.perfil = perfil;
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
