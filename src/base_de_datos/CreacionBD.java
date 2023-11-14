package base_de_datos;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreacionBD {

    private static final String DATABASE_NAME = "proyectosd";

    public static void verificarYCrearBaseDeDatos(Connection con) {
        try (Statement stmt = con.createStatement()) {
            stmt.executeQuery("USE " + DATABASE_NAME);
        } catch (SQLException e) {
            try (Statement stmt = con.createStatement()) {
                stmt.executeUpdate("CREATE DATABASE " + DATABASE_NAME);
                con.setCatalog(DATABASE_NAME); 
                crearTablas(con);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void crearTablas(Connection con) {
        String sqlCliente = "CREATE TABLE IF NOT EXISTS Cliente ("
                          + "correo VARCHAR(191) PRIMARY KEY,"
                          + "contraseña VARCHAR(255) NOT NULL,"
                          + "nombre_completo VARCHAR(255) NOT NULL,"
                          + "rol VARCHAR(50) NOT NULL"
                          + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;";

        String sqlSala = "CREATE TABLE IF NOT EXISTS Sala ("
                       + "nombreSala VARCHAR(191) PRIMARY KEY,"
                       + "mensajesSala TEXT,"
                       + "Emisor VARCHAR(191),"
                       + "FOREIGN KEY (Emisor) REFERENCES Cliente(correo)"
                       + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;";

        String sqlSalaPrivada = "CREATE TABLE IF NOT EXISTS SalaPrivada ("
                              + "nombreSala VARCHAR(191) PRIMARY KEY,"
                              + "mensajesSala TEXT,"
                              + "Emisor VARCHAR(191),"
                              + "Receptor VARCHAR(191),"
                              + "FOREIGN KEY (Emisor) REFERENCES Cliente(correo),"
                              + "FOREIGN KEY (Receptor) REFERENCES Cliente(correo)"
                              + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;";
        
        try (Statement stmt = con.createStatement()) {
            stmt.execute(sqlCliente);
            stmt.execute(sqlSala);
            stmt.execute(sqlSalaPrivada);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}

