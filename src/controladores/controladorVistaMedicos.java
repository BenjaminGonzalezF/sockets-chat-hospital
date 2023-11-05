package controladores;

import java.net.Socket;

import cliente.Cliente;
import cliente.EnviarDatos;
import cliente.gestion_salas.GestionSalas;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class ControladorVistaMedicos {

    private Stage mainWindow;
    private Socket socket;
    private String contenidoHTML = "";
    private Cliente cliente;

    @FXML
    private WebView mensajes;
    @FXML
    private HTMLEditor mensajeAEnviar;
    @FXML
    private Button btnEnviarMensaje;

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    // Actualiza la interfaz desde el hilo de recibir datos
    public void actualizarMensajes(String mensaje) {
        contenidoHTML += mensaje;
        Platform.runLater(() -> {
            mensajes.getEngine().loadContent(contenidoHTML);
        });
    }

    public String getMensajes() {
        return contenidoHTML;
    }

    public String getMensajeAEnviar() {
        return mensajeAEnviar.getHtmlText();
    }

    public void setMainWindow(Stage mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @FXML
    private void enviarMensaje() {
        EnviarDatos enviarDatos = new EnviarDatos(socket, getMensajeAEnviar(), cliente.getNombre());
        mensajeAEnviar.setHtmlText("");
    }

}
