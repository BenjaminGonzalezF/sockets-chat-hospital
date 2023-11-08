package cliente.gestion_creacion_clientes;

import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;

import cliente.gestion_salas.ConexionSalaPrivada;

public class Cliente implements Serializable {
    private String nombre;
    private String rol;

    private Socket socket;
    private Socket socketConMedicos;
    private Socket socketConAuxiliares;
    private Socket socketConExamenes;
    private Socket socketConPabellon;
    private Socket setSocketConAdmision;
    private ArrayList<ConexionSalaPrivada> salasPrivadas = new ArrayList<ConexionSalaPrivada>();

    public Cliente(String nombre , String rol) {
        this.nombre = nombre;
        this.rol = rol;
    }

    public void addSalaPrivada(ConexionSalaPrivada salaPrivada) {
        this.salasPrivadas.add(salaPrivada);
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public Socket getSocketConAdmision() {
        return this.setSocketConAdmision;
    }

    public void setSocketConAdmision(Socket setSocketConAdmision) {
        this.setSocketConAdmision = setSocketConAdmision;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getRol() {
        return this.rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Socket getSocketConMedicos() {
        return this.socketConMedicos;
    }

    public void setSocketConMedicos(Socket socketConMedicos) {
        this.socketConMedicos = socketConMedicos;
    }

    public Socket getSocketConAuxiliares() {
        return this.socketConAuxiliares;
    }

    public void setSocketConAuxiliares(Socket socketConAuxiliares) {
        this.socketConAuxiliares = socketConAuxiliares;
    }

    public Socket getSocketConExamenes() {
        return this.socketConExamenes;
    }

    public void setSocketConExamenes(Socket socketConExamenes) {
        this.socketConExamenes = socketConExamenes;
    }

    public Socket getSocketConPabellon() {
        return this.socketConPabellon;
    }

    public void setSocketConPabellon(Socket socketConPabellon) {
        this.socketConPabellon = socketConPabellon;
    }

}
