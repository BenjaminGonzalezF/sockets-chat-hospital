import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/vistaMedicos.fxml"));
        loader.setController(new controladores.controladorVistaMedicos());
        Parent root = loader.load();
        primaryStage.setTitle("Vista Medico");
        primaryStage.setScene(new Scene(root, 1120, 700));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
