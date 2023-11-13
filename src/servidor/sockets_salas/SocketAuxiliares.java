package servidor.sockets_salas;

import servidor.Servidor;
import java.net.Socket;

public class SocketAuxiliares extends SalaSocket {
    public SocketAuxiliares(Servidor servidor) {
        super(servidor, 5002, "salaAuxiliares");
    }

    @Override
    protected void conectarCliente(Socket socketCliente) {
        servidor.conectarClientecon(socketCliente, nombreServidor);
    }
}
