package gerador;

public class Perfeitos extends Gerador{
    public Perfeitos() {
        super();
    }

    @Override
    public void gerar(int escolha) {
        int aux = 1;
        for (int i = 0; i < escolha; i++) {
            while (true){
                if (verPerfeito(aux)){
                    addSequencia(aux);
                    aux++;
                    break;
                }

                aux++;
            }
        }
    }

    private boolean verPerfeito(int p){
        int aux = 0;

        for (int i = 1; i < p; i++) {
            if (p % i == 0){
                aux += i;
            }
        }

        return aux == p;
    }
}
