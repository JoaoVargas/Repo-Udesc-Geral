package gerador;

import java.util.ArrayList;
import java.util.Random;

public abstract class Gerador implements ISequencia {
    private ArrayList<Integer> sequencia;

    public Gerador() {
        sequencia = new ArrayList<>();
    }

    public ArrayList<Integer> getSequencia() {
        return sequencia;
    }
    public void addSequencia(int i){
        this.sequencia.add(i);
    }
    public void limparSequencia(){
        this.sequencia.clear();
    }

    public abstract void gerar(int escolha);

    //////////////////////////////////////////////////

    @Override
    public int sortear() {
        Random r = new Random();
        return sequencia.get(r.nextInt(sequencia.size()));
    }

    @Override
    public long somatorio() {
        int aux = 0;
        for (int i = 0; i < sequencia.size(); i++) {
            aux += sequencia.get(i);
        }
        return aux;
    }

    @Override
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

    @Override
    public double mediaGeometrica() {;
        return Math.pow(produtorio(), ((double) 1) / ((double) (sequencia.size())));
    }

    @Override
    public double variancia() {
        double mediaA = mediaAritmetica();
        double aux = 0;

        for (int i = 0; i < sequencia.size(); i++) {
            aux += Math.pow(sequencia.get(i) - mediaA, 2);
        }

        return aux / ((double) (sequencia.size() - 1));
    }

    @Override
    public double desvioPadrao() {
        return Math.sqrt(variancia());
    }

    @Override
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
