package Animal;

public abstract class Animal {
    String nome;

    public Animal(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public abstract String emitirSom();
}
