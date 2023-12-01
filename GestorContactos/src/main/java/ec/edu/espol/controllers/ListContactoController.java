package ec.edu.espol.controllers;

import ec.edu.espol.gestorcontactos.App;
import ec.edu.espol.model.Contacto;
import ec.edu.espol.model.Empresa;
import ec.edu.espol.model.Persona;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import util.LinkedList;
import util.List;

public class ListContactoController implements Initializable {

    @FXML
    private VBox panelContactos;
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
    
    List<Contacto> allContacts  = Utilitaria.readFileContacto("Contacto.XML");
    List<Contacto> contactsDisplayed = Utilitaria.clone(allContacts);
    
    //Opciones de filtro
    TextField inicial = new TextField();;
    ComboBox<String> tipo;
    TextField telefonos = new TextField();;
    

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
        //contactsDisplayed = Utilitaria.clone(allContacts);
        //allContacts = Utilitaria.readFileContacto("Contacto.XML");
        showContacts(allContacts, panelContactos);
        
    }
    
    private void showContacts(List<Contacto> contactos, VBox panel) {
        
        panel.getChildren().clear();

        for (Contacto c : contactos) {
            Pane contactoView = setContactView(c);
            panel.getChildren().addAll(contactoView);
        }
        
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
        
        //Ordena según el criterio elegido, y por defecto ordena alfabeticamente
        
        contactsDisplayed = new LinkedList<>();
        panelContactos.getChildren().clear();
        ComboBox<String> cb = (ComboBox)event.getSource();
        String orden = cb.getValue();
        Queue<Contacto> contactosOrdenados;
        
        if (orden.equals("Alfabeticamente")) {
            Comparator<Contacto> cmp = (Contacto c1, Contacto c2) -> {
                return c1.getNombre().toLowerCase().compareTo(c2.getNombre().toLowerCase());
            };
            contactosOrdenados = new PriorityQueue<>(cmp);
            
        } else if (orden.equals("Por Tipo")) {
            Comparator<Contacto> cmp = (Contacto c1, Contacto c2) -> {
                if (c1.getClass().equals(c2.getClass()))
                    return c1.getNombre().toLowerCase().compareTo(c2.getNombre().toLowerCase());
                else if (c1.getClass().equals("Persona")) return 1;
                else return -1;
            };
            contactosOrdenados = new PriorityQueue<>(cmp);
            
        } else if (orden.equals("Por número de Telefonos")) {
            Comparator<Contacto> cmp = (Contacto c1, Contacto c2) -> {
                if (c1.getTelefonos().size() - c2.getTelefonos().size() == 0)
                    return c1.getNombre().toLowerCase().compareTo(c2.getNombre().toLowerCase());
                else
                    return c1.getTelefonos().size() - c2.getTelefonos().size();
            };
            contactosOrdenados = new PriorityQueue<>(cmp);

        } else {
            contactsDisplayed = Utilitaria.clone(allContacts);
            showContacts(allContacts, panelContactos);
            return;
        }
        
        for(Contacto c : allContacts){
            contactosOrdenados.add(c);
        }
        while (!contactosOrdenados.isEmpty()) {
            contactsDisplayed.addLast(contactosOrdenados.poll());
        }

        showContacts(contactsDisplayed, panelContactos);
        
    }
    
    @FXML
    private void mostrarFiltrar(MouseEvent event) {
        
        panelContactos.getChildren().clear();
        
        VBox vbx = new VBox();
        vbx.setSpacing(20);
        vbx.setAlignment(Pos.CENTER);
        
        Label titulo = new Label("Buscar por");
 
        HBox hbxInicial = new HBox();
        hbxInicial.setAlignment(Pos.CENTER);
        Label nomInicial = new Label("Inicial del nombre: ");
        nomInicial.setMinWidth(120); nomInicial.setMaxHeight(15);
        inicial.setMaxWidth(50); inicial.setMaxHeight(15);
        hbxInicial.getChildren().addAll(nomInicial, inicial);
        
        tipo = new ComboBox();
        tipo.getItems().addAll("Persona", "Empresa");
        tipo.setPromptText("Tipo de Contacto");
        
        HBox hbxTelefono = new HBox();
        hbxTelefono.setAlignment(Pos.CENTER);
        Label numeroTelf = new Label("Número de telefonos: ");
        nomInicial.setMinWidth(120); nomInicial.setMaxHeight(15);
        telefonos.setMaxWidth(50); telefonos.setMaxHeight(15);
        hbxTelefono.getChildren().addAll(numeroTelf, telefonos);
        
        HBox botones = new HBox();
        botones.setAlignment(Pos.CENTER);
        botones.setSpacing(30);
        Button reestablecer = new Button("Reestablecer");
        reestablecer.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent t) -> {
            contactsDisplayed = Utilitaria.clone(allContacts);
            panelContactos.getChildren().clear();
            showContacts(allContacts, panelContactos);
        });
        Button filtrar = new Button("Filtrar");
        filtrar.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent t) -> {
            panelContactos.getChildren().clear();
            contactsDisplayed = filtrar();
            if(contactsDisplayed == null){
                    mostrarFiltrar(event);
            } else showContacts(contactsDisplayed, panelContactos);
        });
        botones.getChildren().addAll(reestablecer, filtrar);
        
        vbx.getChildren().addAll(new Label("Buscar por"), tipo, hbxInicial,hbxTelefono, botones);
        panelContactos.getChildren().add(vbx);
        
    }
    
    private List<Contacto> filtrar() {
        
        //Filtra segun los parametros dados, si no se llena ningun parametro devuelve toda la lista
        
        List<Contacto> retorno = new LinkedList<>();
        
        Class tp;
        String tipoSeleccionado = tipo.getSelectionModel().getSelectedItem();
        if(tipoSeleccionado != null && tipoSeleccionado.equals("Persona")) tp = Persona.class;
        else tp = Empresa.class;
        
        Integer numeroTelf = -1;
        try {
            if(!telefonos.getText().equals(""))
                numeroTelf = Integer.parseInt(telefonos.getText());
        } catch (NumberFormatException nfe) {
            Utilitaria.mostrarAlerta("Hay un campo que debe ser llenado con numeros");
            telefonos.setText("");
            return null;
        }
        
        String letraInicial = "";
        if(!inicial.getText().equals(""))
            letraInicial = ""+inicial.getText().toLowerCase().charAt(0);

        for (Contacto c: allContacts){
            Contacto añadir;
            if(c.getNombre().toLowerCase().startsWith(letraInicial)){
                añadir = c;
            if(numeroTelf != -1 && !(c.getTelefonos().size() == numeroTelf))
                añadir = null;
            if(tipoSeleccionado != null && !c.getClass().equals(tp))
                añadir = null;
            if(añadir != null)
                retorno.addLast( añadir);
            }
        }
        
        inicial.setText("");
        tipo = null;
        telefonos.setText("");
        return retorno;
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
            icc.setContactsDisplayed(contactsDisplayed);
            App.setScene(sc);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
}
