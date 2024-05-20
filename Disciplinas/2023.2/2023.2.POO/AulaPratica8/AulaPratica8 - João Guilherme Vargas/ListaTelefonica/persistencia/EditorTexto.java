package persistencia;

import dados.Contato;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.List;

public class EditorTexto {
    public List<String> leTexto(String path){
        List<String> listaContatos = new LinkedList<String>();

        FileReader out;
        BufferedReader lerArq;

        try {
            out = new FileReader(path);
            lerArq = new BufferedReader(out);
            String s = lerArq.readLine();

            while (s != null) {
                listaContatos.add(s);
                s = lerArq.readLine();
            }

            out.close();
        } catch (Exception e) {
            System.err.println("Error: Abrir o arquivo, ler");
            System.exit(0);
        }

        return listaContatos;
    }

    public void gravaTexto(String path, String linha){
        FileWriter out;

        try {
            out = new FileWriter(path, true);
            out.write(linha);

            out.close();
        } catch (Exception e) {

            System.err.println("Error: Abrir o arquivo, gravar");
            System.exit(0);
        }
    }

    public void gravaTexto(String path, List<String> contatos){
        FileWriter arq;

        try {
            arq = new FileWriter(path);

            for (int i = 0; i < contatos.size(); i++) {
                arq.write(contatos.get(i) + "\n");
            }
            arq.close();

        } catch (Exception e) {
            System.err.println("Error: Abrir o arquivo, gravar");
            System.exit(0);
        }
    }
}
