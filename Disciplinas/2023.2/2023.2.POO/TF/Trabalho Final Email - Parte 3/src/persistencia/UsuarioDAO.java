package persistencia;

import dados.*;
import execoes.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private static UsuarioDAO instance = null;

    private PreparedStatement selectNewId;
    private PreparedStatement select;
    private PreparedStatement selectAll;
    private PreparedStatement insert;
    private PreparedStatement delete;
    private PreparedStatement update;

    public static UsuarioDAO getInstance() throws ClassNotFoundException, SQLException, SelectException {
        if (instance == null) {
            instance = new UsuarioDAO();
        }

        return instance;
    }

    private UsuarioDAO() throws ClassNotFoundException, SQLException, SelectException{
        Connection con = Conexao.getCon();

        selectNewId = con.prepareStatement("select nextval('\"idUsuarioSequencia\"')");
        insert = con.prepareStatement("insert into \"Usuario\" values(?, ?, ?, ?)");
        select = con.prepareStatement("select * from \"Usuario\" where \"idUsuario\" = ?");
        selectAll = con.prepareStatement("select * from \"Usuario\"");
        update = con.prepareStatement("update \"Usuario\" set \"email\" = ?, \"senha\" = ?, \"nome\" = ? where \"idUsuario\"=?");
        delete = con.prepareStatement("delete from \"Usuario\" where \"idUsuario\"=?");
    }

    private int selectNewId() throws SelectException {
        try {
            ResultSet rs = selectNewId.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
            throw new SelectException("Erro: Pegar novo ID Usuario, selectNewId()");
        }

        return 0;
    }
    public void insert(Usuario u) throws InsertException, SelectException {
        try{
            insert.setInt(1, selectNewId());
            insert.setString(2, u.getEmail());
            insert.setBytes(3, u.getSenha());
            insert.setString(4, u.getNome());
            insert.executeUpdate();
        } catch(SQLException e){
            System.out.println(e);
            throw new InsertException("Erro: Inserir usuario, insert(Usuario u)");
        }
    }
    public Usuario select(int idEscolha) throws SelectException {
        try{
            select.setInt(1, idEscolha);
            ResultSet rs = select.executeQuery();
            if (rs.next()){
                String email = rs.getString(1);
                byte[] senha = rs.getBytes(2);
                String nome = rs.getString(3);

                return new Usuario(idEscolha, email, senha, nome);
            }

        }catch(SQLException e){
            System.out.println(e);
            throw new SelectException("Erro: Buscar usuario, select(int idEscolha)");
        }

        return null;
    }
    public List<Usuario> selectAll() throws SelectException {
        List<Usuario> usuarios = new ArrayList<>();

        try{
            ResultSet rs = selectAll.executeQuery();
            while (rs.next()) {
                int idUsuario = rs.getInt(1);
                String email = rs.getString(2);
                byte[] senha = rs.getBytes(3);
                String nome = rs.getString(4);

                usuarios.add(new Usuario(idUsuario, email, senha, nome));
            }
            return usuarios;

        }catch(SQLException e){
            System.out.println(e);
            throw new SelectException("Erro: Selecionar todos usuarios, selectAll()");
        }
    }
    public void update(Usuario u) throws UpdateException {
        try{
            update.setString(1, u.getEmail());
            update.setBytes(2, u.getSenha());
            update.setString(3, u.getNome());
            update.setInt(4, u.getIdUsuario());

            update.executeUpdate();

        }catch(SQLException e){
            System.out.println(e);
            throw new UpdateException("Erro: Atualizar usuario, update(Usuario u)");
        }
    }
    public void delete(Usuario u) throws DeleteException {
        try{
            delete.setInt(1, u.getIdUsuario());

            delete.executeUpdate();

        }catch(SQLException e){
            System.out.println(e);
            throw new DeleteException("Erro: Deletar usuario, delete(Usuario u)");
        }
    }
}
