/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controllers;

/**
 *
 * @author Usuario
 */
import ec.edu.espol.gestorcontactos.App;
import ec.edu.espol.model.AtributoComplejo;
import ec.edu.espol.model.Contacto;
import ec.edu.espol.model.Empresa;
import ec.edu.espol.model.Persona;
import ec.edu.espol.model.Utilitaria;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import util.ArrayList;
import java.util.Comparator;
import util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EditarContactoController implements Initializable {
    @FXML
    private Label tipoContacto;
    @FXML
    private ImageView imageView;
    @FXML
    private TextField nombre;
    @FXML
    private VBox contactos;
    @FXML
    private VBox telefonos;
    @FXML
    private VBox correos;
    @FXML
    private VBox fotos;
    @FXML
    private Button antFoto;
    @FXML
    private ImageView imvFotos;
    @FXML
    private Button sgteFoto;
    @FXML
    private Button x;
    @FXML
    private VBox atributos;
    @FXML
    private VBox cont1;
    @FXML
    private VBox cont2;
    @FXML
    private Label lbl1;    
    @FXML
    private Label lbl2;    
    @FXML
    private VBox ubicacion;    
    
    private ArrayList<Contacto> contactosList;
    private Contacto contacto;
    private int currentPos;
    private int cont=0;
    
    private List<HBox> listaTelefonos = new ArrayList();
    private List<HBox> listaCorreos = new ArrayList();
    private List<HBox> listaUbicacion = new ArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contactosList = Utilitaria.readFileContacto("Contacto.XML");
    }
    
    @FXML
    private void anteriorFoto(MouseEvent event) {
        if(cont == 0)
            cont = contacto.getFotos().size();
        cont--;
        imvFotos.setImage(new Image(contacto.getFotos().get(cont)));
    }
    
    @FXML
    private void siguienteFoto(MouseEvent event) {
        if(cont == contacto.getFotos().size() - 1)
            cont = -1;
        cont++;
        imvFotos.setImage(new Image(contacto.getFotos().get(cont)));
    }
    
    public void setContacto(Contacto c){
        contacto = c;
        if (contacto.getFotos().size() > 1){
        antFoto.setVisible(true);
            sgteFoto.setVisible(true);
        }

        // Implementar un método para hacer esta búsqueda
        for (int i = 0; i < contactosList.size(); i++){
            if (contactosList.get(i).getNombre().equals(contacto.getNombre()))
                currentPos = i;
        }

        // Mover a otro método showData
        System.out.println(""+currentPos);
        nombre.setText(contacto.getNombre());
        //imageView.setImage(new Image(contacto.getPerfil()));
        //imvFotos.setImage(new Image(contacto.getFotos().get(cont)));

        // Agregar nuevos elementos
        if (contacto instanceof Persona){
            Persona ctn = (Persona) contacto;
            lbl1.setText("Apellido");
            lbl2.setText("Apodo");
            cont2.getChildren().add(new TextField(ctn.getApodo()));
            cont1.getChildren().add(new TextField(ctn.getApellido()));       
        }
        if (contacto instanceof Empresa){
            Empresa ctn = (Empresa) contacto;
            lbl1.setText("Departemaneto");
            lbl2.setText("Sitio Web");
            cont2.getChildren().add(new TextField(ctn.getSitioWeb()));
            cont1.getChildren().add(new TextField(ctn.getDepartamento()));       
        } 
       // agregarElementos(telefonos, contacto.getTelefonos());
        //agregarElementos(correos, contacto.getCorreos());
        //agregarElementosContactos(contactos, contacto.getContactos());
    }

    private void agregarElementos(VBox vbox, List<String> datos) {

        for (int i = datos.size() - 1; i >= 0; i--) {
            String s = datos.get(i);

            HBox h = new HBox();
            Label lb = new Label();
            lb.setText(s);

            ComboBox<String> cbx = new ComboBox();
            cbx.setPromptText("Personal");
            cbx.getItems().addAll("Personal", "Domicilio", "Trabajo", "Otro");
            
            Button bt = new Button("+");
            bt.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent t) -> {
                if(vbox.equals(telefonos)){
                    ComboBox cbx2 = new ComboBox();
                    cbx2.setPromptText("Personal");
                    cbx2.getItems().addAll("Personal", "Domicilio", "Trabajo", "Otro");                    
                    HBox hbox = new HBox();
                    hbox.getChildren().addAll(cbx2, new TextField());
                    telefonos.getChildren().addAll(hbox);
                    listaTelefonos.addLast(hbox);
                }
                if(vbox.equals(correos)){
                    ComboBox cbx2 = new ComboBox();
                    cbx2.setPromptText("Personal");
                    cbx2.getItems().addAll("Personal", "Domicilio", "Trabajo", "Otro");                    
                    HBox hbox = new HBox();
                    hbox.getChildren().addAll(cbx2, new TextField());
                    correos.getChildren().addAll(hbox);
                    listaCorreos.addLast(hbox);
                }
                if(vbox.equals(ubicacion)){
                    ComboBox cbx2 = new ComboBox();
                    cbx2.setPromptText("Personal");
                    cbx2.getItems().addAll("Personal", "Domicilio", "Trabajo", "Otro");                    
                    HBox hbox = new HBox();
                    hbox.getChildren().addAll(cbx2, new TextField());
                    ubicacion.getChildren().addAll(hbox);
                    listaUbicacion.addLast(hbox);
                }                
            });
            // Crear TextField y botón "X"
            TextField textField = new TextField(lb.getText());
            Button x = new Button("X");

            final int index = i; // Capturamos el índice para usarlo en el evento

            // Configurar evento para el botón "X"
            x.setOnAction(event -> {
                // Remover el HBox del VBox
                vbox.getChildren().remove(h);

                // También puedes eliminar el elemento correspondiente de la lista
                if (index >= 0 && index < datos.size()) {
                    datos.removeForIndex(index);
                }
            });

            // Agregar TextField y botón "X" al HBox
            h.getChildren().addAll(cbx,textField, bt, x);

            // Agregar HBox al VBox
            vbox.getChildren().add(h);
            if(vbox.equals(telefonos))
                listaTelefonos.addLast(h);
            if(vbox.equals(correos));
                listaCorreos.addLast(h);
            if(vbox.equals(listaUbicacion))
                listaUbicacion.addLast(h);
            
        }
    }

    private void agregarElementosContactos(VBox vbox, List<Contacto> contactos) {   
        for (Contacto ct : contactos) {
            Label lb = new Label();
            lb.setText(ct.getNombre());
            vbox.getChildren().add(lb);
        }
    }
    
    @FXML
    private void otroTelf(MouseEvent event) {
        TextField t = new TextField();
        int num = telefonos.getChildren().size();
        t.setPromptText("Telefono " + (num+1));
        telefonos.getChildren().add(t);
    }

    @FXML
    private void otroCorr(MouseEvent event) {
        TextField c = new TextField();
        int num = correos.getChildren().size();
        c.setPromptText("Correo " + (num+1));
        correos.getChildren().add(c);
    } 

    @FXML
    private void volver(MouseEvent event) {
        try {
            FXMLLoader loader;
            loader = App.loadFXML("infoContacto");
            Scene sc = new Scene(loader.load());
            App.setScene(sc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void guardar(MouseEvent event) {
        File archivoContactos = new File("Contacto.XML");
        if (archivoContactos.exists()) {
            // Intentar borrar el archivo
            if (archivoContactos.delete()) {
                System.out.println("El archivo ha sido borrado exitosamente.");
            } else {
                System.out.println("No se pudo borrar el archivo.");
            }
        } else {
            System.out.println("El archivo no existe.");
        }
        Comparator<Contacto> cmp = new Comparator<>(){
            @Override
            public int compare(Contacto o1, Contacto o2) {
               return o1.getNombre().compareTo(o2.getNombre());
            }
            
        };
        contactosList.removeElement(cmp, contacto);
        List<AtributoComplejo> tlfs = getComplexAttributes(listaTelefonos, "telefonos");
        List<AtributoComplejo> crrs = getComplexAttributes(listaCorreos, "correos");
        List<AtributoComplejo> ubcs = getComplexAttributes(listaUbicacion, "correos");
        List<String> fts = contacto.getFotos();
        if (contacto instanceof Persona){
            TextField apell =  (TextField) cont1.getChildren().get(1);
            TextField apod =  (TextField) cont2.getChildren().get(1);
            Contacto cnt = new Persona(apod.getText(), apell.getText(), nombre.getText(), contacto.getPerfil(), fts, ubcs, crrs, tlfs, contacto.getContactos());
            contactosList.addFirst(cnt);
        }
        if (contacto instanceof Empresa){
            TextField dept =  (TextField) cont1.getChildren().get(1);
            TextField sitioW =  (TextField) cont2.getChildren().get(1);
            Contacto cnt = new Empresa(dept.getText(), sitioW.getText(), nombre.getText(), contacto.getPerfil(), fts, ubcs, crrs, tlfs, contacto.getContactos());
            contactosList.addFirst(cnt);
        }
        for (Contacto c: contactosList){
            Utilitaria.saveFile(c, "Contacto.XML", true); 
        }
        
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
    
    private util.List<AtributoComplejo> getComplexAttributes(List<HBox> atributos, String nombre){
        
        //Este metodo obtiene una lista de Atributos Complejos a partir de una lista de hbox creados en setComplexAttribute
        
        util.List<AtributoComplejo> retorno = new ArrayList<>();
        for (HBox hbox : atributos){
            AtributoComplejo ac = getComplexAttribute(hbox, nombre);
            if(ac != null){
                retorno.addLast(ac);
            }
        }
        return retorno;
        
    }    
    
}
