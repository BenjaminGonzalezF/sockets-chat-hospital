package servidor;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import cliente.gestion_creacion_clientes.Cliente;
import servidor.gestion_clientes_online.RegistroClientesOnline;
import servidor.gestion_comunicacion_cliente.ConexionCliente;
import servidor.gestion_comunicacion_cliente.GestionMensajes;
import servidor.sockets_salas.SocketAdmision;
import servidor.sockets_salas.SocketAuxiliares;
import servidor.sockets_salas.SocketExamenes;
import servidor.sockets_salas.SocketMedicos;
import servidor.sockets_salas.SocketPabellon;
import servidor.sockets_salas.SocketPrivado;

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
        RegistroClientesOnline registroClientes = new RegistroClientesOnline();
        
        socketMedicos.start();
        socketAuxiliares.start();
        socketExamenes.start();
        socketPabellon.start();
        socketAdmision.start();

        try {
            // Se crea el serverSocket
            servidor = new ServerSocket(puerto);
            // Bucle infinito para esperar conexiones
            System.out.println("Servidor a la espera de conexiones.");
            while (true) {
                socketCliente = servidor.accept();
                Cliente cliente = obtenerCliente(socketCliente);
                registroClientes.agregarCliente(cliente, socketCliente);               

            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    

    }


    public Cliente obtenerCliente(Socket socketCliente) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(socketCliente.getInputStream());
            Cliente cliente = (Cliente) objectInputStream.readObject();
            System.out.println("HEMOS RECIBIDO UN CLIENTEE " + cliente.getNombre());
            return cliente;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error al obtener el cliente");
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

    public void conectarClientecon(Socket socket, GestionMensajes gestorMensajes) {
        ConexionCliente conexionCliente = new ConexionCliente(socket, gestorMensajes);
        gestorMensajes.agregarObservador(conexionCliente);
        conexionCliente.start();
    }

    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        servidor.iniciarServidor();
    }

}
