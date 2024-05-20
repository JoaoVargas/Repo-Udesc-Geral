package exceptions;

public class ErroArquivoGravacaoException extends ErroArquivoException {
    public ErroArquivoGravacaoException(){
        super("Erro: Gravacao do arquivo");
    }
}
