package ec.edu.espol.model;

import util.List;

public class Empresa extends Contacto{
    private String departamento;
    private String sitioWeb;

    public Empresa(String departamento, String sitioWeb, String nombre, String perfil, List<String> fotos, List<AtributoComplejo> ubicacion, List<AtributoComplejo> correos, List<AtributoComplejo> telefonos, List<Contacto> contactos) {
        super(nombre, perfil, fotos, ubicacion, correos, telefonos, contactos);
        this.departamento = departamento;
        this.sitioWeb = sitioWeb;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

}
