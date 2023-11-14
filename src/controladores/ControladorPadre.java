package controladores;

import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import cliente.gestion_clientes_online.UsuarioOnline;
import cliente.gestion_comunicacion_servidor.EnviarDatos;
import cliente.gestion_creacion_clientes.Cliente;
import cliente.gestion_salas.ConexionSalas;



import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableView<UsuarioOnline> tablaUsuariosOnline;
    @FXML
    private TableColumn<UsuarioOnline, String> columnaUsuarios;
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

    @FXML
    public void initialize() {
        columnaUsuarios.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    }

    // Actualiza la interfaz, como se llama desde un hilo (RecibirDatos), se debe
    // obtener el hilo que controla la interfaz
    public void actualizarMensajes(String mensaje) {
        contenidoHTML = mensaje;
        Platform.runLater(() -> mensajes.getEngine().loadContent(contenidoHTML));
    }

    @FXML
    private void enviarMensaje() {
        socket = obtenerSocketSala(salaActual);
        EnviarDatos enviarDatos = new EnviarDatos(socket, getMensajeAEnviar(), cliente.getNombre());
        mensajeAEnviar.setHtmlText("");
    }

    @FXML
    private void cambiarASala(String nuevaSala) {
        salaActual = nuevaSala;
        actualizarMensajes("");
    }


    // Recibe un arraylist de usuarios online y lo convierte en una ObservableList
    // de UsuarioOnline para mostrarlo en la tabla
    public void actualizarClientesOnline(ArrayList<String> usuariosOnline) {
        System.out.println("Actualizando clientes online" + usuariosOnline.toString());
        ObservableList<UsuarioOnline> usuarios = FXCollections.observableArrayList(
                usuariosOnline.stream().map(UsuarioOnline::new).toList());
        tablaUsuariosOnline.setItems(usuarios);
    }

    private Socket obtenerSocketSala(String nombreSala) {
        switch (nombreSala) {
            case "salaMedicos":
                return cliente.getSocketConMedicos();
            case "salaAuxiliares":
                return cliente.getSocketConAuxiliares();
            case "salaExamenes":
                return cliente.getSocketConExamenes();
            case "salaAdmision":
                return cliente.getSocketConAdmision();
            default:
                return cliente.getSocketConPabellon();
        }
    }

    // Getters y Setters
    public void setMainWindow(Stage mainWindow) {
        this.mainWindow = mainWindow;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
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

    //Cambios de sala de chat (funciones de los botones)
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

}
