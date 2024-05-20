package exceptions;

public class ErroContatoDuplicadoException extends Exception {
    public ErroContatoDuplicadoException(){
        super("Erro: Contato ja cadastrado");
    }
}
