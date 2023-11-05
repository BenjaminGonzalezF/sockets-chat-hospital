package servidor.sockets_salas;

import servidor.Servidor;
import java.net.Socket;

public class SocketPabellon extends SalaSocket {
    public SocketPabellon(Servidor servidor) {
        super(servidor, 5003, "salaPabellon");
    }

    @Override
    protected void conectarCliente(Socket socketCliente) {
        servidor.conectarClientecon(socketCliente, nombreServidor);
    }
}
