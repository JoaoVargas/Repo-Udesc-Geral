package gerador;

public class Quadrados extends Gerador {
    public Quadrados() {
        super();
    }

    @Override
    public void gerar(int escolha) {
        for (int i = 1; i < escolha; i++) {
            addSequencia(i * i);
        }
    }
}
