import java.util.ArrayList;

public abstract class Gerador {
    private ArrayList<Integer> sequencia;

    public Gerador() {
        sequencia = new ArrayList<>();
    }

    public ArrayList<Integer> getSequencia() {
        return sequencia;
    }
    public void setSequencia(ArrayList<Integer> sequencia) {
        this.sequencia = sequencia;
    }

    public void addSequencia(int i){
        this.sequencia.add(i);
    }

    public abstract void gerar(int escolha);
}
