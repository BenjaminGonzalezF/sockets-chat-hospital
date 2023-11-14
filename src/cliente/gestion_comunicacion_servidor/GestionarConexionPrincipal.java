package cliente.gestion_comunicacion_servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

import cliente.gestion_clientes_online.ActualizarClientesOnline;
import cliente.gestion_creacion_clientes.Cliente;
import cliente.gestion_creacion_clientes.GeneracionClientes;
import cliente.gestion_salas.ConexionSalas;
import controladores.ControladorPadre;
import controladores.ControladorVistaMedicos;


public class GestionarConexionPrincipal {

    private DataInputStream dataInput;
    private DataOutputStream dataOutput;
    private ControladorPadre controlador;
    private ObjectInputStream obtenerUsuariosOnline;

    private Socket socket;
    private ConexionSalas gestionarSalas;
    private GeneracionClientes gestionClientes = new GeneracionClientes();

    public GestionarConexionPrincipal(Socket socket, ControladorPadre controlador, Cliente cliente) {
        this.controlador = controlador;
        this.socket = socket;
        try {
            dataInput = new DataInputStream(socket.getInputStream());
            dataOutput = new DataOutputStream(socket.getOutputStream());

            
            gestionClientes.crearCliente(cliente);
            controlador.setCliente(cliente);
            controlador.setSocket(socket);

            EnviarDatos enviarCliente = new EnviarDatos(socket, cliente, "Medico1");
            gestionarSalas = new ConexionSalas(cliente, controlador);

            ActualizarClientesOnline actualizarClientesOnline = new ActualizarClientesOnline(socket, controlador);
            actualizarClientesOnline.start();
            


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
