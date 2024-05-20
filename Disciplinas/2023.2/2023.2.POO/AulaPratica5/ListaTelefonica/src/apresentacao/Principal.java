package apresentacao;


import dados.Contato;
import negocio.Sistema;
import java.util.Scanner;

public class Principal {
    private static Scanner s = new Scanner(System.in);
    private static Sistema sys = new Sistema();

    public static void main(String[] args) {
        int escolha = -1;

        while (escolha != 0){
            imprimeMenu();
            escolha = tryParseInt(s.nextLine());

            switch (escolha){
                default -> System.out.println("Escolha uma opção válida");
                case 1 -> exibirContatos();
                case 2 -> adicionarContato();
                case 3 -> removerContato();
            }
        }

    }


    public static void imprimeMenu(){
        System.out.println("""
                ---Menu---
                0 - Sair
                1 - Mostrar contatos
                2 - Adicionar contato
                3 - Remover contato""");
    }

    public static int tryParseInt(String entrada) {
        try {
            return Integer.parseInt(entrada);
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }

    public static void exibirContatos(){
        int escolha = -1;

        while (escolha != 0){
            imprimeMenuExibirContatos();
            escolha = tryParseInt(s.nextLine());

            switch (escolha) {
                default -> System.out.println("Escolha uma opção válida");
                case 1 -> exibirContatosTodos();
                case 2 -> exibirContatosInicial();
            }
        }
    }

    public static void imprimeMenuExibirContatos(){
        System.out.println("""
                0 - Cancelar
                1 - Exibir todos os contatos
                2 - Exibir contatos por inicial""");
    }

    public static void exibirContatosTodos(){
        sys.buscarContatos().forEach((inicial, lista) -> {
            System.out.println(inicial + ": ");

            for (Contato c : lista) {
                System.out.println("     " + c.toString());
            }
        });
    }

    public static void exibirContatosInicial(){
        System.out.println("Digite a inicial: ");
        char inicial = Character.toUpperCase(s.nextLine().toCharArray()[0]);

        System.out.println(inicial + ": ");
        for (Contato c : sys.buscarContatos(inicial)) {
            System.out.println("     " + c.toString());
        }
    }

    public static void adicionarContato() {
        sys.adicionarContato(criarContato());
    }

    public static Contato criarContato(){
        Contato aux = new Contato();

        System.out.println("Nome do contato:");
        aux.setNome(s.nextLine());

        System.out.println("Telefone do contato:");
        aux.setTelefone(tryParseInt(s.nextLine()));

        while (aux.getTelefone() == -1){
            System.out.println("Digite um numero válido");
            aux.setTelefone(tryParseInt(s.nextLine()));
        }

        return aux;
    }

    public static void removerContato() {
        System.out.println("Digite a inicial: ");
        char inicial = Character.toUpperCase(s.nextLine().toCharArray()[0]);

        if (sys.buscarContatos(inicial).isEmpty()) {
            System.out.println("Não existem contatos com essa inicial");
            return;
        }

        int i = 0;
        System.out.println(inicial + ": ");
        for (Contato c : sys.buscarContatos(inicial)) {
            System.out.println("     [%d] - ".formatted(i) + c.toString());
            i++;
        }

        System.out.println("Digite o código do contato para remover: ");
        int indexRem = tryParseInt(s.nextLine());

        while (indexRem == -1) {
            System.out.println("Digite um numero válido");
            indexRem = tryParseInt(s.nextLine());
        }

        if (indexRem < sys.buscarContatos(inicial).size()) {
            sys.removerContato(sys.buscarContatos(inicial).get(indexRem));
        }
    }
}
