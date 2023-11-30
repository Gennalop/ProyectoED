package ec.edu.espol.controllers;

import ec.edu.espol.gestorcontactos.App;
import ec.edu.espol.model.Contacto;
import ec.edu.espol.model.Utilitaria;
import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import util.List;

public class ListContactoController implements Initializable {

    @FXML
    private VBox panelContactos;
    @FXML
    private VBox panelFavoritos;
    @FXML
    private VBox panelGrupos;
    @FXML
    private ComboBox<String> cbxOrden;
    @FXML
    private ImageView ivNuevo;
    @FXML
    private ImageView ivOpciones;
    @FXML
    private ImageView ivOrdenar;
    @FXML
    private ImageView ivFiltro;
    
    List<Contacto> allContacts;
    List<Contacto> contactsDisplayed;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ivNuevo.setImage(new Image("img/iconoMas.png"));
        ivNuevo.setFitWidth(22); ivNuevo.setFitHeight(22);
        
        ivOpciones.setImage(new Image("img/iconoOpciones.png"));
        ivOpciones.setFitWidth(20); ivOpciones.setFitHeight(15);
        
        ivOrdenar.setImage(new Image("img/iconoOrdenar.png"));
        ivOrdenar.setFitWidth(20); ivOrdenar.setFitHeight(17);
        
        ivFiltro.setImage(new Image("img/iconoFiltrar.png"));
        ivFiltro.setFitWidth(20); ivFiltro.setFitHeight(20);
        
        cbxOrden.getItems().add("Alfabeticamente");
        cbxOrden.getItems().add("Por Tipo");
        cbxOrden.getItems().add("Por número de Telefonos");
        cbxOrden.getItems().add("Por Inserción");
        
        allContacts = Utilitaria.readFileContacto("Contacto.XML");
        contactsDisplayed = showContacts(allContacts, panelContactos);
        //HACER COPIA
    }
    
    private List<Contacto> showContacts(List<Contacto> contactos, VBox panel) {
        
        panel.getChildren().clear();

        for (Contacto c : contactos) {
            Pane contactoView = setContactView(c);
            panel.getChildren().addAll(contactoView);
        }
        
        return contactos;
        
    }
    
    private Pane setContactView(Contacto contacto) {
        
        HBox hbox = new HBox();
        hbox.setStyle("-fx-background-color: #e1e1e1");
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setSpacing(10);
        hbox.setPadding(new Insets(5,5,5,5));
        
        ImageView perfil = new ImageView(new Image(contacto.getPerfil()));
        perfil.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent t) -> {
                infoContacto(t, contacto);
        });
        perfil.setFitWidth(40); perfil.setFitHeight(40);
        
        Label nombre = new Label(contacto.getNombre());
        nombre.setMaxWidth(165); nombre.setMinWidth(165);
        
        ImageView eliminar = new ImageView(new Image("img/iconoEliminar.png"));
        eliminar.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent t) -> {
                Utilitaria.removeContact(contacto, "Contacto.XML");
                allContacts = Utilitaria.readFileContacto("Contacto.XML");
                showContacts(allContacts, panelContactos);
        });
        eliminar.setFitWidth(20); eliminar.setFitHeight(20);
        hbox.getChildren().addAll(perfil,nombre,eliminar);
        
        return hbox;
        
    }
    
    @FXML
    private void ordenar(ActionEvent event) {
        
        //contactsDisplayed.clear();
        panelContactos.getChildren().clear();
        ComboBox<String> cb = (ComboBox)event.getSource();
        String orden = cb.getValue();
        Queue<Contacto> contactosOrdenados;
        
        if (orden.equals("Alfabeticamente")) {
            Comparator<Contacto> cmp = (Contacto c1, Contacto c2) -> {
                return c1.getNombre().compareTo(c2.getNombre());
            };
            contactosOrdenados = new PriorityQueue<>(cmp);
            
            
        } else if (orden.equals("Por Tipo")) {
            Comparator<Contacto> cmp = (Contacto c1, Contacto c2) -> {
                if (c1.getClass().equals(c2.getClass())) return 0;
                else if (c1.getClass().equals("Persona")) return 1;
                else return -1;
            };
            contactosOrdenados = new PriorityQueue<>(cmp);
            
        } else if (orden.equals("Por número de Telefonos")) {
            Comparator<Contacto> cmp = (Contacto c1, Contacto c2) -> {
                return c1.getTelefonos().size() - c2.getTelefonos().size();
            };
            contactosOrdenados = new PriorityQueue<>(cmp);

        } else {
            contactsDisplayed = allContacts;
            showContacts(allContacts, panelContactos);
            return;
        }
        
        for(Contacto c : allContacts){
            contactosOrdenados.add(c);
        }
        
        while (!contactosOrdenados.isEmpty()) {
            //contactsDisplayed.add(contactosOrdenados.poll());
        }

        showContacts(contactsDisplayed, panelContactos);
        
    }
    
    @FXML
    private void nuevoContacto (MouseEvent event) {
        
        try {
            FXMLLoader loader;
            loader = App.loadFXML("nuevoContacto");
            Scene sc = new Scene(loader.load());
            NuevoContactoController ncc = loader.getController();
            ncc.setContacts(allContacts);
            App.setScene(sc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
    
    private void infoContacto(MouseEvent event, Contacto contacto) {
        
        try {
            FXMLLoader loader;
            loader = App.loadFXML("infoContacto");
            Scene sc = new Scene(loader.load());
            InfoContactoController icc = loader.getController();
            icc.setContacto(contacto);
            App.setScene(sc);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    @FXML
    private void filtrar(MouseEvent event) {
    }
    
}
