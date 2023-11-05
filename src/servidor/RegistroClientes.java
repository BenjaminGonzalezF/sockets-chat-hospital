package servidor;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cliente.Cliente;

public class RegistroClientes {
    private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    private Map<Socket, Cliente> clienteSocketMap = new HashMap<>();

    public void agregarCliente(Cliente cliente, Socket socketCliente) {
        clientes.add(cliente);
        clienteSocketMap.put(socketCliente, cliente);
    }

    public ArrayList<Cliente> getClientes() {
        return this.clientes;
    }

    public int getCantidadClientes() {
        return clientes.size();
    }

    public void eliminarCliente(Cliente cliente) {
        clientes.remove(cliente);
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

}
