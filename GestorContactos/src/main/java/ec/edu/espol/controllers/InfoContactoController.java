package ec.edu.espol.controllers;

import ec.edu.espol.gestorcontactos.App;
import ec.edu.espol.model.AtributoComplejo;
import ec.edu.espol.model.Contacto;
import ec.edu.espol.model.Empresa;
import ec.edu.espol.model.Persona;
import ec.edu.espol.model.Utilitaria;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
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
import javafx.scene.paint.Color;
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
        ant.setVisible(true);
        sgte.setVisible(true);
        
        contactsDisplayed = Utilitaria.readFileContacto("Contacto.XML");        
         
    }
    
@FXML
private void siguiente(MouseEvent event) {
    try {
        FXMLLoader loader = App.loadFXML("infoContacto");
        Scene sc = new Scene(loader.load());
        InfoContactoController icc = loader.getController();

        if (!contactsDisplayed.isEmpty()) {
            currentPos = (currentPos + 1) % contactsDisplayed.size();
            System.out.println("Siguiente - Nuevo índice: " + currentPos);
            Contacto c = contactsDisplayed.get(currentPos);
            System.out.println("Siguiente - Mostrando contacto: " + c);
            icc.setContacto(c);
        } else {
            System.out.println("Siguiente - Lista vacía");
        }

        App.setScene(sc);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

@FXML
private void anterior(MouseEvent event) {
    try {
        FXMLLoader loader = App.loadFXML("infoContacto");
        Scene sc = new Scene(loader.load());
        InfoContactoController icc = loader.getController();

        if (!contactsDisplayed.isEmpty()) {
            currentPos = (currentPos - 1 + contactsDisplayed.size()) % contactsDisplayed.size();
            System.out.println("Anterior - Nuevo índice: " + currentPos);
            Contacto c = contactsDisplayed.get(currentPos);
            System.out.println("Anterior - Mostrando contacto: " + c);
            icc.setContacto(c);
        } else {
            System.out.println("Anterior - Lista vacía");
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
                Comparator<Contacto> cmp = new Comparator<>(){
           @Override
           public int compare(Contacto o1, Contacto o2) {
               return o1.getNombre().compareTo(o2.getNombre());
           }
 
        };
        currentPos = contactsDisplayed.getIndexOf(contacto, cmp);
        
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
        

        //Mover a otro metodo showData
        System.out.println(""+currentPos);
        nombre.setText(contacto.getNombre());
        ivPerfil.setImage(new Image(contacto.getPerfil()));
        VBox telefonos = showComplexAtributte("Telefono", c.getTelefonos());
        VBox correos = showComplexAtributte("Correo", c.getCorreos());
        VBox ubicacion = showComplexAtributte("Ubicacion", c.getUbicacion());
        panelInformacion.getChildren().addAll(telefonos, correos, ubicacion);
        
        if (c.getFotos().size()>1){
            antFoto.setVisible(true); sgteFoto.setVisible(true);
        }
        
        if(c.getFotos().size()!=0){          
        imvFotos.setImage(new Image(c.getFotos().get(0)));
        }
        
        if(c.getContactos()!=null){
            VBox contactos = showAtributteContact("Contactos Asociados", c.getContactos());
            panelInformacion.getChildren().add(0,contactos);
        }
        
    }
    
    private VBox showAtributteContact(String titulo, List<Contacto> contactos){
        VBox vbx = new VBox();
        vbx.setMaxHeight(30); vbx.setMaxWidth(232);
        vbx.setAlignment(Pos.CENTER_LEFT);
        vbx.setStyle("-fx-background-color:  #e1e1e1" );
        vbx.setPadding(new Insets(10,10,10,10));
        vbx.setSpacing(10);
        
        Label label = new Label(titulo);
        label.setStyle("-fx-font-weight: bold;");
        
        vbx.getChildren().add(label);
        for (Contacto c : contactos) {
            HBox hbox = new HBox();
            hbox.setId(c.getIdContacto() + "");
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setSpacing(10);
            hbox.setMaxHeight(25);
            hbox.setMaxWidth(222);

            ImageView perfil = new ImageView(new Image(c.getPerfil()));
            perfil.setFitWidth(20);
            perfil.setFitHeight(20);
            perfil.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent t) -> {
                try {
                    FXMLLoader loader;
                    loader = App.loadFXML("infoContacto");
                    Scene sc = new Scene(loader.load());
                    InfoContactoController icc = loader.getController();
                    icc.setContacto(c);
                    App.setScene(sc);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            
            Label nombre = new Label(c.getNombre());
            nombre.setTextFill(Color.BLACK);;
            nombre.setMaxWidth(150);
            nombre.setMinWidth(150);
            hbox.getChildren().addAll(perfil, nombre);
            
            vbx.getChildren().add(hbox);
        }
        return vbx;
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
