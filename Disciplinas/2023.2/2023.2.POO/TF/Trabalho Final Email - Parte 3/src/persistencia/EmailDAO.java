package persistencia;

import dados.*;
import execoes.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmailDAO {
    private static EmailDAO instance = null;

    private PreparedStatement selectNewId;
    private PreparedStatement select;
    private PreparedStatement selectAll;
    private PreparedStatement insert;
    private PreparedStatement delete;
    private PreparedStatement update;

    public static EmailDAO getInstance() throws ClassNotFoundException, SQLException, SelectException {
        if (instance == null) {
            instance = new EmailDAO();
        }

        return instance;
    }

    private EmailDAO() throws ClassNotFoundException, SQLException, SelectException{
        Connection con = Conexao.getCon();
        selectNewId = con.prepareStatement("select nextval('\"idUsuarioSequencia\"')");
        insert = con.prepareStatement("insert into \"Email\" values(?, ?, ?, ?, ?, ?, ?)");
        select = con.prepareStatement("select * from \"Email\" where \"idEmail\" = ?");
        selectAll = con.prepareStatement("select * from \"Email\"");
        update = con.prepareStatement("update \"Email\" set \"assunto\" = ?, \"mensagem\" = ? where \"idEmail\"=?");
        delete = con.prepareStatement("delete from \"Email\" where \"idEmail\"=?");
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
    public void insert(Email email) throws InsertException, SelectException {
        try{
            insert.setInt(1, selectNewId());
            insert.setString(2, email.getRemetente());
            insert.setString(3, email.getDestinatario());
            insert.setString(4, email.getAssunto());
            insert.setString(5, email.getMensagem());
            insert.setTimestamp(6, email.formatarDataTimeTimestamp(email.getDataHora()));
            insert.setInt(7, email.getEmailAnterior() == null ? 0 : email.getEmailAnterior().getIdEmail());
            insert.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
            throw new InsertException("Erro: Inserir email, insert(Email email)");
        }
    }
    public Email select(int idEscolha) throws SelectException {
        try{
            select.setInt(1, idEscolha);
            ResultSet rs = select.executeQuery();
            if (rs.next()){
                int idEmail = rs.getInt(1);
                String remetente = rs.getString(2);
                String destinatario = rs.getString(3);
                String assunto = rs.getString(4);
                String mensagem = rs.getString(5);
                Timestamp dataHora = rs.getTimestamp(6);
                Email emailAnterior = select(rs.getInt(7));

                return new Email(idEmail, remetente, destinatario, assunto, mensagem, dataHora, emailAnterior);
            }

        }catch(SQLException e){
            System.out.println(e);
            throw new SelectException("Erro: Buscar email, select(int idEscolha)");
        }

        return null;
    }
    public List<Email> selectAll() throws SelectException {
        List<Email> emails = new ArrayList<>();

        try{
            ResultSet rs = selectAll.executeQuery();
            while (rs.next()) {
                int idEmail = rs.getInt(1);
                String remetente = rs.getString(2);
                String destinatario = rs.getString(3);
                String assunto = rs.getString(4);
                String mensagem = rs.getString(5);
                Timestamp dataHora = rs.getTimestamp(6);
                Email emailAnterior = select(rs.getInt(7));

                emails.add(new Email(idEmail, remetente, destinatario, assunto, mensagem, dataHora, emailAnterior));
            }

        }catch(SQLException e){
            System.out.println(e);
            throw new SelectException("Erro: Selecionar todos usuarios, selectAll()");
        }

        return emails;
    }
    public void update(Email email) throws UpdateException {
        try{
            update.setString(1, email.getAssunto());
            update.setString(2, email.getMensagem());
            update.setInt(3, email.getIdEmail());

            update.executeUpdate();

        }catch(SQLException e){
            System.out.println(e);
            throw new UpdateException("Erro: Atualizar email, update(Email email)");
        }
    }
    public void delete(Email email) throws DeleteException {
        try{
            delete.setInt(1, email.getIdEmail());

            delete.executeUpdate();

        }catch(SQLException e){
            System.out.println(e);
            throw new DeleteException("Erro: Deletar email, delete(Email email)");
        }
    }
}
