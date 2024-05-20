package apresentacao;

import dados.Matriz;

import java.util.List;

public class principal {

    public static void main(String[] args) {
        int x = 3;
        int y = 3;

        Matriz<Integer> integerMatriz = new Matriz<Integer>(Integer.class, x, y);

        integerMatriz.set(1,0,0);
        integerMatriz.set(2,0,1);
        integerMatriz.set(3,0,2);
        integerMatriz.set(4,1,0);
        integerMatriz.set(5,1,1);
        integerMatriz.set(6,1,2);
        integerMatriz.set(7,2,0);
        integerMatriz.set(8,2,1);
        integerMatriz.set(9,2,2);

        List<Integer> a = integerMatriz.getElementosQuadrante(Matriz.Quadrante.QUARTO);

        for (int i = 0; i < 1; i++) {
            System.out.println(a.get(i));
        }
    }
}

