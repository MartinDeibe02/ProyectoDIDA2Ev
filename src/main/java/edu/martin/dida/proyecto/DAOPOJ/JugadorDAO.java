package edu.martin.dida.proyecto.DAOPOJ;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author marti
 */
public class JugadorDAO {

    public static final String URL_CONEXION = "jdbc:h2:./jugadoresDB";
    public static final String USUARIO = "root";
    public static final String PASSWORD = "";

    public JugadorDAO(){
    crearTabla();
}

    //creamos tabla
    private void crearTabla() {
        try (Connection conexion = DriverManager.getConnection(URL_CONEXION, USUARIO, PASSWORD)){
            Statement statement = conexion.createStatement();
             String sql = "CREATE TABLE IF NOT EXISTS jugador" 
                    + "(id INTEGER auto_increment, " 
                    + "nombre VARCHAR(255), "   
                    + "altura INTEGER(50), "
                    + "pais VARCHAR(50),"
                    + "fecha Varchar(50), "
                    + "dorsal VARCHAR(255),"
                    + "equipo VARCHAR(50));";
             statement.executeUpdate(sql);
    }   catch (SQLException ex) {
            ex.printStackTrace();
        }     
}
    
    public Map<String, Integer> contarJugadoresEquipos(){
        List<Jugadores> jugadores = buscar();
        Map<String, Integer> jugadoresEquipo = new HashMap<>();
        
        try(Connection conexion = DriverManager.getConnection(URL_CONEXION, USUARIO, PASSWORD)){
            Statement stmt = conexion.createStatement();
            String sql = "SELECT COUNT(*) as cant FROM jugador GROUP BY equipo";
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                int cantidadJug = rs.getInt("cant");
                
                for(Jugadores jugador : jugadores){
                        jugadoresEquipo.put(jugador.getEquipo(), cantidadJug);
                    
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return jugadoresEquipo;
    }

//comprobamos con el id si es guardar o editar
    public void guardarEditar(Jugadores jugador){
        if(jugador.getId()==0){
            insertar(jugador);
        }else{
            editar(jugador);
        }
    }

    //metodo insertar
    private void insertar(Jugadores jugador) {
        try (Connection conexion = DriverManager.getConnection(URL_CONEXION, USUARIO, PASSWORD)){
            Statement statement = conexion.createStatement();
            String sql = "INSERT INTO jugador (nombre, altura, pais,equipo, fecha, dorsal)" 
                    +"VALUES ('"+ jugador.getNombre() 
                     +"','"+ jugador.getAltura()
                     +"','"+ jugador.getPais()
                     +"','"+ jugador.getEquipo()
                     +"','"+ jugador.getFecha()
                     +"','"+ jugador.getDorsal()+"')";
            statement.executeUpdate(sql);

    }   catch (Exception e) {
           throw new RuntimeException("Error al guardar informacion: " + e.getMessage());
        }
    }


  //metodo para buscar los usuarios de la base de datos para despues mostrarlos por pantalla
    public List<Jugadores> buscar(){
        List<Jugadores> jugadores = new ArrayList<>();
           try(Connection conexion = DriverManager.getConnection(URL_CONEXION, USUARIO, PASSWORD)){
            Statement statement = conexion.createStatement();
            String sql = "SELECT * FROM jugador";
            ResultSet resultSet =  statement.executeQuery(sql);
             while(resultSet.next()){
                 Jugadores jug = new Jugadores();
                 jug.setId(resultSet.getInt("id"));
                 jug.setNombre(resultSet.getString("nombre"));
                 jug.setAltura(resultSet.getInt("altura"));
                 jug.setPais(resultSet.getString("pais"));
                 jug.setEquipo(resultSet.getString("equipo"));
                 jug.setFecha(resultSet.getString("fecha"));
                 jug.setDorsal(resultSet.getString("dorsal"));
                 jugadores.add(jug);
}
    }   catch (SQLException e) {
        e.printStackTrace();
    }
        return jugadores;
    }

    //metodo editar
    private void editar(Jugadores jugador) {
        try(Connection conexion = DriverManager.getConnection(URL_CONEXION, USUARIO, PASSWORD)){
            Statement statement = conexion.createStatement();
            String sql = "UPDATE jugador SET nombre = '" + jugador.getNombre()
                    + "',altura = '" + jugador.getAltura()
                    + "',pais = '" + jugador.getPais()
                    + "',equipo = '" + jugador.getEquipo()
                    + "',fecha = '" + jugador.getFecha()
                    + "',dorsal ='" + jugador.getDorsal()
                    + "' WHERE id= " + jugador.getId();
            statement.executeUpdate(sql);
        }catch(SQLException e){
            e.printStackTrace();
                    
        }
    }
    
    public void eliminar(Jugadores jugador){
         try(Connection conexion = DriverManager.getConnection(URL_CONEXION, USUARIO, PASSWORD)){
            Statement statement = conexion.createStatement();
            String sql = "DELETE FROM jugador WHERE id=" + jugador.getId();
            statement.executeUpdate(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}
