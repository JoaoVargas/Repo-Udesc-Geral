import static java.lang.Math.sqrt;

public class Abundantes extends Gerador{
    public Abundantes() {
        super();
    }

    @Override
    public void gerar(int escolha) {
        for (int i = 0; i < escolha; i++) {
            if (soma(i) > i){
                addSequencia(i);
            }
        }
    }

    private int soma(int n){
        int soma = 0;

        for (int i = 1; i < sqrt(n); i++) {
            if (n % i == 0){
                if (n / i == i){
                    soma += i;
                } else {
                    soma += i;
                    soma += (n / i);
                }
            }
        }

        soma -= n;
        return soma;
    }
}
