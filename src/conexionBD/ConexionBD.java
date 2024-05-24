package conexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {


    private static Connection con;
    private static   String url = "jdbc:mysql://localhost:3306/tiendamusica";
    private static  String user = "root";
    private static  String password = "";



    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection(url, user, password);


    }

    public static void closeConnection(){
        if (con != null) {
            try {
                con.close();
                System.out.println("¡Conexion cerrada!");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
    }
}
