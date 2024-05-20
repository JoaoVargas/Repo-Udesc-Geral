package negocio;

import java.util.List;

import dados.Contato;
import exceptions.ErroContatoDuplicadoException;
import exceptions.ErroContatoNaoCadastradoException;
import exceptions.ErroArquivoGravacaoException;
import exceptions.ErroArquivoLeituraException;
import persistencia.ContatoDAO;

import java.util.Map;

public class ListaTelefonica {
    private ContatoDAO contatoDAO = new ContatoDAO();

    public void adicionaContato(Contato contato)
            throws ErroContatoDuplicadoException, ErroArquivoLeituraException, ErroArquivoGravacaoException {
        contatoDAO.insert(contato);
    }

    public void removeContato(Contato contato)
            throws ErroContatoNaoCadastradoException, ErroArquivoLeituraException, ErroArquivoGravacaoException {
        contatoDAO.delete(contato);
    }

    public List<Contato> buscarContatos(char letra) throws ErroArquivoLeituraException {
        return contatoDAO.getAll().get(letra);
    }

    public Map<Character, List<Contato>> buscarContatos() throws ErroArquivoLeituraException {
        return contatoDAO.getAll();
    }
}