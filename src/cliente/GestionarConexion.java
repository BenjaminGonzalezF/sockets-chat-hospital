package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

import cliente.conexion_salas.ConexionSalas;
import controladores.ControladorVistaMedicos;

public class GestionarConexion {

    private DataInputStream dataInput;
    private DataOutputStream dataOutput;
    private ControladorVistaMedicos controlador;
    private ObjectInputStream obtenerUsuariosOnline;
    private Socket socket;
    private Boolean conectado;
    private ConexionSalas gestionarSalas;

    public GestionarConexion(Socket socket, ControladorVistaMedicos controlador) {
        this.controlador = controlador;

        try {
            dataInput = new DataInputStream(socket.getInputStream());
            dataOutput = new DataOutputStream(socket.getOutputStream());

            // Se envia el rol del usuario al servidor
            Cliente cliente = new Cliente("nombre", socket, "Examenes");

            controlador.setCliente(cliente);
            EnviarDatos enviarDatos = new EnviarDatos(socket, cliente.getRol(), "Medico1");

            Thread hiloActulizadorDatos = new Thread(new RecibirDatos(socket, controlador, "salaTodos"));
            hiloActulizadorDatos.start();
            gestionarSalas = new ConexionSalas(cliente, controlador);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
