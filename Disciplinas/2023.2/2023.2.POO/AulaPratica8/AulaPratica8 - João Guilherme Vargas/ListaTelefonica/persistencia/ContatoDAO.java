package persistencia;

import dados.Contato;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ContatoDAO {
    private ArquivoContatoDAO acDAO = new ArquivoContatoDAO();

    public void insert(Contato c) {
        acDAO.salvarContato(c);
    }

    public void delete(Contato c) {
        List<Contato> contatos = acDAO.lerContatos();
        contatos.remove(c);
        acDAO.salvarContatos(contatos);
    }

    public Map<Character, List<Contato>> getAll() {
        Map<Character, List<Contato>> hashContatos = new HashMap<Character, List<Contato>>();

        for (char i = 65; i < 91; i++) {
            List<Contato> listaContatosInicial = new LinkedList<Contato>();
            hashContatos.put(i, listaContatosInicial);
        }

        for (Contato contato : acDAO.lerContatos()) {
            char inicial = contato.getNome().toUpperCase().charAt(0);
            hashContatos.get(inicial).add(contato);

        }

        return hashContatos;
    }

}
