/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;

/**
 *
 * @author Nicolás Herrera
 */
public class UsuarioDAO {
    
    Conexion con;
    
    public UsuarioDAO() {
        
        con = new Conexion();
        
    }
    
    // Método que retorna un ArrayList con todos los objetos de la tabla usuario de la BD.
    
    public ArrayList<Usuario> getUsuarios() {

        ArrayList<Usuario> usuarios = new ArrayList<>();
        Connection accesoBD = con.getConexion();
        
         try{

            String sql="SELECT * FROM usuario";
            Statement st = accesoBD.createStatement();
            ResultSet resultados = st.executeQuery(sql);
              
            while (resultados.next()) {
                
                int id_usuario = resultados.getInt("id_venta");
                String nombre = resultados.getString("nombre");
                
                usuarios.add(new Usuario(id_usuario, nombre));
                
            }
                accesoBD.close();
                
                return usuarios;

        }catch (Exception e) {

            System.out.println();
            System.out.println("Error al obtener la lista de usuarios.");
            e.printStackTrace();
            return null;
            
        }
    }

    // Método que permite añadir objetos a la tabla usuario de la BD.
    
    public boolean addUsuario(Usuario u) {
        Connection accesoBD = this.con.getConexion();

        String sql = "INSERT INTO usuario (nombre) VALUES (?)";

        try {
            
            PreparedStatement rs = accesoBD.prepareStatement(sql);

            rs.setString(1, u.getNombre());

            rs.executeUpdate();
            System.out.println("Nueva usuario registrado.");
            accesoBD.close();
            return true;

        }catch (Exception e) {
            
            System.out.println(e);
            return false;
            
        }

    }
       
}
