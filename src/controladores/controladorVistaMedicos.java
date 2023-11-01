package controladores;

import java.net.Socket;

import cliente.EnviarDatos;
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
    private String usuario = "Medico";
    private String contenidoHTML = "";

    @FXML private WebView mensajes;
    @FXML private HTMLEditor mensajeAEnviar;
    @FXML private Button btnEnviarMensaje;

    public void setSocket(Socket socket){
        this.socket = socket;
    }
//Actualiza la interfaz desde el hilo de recibir datos
public void actualizarMensajes(String mensaje) {
    Platform.runLater(() -> {
        mensajes.getEngine().loadContent(mensaje);
    });
}
    public String getMensajes(){
        return contenidoHTML;
    }

    public String getMensajeAEnviar(){
        return mensajeAEnviar.getHtmlText();
    }

    public void setMainWindow(Stage mainWindow) {
            this.mainWindow = mainWindow;
    }

    @FXML
    private void enviarMensaje(){
        EnviarDatos enviarDatos = new EnviarDatos(socket,getMensajeAEnviar(), usuario);
    }
    

}
