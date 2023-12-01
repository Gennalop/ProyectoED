package ec.edu.espol.controllers;

import ec.edu.espol.gestorcontactos.App;
import ec.edu.espol.model.AtributoComplejo;
import ec.edu.espol.model.Contacto;
import ec.edu.espol.model.Empresa;
import ec.edu.espol.model.Persona;
import ec.edu.espol.model.Utilitaria;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import util.List;

public class InfoContactoController implements Initializable {

    @FXML
    private Label tipoContacto;
    @FXML
    private Label nombre;
    
    @FXML
    private Button ant;
    @FXML
    private Button sgte;
    private VBox contactos;
    @FXML
    private Button antFoto;
    @FXML
    private Button sgteFoto;
    @FXML
    private ImageView ivEliminar;
    @FXML
    private ImageView ivEditar;
    @FXML
    private ImageView ivPerfil;
    private ImageView ivFotos;
    
    private Contacto contacto;
    private List<Contacto> contactsDisplayed;
    
    private int currentPos;
    private int cont=0;
    @FXML
    private VBox panelInformacion;
    @FXML
    private ImageView imvFotos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ivEliminar.setImage(new Image("img/iconoEliminar.png"));
        ivEliminar.setFitWidth(20); ivEditar.setFitHeight(20);
        ivEditar.setImage(new Image("img/iconoEditar.png"));
        ivEditar.setFitWidth(20); ivEditar.setFitHeight(20);
        
