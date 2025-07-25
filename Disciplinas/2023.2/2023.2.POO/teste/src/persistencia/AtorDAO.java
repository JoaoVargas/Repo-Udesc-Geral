package persistencia;

import dados.Ator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AtorDAO {
    private static AtorDAO instance = null;
    private Connection connection;

    public static AtorDAO getInstance() {
        if (instance == null)
            instance = new AtorDAO();
        return instance;
    }

    private AtorDAO() {
        this.connection = DataBaseConnection.getConnection();
    }

    public int selectNextID() throws SQLException {
        String query = "select nextval('filmeID');";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao achar o próximo ID no banco");
        }
        return 0;
    }

    public Ator carregar(int code) {
        ResultSet resultSet;
        Ator ator = null;
        String query = "select * from Ator WHERE atorID=?";
        try {
            PreparedStatement selectAtor = connection.prepareStatement(query);
            selectAtor.setInt(1, code);
            resultSet = selectAtor.executeQuery();
            if (resultSet.next()) {
                ator = new Ator();
                ator.setId(resultSet.getInt("atorID"));
                ator.setNome(resultSet.getString("nome"));
                ator.setDataNascimento(resultSet.getString("dataNascimento"));
                ator.setSexo((resultSet.getString("sexo")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ator;
    }

    public List<Ator> listar() throws SQLException {
        List<Ator> atores = new ArrayList<>();
        String query = "SELECT * FROM ator";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Ator ator = new Ator();
                ator.setId(resultSet.getInt("AtorID"));
                ator.setNome(resultSet.getString("nome"));
                ator.setSexo(resultSet.getString("sexo"));
                ator.setDataNascimento(resultSet.getString("dataNascimento"));
                atores.add(ator);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar Ator");
        }
        return atores;
    }

    public int inserir(Ator ator) throws SQLException {
        String query = "INSERT INTO ator(atorID, nome, dataNascimento, sexo) VALUES(?,?,?,?)";
        int id = this.selectNextID();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, ator.getNome());
            preparedStatement.setString(3, ator.getDataNascimento());
            preparedStatement.setString(4, ator.getSexo());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao inserir Ator no Banco de Dados");
        }
        return id;
    }

    public void alterar(Ator ator) throws SQLException {
        String query = "UPDATE ator SET nome=?, dataNascimento=?, sexo=? WHERE atorID=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, ator.getNome());
            preparedStatement.setString(2, ator.getDataNascimento());
            preparedStatement.setString(3, ator.getSexo());
            preparedStatement.setInt(4, ator.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar o Ator no Banco de Dados");
        }
    }

    public void remover(Ator ator) throws SQLException {
        String query = "DELETE FROM ator WHERE atorID=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, ator.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar o Ator do Banco de Dados");
        }
    }
}