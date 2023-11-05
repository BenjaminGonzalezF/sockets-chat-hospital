package servidor.sockets_salas;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import cliente.Cliente;
import servidor.Servidor;

public class SocketAuxiliares extends Thread {
    Servidor servidor;

    public SocketAuxiliares(Servidor servidor) {
        this.servidor = servidor;
    }

    @Override
    public void run() {
        ServerSocket socketServidor = null;
        Socket socketCliente = null;

        try {
            socketServidor = new ServerSocket(5002);
            while (true) {
                System.out.println("Servidor auxiliares a la espera de conexiones.");
                socketCliente = socketServidor.accept();
                System.out.println("Cliente con la IP " + socketCliente.getInetAddress().getHostName()
                        + " conectado a auxiliares");
                servidor.conectarClientecon(socketCliente, "salaAuxiliares");

            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            try {
                socketCliente.close();
                socketServidor.close();
            } catch (IOException ex) {
                System.out.println("Error al cerrar el servidor auxiliares " + ex.getMessage());
            }
        }

    }
}
