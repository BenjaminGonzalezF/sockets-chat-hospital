package servidor.sockets_salas;

import servidor.Servidor;
import java.net.Socket;

public class SocketAdmision extends SalaSocket {
    public SocketAdmision(Servidor servidor) {
        super(servidor, 5005, "salaAdmision");
    }

    @Override
    protected void conectarCliente(Socket socketCliente) {
        servidor.conectarClientecon(socketCliente, nombreServidor);
    }
}
