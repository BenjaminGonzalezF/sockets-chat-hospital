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
    public static void actualizarCliente(Connection con, String correo, String nuevaContraseña, String nuevoNombreCompleto, String nuevoRol) {
        String query = "UPDATE Cliente SET contraseña = ?, nombre_completo = ?, rol = ? WHERE correo = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, nuevaContraseña);
            stmt.setString(2, nuevoNombreCompleto);
            stmt.setString(3, nuevoRol);
            stmt.setString(4, correo);
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Cliente actualizado con éxito.");
            } else {
                System.out.println("No se encontró el cliente con correo: " + correo);
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: " + e.getMessage());
        }
    }
    public static void eliminarCliente(Connection con, String correo) {
        String query = "DELETE FROM Cliente WHERE correo = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, correo);
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Cliente eliminado con éxito.");
            } else {
                System.out.println("No se encontró el cliente con correo: " + correo);
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar cliente: " + e.getMessage());
        }
    }
    public static void buscarCliente(Connection con, String correo) {
        String query = "SELECT * FROM Cliente WHERE correo = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Cliente encontrado: ");
                System.out.format("%s, %s, %s, %s\n", rs.getString("correo"), rs.getString("contraseña"), rs.getString("nombre_completo"), rs.getString("rol"));
            } else {
                System.out.println("No se encontró el cliente con correo: " + correo);
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar cliente: " + e.getMessage());
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
    public static void obtenerMensajesSala(Connection con, String nombreSala) {
        String query = "SELECT mensajesSala FROM Sala WHERE nombreSala = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, nombreSala);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String mensajes = rs.getString("mensajesSala");
                System.out.println("Mensajes en Sala " + nombreSala + ": " + mensajes);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener mensajes de la sala: " + e.getMessage());
        }
    }
    
    public static void obtenerMensajesSalaPrivada(Connection con, String nombreSalaPrivada) {
        String query = "SELECT mensajesSala FROM SalaPrivada WHERE nombreSala = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, nombreSalaPrivada);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String mensajes = rs.getString("mensajesSala");
                System.out.println("Mensajes en Sala Privada " + nombreSalaPrivada + ": " + mensajes);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener mensajes de la sala privada: " + e.getMessage());
        }
    }

    public static void enviarMensajeSala(Connection con, String nombreSala, String mensaje) {
        String query = "UPDATE Sala SET mensajesSala = CONCAT(mensajesSala, ?) WHERE nombreSala = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, mensaje);
            stmt.setString(2, nombreSala);
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Mensaje enviado a la sala: " + nombreSala);
            } else {
                System.out.println("No se encontró la sala: " + nombreSala);
            }
        } catch (SQLException e) {
            System.out.println("Error al enviar mensaje a la sala: " + e.getMessage());
        }
    }
    public static void enviarMensajeSalaPrivada(Connection con, String nombreSalaPrivada, String mensaje) {
        String query = "UPDATE SalaPrivada SET mensajesSala = CONCAT(mensajesSala, ?) WHERE nombreSala = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, mensaje);
            stmt.setString(2, nombreSalaPrivada);
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Mensaje enviado a la sala privada: " + nombreSalaPrivada);
            } else {
                System.out.println("No se encontró la sala privada: " + nombreSalaPrivada);
            }
        } catch (SQLException e) {
            System.out.println("Error al enviar mensaje a la sala privada: " + e.getMessage());
        }
    }
   


}
