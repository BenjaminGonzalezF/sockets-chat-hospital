package servidor.sockets_salas;

import servidor.Servidor;
import java.net.Socket;

public class SocketMedicos extends SalaSocket {
    public SocketMedicos(Servidor servidor) {
        super(servidor, 5001, "salaMedicos");
    }

    @Override
    protected void conectarCliente(Socket socketCliente) {
        servidor.conectarClientecon(socketCliente, nombreServidor);
    }
}
