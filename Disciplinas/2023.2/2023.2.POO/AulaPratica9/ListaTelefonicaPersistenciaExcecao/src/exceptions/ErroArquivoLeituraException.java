package exceptions;

public class ErroArquivoLeituraException extends ErroArquivoException{
    public ErroArquivoLeituraException(){
        super("Erro: Leitura do aquivo");
    }
}
