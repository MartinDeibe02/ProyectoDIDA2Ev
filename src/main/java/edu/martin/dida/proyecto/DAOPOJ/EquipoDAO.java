/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.martin.dida.proyecto.DAOPOJ;

import static edu.martin.dida.proyecto.DAOPOJ.JugadorDAO.PASSWORD;
import static edu.martin.dida.proyecto.DAOPOJ.JugadorDAO.URL_CONEXION;
import static edu.martin.dida.proyecto.DAOPOJ.JugadorDAO.USUARIO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Markyuu
 */
public class EquipoDAO {
    
    public static final String URL_CONEXION = "jdbc:h2:./jugadoresDB";
    public static final String USUARIO = "root";
    public static final String PASSWORD = "";
    
    public EquipoDAO(){
        crearTabla();
    }

    private void crearTabla(){
        try (Connection conexion = DriverManager.getConnection(URL_CONEXION, USUARIO, PASSWORD)){
            Statement statement = conexion.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS equipos"
                    + "(id INTEGER auto_increment,"
                    + "nombre VARCHAR(255),"
                    + "presupuesto VARCHAR(255),"
                    + "mascota VARCHAR(255),"
                    + "propietario VARCHAR(255),"
                    + "ciudad VARCHAR(255));";
            statement.executeUpdate(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void guardarEditar(Equipos equipo){
        if(equipo.getId()==0){
            insertar(equipo);
        }else{
            editar(equipo);
        }
    }

    private void insertar(Equipos equipo) {
        try (Connection conexion = DriverManager.getConnection(URL_CONEXION, USUARIO, PASSWORD)){
            Statement statement = conexion.createStatement();
            String sql = "INSERT INTO equipos(nombre, presupuesto, mascota, propietario, ciudad)"
                    + "VALUES ('"+ equipo.getNombre()
                    +"','"+ equipo.getPresupuesto()
                    +"','"+ equipo.getMascota()
                    +"','"+ equipo.getPropietario()
                    +"','"+ equipo.getCiudad()+"')";
            
                    statement.executeUpdate(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private void editar(Equipos equipo) {
         try (Connection conexion = DriverManager.getConnection(URL_CONEXION, USUARIO, PASSWORD)){
            Statement statement = conexion.createStatement();
            String sql = "UPDATE equipos SET nombre = '" + equipo.getNombre()
                    + "',presupuesto = '" + equipo.getPresupuesto()
                    + "',mascota = '" + equipo.getMascota()
                    + "',propietario = '" + equipo.getPropietario()
                    + "',ciudad = '" + equipo.getCiudad()
                    + "' WHERE id= " + equipo.getId();
            statement.executeUpdate(sql);
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    
    public void eliminar(Equipos equipo){
        try (Connection conexion = DriverManager.getConnection(URL_CONEXION, USUARIO, PASSWORD)){
            Statement statement = conexion.createStatement();
            String sql = "DELETE FROM equipos WHERE id=" + equipo.getId();
            String sql2 = "UPDATE jugadores SET equipo = Agente Libre WHERE equipo=" + equipo.getNombre();
            statement.executeUpdate(sql);
            statement.executeUpdate(sql2);
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    
        public List<Equipos> buscar(){
        List<Equipos> equipos = new ArrayList<>();
           try(Connection conexion = DriverManager.getConnection(URL_CONEXION, USUARIO, PASSWORD)){
            Statement statement = conexion.createStatement();
            String sql = "SELECT * FROM equipos";
            ResultSet resultSet =  statement.executeQuery(sql);
             while(resultSet.next()){
                 Equipos eq = new Equipos();
                 eq.setId(resultSet.getInt("id"));
                 eq.setNombre(resultSet.getString("nombre"));
                 eq.setPresupuesto(resultSet.getString("presupuesto"));
                 eq.setMascota(resultSet.getString("mascota"));
                 eq.setPropietario(resultSet.getString("propietario"));
                 eq.setCiudad(resultSet.getString("ciudad"));
                 equipos.add(eq);
}
    }   catch (SQLException e) {
        e.printStackTrace();
    }
        return equipos;
    }

}

