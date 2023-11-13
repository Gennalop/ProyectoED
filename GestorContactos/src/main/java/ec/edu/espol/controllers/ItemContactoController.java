package ec.edu.espol.controllers;

import ec.edu.espol.gestorcontactos.App;
import ec.edu.espol.model.Contacto;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ItemContactoController implements Initializable {

    @FXML
    private ImageView imageView;
    @FXML
    private Label nombre;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    void setData(Contacto c) {
        imageView.setImage(new Image(c.getPerfil()));
        nombre.setText(c.getNombre());
    }

    @FXML
    private void infoContacto(MouseEvent event) {
        try {
            FXMLLoader loader;
            loader = App.loadFXML("infoContacto");
            Scene sc = new Scene(loader.load());
            App.setScene(sc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
