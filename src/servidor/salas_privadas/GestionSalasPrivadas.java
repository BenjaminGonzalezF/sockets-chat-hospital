package servidor.salas_privadas;

import java.net.Socket;
import java.util.ArrayList;

import servidor.Servidor;
import servidor.gestion_comunicacion_cliente.ConexionCliente;
import servidor.gestion_comunicacion_cliente.GestionMensajes;
import servidor.sockets_salas.SocketPrivado;

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
