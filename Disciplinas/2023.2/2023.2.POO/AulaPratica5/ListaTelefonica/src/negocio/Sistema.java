package negocio;

import dados.Contato;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.List;

public class Sistema {
    private HashMap<Character, LinkedList<Contato>> mapLista = new HashMap<>();

    public Sistema(){
        for (int i = 'A'; i <= 'Z'; i++) {
            LinkedList<Contato> listaInicial = new LinkedList<>();
            mapLista.put((char) i, listaInicial);
        }
    }

    public void adicionarContato(Contato c) {
        char inicial = Character.toUpperCase(c.getNome().toCharArray()[0]);
        mapLista.get(inicial).add(c);
    }

    public void removerContato(Contato c) {
        char inicial = Character.toUpperCase(c.getNome().toCharArray()[0]);
        mapLista.get(inicial).remove(c);
    }

    public LinkedList<Contato> buscarContatos(char inicial){
        return mapLista.get(Character.toUpperCase(inicial));
    }

    public HashMap<Character, LinkedList<Contato>> buscarContatos(){
        return mapLista;
    }


}
