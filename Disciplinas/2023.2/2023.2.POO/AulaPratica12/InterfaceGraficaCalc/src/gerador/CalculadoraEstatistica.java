package gerador;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class CalculadoraEstatistica implements ISequencia{
    private static CalculadoraEstatistica instance = null;
    public static CalculadoraEstatistica getInstance(){
        if (instance == null){
            instance = new CalculadoraEstatistica();
        }

        return instance;
    }

    private CalculadoraEstatistica() {
    }

    private List<Integer> sequencia = new LinkedList<>();

    public List<Integer> getSequencia() {
        return sequencia;
    }
    public void addSequencia(int i){
        this.sequencia.add(i);
    }
    public void limparSequencia(){
        this.sequencia.clear();
    }

    //////////////////////////////////////////////////

    public int sortear() {
        Random r = new Random();
        return sequencia.get(r.nextInt(sequencia.size()));
    }
    public long somatorio() {
        int aux = 0;
        for (int i = 0; i < sequencia.size(); i++) {
            aux += sequencia.get(i);
        }
        return aux;
    }
    public double mediaAritmetica() {
        return ((double) somatorio()) / ((double) sequencia.size());
    }
    private long produtorio(){
        int aux = 1;
        for (int i = 0; i < sequencia.size(); i++) {
            aux *= sequencia.get(i);
        }
        return aux;
    }
    public double mediaGeometrica() {;
        return Math.pow(produtorio(), ((double) 1) / ((double) (sequencia.size())));
    }
    public double variancia() {
        double mediaA = mediaAritmetica();
        double aux = 0;

        for (int i = 0; i < sequencia.size(); i++) {
            aux += Math.pow(sequencia.get(i) - mediaA, 2);
        }

        return aux / ((double) (sequencia.size() - 1));
    }
    public double desvioPadrao() {
        return Math.sqrt(variancia());
    }
    public int amplitude() {
        int aux1 = -2147483646;
        int aux2 = 2147483646;

        for (int i = 0; i < sequencia.size(); i++) {
            if (sequencia.get(i) > aux1){
                aux1 = sequencia.get(i);
            }
            if (sequencia.get(i) < aux2){
                aux2 = sequencia.get(i);
            }
        }
        return aux1 - aux2;
    }
}
