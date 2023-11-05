package servidor.sockets_salas;

import java.net.Socket;
import java.util.ArrayList;

import servidor.ConexionCliente;
import servidor.GestionMensajes;
import servidor.Servidor;

public class GestionSalasPrivadas {
    private ArrayList<GestionMensajes> SalasPrivadas = new ArrayList<GestionMensajes>();

    private Servidor servidor;
    private int puerto = 5005;

    public GestionSalasPrivadas(Servidor servidor) {
        this.servidor = servidor;
    }

    public void creaSalaPrivada(Socket socket) {
        puerto++;

        SocketPrivado socketPrivado = new SocketPrivado(servidor, puerto);
        socketPrivado.run();

        GestionMensajes gestionSalaPrivada = new GestionMensajes();
        ConexionCliente conexionCliente = new ConexionCliente(socket, gestionSalaPrivada);
        gestionSalaPrivada.agregarObservador(conexionCliente);

        SalasPrivadas.add(gestionSalaPrivada);
        conexionCliente.start();

    }

}
