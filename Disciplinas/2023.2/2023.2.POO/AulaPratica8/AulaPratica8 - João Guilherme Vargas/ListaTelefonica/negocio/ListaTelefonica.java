package negocio;

import persistencia.ContatoDAO;
import dados.Contato;

import java.util.List;
import java.util.Map;


public class ListaTelefonica {
    private ContatoDAO cDAO;

    public ListaTelefonica() {
        this.cDAO = new ContatoDAO();;
    }

    public void adicionaContato(Contato c) {
        cDAO.insert(c);
    }

    public void removeContato(Contato c) {
        cDAO.delete(c);
    }

    public List<Contato> buscarContatos(char letra) {
        return cDAO.getAll().get(letra);
    }

    public Map<Character, List<Contato>> listarContatos() {
        return cDAO.getAll();
    }
}
