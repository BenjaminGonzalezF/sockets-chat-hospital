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
import java.util.HashMap;
import java.util.Map;

import cliente.gestion_creacion_clientes.Cliente;

public class ControladorVistaLogin {

    private Cliente cliente = new Cliente("","");


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
        if (autenticarUsuario()) {
            mostrarVistaCambioContrasena();
        } else {
            mensajeError.setText("Credenciales incorrectas");
            mensajeError.setVisible(true);
        }
    }

private boolean autenticarUsuario() {
    String usuario = campoUsuario.getText();
    String contrasena = campoContrasena.getText();

    Map<String, String> usuariosYContrasenas = new HashMap<>();
    usuariosYContrasenas.put("Administrador", "Administrador");
    usuariosYContrasenas.put("Medico", "Medico");
    usuariosYContrasenas.put("Pabellon", "Pabellon");
    usuariosYContrasenas.put("Auxiliar", "Auxiliar");
    usuariosYContrasenas.put("Examenes", "Examenes");
    usuariosYContrasenas.put("Admision", "Admision");

    if (usuariosYContrasenas.containsKey(usuario) && usuariosYContrasenas.get(usuario).equals(contrasena)) {
        String rol = usuariosYContrasenas.get(usuario);
        System.out.println("Iniciando como " + rol);
        cliente.setRol(rol);
        return true;
    }

    return false;
}


    private void mostrarVistaCambioContrasena() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/vistaCambioContraseña.fxml"));
            Parent vistaCambioContrasena = loader.load();

            ControladorVistaCambioContrasena controladorCambioContrasena = loader.getController();
            controladorCambioContrasena.setCliente(cliente);

            Scene escenaActual = btnIniciarSesion.getScene();
            Scene nuevaEscena = new Scene(vistaCambioContrasena, escenaActual.getWidth(), escenaActual.getHeight());

            Stage ventana = (Stage) escenaActual.getWindow();
            ventana.setScene(nuevaEscena);

            controladorCambioContrasena.setControladorVistaLogin(this); // Pasa una referencia a este controlador
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     public void inicializarVista() {
        // Lógica para inicializar la vista
        campoUsuario.setText("");
        campoContrasena.setText("");
        mensajeError.setVisible(false);
     }
}
