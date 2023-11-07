package cliente;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import controladores.ControladorVistaMedicos;
import java.io.IOException;
import java.net.Socket;

public class ClienteChat extends Application {
    private String username = "";
    private Socket socket;
    private ControladorVistaMedicos controlador;
    private GestionarConexion gestionarConexion;
    private GestionClientes gestionClientes;

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
            gestionarConexion = new GestionarConexion(socket, controlador);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
