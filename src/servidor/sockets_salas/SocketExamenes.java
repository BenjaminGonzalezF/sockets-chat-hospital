package servidor.sockets_salas;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import cliente.Cliente;
import servidor.Servidor;

public class SocketExamenes extends Thread {
    Servidor servidor;

    public SocketExamenes(Servidor servidor) {
        this.servidor = servidor;
    }

    @Override
    public void run() {
        ServerSocket socketServidor = null;
        Socket socketCliente = null;

        try {
            socketServidor = new ServerSocket(5004);
            while (true) {
                System.out.println("Servidor examenes a la espera de conexiones.");
                socketCliente = socketServidor.accept();
                System.out.println(
                        "Cliente con la IP " + socketCliente.getInetAddress().getHostName() + " conectado a examenes");
                servidor.conectarClientecon(socketCliente, "salaExamenes");

            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            try {
                socketCliente.close();
                socketServidor.close();
            } catch (IOException ex) {
                System.out.println("Error al cerrar el servidor examenes " + ex.getMessage());
            }
        }

    }
}
