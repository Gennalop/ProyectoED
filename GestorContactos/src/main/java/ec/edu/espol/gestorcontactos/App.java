package ec.edu.espol.gestorcontactos;

import ec.edu.espol.model.Contacto;
import ec.edu.espol.model.Utilitaria;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.LinkedList;

public class App extends Application {

    private static Scene scene;
    private static Stage st;

    @Override
    public void start(Stage stage) throws IOException {
        st = stage;
        scene = new Scene(loadFXML("listContacto").load());
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml).load());
    }
    
    public static void setScene(Scene sc){
        st.setScene(sc);
    }
    
    public static Stage getStage(){
        return st;
    }
    
    public static FXMLLoader loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader;
    }
    
    public static void main(String[] args) {
        /*
        LinkedList<String> contactosLK1 = new LinkedList<>();
        contactosLK1.add("img/iconoMas.png");
        contactosLK1.add("img/iconoOpciones.png");
        Contacto c1 = new Contacto("Pepe", "img/usuarioDefault.png", contactosLK1);
        
        LinkedList<String> contactosLK2 = new LinkedList<>();
        contactosLK2.add("img/iconoFiltrar.png");
        contactosLK2.add("img/iconoFavorito.png");
        contactosLK2.add("img/iconoBuscar.png");
        Contacto c2 = new Contacto("Mar", "img/usuarioDefault.png", contactosLK2);
        
        LinkedList<String> contactosLK3 = new LinkedList<>();
        contactosLK3.add("img/iconoUsuario.png");
        Contacto c3 = new Contacto("AHHHH", "img/usuarioDefault.png", contactosLK3);
        
        Utilitaria.saveFile(c1, "Contacto.XML");
        Utilitaria.saveFile(c2, "Contacto.XML");
        Utilitaria.saveFile(c3, "Contacto.XML");*/
        
        launch();
    }

}