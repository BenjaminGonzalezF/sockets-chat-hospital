package cliente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

import controladores.ControladorVistaMedicos;
import javafx.scene.control.TextArea;
        
public class GestionarConexion {

        private DataInputStream dataInput;
        private DataOutputStream dataOutput;
        private ControladorVistaMedicos controlador;
        private ObjectInputStream obtenerUsuariosOnline;
        private Socket socket;
        private Boolean conectado;
    
        public GestionarConexion(Socket socket, ControladorVistaMedicos controlador){
            this.controlador = controlador;

            try{
                dataInput = new DataInputStream(socket.getInputStream());
                dataOutput = new DataOutputStream(socket.getOutputStream());
    
                Thread hiloActulizadorDatos = new Thread(new RecibirDatos(socket, controlador));
                hiloActulizadorDatos.start();
            } catch (Exception e){
                e.printStackTrace();
            }
        }

      
}
