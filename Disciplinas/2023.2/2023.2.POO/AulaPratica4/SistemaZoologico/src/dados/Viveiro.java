package dados;

public class Viveiro {
    protected String nome;
    protected float comprimento;
    protected float largura;
    protected Animal[] animais;
    protected int numeroAnimais;

    public Viveiro() {
        this.animais = new Animal[1000];
        this.numeroAnimais = 0;
    }
    public Viveiro(String nome, float comprimento, float largura) {
        this.nome = nome;
        this.comprimento = comprimento;
        this.largura = largura;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public float getComprimento() {
        return comprimento;
    }
    public void setComprimento(float comprimento) {
        this.comprimento = comprimento;
    }
    public float getLargura() {
        return largura;
    }
    public void setLargura(float largura) {
        this.largura = largura;
    }

    public float calculaEspaco(){
        return this.comprimento * this.largura;
    }

    public float espacoOcupado(){
        float espacoOcupadoAnimais = 0;

        if (numeroAnimais > 0){
            for (int i = 0; i < numeroAnimais; i++) {
                espacoOcupadoAnimais += animais[i].calculaEspacoOcupado();
            }
        }

        return espacoOcupadoAnimais;
    }

    public float espacoDisponivel(){
        return calculaEspaco()-espacoOcupado();
    }

    public boolean adicionarAnimal(Animal animal){
        if (espacoOcupado() + animal.calculaEspacoOcupado() <= 0.7 * calculaEspaco()){
            animais[numeroAnimais] = animal;
            numeroAnimais ++;
            return true;
        } else {
            return false;
        }
    }

    public String toString(){
        String viveiroString = "";

        viveiroString += "Viveiro %s (%s x %s) (%sm2 livres): \n".formatted(this.nome, this.comprimento, this.largura, espacoDisponivel());

        viveiroString += "Locacao atual [%s]\n".formatted(this.numeroAnimais);

        if (this.numeroAnimais > 0) {
            viveiroString += "Animais:\n";
            for (int i = 0; i < this.numeroAnimais; i++) {
                viveiroString += "%s\n".formatted(animais[i].toString());
            }
        } else {
            viveiroString += "(Vazio)\n";
        }

        return viveiroString;
    }
}
