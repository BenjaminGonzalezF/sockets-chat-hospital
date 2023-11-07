package cliente;

import controladores.ControladorVistaMedicos;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ActualizarClientesOnline extends Thread {

    private Socket socket;
    private ControladorVistaMedicos controlador;
    private ObjectInputStream entradaObjetos;
    private boolean conectado = true;
    ArrayList<String> usuariosOnline = new ArrayList<String>();

    public ActualizarClientesOnline(Socket socket, ControladorVistaMedicos controlador) {
        this.socket = socket;
        this.controlador = controlador;

        try {
            entradaObjetos = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            // Manejar la excepción apropiadamente, por ejemplo, registrándola o notificando
            // al usuario.
            ex.printStackTrace();
            conectado = false;
        }
    }

    public void recibirMensajesServidor() {
        while (conectado) {
            try {
                this.usuariosOnline = (ArrayList<String>) entradaObjetos.readObject();
                controlador.actualizarClientesOnline(usuariosOnline);

            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("Error al recibir los objetos del servidor");
                conectado = false;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                conectado = false;
            }
        }
    }

    @Override
    public void run() {
        recibirMensajesServidor();
    }
}
