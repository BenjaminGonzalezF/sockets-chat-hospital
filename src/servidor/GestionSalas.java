package servidor;

import cliente.Cliente;

public class GestionSalas {
    private GestionMensajes salaMedicos = new GestionMensajes();
    private GestionMensajes salaAuxiliares = new GestionMensajes();
    private GestionMensajes salaExamenes = new GestionMensajes();
    private GestionMensajes salaPabellon = new GestionMensajes();

    // Dependiendo del rol se le asignan las salas correspondientes
    public void asignarSalas(Cliente cliente) {
        switch (cliente.getRol()) {
            case "Medico":
                conectarClientecon(cliente, "salaMedicos");
                conectarClientecon(cliente, "salaAuxiliares");
                conectarClientecon(cliente, "salaExamenes");
                conectarClientecon(cliente, "salaPabellon");
                break;
            case "Auxiliar":
                conectarClientecon(cliente, "salaAuxiliares");
                break;
            case "Examen":
                conectarClientecon(cliente, "salaExamenes");
                break;
            case "Pabellon":
                conectarClientecon(cliente, "salaPabellon");
                break;
            default:
                break;
        }
    }

    // Crea un hilo que se encarga de enviar los mensajes a los clientes y de
    // recibir los mensajes de la sala
    // Ademas suscribe el cliente a la sala
    private void conectarClientecon(Cliente cliente, String sala) {

        if (sala.equals("salaMedicos")) {
            ConexionCliente conexionCliente = new ConexionCliente(cliente.getSocket(), salaMedicos);
            salaMedicos.agregarObservador(conexionCliente);
            conexionCliente.start();

        } else if (sala.equals("salaAuxiliares")) {
            ConexionCliente conexionCliente = new ConexionCliente(cliente.getSocket(), salaAuxiliares);
            salaAuxiliares.agregarObservador(conexionCliente);
            conexionCliente.start();
        } else if (sala.equals("salaExamenes")) {
            ConexionCliente conexionCliente = new ConexionCliente(cliente.getSocket(), salaExamenes);
            salaExamenes.agregarObservador(conexionCliente);
            conexionCliente.start();
        } else if (sala.equals("salaPabellon")) {
            ConexionCliente conexionCliente = new ConexionCliente(cliente.getSocket(), salaPabellon);
            salaPabellon.agregarObservador(conexionCliente);
            conexionCliente.start();
        }
    }
}
