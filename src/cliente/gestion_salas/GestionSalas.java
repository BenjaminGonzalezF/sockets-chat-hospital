package cliente.gestion_salas;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import cliente.Cliente;
import cliente.RecibirDatos;
import controladores.ControladorVistaMedicos;

public class GestionSalas {
    private Cliente cliente;
    private ControladorVistaMedicos controlador;
    private String salaActual = "Medicos";

    private static final int PUERTO_MEDICOS = 5001;
    private static final int PUERTO_AUXILIARES = 5002;
    private static final int PUERTO_EXAMENES = 5003;
    private static final int PUERTO_PABELLON = 5004;

    public GestionSalas(Cliente cliente, ControladorVistaMedicos controlador) {
        this.cliente = cliente;
        this.controlador = controlador;
        conectarASalas();
    }

    private void conectarASalas() {
        switch (cliente.getRol()) {
            case "Medico":
                conectarConSala("localhost", PUERTO_MEDICOS);
                conectarConSala("localhost", PUERTO_AUXILIARES);
                conectarConSala("localhost", PUERTO_EXAMENES);
                conectarConSala("localhost", PUERTO_PABELLON);
                break;
            case "Auxiliar":
                conectarConSala("localhost", PUERTO_AUXILIARES);
                break;
            case "Examen":
                conectarConSala("localhost", PUERTO_EXAMENES);
                break;
            case "Pabellon":
                conectarConSala("localhost", PUERTO_PABELLON);
                break;
            default:
                break;
        }
    }

    private void conectarConSala(String host, int puerto) {
        try {
            Socket socket = new Socket(host, puerto);
            cliente.setSocket(socket);

            Thread hiloActualizadorDatos = new Thread(new RecibirDatos(socket, controlador));
            hiloActualizadorDatos.start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println("No se pudo conectar con la sala en el puerto " + puerto);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("No se pudo conectar con la sala en el puerto " + puerto);
        }
    }

    public void setSalaActual(String sala) {
        this.salaActual = sala;
    }

    public String getSalaActual() {
        return this.salaActual;
    }
}