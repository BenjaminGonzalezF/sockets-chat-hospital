package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import controladores.controladorVistaMedicos;
        
public class GestionarConexion {

        private DataInputStream dataInput;
        private DataOutputStream dataOutput;
        private controladorVistaMedicos controlador;
        private ObjectInputStream obtenerUsuariosOnline;
        private Socket socket;
        private Boolean conectado;
    
        public GestionarConexion(Socket socket, controladorVistaMedicos controlador){
            this.controlador = controlador;

            try{
                dataInput = new DataInputStream(socket.getInputStream());
                dataOutput = new DataOutputStream(socket.getOutputStream());

                //Se envia el rol del usuario al servidor
                EnviarDatos enviarDatos = new EnviarDatos(socket, "medico", "Medico1");


                Thread hiloActulizadorDatos = new Thread(new RecibirDatos(socket, controlador));
                hiloActulizadorDatos.start();
            } catch (Exception e){
                e.printStackTrace();
            }
        }

      
}
