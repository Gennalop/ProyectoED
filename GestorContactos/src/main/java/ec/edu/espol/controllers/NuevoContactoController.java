package ec.edu.espol.controllers;

import ec.edu.espol.gestorcontactos.App;
import ec.edu.espol.model.Contacto;
import ec.edu.espol.model.Empresa;
import ec.edu.espol.model.Persona;
import ec.edu.espol.model.Utilitaria;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

public class NuevoContactoController implements Initializable {

    @FXML
    private ImageView imageView;
    @FXML
    private TextField nombre;
    @FXML
    private TextField telefono;
    @FXML
    private TextField correo;
    @FXML
    private ComboBox<String> cbox;
    @FXML
    private TextField apellido;
    @FXML
    private TextField apodo;
    @FXML
    private VBox panelTelf;
    @FXML
    private VBox panelCorr;
    @FXML
    private VBox panelFotos;
    
    String perfil = "img/usuarioDefault.png";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imageView.setImage(new Image(perfil));
        imageView.setFitWidth(100); imageView.setFitHeight(100);
        cbox.getItems().addAll("Empresa", "Persona");
    }    

    @FXML
    private void insertarImagen(MouseEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPEG Image","*.jpg"), new FileChooser.ExtensionFilter("PNG Image", "*.png"), new FileChooser.ExtensionFilter("All image files","*.jpg","*.png"));
        File imgFile = fc.showOpenDialog(App.getStage());
        if (imgFile != null) {
            perfil = "File:"+imgFile.getPath();
            imageView.setImage(new Image(perfil));
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
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

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alerta");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    private void guardar(MouseEvent event) {
        if (nombre.getText().equals("") || telefono.getText().equals("")) {
            mostrarAlerta("Nombre y Telefono1 son obligatorios");
            return; // Sale del método si la validación falla
        } else{ 
            cancelar(event);
        }
    }

    @FXML
    private void otroTelf(MouseEvent event) {
        TextField t = new TextField();
        int num = panelTelf.getChildren().size();
        t.setPromptText("Telefono " + (num+1));
        panelTelf.getChildren().add(t);
    }

    @FXML
    private void otroCorr(MouseEvent event) {
        TextField c = new TextField();
        int num = panelCorr.getChildren().size();
        c.setPromptText("Correo " + (num+1));
        panelCorr.getChildren().add(c);
    }

    @FXML
    private void subirGaleria(MouseEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPEG Image","*.jpg"), new FileChooser.ExtensionFilter("PNG Image", "*.png"), new FileChooser.ExtensionFilter("All image files","*.jpg","*.png"));
        File imgFile = fc.showOpenDialog(App.getStage());
        if (imgFile != null) {
            String dir = "File:"+imgFile.getPath();
            Label lb = new Label();
            lb.setText(dir);
            panelFotos.getChildren().add(lb);
        }
    }
}
