package dados;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.LinkedList;

public class Usuario {
    private int idUsuario;
    private String email;
    private byte[] senha;
    private String nome;

    private LinkedList<Email> emailsRecebidos;
    private LinkedList<Email> emailsEnviados;

    private final MessageDigest digest;
    {
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setSenha(String senha){
        this.senha = digest.digest(senha.getBytes(StandardCharsets.UTF_8));
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public LinkedList<Email> getEmailsRecebidos() {
        return emailsRecebidos;
    }
    public LinkedList<Email> getEmailsEnviados() {
        return emailsEnviados;
    }

    public Usuario() {
        this.emailsRecebidos = new LinkedList<Email>();
        this.emailsEnviados = new LinkedList<Email>();
    }

    @Override
    public String toString() {
        return "[%s] %s (%s)".formatted(this.idUsuario, this.nome, this.email);
    }

    public boolean emailRecebido(Email e){
        try {
            this.emailsRecebidos.add(e);
            return true;
        } catch (Exception err){
            return false;
        }
    }
    public boolean emailEnviado(Email e){
        try {
            this.emailsEnviados.add(e);
            return true;
        } catch (Exception err){
            return false;
        }
    }

    public String listarEmailsRecebidos() {
        String emails = "";

        for (Email email : emailsRecebidos) {
            emails += email.toStringResumido() + "\n";
        }

        return emails;
    }
    public String listarEmailsEnviados() {
        String emails = "";

        for (Email email : emailsEnviados) {
            emails += email.toStringResumido() + "\n";
        }

        return emails;
    }

    public boolean verificarSenha(String s){
        byte[] crypt = digest.digest(s.getBytes(StandardCharsets.UTF_8));

        return Arrays.equals(this.senha, crypt);
    }
}
