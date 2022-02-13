 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.martin.dida.proyecto.DAOPOJ;



/**
 *
 * @author marti
 */
public class Jugadores {
    private int id;
    private String nombre;
    private int altura;
    private String pais;
    private String dorsal;
    private String fecha;
    private String equipo;
    
    public Jugadores(int id, String nombre,String pais, int altura, String equipo, String dorsal, String fecha) {
        this.id = id;
        this.nombre = nombre;
        this.altura = altura;
        this.pais = pais;
        this.dorsal= dorsal;
        this.fecha=fecha;
        this.equipo=equipo;
    }

    public Jugadores() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAltura() {
        return Math.round(altura);
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }
    
    public String getPais(){
        return pais;
    }
    
    public void setPais(String pais){
        this.pais=pais;
    }

    public String getDorsal() {
        return dorsal;
    }
    
        public String getEquipo(){
        return equipo;
    }
    
    public void setEquipo(String equipo){
        this.equipo=equipo;
    }

    public void setDorsal(String dorsal) {
        this.dorsal = dorsal;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
   
    
}
        

