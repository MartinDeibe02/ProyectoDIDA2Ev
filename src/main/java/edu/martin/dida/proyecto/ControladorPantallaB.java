/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.martin.dida.proyecto;

import edu.martin.dida.proyecto.DAOPOJ.JugadorDAO;
import edu.martin.dida.proyecto.DAOPOJ.Jugadores;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Slider;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author marti
 */
public class ControladorPantallaB extends ControladorConNavegabilidad implements Initializable{
        @FXML
        TextField txtnombre;
        
        @FXML
        TextField cuentaSlider;
        
        @FXML
        Slider altura0;

        @FXML
        ComboBox comboPais;
        
        @FXML
        DatePicker fecha;
        
        @FXML
        TextField txtdorsal;
        
        @FXML
        ComboBox comboEquipo;
        
        @FXML
        TableView<Jugadores> tablabd;
        private int id;
        
        @FXML
        PieChart pie;
        
        JugadorDAO jugadorDao;
        

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        jugadorDao = new JugadorDAO();
        cargar();      
        cargarGrafico();
        
        altura0.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observar, Number numeroAnterior, Number numeroNuevo) {
                cuentaSlider.setText(String.valueOf((int)Math.round((double) numeroNuevo)));
                
            }
        });
        
        cuentaSlider.textProperty().addListener((observable, oldValue, newValue) -> {
            if(cuentaSlider.getText().equals("")){
                altura0.setValue(100);
            }else{
                altura0.setValue(Double.parseDouble(cuentaSlider.getText()));
            }
            
        });    
        
    }
    
    public void volver(){
        this.layout.mostrarPantallaActual("a");
    }
    
    public void cargarGrafico(){
        Map<String, Integer> jugadoresEquipo = jugadorDao.contarJugadoresEquipos();
        
        ObservableList<PieChart.Data> datos = FXCollections.observableArrayList();
        
        jugadoresEquipo.forEach((nombrejugador,cantidad) ->{
            PieChart.Data data = new PieChart.Data(nombrejugador,cantidad);
            datos.add(data);
        });
        pie.setData(datos);
    }
    
       public void guardar(){
      Jugadores jugador=new Jugadores();
      
        if(txtdorsal.getText().equals("") || fecha.getValue().equals("") || txtdorsal.getText().equals("")){
      
          JOptionPane.showMessageDialog(new JFrame(), "Todos los campos deben estar cubiertos", "Error",
        JOptionPane.ERROR_MESSAGE);
      }else{
                
      jugador.setId(id);
      jugador.setNombre(txtnombre.getText());
      jugador.setPais(comboPais.getSelectionModel().getSelectedItem().toString());
      jugador.setEquipo(comboEquipo.getSelectionModel().getSelectedItem().toString());
      jugador.setAltura((int) Math.round(altura0.getValue()));
      
      //Convierto LocalDate en String para cambiar el formato de la fecha
      LocalDate date = fecha.getValue() ;
      String convertirFecha = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
      jugador.setFecha(convertirFecha);
      jugador.setDorsal(txtdorsal.getText());
      
      //inserto en bbdd

      jugadorDao.guardarEditar(jugador);
      
      id = 0;
      
      cargar();
      //limpio valores
      txtnombre.clear();
      txtdorsal.clear();
      fecha.setValue(null);
      altura0.setValue(0);
      comboPais.setValue(null);
      comboEquipo.setValue(null);
    }
       }
       
           public void eliminar(){
            Jugadores jugador = tablabd.getSelectionModel().getSelectedItem();
            if(jugador==null){
                JOptionPane.showMessageDialog(new JFrame(), "Tienes que seleccionar un jugador", "Error",
        JOptionPane.ERROR_MESSAGE);
            }else{
            jugadorDao.eliminar(jugador);
           cargar();
    }
           }
           
    public void editar(){
        //seleccion con click
            Jugadores jugador = tablabd.getSelectionModel().getSelectedItem();
            
            if(jugador==null){
                JOptionPane.showMessageDialog(new JFrame(), "Tienes que seleccionar un jugador", "Error",
        JOptionPane.ERROR_MESSAGE);
            }else{
            //seteo los nombres del registro clicado en los campos de texto
            txtnombre.setText(jugador.getNombre());
            altura0.setValue(jugador.getAltura());
            comboPais.setValue(jugador.getPais());
            comboEquipo.setValue(jugador.getEquipo());
            //convertir string a localDate(DATE)
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String date = jugador.getFecha();
            LocalDate localDate = LocalDate.parse(date, formatter);
            
            fecha.setValue(localDate);
            txtdorsal.setText(jugador.getDorsal());
            
            //recojo id del registro para comprobar en dao si es nuevo registro o o actualizar
            id= jugador.getId(); 
            
            }
            }

    public void cargar(){
        ObservableList<Jugadores> jugadores = FXCollections.observableArrayList();
        List<Jugadores> jugadoresE = jugadorDao.buscar();
        jugadores.addAll(jugadoresE);
        tablabd.setItems(jugadores);        
    }
}
