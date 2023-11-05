package cliente;

import controladores.ControladorVistaMedicos;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class RecibirDatos implements Runnable {

    private Socket socket;
    private ControladorVistaMedicos controlador;
    private Boolean conectado = true;
    private DataInputStream entradaDatos;
    private String salaActual;

    private String mensajes = "";

    public RecibirDatos(Socket socket, ControladorVistaMedicos controlador, String salaActual) {
        this.socket = socket;
        this.controlador = controlador;
        this.salaActual = salaActual;
    }

    public void recibirMensajesServidor() {
        // Obtiene el flujo de entrada del socket
        String mensaje;
        System.out.println(salaActual + ": Ojos abiertos");
        try {
            entradaDatos = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            System.out.println("Error al crear el stream de entrada: " + ex.getMessage());
        } catch (NullPointerException ex) {
            System.out.println("El socket no se creo correctamente. " + ex.getMessage());
        }

        while (conectado) {
            try {
                mensaje = entradaDatos.readUTF();
                System.out.println(salaActual + ": " + mensaje);
                mensajes += mensaje + "\n";

                if (salaActual == controlador.getSalaActual()) {
                    controlador.actualizarMensajes(mensajes);
                }

            } catch (IOException ex) {
                System.out.println("Error al leer del stream de entrada: " + ex.getMessage());
                conectado = false;
            } catch (NullPointerException ex) {
                System.out.println("El socket no se creo correctamente. " + ex.getMessage());
                conectado = false;
            }
        }
    }

    @Override
    public void run() {
        recibirMensajesServidor();
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

    public String getMensajes() {
        return this.mensajes;
    }

    public void setMensajes(String mensajes) {
        this.mensajes = mensajes;
    }

}
