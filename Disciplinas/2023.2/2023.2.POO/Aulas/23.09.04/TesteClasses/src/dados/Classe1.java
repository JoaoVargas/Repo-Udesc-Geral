package dados;

public class Classe1 {
    private int num;
    private Classe2 teste;

    public Classe1() {
    }
    public Classe1(int num, Classe2 teste) {
        this.num = num;
        this.teste = teste;
    }

    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }
    public Classe2 getTeste() {
        return teste;
    }
    public void setTeste(Classe2 teste) {
        this.teste = teste;
    }
}
