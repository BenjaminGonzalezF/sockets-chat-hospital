package servidor;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
    private int puerto = 5000;
    private GestionMensajes salaMedicos = new GestionMensajes();
    private GestionMensajes salaAuxiliares = new GestionMensajes();


    private void iniciarServidor() {
 
        ServerSocket servidor = null; 
        Socket socketCliente = null;
        GestionMensajes gestionMensajes = new GestionMensajes();

        
        try {
            // Se crea el serverSocket
            servidor = new ServerSocket(puerto);
            
            // Bucle infinito para esperar conexiones
            while (true) {
                System.out.println("Servidor a la espera de conexiones.");
                socketCliente = servidor.accept();
                System.out.println("Cliente con la IP " + socketCliente.getInetAddress().getHostName() + " conectado.");

                String rol = obtenerRol(socketCliente);

                if(rol.equals("medico")){
                    gestionMensajes = salaMedicos;
                    System.out.println("Se conecto un medico");
                }
                else if(rol.equals("auxiliar")){
                    gestionMensajes = salaAuxiliares;
                    System.out.println("Se conecto un auxiliar");
                }
                
                ConexionCliente cc = new ConexionCliente(socketCliente, gestionMensajes);

                // Se añade el cliente a la lista de clientes
                gestionMensajes.agregarObservador(cc);
                cc.start();
                
            }
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        } finally{
            try {
                socketCliente.close();
                servidor.close();
            } catch (IOException ex) {
                System.out.println("Error al cerrar el servidor: " + ex.getMessage());
            }
        }
        
    }
    
    private String obtenerRol(Socket socketCliente){
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

    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        servidor.iniciarServidor();
    }

}

