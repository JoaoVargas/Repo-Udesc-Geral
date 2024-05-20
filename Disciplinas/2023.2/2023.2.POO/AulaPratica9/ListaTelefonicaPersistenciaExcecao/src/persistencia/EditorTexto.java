package persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.List;

import exceptions.ErroArquivoGravacaoException;
import exceptions.ErroArquivoLeituraException;

public class EditorTexto {
    public void gravaTexto(String caminho, List<String> dados) throws ErroArquivoGravacaoException {
        FileWriter arq;

        try {
            arq = new FileWriter(caminho);
            for (String dado : dados) {
                arq.write(dado + "\n");
            }

            arq.close();
        } catch (Exception e) {
            ErroArquivoGravacaoException erro = new ErroArquivoGravacaoException();
            erro.setCaminho(caminho);
            throw erro;
        }
    }

    public List<String> leTexto(String caminho) throws ErroArquivoLeituraException {
        List<String> dados = new LinkedList<String>();

        FileReader arq;
        BufferedReader lerArq;

        try {
            arq = new FileReader(caminho);
            lerArq = new BufferedReader(arq);
            String s = lerArq.readLine();

            while (s != null) {
                dados.add(s);
                s = lerArq.readLine();
            }

            arq.close();
        } catch (Exception e) {
            ErroArquivoLeituraException erro = new ErroArquivoLeituraException();
            erro.setCaminho(caminho);
            throw erro;
        }

        return dados;
    }
}