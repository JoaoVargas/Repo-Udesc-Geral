import java.util.Scanner;

public class Principal {
    private static final Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        int escolha = -1;
        String texto;

        while (escolha != 0){
            System.out.println("Digite a String:");
            texto = s.nextLine();

            printarMenu();
            escolha = tryParseInt(s.nextLine());

            switch (escolha){
                default -> System.out.println("Digite uma opção válida.");
                case 0 -> {
                    return;
                }
                case 1 -> removerVogais(texto);
                case 2 -> removerConsoantes(texto);
            }
        }
    }

    private static int tryParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }

    private static void printarMenu() {
        System.out.println("""
                Escolha:
                0 - Sair
                1 - Remover Vogais
                2 - Remover Consoantes""");
    }

    private static void removerVogais(String s){
        System.out.println(ModificadorDeString.removerVogais(s));
    }

    private static void removerConsoantes(String s){
        System.out.println(ModificadorDeString.removerConsoantes(s));
    }
}
