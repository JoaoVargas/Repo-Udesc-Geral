import FormasGeometricas.*;
import java.util.Random;


public class Principal {
    public static void main(String[] args) {
        Random r = new Random();

        TrinaguloEquilatero trinagulo1 = new TrinaguloEquilatero();
        trinagulo1.setLado(r.nextInt(10));
        TrinaguloEquilatero trinagulo2 = new TrinaguloEquilatero();
        trinagulo2.setLado(r.nextInt(10));

        Losango losango1 = new Losango();
        losango1.setD(r.nextInt(10));
        losango1.setd(r.nextInt(10));
        Losango losango2 = new Losango();
        losango2.setD(r.nextInt(10));
        losango2.setd(r.nextInt(10));

        Circulo circulo1 = new Circulo();
        circulo1.setRaio(r.nextInt(10));
        Circulo circulo2 = new Circulo();
        circulo2.setRaio(r.nextInt(10));

        FormaGeometrica[] lista = {trinagulo1, trinagulo2, losango1, losango2, circulo1, circulo2};

        for (FormaGeometrica f : lista) {
            System.out.println(f.toString());
            System.out.println();
        }
    }
}
