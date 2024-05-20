package dados;

public class Aquario  extends Viveiro{
    private float altura;
    private float temperatura;

    public Aquario() {
    }
    public Aquario(String nome, float comprimento, float largura, float altura, float temperatura) {
        super(nome, comprimento, largura);
        this.altura = altura;
        this.temperatura = temperatura;
    }

    public float getAltura() {
        return altura;
    }
    public void setAltura(float altura) {
        this.altura = altura;
    }
    public float getTemperatura() {
        return temperatura;
    }
    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public float calculaEspaco() {
        return super.comprimento * super.largura * this.altura;
    }

    public boolean adicionarAnimal(Animal animal){
        if (!(animal instanceof Peixe)) {
            return false;
        }

        if (espacoOcupado() + animal.calculaEspacoOcupado() <= 0.7 * calculaEspaco()) {
            Peixe peixeAnimal = (Peixe) animal;

            if ((peixeAnimal.getTemperaturaIdeal() < temperatura - 3) || (peixeAnimal.getTemperaturaIdeal() > temperatura + 3)){
                return false;
            }

            animais[numeroAnimais] = animal;
            numeroAnimais++;
            return true;
        }

        return false;
    }

    public String toString(){
        String aquarioString = "";

        aquarioString += "Aquario %s (%s x %s x %s) (%sm3 livres): \n".formatted(this.nome, this.comprimento, this.largura, this.altura, espacoDisponivel());

        aquarioString += "Locacao atual [%s]\n".formatted(this.numeroAnimais);

        if (this.numeroAnimais > 0) {
            for (int i = 0; i < this.numeroAnimais; i++) {
                aquarioString += "%s\n".formatted(animais[i].toString());
            }
        } else {
            aquarioString += "(Vazio)\n";
        }

        return aquarioString;
    }
}
