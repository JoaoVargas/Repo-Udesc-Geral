package exceptions;

public class ErroContatoNaoCadastradoException extends Exception {
    public ErroContatoNaoCadastradoException(){
        super("Erro: Contato nao cadastrado");
    }
}
