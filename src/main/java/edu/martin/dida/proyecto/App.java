package edu.martin.dida.proyecto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.stage.Window;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
        
    @Override
    public void start(Stage primaryStage) throws IOException {
        LayoutPane layoutPane = new LayoutPane();
        layoutPane.cargarPantallas("a", ControladorPantallaA.class.getResource("pantallaA.fxml"));
        layoutPane.cargarPantallas("b", ControladorPantallaB.class.getResource("pantallaB.fxml"));
        

        layoutPane.mostrarPantallaActual("a");
        Scene escena = new Scene(layoutPane);
        
        primaryStage.setScene(escena);
        
        primaryStage.getScene().getWindow().sizeToScene();
        primaryStage.show();
        
        
        
    }



    public static void main(String[] args) {
        launch();
    }
    


}