package Animal;

public class Porco extends Animal{

    public Porco(String nome) {
        super(nome);
    }

    @Override
    public String emitirSom() {
        return "Oink oink oink";
    }
}
