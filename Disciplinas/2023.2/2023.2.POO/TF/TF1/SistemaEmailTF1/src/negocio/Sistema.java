package negocio;

import dados.*;

import java.util.LinkedList;
import java.util.Objects;

public class Sistema {
    private int numUsuarios;
    private int numEmails;
    private Usuario usuarioLogado;

    private LinkedList<Usuario> listaUsuarios;

    public boolean getUsuarioLogado(){
        return usuarioLogado != null;
    }

    public Sistema() {
        this.numUsuarios = 0;
        this.numEmails = 0;
        this.usuarioLogado = null;
        this.listaUsuarios = new LinkedList<>();
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
            u.setIdUsuario(this.numUsuarios);
            this.listaUsuarios.add(u);
            this.numUsuarios++;
            return true;
        } catch (Exception err){
            return false;
        }
    }

    public boolean logarUsuario(String email, String senha){
        if (numUsuarios == 0) {
            return false;
        }

        for (Usuario u : listaUsuarios) {
            if (Objects.equals(email, u.getEmail())){
                if (u.verificarSenha(senha)) {
                    this.usuarioLogado = u;
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
            this.usuarioLogado = null;
            return true;
        } catch (Exception err){
            return false;
        }
    }

    public String verEmailsRecebidos() {
        if (this.usuarioLogado == null){
            return "Erro usuario não logado.";
        }

        return usuarioLogado.listarEmailsRecebidos();
    }
    public String verEmailsEnviados() {
        if (this.usuarioLogado == null){
            return "Erro usuario não logado.";
        }

        return usuarioLogado.listarEmailsEnviados();
    }
    public String verEmailDetalhes(int id, int tipo) {
        if (this.usuarioLogado == null){
            return "Erro usuario não logado.";
        }

        if (tipo == 1){
            for (Email email : usuarioLogado.getEmailsEnviados()) {
                if (id == email.getIdEmail()){
                    return email.toString();
                }
            }
        } else if (tipo == 2) {
            for (Email email : usuarioLogado.getEmailsRecebidos()) {
                if (id == email.getIdEmail()){
                    return email.toString();
                }
            }
        }

        return null;
    }

    public boolean enviarEmail(Email email){
        if (this.usuarioLogado == null || this.numUsuarios < 2){
            return false;
        }

        for (Usuario desti : listaUsuarios) {
            if (Objects.equals(email.getDestinatario(), desti.getEmail()) && !Objects.equals(email.getDestinatario(), this.usuarioLogado.getEmail())){
                try {
                    email.setIdEmail(this.numEmails);
                    email.setRemetente(this.usuarioLogado.getEmail());
                    email.cifrar(0);

                    this.usuarioLogado.emailEnviado(email);
                    desti.emailRecebido(email);
                    this.numEmails++;

                    return true;
                } catch (Exception err) {
                    return false;
                }
            }
        }

        return false;
    }

    public boolean excluirEmailRecebido(int id){
        if (numEmails == 0 || this.usuarioLogado == null){
            return false;
        }

        for (Email e : usuarioLogado.getEmailsRecebidos()) {
            if (e.getIdEmail() == id){
                return usuarioLogado.getEmailsRecebidos().remove(e);
            }
        }
        return false;
    }

    public boolean responderEmail(int id, Email resposta){
        if (numEmails == 0 || this.usuarioLogado == null){
            return false;
        }

        for (Email e : this.usuarioLogado.getEmailsRecebidos()) {
            if (e.getIdEmail() == id){
                try {
                    resposta.setEmailAnterior(e);
                    resposta.setDestinatario(e.getRemetente());

                    return enviarEmail(resposta);
                } catch (Exception err) {
                    return false;
                }
            }
        }
        return false;
    }


}
