package servidor;

import java.net.Socket;

import cliente.Cliente;

public class ComprobarConexion extends Thread {

    private Socket socketCliente;
    private Cliente cliente;
    private RegistroClientes registroClientes;

    // Realiza una verificación periódica de la conexión con el cliente.
    public ComprobarConexion(Socket socketCliente, Cliente cliente, RegistroClientes registroClientes) {
        this.socketCliente = socketCliente;
        this.cliente = cliente;
        this.registroClientes = registroClientes;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (!socketCliente.isConnected()) {
                    System.out.println("Cliente desconectado: " + cliente.getNombre());
                    registroClientes.eliminarCliente(cliente);
                    break;
                }
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
