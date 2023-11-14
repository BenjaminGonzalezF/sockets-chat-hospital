package cliente.gestion_comunicacion_servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
<<<<<<< HEAD:src/cliente/GestionarConexion.java
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
=======
import java.util.ArrayList;

import cliente.gestion_clientes_online.ActualizarClientesOnline;
import cliente.gestion_creacion_clientes.Cliente;
import cliente.gestion_creacion_clientes.GeneracionClientes;
import cliente.gestion_salas.ConexionSalas;
import controladores.ControladorVistaMedicos;


public class GestionarConexionPrincipal {

    private DataInputStream dataInput;
    private DataOutputStream dataOutput;
    private ControladorVistaMedicos controlador;
    private ObjectInputStream obtenerUsuariosOnline;

    private Socket socket;
    private ConexionSalas gestionarSalas;
    private GeneracionClientes gestionClientes = new GeneracionClientes();

    public GestionarConexionPrincipal(Socket socket, ControladorVistaMedicos controlador) {
        this.controlador = controlador;
        this.socket = socket;
        try {
            dataInput = new DataInputStream(socket.getInputStream());
            dataOutput = new DataOutputStream(socket.getOutputStream());

            
            Cliente cliente = gestionClientes.crearCliente();
            controlador.setCliente(cliente);
            controlador.setSocket(socket);

            EnviarDatos enviarCliente = new EnviarDatos(socket, cliente, "Medico1");
            gestionarSalas = new ConexionSalas(cliente, controlador);

            ActualizarClientesOnline actualizarClientesOnline = new ActualizarClientesOnline(socket, controlador);
            actualizarClientesOnline.start();
            


        } catch (Exception e) {
            e.printStackTrace();
>>>>>>> Benjamin:src/cliente/gestion_comunicacion_servidor/GestionarConexionPrincipal.java
        }
    }


}
