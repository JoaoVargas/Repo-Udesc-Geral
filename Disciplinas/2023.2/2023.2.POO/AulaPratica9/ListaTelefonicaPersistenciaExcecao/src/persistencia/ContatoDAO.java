package persistencia;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import dados.Contato;
import exceptions.ErroContatoDuplicadoException;
import exceptions.ErroContatoNaoCadastradoException;
import exceptions.ErroArquivoGravacaoException;
import exceptions.ErroArquivoLeituraException;

public class ContatoDAO {
    private ArquivoContatoDAO arquivoContatoDAO = new ArquivoContatoDAO();

    public void insert(Contato contato)
            throws ErroContatoDuplicadoException, ErroArquivoLeituraException, ErroArquivoGravacaoException {
        List<Contato> contatos = arquivoContatoDAO.lerContatos();

        if (!contatos.contains(contato)) {
            contatos.add(contato);
            arquivoContatoDAO.salvarContatos(contatos);
        } else {
            throw new ErroContatoDuplicadoException();
        }
    }

    public void delete(Contato contato)
            throws ErroContatoNaoCadastradoException, ErroArquivoLeituraException, ErroArquivoGravacaoException {
        List<Contato> contatos = arquivoContatoDAO.lerContatos();

        if (contatos.contains(contato)) {
            contatos.remove(contato);
            arquivoContatoDAO.salvarContatos(contatos);
        } else {
            throw new ErroContatoNaoCadastradoException();
        }
    }

    public Map<Character, List<Contato>> getAll() throws ErroArquivoLeituraException {
        Map<Character, List<Contato>> contatos = new HashMap<Character, List<Contato>>();

        for (char i = 65; i < 91; i++) {
            List<Contato> lista = new LinkedList<Contato>();
            contatos.put(i, lista);
        }

        for (Contato contato : arquivoContatoDAO.lerContatos()) {
            char inicial = contato.getNome().toUpperCase().charAt(0);
            contatos.get(inicial).add(contato);
        }

        return contatos;
    }
}