package cliente.gestion_comunicacion_servidor;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import controladores.ControladorPadre;
import controladores.ControladorVistaMedicos;
import java.io.IOException;
import java.net.Socket;

import cliente.gestion_creacion_clientes.Cliente;
import cliente.gestion_creacion_clientes.GeneracionClientes;

public class ConexionInicial{
    private Socket socket;
    private ControladorVistaMedicos controlador;
    private GestionarConexionPrincipal gestionarConexion;
    private GeneracionClientes gestionClientes;

    public void iniciar(Cliente cliente,ControladorPadre controlador) {
        try {
            socket = new Socket("localhost", 5000);
            controlador.setSocket(socket);
            gestionClientes = new GeneracionClientes();
            gestionarConexion = new GestionarConexionPrincipal(socket, controlador, cliente);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
