public class Fatoriais extends Gerador{
    public Fatoriais() {
        super();
    }

    @Override
    public void gerar(int escolha) {
        int aux = 1;

        for (int i = 1; i < escolha; i++) {
            for (int j = i; j > 0 ; j--) {
                aux *= j;
            }
            addSequencia(aux);
            aux = 1;
        }
    }
}
