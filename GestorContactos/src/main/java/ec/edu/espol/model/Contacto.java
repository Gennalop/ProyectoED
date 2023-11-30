package ec.edu.espol.model;

import java.io.Serializable;

import util.List;

public class Contacto implements Serializable {
    
    private static final long serialVersionUID = 8799656478674716638L;
    protected long idContacto = 0; 
    protected String nombre;
    protected String perfil;
    protected List<String> fotos;
    protected List<AtributoComplejo> ubicacion;
    protected List<AtributoComplejo> correos;
    protected List<AtributoComplejo> telefonos;
    protected List<Contacto> contactos;

    public Contacto(String nombre, String perfil, List<String> fotos, List<AtributoComplejo> ubicacion, List<AtributoComplejo> correos, List<AtributoComplejo> telefonos, List<Contacto> contactos) {
        this.idContacto = idContacto++;
        this.nombre = nombre;
        this.perfil = perfil;
        this.fotos = fotos;
        this.ubicacion = ubicacion;
        this.correos = correos;
        this.telefonos = telefonos;
        this.contactos = contactos;
    }
    
    //Pasar a utilitaria y comparar con saveFile========================================================
    
    
/*    public LinkedList<Contacto> leerContacto(){
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
    }*/
    //==================================================================================================

    public long getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(long idContacto) {
        this.idContacto = idContacto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public List<String> getFotos() {
        return fotos;
    }

    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }

    public List<AtributoComplejo> getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(List<AtributoComplejo> ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<AtributoComplejo> getCorreos() {
        return correos;
    }

    public void setCorreos(List<AtributoComplejo> correos) {
        this.correos = correos;
    }

    public List<AtributoComplejo> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<AtributoComplejo> telefonos) {
        this.telefonos = telefonos;
    }

    public List<Contacto> getContactos() {
        return contactos;
    }

    public void setContactos(List<Contacto> contactos) {
        this.contactos = contactos;
    }

    public String toString(){
        return this.idContacto + "-" + this.nombre;
    }

}