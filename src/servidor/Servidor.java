package servidor;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import cliente.Cliente;
import servidor.sockets_salas.SocketAdmision;
import servidor.sockets_salas.SocketAuxiliares;
import servidor.sockets_salas.SocketExamenes;
import servidor.sockets_salas.SocketMedicos;
import servidor.sockets_salas.SocketPabellon;

public class Servidor {
    private int puerto = 5000;

    private GestionMensajes salaMedicos = new GestionMensajes();
    private GestionMensajes salaAuxiliares = new GestionMensajes();
    private GestionMensajes salaExamenes = new GestionMensajes();
    private GestionMensajes salaPabellon = new GestionMensajes();
    private GestionMensajes salaAdmision = new GestionMensajes();

    private void iniciarServidor() {

        ServerSocket servidor = null;
        Socket socketCliente = null;

        SocketMedicos socketMedicos = new SocketMedicos(this);
        SocketAuxiliares socketAuxiliares = new SocketAuxiliares(this);
        SocketExamenes socketExamenes = new SocketExamenes(this);
        SocketPabellon socketPabellon = new SocketPabellon(this);
        SocketAdmision socketAdmision = new SocketAdmision(this);

        socketMedicos.start();
        socketAuxiliares.start();
        socketExamenes.start();
        socketPabellon.start();
        socketAdmision.start();

        try {
            // Se crea el serverSocket
            servidor = new ServerSocket(puerto);

            // Bucle infinito para esperar conexiones
            while (true) {
                System.out.println("Servidor a la espera de conexiones.");
                socketCliente = servidor.accept();
                System.out.println("Cliente con la IP " + socketCliente.getInetAddress().getHostName() + " conectado.");

                String rol = obtenerRol(socketCliente);
                Cliente cliente = new Cliente("nombre", socketCliente, rol);

            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally {
            try {
                socketCliente.close();
                servidor.close();
            } catch (IOException ex) {
                System.out.println("Error al cerrar el servidor: " + ex.getMessage());
            }
        }

    }

    public String obtenerRol(Socket socketCliente) {
        DataInputStream entradaDatos;
        try {
            entradaDatos = new DataInputStream(socketCliente.getInputStream());
            String rol = entradaDatos.readUTF();
            System.out.println("El rol es: " + rol);
            return rol;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    // Crea un hilo que se encarga de enviar los mensajes a los clientes y de
    // recibir los mensajes de la sala
    // Ademas suscribe el cliente a la sala

    public void conectarClientecon(Socket socket, String sala) {
        if (sala.equals("salaMedicos")) {
            ConexionCliente conexionCliente = new ConexionCliente(socket, salaMedicos);
            salaMedicos.agregarObservador(conexionCliente);
            conexionCliente.start();
        } else if (sala.equals("salaAuxiliares")) {
            ConexionCliente conexionCliente = new ConexionCliente(socket, salaAuxiliares);
            salaAuxiliares.agregarObservador(conexionCliente);
            conexionCliente.start();
        } else if (sala.equals("salaExamenes")) {
            ConexionCliente conexionCliente = new ConexionCliente(socket, salaExamenes);
            salaExamenes.agregarObservador(conexionCliente);
            conexionCliente.start();
        } else if (sala.equals("salaPabellon")) {
            ConexionCliente conexionCliente = new ConexionCliente(socket, salaPabellon);
            salaPabellon.agregarObservador(conexionCliente);
            conexionCliente.start();
        } else if (sala.equals("salaAdmision")) {
            ConexionCliente conexionCliente = new ConexionCliente(socket, salaAdmision);
            salaAdmision.agregarObservador(conexionCliente);
            conexionCliente.start();
        }

    }

    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        servidor.iniciarServidor();
    }

}
