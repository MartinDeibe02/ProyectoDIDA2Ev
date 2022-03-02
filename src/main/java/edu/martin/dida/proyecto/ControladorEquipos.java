/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.martin.dida.proyecto;

import edu.martin.dida.proyecto.DAOPOJ.EquipoDAO;
import edu.martin.dida.proyecto.DAOPOJ.Equipos;
import edu.martin.dida.proyecto.DAOPOJ.JugadorDAO;
import static edu.martin.dida.proyecto.DAOPOJ.JugadorDAO.PASSWORD;
import static edu.martin.dida.proyecto.DAOPOJ.JugadorDAO.URL_CONEXION;
import static edu.martin.dida.proyecto.DAOPOJ.JugadorDAO.USUARIO;
import edu.martin.dida.proyecto.DAOPOJ.Jugadores;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author marti
 */
public class ControladorEquipos extends ControladorConNavegabilidad implements Initializable{
    @FXML
    TextField txtEquipo;
    
    @FXML
    TextField txtPresu;
    
    @FXML
    TextField txtPet;
    
    @FXML
    CheckBox checkPet;
    
    @FXML
    TextField txtCity;
    
    @FXML
    TextField txtOwn;
    
    @FXML
    TableView tablaEq;
    
    @FXML
    TextField txtFiltrar;
    
    private int id;
    
    EquipoDAO equiposDao;
    
    ControladorPantallaB cont = new ControladorPantallaB();
    ObservableList<String> items = FXCollections.observableArrayList();
    ObservableList<Equipos> equipos;

    ObservableList<Equipos> equipoFiltro = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        equiposDao = new EquipoDAO();
        cargar();
        txtPet.setDisable(true);
        
        cargarFiltro();
        
        
        
        
        
        checkPet.selectedProperty().addListener(new ChangeListener<Boolean>() {
    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        if(checkPet.isSelected()){
            txtPet.setDisable(false);
            txtPet.setText("");
        }else{
            txtPet.setDisable(true);
        }
    }
    });
    }
    
        public void volver(){
            this.layout.mostrarPantallaActual("b");
            ControladorPantallaB cont = new ControladorPantallaB();

        }
    
        public void guardar(){
        Equipos equipo = new Equipos();
        
        if(txtEquipo.getText().equals("") || txtPresu.equals("") || txtOwn.getText().equals("") || txtCity.getText().equals("")){
            JOptionPane.showMessageDialog(new JFrame(), "Todos los campos deben estar cubiertos", "Error",
        JOptionPane.ERROR_MESSAGE);
        }else{
            
            equipo.setId(id);
            equipo.setNombre(txtEquipo.getText());
            equipo.setPresupuesto(txtPresu.getText());
            
            
            NumberFormat format = NumberFormat.getCurrencyInstance(Locale.CANADA);
            String moneda = format.format(new BigDecimal(Double.parseDouble(txtPresu.getText())));
            equipo.setPresupuesto(moneda);
            
            if(txtPet.getText().equals("")){
                equipo.setMascota("Sin mascota");
            }else{
                equipo.setMascota(txtPet.getText());
            }
             
            equipo.setPropietario(txtOwn.getText());
            equipo.setCiudad(txtCity.getText());
            
            equiposDao.guardarEditar(equipo);
            
            id=0;
            
            cargar();
            cargarFiltro();
            
            cont.cargarEquipos();
            
            txtEquipo.clear();
            txtPresu.clear();
            txtPet.clear();
            checkPet.setSelected(false);
            txtOwn.clear();
            txtCity.clear();
        }
        
        
    } 
        public void editar() throws ParseException{
            Equipos equipo = (Equipos) tablaEq.getSelectionModel().getSelectedItem();
            
            if(equipo==null){
                JOptionPane.showMessageDialog(new JFrame(), "Tienes que seleccionar un jugador", "Error",
        JOptionPane.ERROR_MESSAGE);
            }else{
            //seteo los nombres del registro clicado en los campos de texto
            txtEquipo.setText(equipo.getNombre());
            
            
            NumberFormat format = NumberFormat.getCurrencyInstance(Locale.CANADA);
            Number eur = format.parse(equipo.getPresupuesto());
            txtPresu.setText(eur.toString());
            
            
            
            if(equipo.getMascota().equals("Sin mascota")){
                txtPet.setText("");
            }else{
                txtPet.setText(equipo.getMascota());
            }
            
            
            txtOwn.setText(equipo.getPropietario());
            txtCity.setText(equipo.getCiudad());

            
            id= equipo.getId();
            
            }
            
        }
        
           public void eliminar(){
            Equipos equipo = (Equipos) tablaEq.getSelectionModel().getSelectedItem();
            if(equipo==null){
                JOptionPane.showMessageDialog(new JFrame(), "Tienes que seleccionar un jugador", "Error",
        JOptionPane.ERROR_MESSAGE);
            }else{
            equiposDao.eliminar(equipo);
            cargar();
            cont.cargarEquipos();
            cargarFiltro();
    }
           }

    private void cargar() {
        equipos = FXCollections.observableArrayList();
        List<Equipos> equiposE = equiposDao.buscar();
        equipos.addAll(equiposE);
        tablaEq.setItems(equipos);

    }

    private void cargarFiltro() {
        FilteredList<Equipos> filtrarEq = new FilteredList<>(equipos, e -> true);
        txtFiltrar.setOnKeyReleased(e ->{
            txtFiltrar.textProperty().addListener((observableValue, oldValue, newValue)->{
                filtrarEq.setPredicate((Predicate<? super Equipos>) equipo->{
                    if(newValue == null || newValue.isEmpty()){
                        return true;
                    }else if(equipo.getNombre().contains(newValue)){
                        return  true;
                    }
                    return false;
                });
        });
            SortedList<Equipos> sortedList = new SortedList<>(filtrarEq);
            sortedList.comparatorProperty().bind(tablaEq.comparatorProperty());
            tablaEq.setItems(sortedList);
    });
    }


    
    
    
    
    
    
}
