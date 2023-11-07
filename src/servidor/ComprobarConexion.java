package servidor;

import java.net.Socket;

import cliente.Cliente;
import java.io.DataInputStream;
import java.io.IOException;

public class ComprobarConexion extends Thread {

    private Socket socketCliente;
    private Cliente cliente;
    private RegistroClientes registroClientes;
    private DataInputStream entradaDatos;

    // Realiza una verificación periódica de la conexión con el cliente.
    public ComprobarConexion(Socket socketCliente, Cliente cliente, RegistroClientes registroClientes) {
        this.socketCliente = socketCliente;
        this.cliente = cliente;
        this.registroClientes = registroClientes;
        try {
            this.entradaDatos = new DataInputStream(socketCliente.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        };
    }

 @Override
    public void run() {
        String mensajeRecibido;
        boolean conectado = true;
        while (conectado) {
            try {
                mensajeRecibido = entradaDatos.readUTF();
            } catch (IOException ex) {
                System.out.println("Se cierra la conexion con" + cliente.getNombre());
                conectado = false;
                registroClientes.eliminarCliente(cliente.getNombre());
                try {
                    entradaDatos.close();
                } catch (IOException ex2) {
                    System.out.println("Error al cerrar los stream de entrada y salida :" + ex2.getMessage());
                }
            }
        }
    }
}
