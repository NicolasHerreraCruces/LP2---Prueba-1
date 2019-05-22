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
public class MensajeDAO {
    
    Conexion con;
    
    public MensajeDAO() {
        
        con = new Conexion();
        
    }
    
    // Método que retorna un ArrayList con todos los objetos de la tabla de la BD.
    
    public ArrayList<Mensaje> getMensajes() {

        ArrayList<Mensaje> mensajes = new ArrayList<>();
        Connection accesoBD = con.getConexion();
        
         try{

            String sql="SELECT * FROM mensaje";
            Statement st = accesoBD.createStatement();
            ResultSet resultados = st.executeQuery(sql);
              
            while (resultados.next()) {
                
                int id_mensaje = resultados.getInt("id_mensaje");
                String contenido = resultados.getString("contenido");
                int id_usr_emisor = resultados.getInt("id_usr_emisor");
                int id_usr_receptor = resultados.getInt("id_usr_receptor");
                
                //Obtengo el usuario emisor de cada venta
                String sqlUsuarioE = "SELECT * FROM usuario WHERE id_usuario = "+id_usr_emisor;
               
                try{
                    Statement stUsuarioE = accesoBD.createStatement();
                    ResultSet resUsuarioE = stUsuarioE.executeQuery(sqlUsuarioE);
                    
                    while(resUsuarioE.next()){
                        int idUsuarioBDE = Integer.parseInt(resUsuarioE.getString("id_usr_emisor"));
                        String nombreE = resUsuarioE.getString("nombre");
                        
                        Usuario uEmisor = new Usuario(idUsuarioBDE, nombreE);
                        
                        //Obtengo el usuario receptor de cada venta
                        String sqlUsuarioR = "SELECT * FROM usuario WHERE id_usuario = "+id_usr_receptor;
               
                        try{
                            Statement stUsuarioR = accesoBD.createStatement();
                            ResultSet resUsuarioR = stUsuarioR.executeQuery(sqlUsuarioR);
                    
                            while(resUsuarioR.next()){
                                int idUsuarioBDR= Integer.parseInt(resUsuarioR.getString("id_usr_receptor"));
                                String nombreR = resUsuarioR.getString("nombre");
                        
                                Usuario uReceptor = new Usuario(idUsuarioBDR, nombreR);
                        
                                //Ya teniendo ambos usuarios creo una venta y la añado a la lista
                                mensajes.add(new Mensaje(id_mensaje, uEmisor, uReceptor, contenido));
                            }
          
                        }catch(Exception ex3){
                            System.out.println("Error al obtener al usuario receptor.");
                            ex3.printStackTrace();
                        }
                    }
          
                }catch(Exception ex2){
                    System.out.println("Error al obtener al usuario emisor.");
                    ex2.printStackTrace();
                }
 
            }
                accesoBD.close();
                
                return mensajes;

        }catch (Exception e) {

            System.out.println();
            System.out.println("Error al obtener la lista de mensajes.");
            e.printStackTrace();
            return null;
            
        }
    } 
    
    // Método que permite añadir objetos a la tabla de la BD.
    
    public boolean addMensaje(Mensaje v) {
        Connection accesoBD = this.con.getConexion();

        String sql = "INSERT INTO mensaje (contenido,id_usr_emisor,id_usr_receptor) VALUES (?,?,?)";

        try {
            
            PreparedStatement rs = accesoBD.prepareStatement(sql);

            rs.setString(1, v.getContenido());
            rs.setInt(2, v.getEmisor().getId_usuario());
            rs.setInt(3, v.getReceptor().getId_usuario());
            
            rs.executeUpdate();
            System.out.println("Nuevo mensaje registrado");
            accesoBD.close();
            return true;

        }catch (Exception e) {
            
            System.out.println(e);
            return false;
            
        }

    }
    
    // Método que retorna un ArrayList con todos los objetos de la tabla de la BD según el valor de un campo en específico.
    
    /*
    public ArrayList<Venta> getVentasMes(int mes) {

        ArrayList<Venta> ventas = new ArrayList<>();
        Connection accesoBD = con.getConexion();
        
         try{

            String sql="SELECT * FROM venta WHERE MONTH(fecha) = " + mes;
            Statement st = accesoBD.createStatement();
            ResultSet resultados = st.executeQuery(sql);
              
            while (resultados.next()) {
                
                int id_venta = resultados.getInt("id_venta");
                String sucursal = resultados.getString("sucursal");
                int monto = resultados.getInt("monto");
                String fecha = resultados.getString("fecha");
                int id_vendedor = resultados.getInt("id_vendedor");
                
                ventas.add(new Venta(id_venta, sucursal, monto, fecha, id_vendedor));
                
            }
                accesoBD.close();
                
                return ventas;

        }catch (Exception e) {

            System.out.println();
            System.out.println("Error al obtener");
            e.printStackTrace();
            return null;
            
        }
    }
     */  
}
