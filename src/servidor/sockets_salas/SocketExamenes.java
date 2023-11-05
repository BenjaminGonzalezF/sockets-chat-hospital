package servidor.sockets_salas;

import servidor.Servidor;
import java.net.Socket;

public class SocketExamenes extends SalaSocket {
    public SocketExamenes(Servidor servidor) {
        super(servidor, 5004, "salaExamenes");
    }

    @Override
    protected void conectarCliente(Socket socketCliente) {
        servidor.conectarClientecon(socketCliente, nombreServidor);
    }
}
