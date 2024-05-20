package gerador;

public class Fibonacci extends Gerador {
    public Fibonacci() {
        super();
    }

    @Override
    public void gerar(int escolha) {
        if (escolha == 1){
            addSequencia(0);
            return;
        }

        if (escolha == 2){
            addSequencia(0);
            addSequencia(1);
            return;
        }


        addSequencia(0);
        addSequencia(1);

        for (int i = 0; i < escolha; i++) {
            addSequencia(getSequencia().get(getSequencia().size() - 1) + getSequencia().get(getSequencia().size() - 2));
        }
    }
}
