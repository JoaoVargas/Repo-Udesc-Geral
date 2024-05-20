package persistencia;

import exceptions.InsertException;
import exceptions.SelectException;
import negocio.Pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PessoaDAO {
    private static PessoaDAO instance = null;
    private static EnderecoDAO enderecoDAO = null;

    private PreparedStatement selectNewId ;
    private PreparedStatement insert ;
    private PreparedStatement delete ;
    private PreparedStatement selectAll ;
    private PreparedStatement update ;

    private PessoaDAO() throws ClassNotFoundException, SQLException, SelectException {
        Connection conexao = Conexao.getConexao();

        selectNewId = conexao.prepareStatement("SELECT nextval('id_pessoa')");
        insert = conexao.prepareStatement("INSERT INTO pessoa values(?,?,?,?,?)");
        selectAll = conexao.prepareStatement("SELECT ∗ FROM pessoa WHERE id = ?");
        update = conexao.prepareStatement("UPDATE pessoa SET nome = ?, cpf = ?, telefone = ? WHERE id = ?");
        delete = conexao.prepareStatement("DELETE FROM pessoa WHERE id = ?");
    }

    public static PessoaDAO getInstance() throws ClassNotFoundException, SQLException, SelectException {
        if(instance == null) {
            instance = new PessoaDAO();
        }

        return instance;
    }

    private int selectNewId() throws SelectException {
        try {
            ResultSet rs = selectNewId.executeQuery () ;
            if(rs.next()) {
                return rs.getInt(1) ;
            }
        } catch(SQLException e) {
            throw new SelectException("Erro ao buscar novo id da tabela endereço") ;
        }

        return 0;
    }
    public void insert(Pessoa pessoa) throws InsertException, SelectException{
        try {
            insert.setInt(1, selectNewId());
            insert.setString(2, pessoa.get());
            insert.setInt(3, pessoa.getNumero());
            insert.setString(4, pessoa.getCidade());
            insert.setInt(5 , pessoa.getIdPessoa());
            insert.executeUpdate();
        } catch ( SQLException e ) {
            throw new InsertException("Erro ao inserir endereço");
        }
    }
}
