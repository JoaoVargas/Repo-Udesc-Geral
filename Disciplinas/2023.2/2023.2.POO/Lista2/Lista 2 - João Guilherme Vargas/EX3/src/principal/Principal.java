package principal;

import dados.GenericMatrix;
import java.util.Scanner;
import java.util.List;



public class Principal {
    private static final Scanner s = new Scanner(System.in);
    private static GenericMatrix<Integer> matrix = new GenericMatrix<Integer>(Integer.class, 5, 5);

    public static void main(String[] args) {
        System.out.println("Digite os itens da matrix 5x5:");

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println("[%s][%s]: ".formatted(i,j));
                matrix.set(Integer.parseInt(s.nextLine()), i, j);
            }
        }

        int maiorValorQuadrantes = -999999;
        int valorQuadrante = 0;
        for (GenericMatrix.Quadrante a: GenericMatrix.Quadrante.values()) {
            GenericMatrix.Quadrante.valueOf(a.name());
            valorQuadrante = maiorDoQuadrante(matrix.getElementosQuadrante(a));

            System.out.println("Maior valor quadrante %s é %s:".formatted(a, valorQuadrante));

            if (valorQuadrante > maiorValorQuadrantes){
                maiorValorQuadrantes = maiorDoQuadrante(matrix.getElementosQuadrante(GenericMatrix.Quadrante.valueOf(a.name())));
            }
        }

        System.out.println("O maior valor da matriz é %s".formatted(maiorValorQuadrantes));


    }

    public static int maiorDoQuadrante(List<Integer> lista){
        int aux = -99999;
        for (Integer i :
                lista) {
            if (i > aux){
                aux = i;
            }
        }
        return aux;
    }
}
