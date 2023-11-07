package controladores;

import java.net.Socket;
import java.util.ArrayList;

import cliente.Cliente;
import cliente.ConexionSalas;
import cliente.EnviarDatos;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class ControladorPadre {

    private Stage mainWindow;
    private Socket socket;
    private String contenidoHTML = "";
    private String salaActual = "salaAuxiliares";
    private Cliente cliente;
    @FXML 
    private Label labelUsuariosOnline;
    @FXML
    private WebView mensajes;
    @FXML
    private HTMLEditor mensajeAEnviar;
    @FXML
    private Button btnEnviarMensaje;
    @FXML
    private Button btnSalaMedicos;
    @FXML
    private Button btnSalaAuxiliares;
    @FXML
    private Button btnSalaExamenes;
    @FXML
    private Button btnSalaAdmision;

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    // Actualiza la interfaz desde el hilo de recibir datos
    public void actualizarMensajes(String mensaje) {
        contenidoHTML = mensaje;
        Platform.runLater(() -> {
            mensajes.getEngine().loadContent(contenidoHTML);
        });
    }

    @FXML
    private void enviarMensaje() {
        if (salaActual == "salaMedicos") {
            socket = cliente.getSocketConMedicos();
        } else if (salaActual == "salaAuxiliares") {
            socket = cliente.getSocketConAuxiliares();
        } else if (salaActual == "salaExamenes") {
            socket = cliente.getSocketConExamenes();
        } else if (salaActual == "salaAdmision") {
            socket = cliente.getSocketConAdmision();
        } else {
            socket = cliente.getSocketConPabellon();
        }
        EnviarDatos enviarDatos = new EnviarDatos(socket, getMensajeAEnviar(), cliente.getNombre());
        mensajeAEnviar.setHtmlText("");
    }

    @FXML
    private void cambiarASalaMedicos() {
        salaActual = "salaMedicos";
        actualizarMensajes("");
    }

    @FXML
    private void cambiarASalaAuxiliares() {
        salaActual = "salaAuxiliares";
        actualizarMensajes("");

    }

    @FXML
    private void cambiarASalaExamenes() {
        salaActual = "salaExamenes";
        actualizarMensajes("");

    }

    @FXML
    private void cambiarASalaAdmision() {
        salaActual = "salaAdmision";
        actualizarMensajes("");

    }

    public void  actualizarClientesOnline( ArrayList<String> usuariosOnline){
        StringBuilder usuarios = new StringBuilder();

        System.out.println("Actualizando clientes online");
        for (String usuario : usuariosOnline) {
            usuarios.append(usuario).append("\n");
            System.out.println(usuario);
        }
        
        String usuariosTexto = usuarios.toString();
        
        Platform.runLater(() -> {
            this.labelUsuariosOnline.setText(usuariosTexto);
        });
    }

    public void setMainWindow(Stage mainWindow) {
        this.mainWindow = mainWindow;
    }

    public String getMensajes() {
        return contenidoHTML;
    }

    public String getMensajeAEnviar() {
        return mensajeAEnviar.getHtmlText();
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    

    public String getSalaActual() {
        return salaActual;
    }

}
