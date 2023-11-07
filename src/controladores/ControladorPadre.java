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
  public void initialize() {
    columnaUsuarios.setCellValueFactory(new PropertyValueFactory<>("nombre"));
  }
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

    /*
     * public void actualizarClientesOnline(ArrayList<String> usuariosOnline) {
     * StringBuilder usuarios = new StringBuilder();
     * 
     * System.out.println("Actualizando clientes online");
     * for (String usuario : usuariosOnline) {
     * usuarios.append(usuario).append("\n");
     * System.out.println(usuario);
     * }
     * 
     * String usuariosTexto = usuarios.toString();
     * 
     * Platform.runLater(() -> {
     * this.tablaUsuariosOnline.set(usuariosTexto);
     * });
     * }
     */


     public void actualizarClientesOnline(ArrayList<String> usuariosOnline) {
        System.out.println("Actualizando clientes online" + usuariosOnline.toString());
        ObservableList<UsuarioOnline> usuarios = FXCollections.observableArrayList();
    
        for (String usuario : usuariosOnline) {
            usuarios.add(new UsuarioOnline(usuario));
        }
    
        tablaUsuariosOnline.setItems(usuarios);
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
