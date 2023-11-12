package ec.edu.espol.controllers;

import ec.edu.espol.model.Contacto;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ItemContactoController implements Initializable {

    @FXML
    private ImageView imageView;
    @FXML
    private Label nombre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void setData(Contacto c) {
        imageView.setImage(c.getPerfil());
        nombre.setText(c.getNombre());
    }

    @FXML
    private void infoContacto(MouseEvent event) {
    }
    
}
