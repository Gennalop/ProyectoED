package ec.edu.espol.controllers;

import ec.edu.espol.gestorcontactos.App;
import ec.edu.espol.model.AtributoComplejo;
import ec.edu.espol.model.Contacto;
import ec.edu.espol.model.Empresa;
import ec.edu.espol.model.Persona;
import ec.edu.espol.model.Utilitaria;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import util.ArrayList;
import util.List;

public class NuevoContactoController implements Initializable {

    @FXML
    private ComboBox<String> cbxTipo;
    @FXML
    private VBox campos;
    @FXML
    private VBox panelTelefonos;
    @FXML
    private VBox panelCorreos;
    @FXML
    private VBox panelUbicacion;
    @FXML
    private VBox panelFotos;
    @FXML
    private VBox panelContactosAsociados;
    @FXML
    private ImageView ivPerfil;
    @FXML
    private TextField nombre;
    @FXML
    private ComboBox<HBox> cbxContactosAsociados;

    //atributos de Persona
    private TextField apodo;
    private TextField apellido;
    
    //atributos de una Empresa
    private TextField departamento;
    private TextField sitioWeb;
    
    private List<HBox> telefonos = new ArrayList();
    private List<HBox> correos = new ArrayList();
    private List<HBox> ubicacion = new ArrayList();
    private List<Contacto> contactosAsociados = new ArrayList();
    String perfil = "img/usuarioDefault.png";
    List<String> fotos = new ArrayList<>();
    
    List<Contacto> AllContacts;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ivPerfil.setImage(new Image(perfil));
        ivPerfil.setFitWidth(100); ivPerfil.setFitHeight(100);
        cbxTipo.getItems().addAll("Persona", "Empresa");
        setView(cbxTipo.getPromptText());
        
