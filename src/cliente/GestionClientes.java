package cliente;

import java.util.ArrayList;

public class GestionClientes {
    private ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    private int nClientes;

    //Genera un nombre random para el client
    public String generarNombre() {
        int random = (int) (Math.random() * 1000) + 1;
        String nombre = "Cliente" + random;
        return nombre;
    }



    public Cliente crearCliente() {
        nClientes++;
        String nombre = generarNombre();
        Cliente cliente = new Cliente(nombre, "Examenes");
        clientes.add(cliente);
        return cliente;
    }
}