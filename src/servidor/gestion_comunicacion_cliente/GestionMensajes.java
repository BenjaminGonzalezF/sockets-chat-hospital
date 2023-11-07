package servidor.gestion_comunicacion_cliente;

import java.util.ArrayList;
import java.util.List;

import servidor.gestion_comunicacion_cliente.ConexionCliente;

public class GestionMensajes {
    private String mensaje;
    private String nombreSala;

    private List<ConexionCliente> observadores = new ArrayList<>();

    public synchronized void agregarObservador(ConexionCliente observador) {
        observadores.add(observador);
    }

    public synchronized void eliminarObservador(ConexionCliente observador) {
        observadores.remove(observador);
    }

    public synchronized void setMensaje(String mensaje) {
        this.mensaje = mensaje;
        notificarObservadores(mensaje);
    }

    public synchronized void notificarObservadores(String mensaje) {
        for (ConexionCliente observador : observadores) {
            observador.actualizarMensajes(mensaje);
        }
    }
}
