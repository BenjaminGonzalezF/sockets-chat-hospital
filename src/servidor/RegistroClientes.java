package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cliente.Cliente;

public class RegistroClientes {
    private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    private Map<String, Socket> clienteSocketMap = new HashMap<>();

    public void agregarCliente(Cliente cliente, Socket socketCliente) {
        clientes.add(cliente);
        clienteSocketMap.put(cliente.getNombre(), socketCliente);

        System.out.println("Cliente conectado: " + cliente.getNombre());
        System.out.println("Cantidad de clientes: " + clientes.size());
        ComprobarConexion comprobarConexionCliente = new ComprobarConexion(socketCliente, cliente, this);
        comprobarConexionCliente.start();
        actualizarOnlineATodos();
    }

    private void enviarClientesOnline(Socket socket, String clientesOnline){
        System.out.println("Enviando : " + clientesOnline.toString());
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeUTF(clientesOnline);
            dataOutputStream.flush();
            System.out.println("Se envio la lista de clientes conectados: " + clientesOnline);
        } catch (IOException ex) {
            System.out.println("Error al crear el stream de salida : " + ex.getMessage());
        } catch (NullPointerException ex) {
            System.out.println("El socket no se creo correctamente. " + ex.getMessage());
        }
    }



    public void eliminarCliente(String nombre) { 
        clientes.removeIf(cliente -> cliente.getNombre().equals(nombre));
        clienteSocketMap.remove(nombre);
        System.out.println("Eliminar" + nombre);
        actualizarOnlineATodos();
    }    

    private void actualizarOnlineATodos(){
        ArrayList<String> nombresClientes = new ArrayList<String>();
        nombresClientes = getClientesConectados();
        for (Cliente cliente : clientes) {
            enviarClientesOnline(clienteSocketMap.get(cliente.getNombre()), nombresClientes.toString());
        }
        
    }


    public ArrayList<String> getClientesConectados(){
        ArrayList<String> nombresClientes = new ArrayList<String>();
        for (Cliente cliente : clientes) {
            nombresClientes.add(cliente.getNombre());
        }
        return nombresClientes;
    }


    public ArrayList<Cliente> getClientes() {
        return this.clientes;
    }

    public int getCantidadClientes() {
        return clientes.size();
    }
    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

}
