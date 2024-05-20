import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) {
        Gerador gerador = new Perfeitos();
        gerador.gerar(5);
        mostrarLista(gerador.getSequencia());
    }

    public static void mostrarLista(ArrayList<Integer> lista){
        for (int i : lista) {
            System.out.print("%d\n".formatted(i));
        }
    }
}
