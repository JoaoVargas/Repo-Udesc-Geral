package dados;

public class Peixe extends Animal{
    private float temperaturaIdeal;

    public float getTemperaturaIdeal() {
        return temperaturaIdeal;
    }
    public void setTemperaturaIdeal(float temperaturaIdeal) {
        this.temperaturaIdeal = temperaturaIdeal;
    }

    public float calculaEspacoOcupado() {
        return largura * comprimento * altura;
    }

    public String toString() {
        String peixeString = "";

        peixeString += super.toString();
        peixeString += " Temperatura: %s;".formatted(temperaturaIdeal);

        return peixeString;
    }
}
