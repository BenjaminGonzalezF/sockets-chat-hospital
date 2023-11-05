package cliente;

import java.net.Socket;

public class Cliente {
    private String nombre;
    private Socket socket;
    private Socket socketConMedicos;
    private Socket socketConAuxiliares;
    private Socket socketConExamenes;
    private Socket socketConPabellon;
    private String rol;

    public Cliente(String nombre, Socket socket, String rol) {
        this.nombre = nombre;
        this.socket = socket;
        this.rol = rol;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
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
