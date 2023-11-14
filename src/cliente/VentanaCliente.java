package cliente;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class VentanaCliente extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/vistaLogin.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Ingreso");
        primaryStage.setScene(new Scene(root, 1120, 700));
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}