        //allContacts = Utilitaria.readFileContacto("Contacto.XML");
        
         
    }
    
    @FXML
    private void anterior(MouseEvent event) {
        try {
            FXMLLoader loader;
            loader = App.loadFXML("infoContacto");
            Scene sc = new Scene(loader.load());
            InfoContactoController icc = loader.getController();
            currentPos--;
            Contacto c; 
            if(currentPos == -1){
                c=contactsDisplayed.get(contactsDisplayed.size()-1);
                icc.setContacto(c);
            }else{
                c=contactsDisplayed.get(currentPos);
                icc.setContacto(c);
                    
                    }
            App.setScene(sc);
        } catch (IOException e) {
            e.printStackTrace();
        }    
    }
    
    @FXML
    private void anteriorFoto(MouseEvent event) {
        if(cont == 0)
            cont = contacto.getFotos().size();
        cont--;
        ivFotos.setImage(new Image(contacto.getFotos().get(cont)));
    }

    @FXML
    private void siguiente(MouseEvent event) {
        try {
            FXMLLoader loader;
            loader = App.loadFXML("infoContacto");
            Scene sc = new Scene(loader.load());
            InfoContactoController icc = loader.getController();
            currentPos++;
            if(currentPos == (contactsDisplayed.size())){
                icc.setContacto(contactsDisplayed.get(0));
            } else {
                icc.setContacto(contactsDisplayed.get(currentPos));
            }    
            App.setScene(sc);
        } catch (IOException e) {
            e.printStackTrace();
        }    
    }
    
    @FXML
    private void siguienteFoto(MouseEvent event) {
        if(cont == contacto.getFotos().size() - 1)
            cont = -1;
        cont++;
        ivFotos.setImage(new Image(contacto.getFotos().get(cont)));
    }

    @FXML
    private void volver(MouseEvent event) {
        try {
            FXMLLoader loader;
            loader = App.loadFXML("listContacto");
            Scene sc = new Scene(loader.load());
            App.setScene(sc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void setContactsDisplayed(List<Contacto> contacts){
        contactsDisplayed = contacts;
        if(contactsDisplayed.size()>1){
            ant.setVisible(true); sgte.setVisible(true);
        }
    }

    public void setContacto(Contacto c){
        
        contacto = c;
        
        nombre.setText(contacto.getNombre());
        ivPerfil.setImage(new Image(contacto.getPerfil()));
        
        if(c.getClass().equals(Persona.class)){
            tipoContacto.setText("Persona");
            Persona p = (Persona)c;
            HBox apellido = showAtributte("Apellido", p.getApellido());
            HBox apodo = showAtributte("Apodo", p.getApodo());
            panelInformacion.getChildren().add(0, apodo);
            panelInformacion.getChildren().add(0, apellido);
        } else {
            tipoContacto.setText("Empresa");
            Empresa e = (Empresa)c;
            HBox departamento = showAtributte("Departamento", e.getDepartamento());
            HBox sitioWeb = showAtributte("Sitio Web", e.getSitioWeb());
            panelInformacion.getChildren().add(0, departamento);
            panelInformacion.getChildren().add(0, sitioWeb);
        }
        
<<<<<<< HEAD
        //Mover a otro metodo showData
        System.out.println(""+currentPos);
        nombre.setText(contacto.getNombre());
        ivPerfil.setImage(new Image(contacto.getPerfil()));
=======
        VBox telefonos = showComplexAtributte("Telefono", c.getTelefonos());
        VBox correos = showComplexAtributte("Correo", c.getCorreos());
        VBox ubicacion = showComplexAtributte("Ubicacion", c.getUbicacion());
        panelInformacion.getChildren().addAll(telefonos, correos, ubicacion);
        /*
        if(!c.getTelefonos().isEmpty())
            panelInformacion.getChildren().add(showAtributte("Telefono", ));
        
        if(!c.getTelefonos().isEmpty())
            panelInformacion.getChildren().add(showAtributte("Telefono", ));
>>>>>>> 8bea77d8d093ed3b4dc6d8a60db1cc27cf7f2863
        if(contacto.getFotos().size()!=0)
            ivFotos.setImage(new Image(contacto.getFotos().get(cont)));
        /*
        if (contacto.getFotos().size()>1){
            antFoto.setVisible(true); sgteFoto.setVisible(true);
        }
        
        //Implementar un metodo para hacer esta busqueda
        for(int i = 0; i < allContacts.size(); i++){
            if(allContacts.get(i).getNombre().equals(contacto.getNombre()))
                currentPos = i;
        }*/
        
        
        
        /*
        for(String s:contacto.getTelefonos()){
            Label lb = new Label();
            lb.setText(s);
            telefonos.getChildren().add(lb);
        }*/
        /*
        for(String s:contacto.getCorreos()){
            Label lb2 = new Label();
            lb2.setText(s);
            correos.getChildren().add(lb2);
        }
        for(Contacto ct : contacto.getContactos()){
            Label lb3 = new Label();
            lb3.setText(ct.getNombre());
            contactos.getChildren().add(lb3);
        }*/
    }
    
    private HBox showAtributte(String titulo, String contenido){
        HBox hbx = new HBox();
        hbx.setSpacing(10);
        hbx.setMinHeight(30); hbx.setMinWidth(232);
        hbx.setAlignment(Pos.CENTER_LEFT);
        hbx.setStyle("-fx-background-color:  #e1e1e1" );
        hbx.setPadding(new Insets(10, 10,10,10));
        hbx.setSpacing(5);
        Label label1 = new Label(titulo + ": ");
        label1.setStyle("-fx-font-weight: bold;");
        Label label2 = new Label(contenido);
        hbx.getChildren().addAll(label1, label2);
        return hbx;
    }
    
    private VBox showComplexAtributte(String titulo, List<AtributoComplejo> contenido){
        VBox vbx = new VBox();
        vbx.setMaxHeight(30); vbx.setMaxWidth(232);
        vbx.setAlignment(Pos.CENTER_LEFT);
        vbx.setStyle("-fx-background-color:  #e1e1e1" );
        vbx.setPadding(new Insets(10,10,10,10));
        vbx.setSpacing(10);
        Label label1 = new Label(titulo);
        label1.setStyle("-fx-font-weight: bold;");
        vbx.getChildren().add(label1);
        int numeroTelefono = 1;
        for(AtributoComplejo ac : contenido){
            Label label = new Label(ac.getNombre() + " " + numeroTelefono + " ("+ ac.getDescripcion() +") : " + ac.getContenido());
            vbx.getChildren().add(label);
            numeroTelefono++;
        }
        return vbx;
    }

    @FXML
    private void eliminarContacto(MouseEvent event) {
    }

    @FXML
    private void editarContacto(MouseEvent event) {
        try {
            FXMLLoader loader;
            loader = App.loadFXML("editarContacto");
            Scene sc = new Scene(loader.load());
            EditarContactoController edc = loader.getController();
            edc.setContacto(this.contacto);            
            App.setScene(sc);  
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
