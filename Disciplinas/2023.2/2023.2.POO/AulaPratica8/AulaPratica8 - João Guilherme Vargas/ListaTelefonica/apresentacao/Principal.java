package apresentacao;

import negocio.ListaTelefonica;
import dados.Contato;

import java.util.Scanner;

public class Principal {
    private static Scanner s = new Scanner(System.in);
    private static ListaTelefonica sys = new ListaTelefonica();

    public static void main(String[] args) {
        int op = -1;

        while (op != 0) {
            System.out.println("""
                    Escolha uma opção:
                    0 - Sair
                    1 - Mostrar os contatos
                    2 - Cadastrar um contato
                    3 - Remover um contato""");

            op = Integer.parseInt(s.nextLine());

            switch (op) {
                case 0 -> System.out.println("Adeus");
                case 1 -> exibirContatos();
                case 2 -> adicionarContato();
                case 3 -> removerContato();
                default -> System.out.println("Error: Input invalido");
            }
        }
    }

    public static void exibirContatos() {
        System.out.println("""
                1 - Todos os Contatos
                2 - Contatos por Inicial""");

        int op = Integer.parseInt(s.nextLine());

        switch (op) {
            case 1 -> exibirContatosTodos();
            case 2 -> exibirContatosInicial();
            default -> System.out.println("Error: Input invalido");
        }
    }

    private static void exibirContatosTodos() {
        sys.listarContatos().forEach((chave, lista) -> {
            System.out.println(chave + ":");

            for (Contato contato : lista) {
                System.out.println("    " + contato.toString());
            }
        });
    }

    private static void exibirContatosInicial() {
        char letra;

        System.out.println("Digite a inicial:");
        letra = s.nextLine().toUpperCase().toCharArray()[0];

        for (int i = 0; i < sys.buscarContatos(letra).size(); i++) {
            System.out.println("[" + i + "]" + " " + sys.buscarContatos(letra).get(i).toString());
        }
    }

    public static void adicionarContato() {
        Contato c = new Contato();

        System.out.println("Digite o nome do contato:");
        c.setNome(s.nextLine());

        System.out.println("Digite o telefone do contato:");
        c.setTelefone(Integer.parseInt(s.nextLine()));

        sys.adicionaContato(c);
    }

    public static void removerContato(){
        System.out.println("Escolha uma letra que deseja remover:");

        char inicial = s.nextLine().toUpperCase().toCharArray()[0];

        if (sys.buscarContatos(inicial).isEmpty()) {
            System.out.println("Não existem contatos para serem removidos");
            return;
        }

        for (int i = 0; i < sys.buscarContatos(inicial).size(); i++) {
            System.out.println("[" + i + "]" + " " + sys.buscarContatos(inicial).get(i).toString());
        }

        System.out.println("Escolha um contato para remover:");
        int index = Integer.parseInt(s.nextLine());

        if (index < sys.buscarContatos(inicial).size()) {
            sys.removeContato(sys.buscarContatos(inicial).get(index));
        }
    }
}
