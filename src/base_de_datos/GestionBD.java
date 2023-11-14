package base_de_datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestionBD {

    public static void insertarCliente(Connection con, String correo, String contraseña, String nombreCompleto, String rol) {
        String query = "INSERT INTO Cliente (correo, contraseña, nombre_completo, rol) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, correo);
            stmt.setString(2, contraseña);
            stmt.setString(3, nombreCompleto);
            stmt.setString(4, rol);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar cliente: " + e.getMessage());
        }
    }

    public static void mostrarClientes(Connection con) {
        String query = "SELECT * FROM Cliente";
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                String correo = rs.getString("correo");
                String contraseña = rs.getString("contraseña");
                String nombreCompleto = rs.getString("nombre_completo");
                String rol = rs.getString("rol");
                System.out.format("%s, %s, %s, %s\n", correo, contraseña, nombreCompleto, rol);
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar clientes: " + e.getMessage());
        }
    }

    public static void insertarSala(Connection con, String nombreSala, String mensajesSala, String emisor) {
        String query = "INSERT INTO Sala (nombreSala, mensajesSala, Emisor) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, nombreSala);
            stmt.setString(2, mensajesSala);
            stmt.setString(3, emisor);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar sala: " + e.getMessage());
        }
    }
    
    public static void mostrarSalas(Connection con) {
        String query = "SELECT * FROM Sala";
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                String nombreSala = rs.getString("nombreSala");
                String mensajesSala = rs.getString("mensajesSala");
                String emisor = rs.getString("Emisor");
                System.out.format("%s, %s, %s\n", nombreSala, mensajesSala, emisor);
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar salas: " + e.getMessage());
        }
    }

    public static void insertarSalaPrivada(Connection con, String nombreSala, String mensajesSala, String emisor, String receptor) {
        String query = "INSERT INTO SalaPrivada (nombreSala, mensajesSala, Emisor, Receptor) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, nombreSala);
            stmt.setString(2, mensajesSala);
            stmt.setString(3, emisor);
            stmt.setString(4, receptor);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al insertar sala privada: " + e.getMessage());
        }
    }

    public static void mostrarSalasPrivadas(Connection con) {
        String query = "SELECT * FROM SalaPrivada";
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                String nombreSala = rs.getString("nombreSala");
                String mensajesSala = rs.getString("mensajesSala");
                String emisor = rs.getString("Emisor");
                String receptor = rs.getString("Receptor");
                System.out.format("%s, %s, %s, %s\n", nombreSala, mensajesSala, emisor, receptor);
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar salas privadas: " + e.getMessage());
        }
    }
}
