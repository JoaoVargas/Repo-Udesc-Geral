import java.util.Scanner;

public class App {

    private static Scanner s = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        String[][] informacoes = new String[3][2];

        for (int i = 0; i < informacoes.length; i++) {

            System.out.println("Digite o nome da pessoa " + i + " : ");
            informacoes[i][0] = s.nextLine();
            System.out.println("Digite a idade da pessoa " + i + " : ");
            informacoes[i][1] = s.nextLine();
        }

        for (int i = 0; i < informacoes.length; i++) {
            System.out.println("A pessoa " + informacoes[i][0] + " possui " + informacoes[i][1] + " ano(s)");
        }

    }
}
