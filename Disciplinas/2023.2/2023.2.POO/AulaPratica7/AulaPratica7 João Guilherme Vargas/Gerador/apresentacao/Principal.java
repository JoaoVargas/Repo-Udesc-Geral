package apresentacao;

import gerador.*;

public class Principal {
    public static void main(String[] args) {
//        Gerador g = new Abundantes();
        Gerador g = new Fatoriais();
//        Gerador g = new Fibonacci();
//        Gerador g = new Naturais();
//        Gerador g = new Perfeitos();
//        Gerador g = new Primos();
//        Gerador g = new Quadrados();

        g.gerar(5);

        for (int i = 0; i < g.getSequencia().size(); i++) {
            System.out.print("%s ".formatted(g.getSequencia().get(i)));
        }
        System.out.println();

        System.out.println("""
                Sortear: %s
                Somatorio: %s
                Media Aritimétrica: %s
                Media Geométrica: %s
                Variância: %s
                Desvio Padrão: %s
                Amplitude: %s""".formatted(
                                    g.sortear(),
                                    g.somatorio(),
                                    g.mediaAritmetica(),
                                    g.mediaGeometrica(),
                                    g.variancia(),
                                    g.desvioPadrao(),
                                    g.amplitude()
                                    )
        );
    }
}
