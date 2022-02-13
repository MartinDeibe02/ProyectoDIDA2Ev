/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.martin.dida.proyecto;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author marti
 */
public class LayoutPane extends BorderPane{
    private Map<String, Node> pantallasDeLaApp;
    
    public LayoutPane(){
        this.pantallasDeLaApp =new HashMap<>();
    }
    
    public void cargarPantallas(String nombreDeLaPantalla, URL urlArchivoFxml) throws IOException{
        FXMLLoader cargadorPantallas = new FXMLLoader(urlArchivoFxml);
        Parent pantalla = cargadorPantallas.load();
        
        ControladorConNavegabilidad controladorConNavegabilidad = cargadorPantallas.getController();
        controladorConNavegabilidad.setlayout(this);
        
        
        pantallasDeLaApp.put(nombreDeLaPantalla, pantalla);
        
    }
    
    
    public void mostrarPantallaActual(String nombreDeLaPantalla){
        this.setCenter(pantallasDeLaApp.get(nombreDeLaPantalla));
    }


    
}
