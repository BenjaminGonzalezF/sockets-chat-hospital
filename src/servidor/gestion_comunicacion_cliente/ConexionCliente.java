package servidor.gestion_comunicacion_cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import cliente.gestion_creacion_clientes.Cliente;

//Hilo que recibe los mensajes de un cliente y se lo reenvia a todos los clientes suscritos a la sala
public class ConexionCliente extends Thread implements Observador {

    private Socket socket;
    private GestionMensajes mensajes;
    private DataInputStream entradaDatos;
    private DataOutputStream salidaDatos;

    public ConexionCliente(Socket socket, GestionMensajes mensajes) {
        this.socket = socket;
        this.mensajes = mensajes;

        try {
            entradaDatos = new DataInputStream(socket.getInputStream());
            salidaDatos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            System.out.println("Error al crear los stream de entrada y salida : " + ex.getMessage());
        }
    }

    @Override
    public void run() {
        String mensajeRecibido;
        boolean conectado = true;
        while (conectado) {
            try {
                // Lee un mensaje enviado por el cliente
                mensajeRecibido = entradaDatos.readUTF();
                System.out.println(mensajeRecibido);
                // Pone el mensaje recibido en mensajes para que se notifique
                // a sus observadores que hay un nuevo mensaje.
                mensajes.setMensaje(mensajeRecibido);
            } catch (IOException ex) {
                System.out.println("Cliente desconectado de: " + socket.getLocalPort());
                conectado = false;
                // Cerrar la conexión en caso de algun error
                try {
                    entradaDatos.close();
                    salidaDatos.close();
                } catch (IOException ex2) {
                    System.out.println("Error al cerrar los stream de entrada y salida :" + ex2.getMessage());
                }
            }
        }
    }

    @Override
    public void actualizarMensajes(String mensaje) {
        try {
            salidaDatos.writeUTF(mensaje);
            System.out.println("Se envio el mensaje: " + mensaje);
        } catch (IOException ex) {
            System.out.println("Error al enviar mensaje al cliente (" + ex.getMessage() + ").");
        }
    }
}
