package ec.edu.espol.model;

import java.util.ArrayList;
import java.util.LinkedList;
import javafx.scene.image.Image;

public class Empresa extends Contacto{
    private String departamento;
    private String sitioWeb;

    public Empresa(String departamento, String sitioWeb, String nombre, String correo, String perfil, LinkedList<String> fotos, String telefono, ArrayList<Contacto> contactosAsociados) {
        super(nombre, correo, perfil, fotos, telefono, contactosAsociados);
        this.departamento = departamento;
        this.sitioWeb = sitioWeb;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public String getNombre() {
        return nombre;
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

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
