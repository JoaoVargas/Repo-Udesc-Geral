public class Primos extends Gerador{
    public Primos() {
        super();
    }

    @Override
    public void gerar(int escolha) {
        int aux = 2;
        for (int i = 0; i < escolha; i++) {
            while (true){
                if (verPrimo(aux)){
                    addSequencia(aux);
                    aux++;
                    break;
                }

                aux++;
            }
        }

    }

    public boolean verPrimo(int p){
        for (int i = 2; i < p; i++) {
            if (p % i == 0){
                return false;
            }
        }

        return true;
    }
}
