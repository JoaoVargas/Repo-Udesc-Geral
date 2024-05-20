import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        int fim = -1;
        Fabrica fabrica = new Fabrica();
        Scanner s = new Scanner(System.in);

        do {
            System.out.println(fabrica.fabricar().info());
            System.out.println("0 - Cancela");
            fim = s.nextInt();
        } while (fim != 0);
    }
}
