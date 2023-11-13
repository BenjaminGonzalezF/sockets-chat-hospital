package controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;

public class controladorVistaAdministrativo {
    @FXML
    private WebView mensajes;

    @FXML
    private HTMLEditor mensajeAEnviar;

    @FXML
    private Button btnEnviarMensaje;

    /* @FXML
    private ChoiceBox<String> tipoAdministrativo; // Agregar ChoiceBox para seleccionar el tipo de administrativo

    @FXML
    public void initialize() {
        // Configuración inicial, si es necesaria
        tipoAdministrativo.getItems().addAll("Admisión", "Pabellón", "Exámenes", "Auxiliar");
        tipoAdministrativo.setValue("Admisión"); // Establece el tipo por defecto
        cargarConversacion(); // Carga la conversación inicial
    } */

    @FXML
    private void enviarMensaje() {
        // Obtener el mensaje del HTMLEditor
        String mensaje = mensajeAEnviar.getHtmlText();

        // Lógica para enviar el mensaje (personaliza esto según tus necesidades)
        mostrarMensajeEnVista(mensaje);

        // Borrar el contenido del HTMLEditor después de enviar el mensaje
        mensajeAEnviar.setHtmlText("");
    }

    @FXML
    private void tipoAdministrativoSeleccionado() {
        // Se llama cuando se selecciona un tipo de administrativo
        cargarConversacion(); // Carga la conversación correspondiente al tipo seleccionado
    }

    private void cargarConversacion() {
        // Lógica para cargar la conversación basada en el tipo de administrativo seleccionado
        //String tipoSeleccionado = tipoAdministrativo.getValue();
        // Por ejemplo, puedes cargar conversaciones diferentes desde aquí
    }

    private void mostrarMensajeEnVista(String mensaje) {
        // Aquí puedes personalizar cómo se muestra el mensaje en la WebView
        String mensajeHTML = "<p>" + mensaje + "</p>"; // Formatear el mensaje

        // Obtener el contenido actual de la WebView
        String contenidoActual = mensajes.getEngine().executeScript("document.body.innerHTML").toString();

        // Agregar el nuevo mensaje al contenido actual
        contenidoActual += mensajeHTML;

        // Actualizar la WebView con el nuevo contenido
        mensajes.getEngine().loadContent(contenidoActual);
    }
}
