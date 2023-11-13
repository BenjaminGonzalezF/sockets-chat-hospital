package cliente;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
<<<<<<< HEAD:src/cliente/ClienteChat.java
import controladores.controladorVistaMedicos;
=======
import javafx.stage.WindowEvent;
import controladores.ControladorVistaMedicos;
>>>>>>> Benjamin:src/cliente/VentanaCliente.java
import java.io.IOException;
import java.net.Socket;

import cliente.gestion_comunicacion_servidor.GestionarConexionPrincipal;
import cliente.gestion_creacion_clientes.GestionClientes;

public class VentanaCliente extends Application {
    private String username = "";
    private Socket socket;
<<<<<<< HEAD:src/cliente/ClienteChat.java
    private controladorVistaMedicos controlador;
    private GestionarConexion gestionarConexion;
    
=======
    private ControladorVistaMedicos controlador;
    private GestionarConexionPrincipal gestionarConexion;
    private GestionClientes gestionClientes;

>>>>>>> Benjamin:src/cliente/VentanaCliente.java
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/vistaMedicos.fxml"));
        Parent root = loader.load();
        controlador = loader.getController();
        primaryStage.setTitle("Vista Medico");
        primaryStage.setScene(new Scene(root, 1120, 700));
        primaryStage.show();

        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0); 
            }
        });

        iniciar();
    }

    private void iniciar() {
        try {
            socket = new Socket("localhost", 5000);
            controlador.setSocket(socket);
            gestionClientes = new GestionClientes();
            gestionarConexion = new GestionarConexionPrincipal(socket, controlador);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
