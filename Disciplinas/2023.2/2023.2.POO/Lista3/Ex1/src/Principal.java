import Animal.*;

public class Principal {
    public static void main(String[] args) {
        Cachorro cachorro1 = new Cachorro("Mel");
        Cachorro cachorro2 = new Cachorro("Polo");
        Gato gato1 = new Gato("Pedro Alvares Cabral II");
        Gato gato2 = new Gato("O Fim");
        Porco porco1 = new Porco("Bacon");
        Porco porco2 = new Porco("Guanciale");

        Animal[] lista = {cachorro1, cachorro2, gato1, gato2, porco1, porco2};

        for (Animal a : lista) {
            System.out.println("%s: %s".formatted(a.getNome(), a.emitirSom()));
        }
    }
}
