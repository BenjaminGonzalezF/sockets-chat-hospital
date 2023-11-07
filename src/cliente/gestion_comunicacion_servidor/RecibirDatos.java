package cliente.gestion_comunicacion_servidor;

import controladores.ControladorVistaMedicos;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class RecibirDatos implements Runnable {

    private Socket socket;
    private ControladorVistaMedicos controlador;
    private Boolean conectado = true;
    private DataInputStream entradaDatos;
    private String salaActual;
    private String mensajes = "";

    public RecibirDatos(Socket socket, ControladorVistaMedicos controlador, String salaActual) {
        this.socket = socket;
        this.controlador = controlador;
        this.salaActual = salaActual;
        try {
            entradaDatos = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            // Manejar la excepción apropiadamente, por ejemplo, registrándola o notificando al usuario.
            ex.printStackTrace();
            conectado = false;
        }
    }

    public void recibirMensajesServidor() {
        String mensaje;
        System.out.println(salaActual + ": Ojos abiertos");

        while (conectado) {
            try {
                mensaje = entradaDatos.readUTF();
                System.out.println(salaActual + ": " + mensaje);
                mensajes += mensaje + "\n";
                if (salaActual.equals(controlador.getSalaActual())) {
                    controlador.actualizarMensajes(mensajes);
                }
            } catch (IOException ex) {
                // Manejar la excepción apropiadamente.
                ex.printStackTrace();
                conectado = false;
            }
        }
    }
    
    




    @Override
    public void run() {
        recibirMensajesServidor();
    }

    public String getMensajes() {
        return this.mensajes;
    }

    public void setMensajes(String mensajes) {
        this.mensajes = mensajes;
    }
}
