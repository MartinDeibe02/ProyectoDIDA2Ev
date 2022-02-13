/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.martin.dida.proyecto;

/**
 *
 * @author marti
 */
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
public class ControladorPantallaA extends ControladorConNavegabilidad{
    
    @FXML
    TextField txtuser;
    
    @FXML
    PasswordField txtpass;
    
    @FXML 
    Button btnacceder;
    
    @FXML
    Button btnsalir;
    
    public void login(){
        if(txtuser.getText().equals("admin") && txtpass.getText().equals("123")){
            this.layout.mostrarPantallaActual("b");

            
        }else if(txtuser.getText().equals("") || txtpass.getText().equals("")){
            JOptionPane.showMessageDialog(new JFrame(), "Ningun campo puede estar vacio", "Error",
        JOptionPane.ERROR_MESSAGE);
    
    }else{
            JOptionPane.showMessageDialog(new JFrame(), "Introduce bien los datos", "Error",
        JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void salir(){
        int jo = JOptionPane.showConfirmDialog(new JFrame(), "Quieres salir?", "Salir",
        JOptionPane.OK_CANCEL_OPTION);
        
        if(jo == JOptionPane.YES_OPTION){
            System.exit(0);
        }else if (jo == JOptionPane.NO_OPTION){
            return;
        }
    }
    

}
