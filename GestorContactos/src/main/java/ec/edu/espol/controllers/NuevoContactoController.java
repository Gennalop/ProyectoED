package ec.edu.espol.controllers;

import ec.edu.espol.gestorcontactos.App;
import ec.edu.espol.model.Contacto;
import ec.edu.espol.model.Utilitaria;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

    String perfil = "img/usuarioDefault.png";
    
    @FXML
    private ComboBox<?> cbox;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        imageView.setImage(new Image(perfil));
        imageView.setFitWidth(100); imageView.setFitHeight(100);
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

    @FXML
    private void guardar(MouseEvent event) {
        String nomb = nombre.getText();
        String telf = telefono.getText();
        String corr = correo.getText();
        Contacto c = new Contacto(nomb, corr, perfil, telf);
        Utilitaria.saveFile(c, "Contacto.XML");
        cancelar(event);
    }

}
