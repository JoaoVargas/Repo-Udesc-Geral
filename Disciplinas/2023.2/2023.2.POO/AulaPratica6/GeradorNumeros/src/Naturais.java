public class Naturais extends Gerador{

    public Naturais() {
        super();
    }

    @Override
    public void gerar(int escolha) {
        for (int i = 0; i < escolha; i++) {
            addSequencia(i);
        }
    }
}