        setComplexAttribute(panelTelefonos, "Telefono", telefonos);
        setComplexAttribute(panelCorreos, "Correo", correos);
        setComplexAttribute(panelUbicacion, "Ubicacion", ubicacion);
        
    }
    
    private void setView(String item) { 
        
        //Este metodo inicializa la ventana para Persona o Empresa
        
        //Limpiar el VBox específico para campos de persona o empresa
        campos.getChildren().clear();
        
        //Creando los textfield de los atributos
        TextField tf1 = new TextField();
        tf1.setMaxHeight(25); tf1.setMaxWidth(244);
        TextField tf2 = new TextField();
        tf2.setMaxHeight(25); tf2.setMaxWidth(244);
        
        // Mostrar los campos según el tipo seleccionado en el ComboBox
        if ("Persona".equals(item)) {
            apellido = tf1; apodo = tf2;
            apellido.setPromptText("Apellido");
            apodo.setPromptText("Apodo");
            campos.getChildren().addAll(apellido, apodo);
            
        } else if ("Empresa".equals(item)) {
            departamento = tf1; sitioWeb = tf2; 
            departamento.setPromptText("Departamento");
            sitioWeb.setPromptText("Sitio Web");
            campos.getChildren().addAll(departamento, sitioWeb);
        }
        
    }
    
    private void setComplexAttribute(VBox vbox, String promptext, List<HBox> atributos){
        
        //Este metodo muestra en la ventana los atributos complejos como telefono
        //Su visualización es por medio de un hbox
        
        HBox hbox = new HBox();
        
        //Creacion de un combox que almacenará la descripción del atributo
        ComboBox<String> cbx = new ComboBox();
        cbx.setPromptText("Personal");
        cbx.getItems().addAll("Personal", "Domicilio", "Trabajo", "Otro");
        cbx.setMaxHeight(25); cbx.setMaxWidth(94);
        
        //Creacion de un textfield que almacenará el contenido del atributo
        TextField tf = new TextField();
        tf.setPromptText(promptext);
        tf.setMaxHeight(25); tf.setMaxWidth(125);
        
        //Creacion de un button que mostrará por ventana otro atributo complejo del mismo tipo
        Button bt = new Button("+");
        bt.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent t) -> {
                setComplexAttribute(vbox, promptext, atributos);
        });
        bt.setMaxHeight(25); bt.setMaxWidth(25);
        
        hbox.getChildren().addAll(cbx,tf,bt);
        atributos.addLast(hbox);
        vbox.getChildren().add(hbox);
        
    }
    
    private AtributoComplejo getComplexAttribute(HBox atributo, String nombre){
        
        //Este metodo obtiene un Atributo Complejo a partir de un hbox creado en setComplexAttribute
        
        ComboBox<String> cbxDescripcion = (ComboBox<String>) atributo.getChildren().get(0);
        TextField tfContenido = (TextField) atributo.getChildren().get(1);

        String descripcion = cbxDescripcion.getSelectionModel().getSelectedItem();
        String contenido = tfContenido.getText();

        if (!contenido.equals("")) {
            return new AtributoComplejo(nombre, contenido, descripcion);
        }
        return null;
        
    }
    
    private List<AtributoComplejo> getComplexAttributes(List<HBox> atributos, String nombre){
        
        //Este metodo obtiene una lista de Atributos Complejos a partir de una lista de hbox creados en setComplexAttribute
        
        List<AtributoComplejo> retorno = new ArrayList<>();
        for (HBox hbox : atributos){
            AtributoComplejo ac = getComplexAttribute(hbox, nombre);
            if(ac != null){
                retorno.addLast(ac);
            }
        }
        return retorno;
        
    }
    
    @FXML
    private void cboxChange(ActionEvent event) {
        
        //Este metodo inicializa la ventana para Persona o Empresa dependiendo de lo que elija el usuario
        
        ComboBox<String> cb = (ComboBox)event.getSource();
        String selectedItem = cb.getValue();
        setView(selectedItem);
        
    }
    
    @FXML
    private void guardar(MouseEvent event) {
        
        //Este metodo guarda el contacto en el sistema y regresa a la ventana ListContacto
        
        String nomb = nombre.getText();
        List<AtributoComplejo> telf = getComplexAttributes(telefonos, "Telefono");
        
        if (nomb.equals("") || telf.isEmpty()) {
            mostrarAlerta("Nombre y Telefono son obligatorios");
        } else {
            List<AtributoComplejo> corr = getComplexAttributes(correos, "Correo");
            List<AtributoComplejo> ub = getComplexAttributes(ubicacion, "Ubicacion");

            String selectedItem = cbxTipo.getSelectionModel().getSelectedItem();
            
            if ("Persona".equals(selectedItem) || "Persona".equals(cbxTipo.getPromptText())) {
                String apod = apodo.getText();
                String apell = apellido.getText();
                Contacto c = new Persona(apod, apell, nomb, perfil, fotos, ub, corr, telf, contactosAsociados);
                Utilitaria.saveFile(c, "Contacto.XML", false);
            } else if ("Empresa".equals(selectedItem)) {
                String dept = departamento.getText();
                String sitW = sitioWeb.getText();
                Contacto c = new Empresa(dept, sitW, nomb, perfil, fotos, ub, corr, telf, contactosAsociados);
                Utilitaria.saveFile(c, "Contacto.XML", false);
            }
            
            cancelar(event);
        }
        
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alerta");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    
    public void setContacts(List<Contacto> contactos){
        
        AllContacts = contactos;
        
        for (Contacto c : contactos) {
            
            HBox hbox = new HBox();
            hbox.setId(c.getIdContacto()+"");
            hbox.setAlignment(Pos.CENTER_LEFT);
            hbox.setSpacing(10);
            hbox.setMaxHeight(25); hbox.setMaxWidth(222);

            ImageView perfil = new ImageView(new Image(c.getPerfil()));
            perfil.setFitWidth(20); perfil.setFitHeight(20);

            Label nombre = new Label(c.getNombre());
            nombre.setTextFill(Color.BLACK);;
            nombre.setMaxWidth(150); nombre.setMinWidth(150);
            
            Button eliminar = new Button("X");
            eliminar.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent t) -> {
                //contactosAsociados.remove(c);
                //cbxContactosAsociados.getSelectionModel().select(0);
                //cbxContactosAsociados.getItems().remove(hbox);
                cbxContactosAsociados.getItems().add(hbox);
                //cbxContactosAsociados.getSelectionModel().select(-1);
                eliminar.setDisable(true);
                eliminar.setVisible(false);
                panelContactosAsociados.getChildren().remove(hbox);
            });
            eliminar.setDisable(true);
            eliminar.setVisible(false);

            hbox.getChildren().addAll(perfil, nombre, eliminar);
            cbxContactosAsociados.getItems().add(hbox);
        }
        
    }

    @FXML
    private void agregarContactoAsociado(ActionEvent event) {
        
        ComboBox<HBox> cb = (ComboBox)event.getSource();
        HBox selectedItem = cb.getValue();
        
        cbxContactosAsociados.getSelectionModel().select(-1);
        cbxContactosAsociados.getItems().remove(selectedItem);
        
        selectedItem.getChildren().get(2).setDisable(false);
        selectedItem.getChildren().get(2).setVisible(true);
        
        for(Contacto c: AllContacts){
            if((c.getIdContacto()+"").equals(selectedItem.getId())){
                contactosAsociados.addLast(c); break;
            }
        }
        panelContactosAsociados.getChildren().add(selectedItem);
        //cbxContactosAsociados.getSelectionModel().select(0);
        
    }
    
    @FXML
    private void insertarImagen(MouseEvent event) {
        
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPEG Image","*.jpg"), new FileChooser.ExtensionFilter("PNG Image", "*.png"), new FileChooser.ExtensionFilter("All image files","*.jpg","*.png"));
        File imgFile = fc.showOpenDialog(App.getStage());
        
        if (imgFile != null) {
            perfil = "File:"+imgFile.getPath();
            ivPerfil.setImage(new Image(perfil));
            ivPerfil.setFitHeight(100); ivPerfil.setFitWidth(100);
        }
        
    }

    @FXML
    private void subirGaleria(MouseEvent event) {
        
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPEG Image","*.jpg"), new FileChooser.ExtensionFilter("PNG Image", "*.png"), new FileChooser.ExtensionFilter("All image files","*.jpg","*.png"));
        File imgFile = fc.showOpenDialog(App.getStage());
        
        if (imgFile != null) {
            String dir = "File:"+imgFile.getPath();
            fotos.addLast(dir);
            Label lb = new Label();
            lb.setText(dir);
            panelFotos.getChildren().add(lb);
        }
        
    }

    @FXML
    private void cancelar(MouseEvent event) {
        
        try {
            FXMLLoader loader;
            loader = App.loadFXML("listContacto");
            Scene sc = new Scene(loader.load());
            App.setScene(sc);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
}
