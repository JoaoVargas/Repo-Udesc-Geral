package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    static private Connection connection = null;

    static public Connection getConnection() {
        if (connection == null) {
            String url = "jdbc:postgresql://localhost:5432/catalogo";
            String username = "postgres";
            String password = "230505";
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException c) {
                System.err.println("Driver não encontrado");
            } catch (SQLException e) {
                System.err.println("Não foi possível conectar");
            }
        }
        return connection;
    }
}