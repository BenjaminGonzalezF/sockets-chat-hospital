package controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class ControladorVistaLogin {

    @FXML
    private TextField campoUsuario;

    @FXML
    private PasswordField campoContrasena;

    @FXML
    private Button btnIniciarSesion;

    @FXML
    private Label mensajeError;

    @FXML
    public void initialize() {
        mensajeError.setVisible(false);
    }

    @FXML
    private void iniciarSesion() {
        // Realiza la lógica de autenticación aquí
        if (autenticarUsuario()) {
            mostrarVistaCambioContrasena();
        } else {
            mensajeError.setText("Credenciales incorrectas");
            mensajeError.setVisible(true);
        }
    }

    private boolean autenticarUsuario() {
        // Aquí debes implementar la lógica de autenticación.
        // Compara el usuario y la contraseña con tus datos de usuario.
        // Devuelve true si la autenticación es exitosa, de lo contrario, false.
        return campoUsuario.getText().equals("usuario") && campoContrasena.getText().equals("contrasena");
    }

    private void mostrarVistaCambioContrasena() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("VistaCambioContrasena.fxml"));
            Parent vistaCambioContrasena = loader.load();

            ControladorVistaCambioContrasena controladorCambioContrasena = loader.getController();

            Scene escenaActual = btnIniciarSesion.getScene();
            Scene nuevaEscena = new Scene(vistaCambioContrasena, escenaActual.getWidth(), escenaActual.getHeight());

            Stage ventana = (Stage) escenaActual.getWindow();
            ventana.setScene(nuevaEscena);

            controladorCambioContrasena.setControladorVistaLogin(this); // Pasa una referencia a este controlador
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
