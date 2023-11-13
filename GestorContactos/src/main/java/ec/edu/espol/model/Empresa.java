/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.util.ArrayList;
import java.util.LinkedList;
import javafx.scene.image.Image;

/**
 *
 * @author Usuario
 */
public class Empresa extends Contacto{
    private String departamento;
    private String sitioWeb;

    public Empresa(String departamento, String sitioWeb, String nombre, String correo, Image perfil, LinkedList<Image> fotos, String telefono, ArrayList<Contacto> contactosAsociados) {
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

    public String getCorreo() {
        return correo;
    }

    public Image getPerfil() {
        return perfil;
    }

    public LinkedList<Image> getFotos() {
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

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setPerfil(Image perfil) {
        this.perfil = perfil;
    }

    public void setFotos(LinkedList<Image> fotos) {
        this.fotos = fotos;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setContactosAsociados(ArrayList<Contacto> contactosAsociados) {
        this.contactosAsociados = contactosAsociados;
    }
    
    
}
