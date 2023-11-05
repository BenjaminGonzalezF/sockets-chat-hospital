package servidor.sockets_salas;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import servidor.Servidor;

public abstract class SalaSocket extends Thread {
    protected final int puerto;
    protected final Servidor servidor;
    protected final String nombreServidor;

    public SalaSocket(Servidor servidor, int puerto, String nombreServidor) {
        this.servidor = servidor;
        this.puerto = puerto;
        this.nombreServidor = nombreServidor;
    }

    @Override
    public void run() {
        ServerSocket socketServidor = null;

        try {
            socketServidor = new ServerSocket(puerto);
            System.out.println("Oreja pará en " + puerto + " a la espera de conexiones.");

            while (true) {
                Socket socketCliente = socketServidor.accept();
                System.out.println("Cliente con la IP " + socketCliente.getInetAddress().getHostName() + " conectado a "
                        + nombreServidor);
                conectarCliente(socketCliente);
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            if (socketServidor != null && !socketServidor.isClosed()) {
                try {
                    socketServidor.close();
                } catch (IOException ex) {
                    System.out.println("Error al cerrar el servidor en el puerto " + puerto + ": " + ex.getMessage());
                }
            }
        }
    }

    protected abstract void conectarCliente(Socket socketCliente);
}