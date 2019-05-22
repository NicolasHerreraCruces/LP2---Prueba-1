/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Marcelo Esperguel
 */
public class Usuario {
    
    private int id_usuario;
    private String nombre;

    public Usuario(int id_usuario, String nombre) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
    }

    public Usuario(String nombre) {
        this.nombre = nombre;
    }

    public Usuario() {
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
