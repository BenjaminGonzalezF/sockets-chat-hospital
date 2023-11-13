package servidor.sockets_salas;

import servidor.Servidor;
import servidor.salas_privadas.GestionSalasPrivadas;

import java.net.Socket;

public class SocketPrivado extends SalaSocket {
    GestionSalasPrivadas gestionSalasPrivadas;

    public SocketPrivado(Servidor servidor, int puerto) {
        super(servidor, puerto, "salaPrivada" + puerto);
    }

    @Override
    protected void conectarCliente(Socket socketCliente) {
        gestionSalasPrivadas.creaSalaPrivada(socketCliente);
    }
}