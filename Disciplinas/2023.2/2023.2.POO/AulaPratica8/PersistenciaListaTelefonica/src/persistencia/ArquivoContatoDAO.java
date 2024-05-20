package persistencia;

import dados.Contato;

import java.util.LinkedList;
import java.util.List;

public class ArquivoContatoDAO {
    private final String path = "src/files/contatos.csv";
    private EditorTexto arq = new EditorTexto();

    private String toCSV(Contato contato) {
        return contato.getNome() + "," + contato.getTelefone() + "\n";
    }

    private String toCSVnonBreak(Contato contato) {
        return contato.getNome() + "," + contato.getTelefone();
    }

    private Contato fromCSV(String linha) {
        String[] atributos = linha.split(",");

        Contato c = new Contato();
        c.setNome(atributos[0]);
        c.setTelefone(Integer.parseInt(atributos[1]));

        return c;
    }

    public List<Contato> lerContatos() {
        return csvToList(arq.leTexto(path));
    }

    private List<Contato> csvToList(List<String> arquivo) {
        List<Contato> contatos = new LinkedList<Contato>();

        for (String linha : arquivo) {
            try {
                contatos.add(fromCSV(tratarLinha(linha)));
            } catch (Exception e){
                continue;
            }
        }

        return contatos;
    }

    private String tratarLinha(String linha){
        return linha.replace("\n", "");
    }

    private List<String> toCSVMult(List<Contato> contatos) {
        List<String> arquivo = new LinkedList<String>();

        for (Contato c : contatos) {
            arquivo.add(toCSVnonBreak(c));
        }

        return arquivo;
    }

    public void salvarContatos(List<Contato> contatos) {
        arq.gravaTexto(path, toCSVMult(contatos));
    }

    public void salvarContato(Contato contato) {
        arq.gravaTexto(path, toCSV(contato));
    }
}
