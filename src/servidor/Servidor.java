package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private int puerto = 5000;

    private void iniciarServidor() {
 
        int maximoConexiones = 10; // Maximo de conexiones simultaneas
        ServerSocket servidor = null; 
        Socket socket = null;
        MensajesChat mensajes = new MensajesChat();
        
        try {
            // Se crea el serverSocket
            servidor = new ServerSocket(puerto);
            
            // Bucle infinito para esperar conexiones
            while (true) {
                System.out.println("Servidor a la espera de conexiones.");
                socket = servidor.accept();
                System.out.println("Cliente con la IP " + socket.getInetAddress().getHostName() + " conectado.");
                
                ConexionCliente cc = new ConexionCliente(socket, mensajes);
                cc.start();
                
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally{
            try {
                socket.close();
                servidor.close();
            } catch (IOException ex) {
                System.out.println("Error al cerrar el servidor: " + ex.getMessage());
            }
        }
    }
    

    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        servidor.iniciarServidor();
    }

}

