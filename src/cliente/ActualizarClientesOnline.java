package cliente;

import controladores.ControladorVistaMedicos;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ActualizarClientesOnline extends Thread {

    private Socket socket;
    private ControladorVistaMedicos controlador;
    private ObjectInputStream entradaObjetos;
    private DataInputStream entradaMensajes;

    private boolean conectado = true;
    String  textoUsuariosOnline;
    ArrayList<String> usuariosOnline = new ArrayList<String>();

    public ActualizarClientesOnline(Socket socket, ControladorVistaMedicos controlador) {
        this.socket = socket;
        this.controlador = controlador;

        try {
            entradaMensajes = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            // Manejar la excepción apropiadamente, por ejemplo, registrándola o notificando
            // al usuario.
            ex.printStackTrace();
            conectado = false;
        }
    }

    private ArrayList<String> castearUsuarios(String nombres){
        ArrayList<String> nombresClientes = new ArrayList<String>();
        nombres = nombres.replace("[", "");
        nombres = nombres.replace("]", "");
        nombres = nombres.replace(" ", "");
        String[] nombresArray = nombres.split(",");
        for (String nombre : nombresArray) {
            nombresClientes.add(nombre);
        }
        return nombresClientes;
    }

    public void recibirMensajesServidor() {
        while (conectado) {
            try {
                this.textoUsuariosOnline = entradaMensajes.readUTF();
                controlador.actualizarClientesOnline(castearUsuarios(textoUsuariosOnline));

            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("Error al recibir los objetos del servidor");
                conectado = false;
            }
        }
    }

    @Override
    public void run() {
        recibirMensajesServidor();
    }
}
