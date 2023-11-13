<<<<<<< HEAD:src/controladores/controladorVistaCambioContraseña.java
package controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;

public class controladorVistaCambioContraseña {

    @FXML
    private PasswordField campoNuevaContrasena;

    @FXML
    private PasswordField campoConfirmarContrasena;

    @FXML
    private Button btnGuardarCambios;

    @FXML
    private Label mensajeError;

    private controladorVistaLogin controladorVistaLogin;


    public void setControladorVistaLogin(controladorVistaLogin controladorVistaLogin) {
        this.controladorVistaLogin = controladorVistaLogin;
    }

    @FXML
    private void guardarCambiosContrasena() {
        String nuevaContrasena = campoNuevaContrasena.getText();
        String confirmarContrasena = campoConfirmarContrasena.getText();

        if (nuevaContrasena.equals(confirmarContrasena)) {
            // Aquí debes implementar la lógica para guardar la nueva contraseña
            mensajeError.setVisible(false);
            volverAVistaLogin();
        } else {
            mensajeError.setText("Las contraseñas no coinciden");
            mensajeError.setVisible(true);
        }
    }

    private void volverAVistaLogin() {
        // Vuelve a la vista de login
        controladorVistaLogin.initialize();
    }
}

=======
package controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;

public class ControladorVistaCambioContrasena {

    @FXML
    private PasswordField campoNuevaContrasena;

    @FXML
    private PasswordField campoConfirmarContrasena;

    @FXML
    private Button btnGuardarCambios;

    @FXML
    private Label mensajeError;

    private ControladorVistaLogin controladorVistaLogin;

    public void setControladorVistaLogin(ControladorVistaLogin controladorVistaLogin) {
        this.controladorVistaLogin = controladorVistaLogin;
    }

    @FXML
    private void guardarCambiosContrasena() {
        String nuevaContrasena = campoNuevaContrasena.getText();
        String confirmarContrasena = campoConfirmarContrasena.getText();

        if (nuevaContrasena.equals(confirmarContrasena)) {
            // Aquí debes implementar la lógica para guardar la nueva contraseña
            mensajeError.setVisible(false);
            volverAVistaLogin();
        } else {
            mensajeError.setText("Las contraseñas no coinciden");
            mensajeError.setVisible(true);
        }
    }


    private void volverAVistaLogin() {
        // Vuelve a la vista de login
        //controladorVistaLogin.inicializarVista();
    }
}

>>>>>>> Benjamin:src/controladores/ControladorVistaCambioContrasena.java
