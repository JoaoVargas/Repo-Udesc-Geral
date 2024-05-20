package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static Connection con = null;
    private static String senha;

    private Conexao(){}

    public static void setSenha(String senha) {
        Conexao.senha = senha;
    }
    public static Connection getCon() throws ClassNotFoundException, SQLException {
        if (con == null){
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/SistemaEmailBD";
            String usuario = "postgres";

            con = DriverManager.getConnection(url, usuario, senha);
        }

        return con;
    }
}
