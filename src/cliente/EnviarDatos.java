package cliente;

import controladores.ControladorVistaMedicos;
import javafx.event.ActionEvent;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.io.DataOutputStream;

public class EnviarDatos {

    private Socket socket;
    private DataOutputStream dataOutput;
    private String usuario;
    private String mensaje;

    public EnviarDatos(Socket socket, String mensaje, String usuario){
        this.socket = socket;
        this.mensaje = mensaje;
        this.usuario = usuario;

         try {
            this.dataOutput = new DataOutputStream(socket.getOutputStream());
            enviar();
        } catch (IOException ex) {
            System.out.println("Error al crear el stream de salida : " + ex.getMessage());
        } catch (NullPointerException ex) {
            System.out.println("El socket no se creo correctamente. " + ex.getMessage());
        }
    }

    public void enviar() {
        try {
            //dataOutput.writeUTF(usuario + ": " + mensaje );
            dataOutput.writeUTF(mensaje);

            System.out.println("Se envio el mensaje: " + mensaje);
            
        } catch (IOException ex) {
            System.out.println("Error al intentar enviar un mensaje: " + ex.getMessage());
        }
    
    }



    
}
