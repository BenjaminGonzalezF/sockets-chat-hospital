package cliente.gestion_salas;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import cliente.gestion_comunicacion_servidor.RecibirDatos;
import cliente.gestion_creacion_clientes.Cliente;
import controladores.ControladorVistaMedicos;

public class ConexionSalaPrivada {

    private ControladorVistaMedicos controlador;
    private Cliente[] clientes = new Cliente[2];
    private int puerto = 5005;
    private Socket socket;

    public ConexionSalaPrivada(Cliente cliente1, Cliente cliente2, ControladorVistaMedicos controlador, int puerto) {

        this.clientes[0] = cliente1;
        this.clientes[1] = cliente2;
        this.controlador = controlador;
        this.puerto = puerto;
        
        //Se crea una conexion en una nueva sala privada para los dos clientes
        for (Cliente cliente : clientes) {
            try {
                this.socket = new Socket("localhost", this.puerto);
                cliente.addSalaPrivada(this);
                Thread hiloActualizadorDatos = new Thread(new RecibirDatos(socket, controlador, "salaPrivada"));
                hiloActualizadorDatos.start();
            } catch (UnknownHostException e) {
                e.printStackTrace();
                System.out.println("No se pudo conectar con la sala en el puerto " + puerto);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("No se pudo conectar con la sala en el puerto " + puerto);
            }

        }

    }

}
