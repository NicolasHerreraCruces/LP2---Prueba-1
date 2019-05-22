/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import modelo.Mensaje;
import modelo.MensajeDAO;
import vista.Bandeja;

/**
 *
 * @author Nicolás Herrera
 */
public class BandejaController implements ActionListener {
    
    private Bandeja b;

    public BandejaController(Bandeja b) {
        this.b = b;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        String comando = e.getActionCommand();
        
        if (comando == "nuevoMensaje") {
            
            String nombreEmisor = "";
            String contenido = "";
            
            MensajeDAO m = new MensajeDAO();
            ArrayList<Mensaje> mensajes = m.getMensajes();
            
            for (int i = 0; i < mensajes.size(); i++) {
                if (mensajes.get(i).getReceptor().getId_usuario() == b.getIdUsuarioLogueado()) {
                    nombreEmisor = mensajes.get(i).getEmisor().getNombre();
                    contenido = mensajes.get(i).getContenido();
                    
                    String [] newMensaje = {nombreEmisor, contenido};
                    
                    DefaultTableModel d = (DefaultTableModel) b.getjTable1().getModel();
                    
                    d.addRow(newMensaje);
                }
            }
  
        }
        
    }
    
}
