/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.martin.dida.proyecto.DAOPOJ;

/**
 *
 * @author Markyuu
 */
public class Equipos {
    private int id;
    private String nombre;
    private String presupuesto;
    private String mascota;
    private String propietario;
    private String ciudad;

    public Equipos(int id, String nombre, String presupuesto, String mascota, String propietario, String ciudad) {
        this.id = id;
        this.nombre = nombre;
        this.presupuesto = presupuesto;
        this.mascota = mascota;
        this.propietario = propietario;
        this.ciudad = ciudad;
    }

    public Equipos() {
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

    public String getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(String presupuesto) {
        this.presupuesto = presupuesto;
    }

    public String getMascota() {
        return mascota;
    }

    public void setMascota(String mascota) {
        this.mascota = mascota;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    
    
}
