package dados;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Usuario {
    private int idUsuario;
    private String email;
    private byte[] senha;
    private String nome;

    private List<Email> emailsRecebidos;
    private List<Email> emailsEnviados;

    private final MessageDigest digest;
    {
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getEmail() {
        return email;
    }
    public byte[] getSenha() {
        return senha;
    }
    public String getNome() {
        return nome;
    }
    public List<Email> getEmailsRecebidos() {
        return this.emailsRecebidos;
    }
    public List<Email> getEmailsEnviados() {
        return this.emailsEnviados;
    }
    public void setEmailsEnviados(List<Email> emailsEnviados) {
        this.emailsEnviados = emailsEnviados;
    }
    public void setEmailsRecebidos(List<Email> emailsRecebidos) {
        this.emailsRecebidos = emailsRecebidos;
    }
    public ArrayList<Integer> getIdsEmailsRecebidos() {
        ArrayList<Integer> emails = new ArrayList<>();

        for (Email email : emailsRecebidos) {
            emails.add(email.getIdEmail());
        }

        return emails;
    }
    public ArrayList<Integer> getIdsEmailsEnviados() {
        ArrayList<Integer> emails = new ArrayList<>();

        for (Email email : emailsEnviados) {
            emails.add(email.getIdEmail());
        }

        return emails;
    }

    public Usuario(int idUsuario, String email, String senha, String nome) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.senha = digest.digest(senha.getBytes(StandardCharsets.UTF_8));
        this.nome = nome;
        this.emailsRecebidos = new LinkedList<Email>();
        this.emailsEnviados = new LinkedList<Email>();
    }
    public Usuario(int idUsuario, String email, byte[] senha, String nome) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.emailsRecebidos = new LinkedList<Email>();
        this.emailsEnviados = new LinkedList<Email>();
    }

    public String toStringLinha() {
        return "[%s] %s (%s)".formatted(
                this.idUsuario,
                this.nome,
                this.email);
    }

    public boolean addEmailRecebido(Email e){
        try {
            this.emailsRecebidos.add(e);
            return true;
        } catch (Exception err){
            return false;
        }
    }
    public boolean addEmailEnviado(Email e){
        try {
            this.emailsEnviados.add(e);
            return true;
        } catch (Exception err){
            return false;
        }
    }

    public List<String> listaLinhaEmailsRecebidos() {
        List<String> emails = new ArrayList<>();

        for (Email email : emailsRecebidos) {
            emails.add(email.toStringLinha());
        }

        return emails;
    }
    public List<String> listaLinhaEmailsEnviados() {
        List<String> emails = new ArrayList<>();

        for (Email email : emailsEnviados) {
            emails.add(email.toStringLinha());
        }

        return emails;
    }

    public boolean verificarSenha(String s){
        byte[] crypt = digest.digest(s.getBytes(StandardCharsets.UTF_8));

        return Arrays.equals(this.senha, crypt);
    }
}
