package negocio;

import dados.*;
import execoes.*;
import persistencia.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Sistema {
    private EmailDAO emailDAO;
    private UsuarioDAO usuarioDAO;

    private int numUsuarios;
    private int numEmails;
    private Usuario usuarioLogado;
    private List<Usuario> listaUsuarios;
    private List<Email> listaEmails;

    public Sistema(String senha) throws SQLException, SelectException, ClassNotFoundException {
        Conexao.setSenha(senha);
        emailDAO = EmailDAO.getInstance();
        usuarioDAO = UsuarioDAO.getInstance();

        this.numUsuarios = 0;
        this.numEmails = 0;
        this.usuarioLogado = null;
        this.listaUsuarios = new LinkedList<>();
        this.listaEmails = new LinkedList<>();

//        atualizarEmails();
        atualizarUsuarios();
    }

    public void inserirUsuarioDAO(Usuario u) throws InsertException, SelectException{
        usuarioDAO.insert(u);
    }
    public Usuario selectUsuarioDAO(int id) throws SelectException{
        return usuarioDAO.select(id);
    }
    public List<Usuario> selectALLUsuarioDAO() throws SelectException{
        return usuarioDAO.selectAll();
    }
    public void atualizaUsuarioDAO(Usuario u) throws UpdateException{
        usuarioDAO.update(u);
    }
    public void deleteUsuarioDAO(Usuario u) throws DeleteException{
        usuarioDAO.delete(u);
    }
    public void inserirEmailDAO(Email e) throws InsertException, SelectException{
        emailDAO.insert(e);
    }
    public Email selectEmailDAO(int id) throws SelectException{
        return emailDAO.select(id);
    }
    public List<Email> selectALLEmailDAO() throws SelectException{
        return emailDAO.selectAll();
    }
    public void atualizaEmailDAO(Email e) throws UpdateException{
        emailDAO.update(e);
    }
    public void deleteEmailDAO(Email e) throws DeleteException{
        emailDAO.delete(e);
    }

    public boolean getBoolUsuarioLogado(){
        return usuarioLogado != null;
    }
    public String getEmailUsuarioLogado(){
        return usuarioLogado.getEmail();
    }

    public boolean cadastrarUsuario(Usuario u){
        if (numUsuarios != 0){
            for (Usuario usuario : this.listaUsuarios) {
                if (Objects.equals(u.getEmail(), usuario.getEmail())){
                    return false;
                }
            }
        }

        try {
            inserirUsuarioDAO(u);
            atualizarUsuarios();
            this.numUsuarios++;
            return true;
        } catch (Exception err){
            return false;
        }
    }
    public boolean logarUsuario(String email, String senha){
//        if (numUsuarios == 0) {
//            return false;
//        }

        for (Usuario u : this.listaUsuarios) {
            if (Objects.equals(email, u.getEmail())){
                if (u.verificarSenha(senha)) {
                    this.usuarioLogado = u;
                    atualizarEmails();
                    this.usuarioLogado.setEmailsEnviados(listarEmailsRemetente(this.usuarioLogado.getEmail()));
                    this.usuarioLogado.setEmailsRecebidos(listarEmailsDestinatario(this.usuarioLogado.getEmail()));
                    return true;
                } else {
                    return false;
                }
            }
        }

        return false;
    }
    public boolean deslogarUsuario(){
        try {
            atualizarEmails();
            this.usuarioLogado = null;
            return true;
        } catch (Exception err){
            return false;
        }
    }

    public List<Email> listarEmailsRemetente(String email){
        List<Email> emails = new ArrayList<>();
        for (Email e : this.listaEmails) {
            if (Objects.equals(e.getRemetente(), email)){
                emails.add(e);
            }
        }

        return emails;
    }
    public List<Email> listarEmailsDestinatario(String email){
        List<Email> emails = new ArrayList<>();
        for (Email e : this.listaEmails) {
            if (Objects.equals(e.getDestinatario(), email)){
                emails.add(e);
            }
        }

        return emails;
    }
    public List<Email> listarEmailsEnviados(){
        return this.usuarioLogado.getEmailsEnviados();
    }
    public List<Email> listarEmailsRecebidos(){
        return this.usuarioLogado.getEmailsRecebidos();
    }
    public List<String> listarEmailsEnviadosLinha(){
        List<String> emailsLinha = new ArrayList<>();

        if (this.usuarioLogado == null) {
            return emailsLinha;
        }

        for (Email e : this.usuarioLogado.getEmailsEnviados()) {
            emailsLinha.add(e.toStringLinha());
        }

        return emailsLinha;
    }
    public List<String> listarEmailsRecebidosLinha(){
        List<String> emailsLinha = new ArrayList<>();

        if (this.usuarioLogado == null) {
            return emailsLinha;
        }


        for (Email e : this.usuarioLogado.getEmailsRecebidos()) {
            emailsLinha.add(e.toStringLinha());
        }

        return emailsLinha;
    }

    public boolean enviarEmail(Email email){
        if (this.usuarioLogado == null) {
            return false;
        }

        try {
            inserirEmailDAO(email);
            atualizarEmails();
        } catch (Exception err){
            System.out.println(err);
            return false;
        }

        return true;
    }

    public void atualizarUsuarios() throws SelectException {
        this.listaUsuarios = selectALLUsuarioDAO();
    }
    public void atualizarEmails() {
        try {

            this.listaEmails = selectALLEmailDAO();
            this.usuarioLogado.setEmailsEnviados(listarEmailsRemetente(this.usuarioLogado.getEmail()));
            this.usuarioLogado.setEmailsRecebidos(listarEmailsDestinatario(this.usuarioLogado.getEmail()));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean excluirEmail(Email email){
        if (numEmails == 0 || this.usuarioLogado == null){
            return false;
        }

        try {
            deleteEmailDAO(email);
            atualizarEmails();
        } catch (Exception err){
            return false;
        }

        return true;
    }
    public boolean responderEmail(Email resposta, Email respondido){
        if (numEmails == 0){
            return false;
        }

        try {
            resposta.setEmailAnterior(respondido);
            inserirEmailDAO(resposta);
            atualizarEmails();
        } catch (Exception err){
            return false;
        }

        return true;
    }
}
