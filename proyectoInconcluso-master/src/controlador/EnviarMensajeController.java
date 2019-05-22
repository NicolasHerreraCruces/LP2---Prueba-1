/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import modelo.Mensaje;
import modelo.MensajeDAO;
import modelo.Usuario;
import modelo.UsuarioDAO;
import vista.Bandeja;
import vista.EnviarMensaje;

/**
 *
 * @author Nicolás Herrera
 */
public class EnviarMensajeController implements ActionListener {
    
    private EnviarMensaje nm;
    private Bandeja b;

    public EnviarMensajeController(EnviarMensaje nm, Bandeja b) {
        this.nm = nm;
        this.b = b;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        String comando = e.getActionCommand();
        
        if (comando == "enviar") {
            
            // Obtener emisor de la ventana Bandeja.
            int id_usuarioLogueado = b.getIdUsuarioLogueado();
            Usuario emisor = getUsuarioID(id_usuarioLogueado);
            
            // Obtener receptor según nombre del CB.
            Usuario receptor = getUsuario(nm.getDestinatarioCB().getSelectedItem().toString());
            
            String contenido = nm.getCuerpoMensajeTA().getText();
            
            MensajeDAO m = new MensajeDAO();
            m.addMensaje(new Mensaje(emisor, receptor, contenido));          
            
        }
        else if (comando == "cancelar") {            
            nm.dispose();          
        }
    }
    
    public Usuario getUsuario(String nombre) {
        
        UsuarioDAO u = new UsuarioDAO();
        ArrayList<Usuario> usuarios = u.getUsuarios();
        
        Usuario usuario = new Usuario();
        
        for (int i = 0; i < usuarios.size(); i++) {
            if (nombre.equals(usuarios.get(i).getNombre())) {
                usuario = usuarios.get(i);
            }
        }
        
        return usuario;
    }
    
    public Usuario getUsuarioID(int id) {
        
        UsuarioDAO u = new UsuarioDAO();
        ArrayList<Usuario> usuarios = u.getUsuarios();
        
        Usuario usuario = new Usuario();
        
        for (int i = 0; i < usuarios.size(); i++) {
            if (id == usuarios.get(i).getId_usuario()) {
                usuario = usuarios.get(i);
            }
        }
        
        return usuario;
    }
    
}
