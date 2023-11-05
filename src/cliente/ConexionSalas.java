package cliente.conexion_salas;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import cliente.Cliente;
import cliente.RecibirDatos;
import controladores.ControladorVistaMedicos;

public class ConexionSalas {
    private Cliente cliente;
    private ControladorVistaMedicos controlador;

    private static final int PUERTO_MEDICOS = 5001;
    private static final int PUERTO_AUXILIARES = 5002;
    private static final int PUERTO_EXAMENES = 5003;
    private static final int PUERTO_PABELLON = 5004;
    private static final int PUERTO_ADMISION = 5005;

    public ConexionSalas(Cliente cliente, ControladorVistaMedicos controlador) {
        this.cliente = cliente;
        this.controlador = controlador;
        conectarASalas();
    }

    private void conectarASalas() {
        String nombreSala;
        switch (cliente.getRol()) {
            case "Medico":
                conectarConSala("localhost", PUERTO_MEDICOS, "salaMedicos");
                conectarConSala("localhost", PUERTO_AUXILIARES, "salaAuxiliares");
                conectarConSala("localhost", PUERTO_EXAMENES, "salaExamenes");
                conectarConSala("localhost", PUERTO_PABELLON, "salaPabellon");
                conectarConSala("localhost", PUERTO_ADMISION, "salaAdmision");

                break;
            case "Auxiliar":
                conectarConSala("localhost", PUERTO_AUXILIARES, "salaAuxiliares");
                break;
            case "Examenes":
                conectarConSala("localhost", PUERTO_EXAMENES, "salaExamenes");
                conectarConSala("localhost", PUERTO_AUXILIARES, "salaAuxiliares");
                break;
            case "Pabellon":
                conectarConSala("localhost", PUERTO_PABELLON, "salaPabellon");
                conectarConSala("localhost", PUERTO_AUXILIARES, "salaAuxiliares");
                break;
            case "Admision":
                conectarConSala("localhost", PUERTO_ADMISION, "salaAdmision");
                conectarConSala("localhost", PUERTO_AUXILIARES, "salaAuxiliares");
                break;
            default:
                break;
        }
    }

    private void conectarConSala(String host, int puerto, String nombreSala) {
        try {
            Socket socket = new Socket(host, puerto);
            asignarSocket(socket);

            Thread hiloActualizadorDatos = new Thread(new RecibirDatos(socket, controlador, nombreSala));
            hiloActualizadorDatos.start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.out.println("No se pudo conectar con la sala en el puerto " + puerto);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("No se pudo conectar con la sala en el puerto " + puerto);
        }
    }

    private void asignarSocket(Socket socket) {
        if (socket.getPort() == PUERTO_MEDICOS) {
            cliente.setSocketConMedicos(socket);
            return;
        }
        if (socket.getPort() == PUERTO_AUXILIARES) {
            System.out.println("Asignando socket a auxiliares");
            cliente.setSocketConAuxiliares(socket);
            return;
        }
        if (socket.getPort() == PUERTO_EXAMENES) {
            cliente.setSocketConExamenes(socket);
            return;
        }
        if (socket.getPort() == PUERTO_PABELLON) {
            cliente.setSocketConPabellon(socket);
            return;
        }
        if (socket.getPort() == PUERTO_ADMISION) {
            cliente.setSocketConAdmision(socket);
            return;
        }
    }

}