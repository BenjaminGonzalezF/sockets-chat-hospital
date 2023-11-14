package base_de_datos;

import java.sql.Connection;

public class EjecutoraBD {
    public static void main(String[] args) {
        Connection con = ConexionBD.establecerConexion();
        if (con != null) {
            CreacionBD.verificarYCrearBaseDeDatos(con);
            GestionBD.insertarCliente(con, "correo5@example.com", "contrasena2", "Nombre Cliente 2", "Rol Cliente");
            GestionBD.mostrarClientes(con);
            ConexionBD.cerrarConexion(con);
        }
    }
}
