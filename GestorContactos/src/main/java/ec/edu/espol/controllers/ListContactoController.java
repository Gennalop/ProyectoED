package ec.edu.espol.controllers;

import ec.edu.espol.gestorcontactos.App;
import ec.edu.espol.model.Contacto;
import ec.edu.espol.model.Utilitaria;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ListContactoController implements Initializable {
    
    @FXML
    private VBox panelContactos;
    @FXML
    private VBox panelFavoritos;
    @FXML
    private VBox panelGrupos;
    @FXML
    private ImageView ivNuevo;
    @FXML
    private ImageView ivOpciones;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ivNuevo.setImage(new Image("img/iconoMas.png"));
        ivNuevo.setFitWidth(21); ivNuevo.setFitHeight(21);
        ivOpciones.setImage(new Image("img/iconoOpciones.png"));
        ivOpciones.setFitWidth(20); ivOpciones.setFitHeight(15);
        
        
        
        List<Contacto> contactos = new ArrayList<>();
        
        for (Contacto c : contactos) {
            System.out.println(c.toString());
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("itemContacto.fxml"));
            try {
                HBox hbox = fxmlLoader.load();
                ItemContactoController icc = fxmlLoader.getController();
                icc.setData(c);
                panelContactos.getChildren().add(hbox);
            } catch (IOException e)  {
                e.printStackTrace();
            }
        }
    }    

    @FXML
    private void nuevoContacto(MouseEvent event) {
        try {
            FXMLLoader loader;
            loader = App.loadFXML("nuevoContacto");
            Scene sc = new Scene(loader.load());
            App.setScene(sc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }

    
    
}
