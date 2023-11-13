package ec.edu.espol.controllers;

import ec.edu.espol.gestorcontactos.App;
import ec.edu.espol.model.Contacto;
import ec.edu.espol.model.Empresa;
import ec.edu.espol.model.Persona;
import ec.edu.espol.model.Utilitaria;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    private void guardarPersona(MouseEvent event) {
        String nomb = nombre.getText();
        String apell = apellido.getText();
        String telf = telefono.getText();
        
        ArrayList<String> telfs = new ArrayList<>();
        String[] datosTelf = telefonos.getText().split("/n");
        for(String d: datosTelf){
            telfs.add(d);
        }
           
        String corr = correo.getText();  
        ArrayList<String> corrs = new ArrayList<>();
        String[] datosCorrs = correos.getText().split("/n");
        for(String d: datosCorrs){
            corrs.add(d);
        } 
        
        String apod = apodo.getText();
        String img = perfil.getImage();
        
        LinkedList<String> ftos = new LinkedList<>();
        String[] datosFtos = fotos.getText().spilt("");
        for(String d: datosFtos){
            ftos.add(d);
        } 
        
        ArrayList<Contacto> contsAsoc = new ArrayList<>();
        Contacto[] datosContsAsoc = contactosAsociados.getText().split("/n");
        for(Contacto d: datosContsAsoc){
            contsAsoc.add(d);
        }   

        if (nomb == null || telf == null || nomb.trim().isEmpty() || telf.trim().isEmpty()) {
            mostrarAlerta("Nombre y Apellido son obligatorios");
            return; // Sale del método si la validación falla
        }
        
        Contacto c = new Persona(nomb, apell, telf, telfs, corr, corrs, apod, img, ftos, contsAsoc);
        Utilitaria.saveFile(c, "Contacto.XML");
        cancelar(event);
    }
 
     @FXML
    private void guardarEpresa(MouseEvent event) {
        String nomb = nombre.getText();
        String telf = telefono.getText();
        String dept = departamento.getText();
        String sitioW = sitioWeb.gettext();
        String corr = correo.getText();
        String img = perfil.getImage();        
        
        LinkedList<String> ftos = new LinkedList<>();
        String[] datosFtos = fotos.getText().spilt("");
        for(String d: datosFtos){
            ftos.add(d);
        } 
        
        ArrayList<Contacto> contsAsoc = new ArrayList<>();
        Contacto[] datosContsAsoc = contactosAsociados.getText().split("/n");
        for(Contacto d: datosContsAsoc){
            contsAsoc.add(d);
        }
        if (nomb == null || telf == null || nomb.trim().isEmpty() || telf.trim().isEmpty()) {
            mostrarAlerta("Nombre y Apellido son obligatorios");
            return; // Sale del método si la validación falla
        }      
        
        Contacto c = new Empresa(nomb, telf, dept, sitioW, corr, img, ftos, contsAsoc);
        Utilitaria.saveFile(c, "Contacto.XML");
        cancelar(event);
    }
    
    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Alerta");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
