package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

import cliente.gestion_salas.GestionSalas;
import controladores.ControladorVistaMedicos;

public class GestionarConexion {

    private DataInputStream dataInput;
    private DataOutputStream dataOutput;
    private ControladorVistaMedicos controlador;
    private ObjectInputStream obtenerUsuariosOnline;
    private Socket socket;
    private Boolean conectado;
    private GestionSalas gestionarSalas;

    public GestionarConexion(Socket socket, ControladorVistaMedicos controlador) {
        this.controlador = controlador;

        try {
            dataInput = new DataInputStream(socket.getInputStream());
            dataOutput = new DataOutputStream(socket.getOutputStream());

            // Se envia el rol del usuario al servidor
            Cliente cliente = new Cliente("nombre", socket, "medico");

            controlador.setCliente(cliente);
            EnviarDatos enviarDatos = new EnviarDatos(socket, cliente.getRol(), "Medico1");

            Thread hiloActulizadorDatos = new Thread(new RecibirDatos(socket, controlador));
            hiloActulizadorDatos.start();
            gestionarSalas = new GestionSalas(cliente, controlador);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
