package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

import controladores.ControladorVistaMedicos;

public class GestionarConexion {

    private DataInputStream dataInput;
    private DataOutputStream dataOutput;
    private ControladorVistaMedicos controlador;
    private ObjectInputStream obtenerUsuariosOnline;

    private Socket socket;
    private ConexionSalas gestionarSalas;
    private GestionClientes gestionClientes = new GestionClientes();

    public GestionarConexion(Socket socket, ControladorVistaMedicos controlador) {
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
        }
    }


}
