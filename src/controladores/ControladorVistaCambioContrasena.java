package controladores;

import java.io.IOException;

import cliente.gestion_comunicacion_servidor.ConexionInicial;
import cliente.gestion_creacion_clientes.Cliente;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class ControladorVistaCambioContrasena {
    Cliente cliente = new Cliente("","");
    ConexionInicial conexionInicial = new ConexionInicial();

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
            mensajeError.setVisible(false);
            System.out.println("Contrasena cambiada");
            cargarVistaCorrespondiente(cliente.getRol());
        } else {
            mensajeError.setText("Las contraseñas no coinciden");
            mensajeError.setVisible(true);
        }
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    private void cargarVistaCorrespondiente(String rol) {
        if(rol.equals("Administrador")) {
            this.mostrarVista("vistaAdministrativo.fxml");
        } else if(rol.equals("Medico")) {
            this.mostrarVista("vistaMedicos.fxml");
        } else if(rol.equals("Pabellon")) {
            //this.mostrarVista("vistaAdministrativo.fxml");
        } else if(rol.equals("Auxiliar")) {
            //this.mostrarVista("vistaAdministrativo.fxml");
        } else if(rol.equals("Examenes")) {
            //this.mostrarVista("vistaAdministrativo.fxml");
        } else if(rol.equals("Admision")) {
            //this.mostrarVista("vistaAdministrativo.fxml");
        } else {
            System.out.println("Rol no encontrado");
        }
    }

    private void mostrarVista(String vistaFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/" + vistaFXML));
            Parent vista = loader.load();
    
            ControladorPadre controlador = loader.getController();
            controlador.setCliente(cliente);
    
            Scene nuevaEscena = new Scene(vista, 1120, 700);
            Scene escenaActual = btnGuardarCambios.getScene();
    
            Stage ventana = (Stage) escenaActual.getWindow();
            conexionInicial.iniciar(cliente,controlador);
            controlador.definirSalaInicial();
            ventana.setScene(nuevaEscena);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

