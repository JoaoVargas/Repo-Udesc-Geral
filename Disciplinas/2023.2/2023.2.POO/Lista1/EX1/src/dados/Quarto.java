package dados;

public class Quarto {
    private int bloco;
    private int numero;
    private int camasSolteiro;
    private int camasCasal;
    private boolean luxo;

    public int getBloco() {
        return bloco;
    }

    public void setBloco(int bloco) {
        this.bloco = bloco;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCamasSolteiro() {
        return camasSolteiro;
    }

    public void setCamasSolteiro(int camasSolteiro) {
        this.camasSolteiro = camasSolteiro;
    }

    public int getCamasCasal() {
        return camasCasal;
    }

    public void setCamasCasal(int camasCasal) {
        this.camasCasal = camasCasal;
    }

    public boolean isLuxo() {
        return luxo;
    }

    public void setLuxo(boolean luxo) {
        this.luxo = luxo;
    }

    public String toString(){
        String q = "";

        q += "Bloco" + this.bloco + " ";
        q += "n" + this.numero + " - ";
        q += this.camasSolteiro + "S / ";
        q += this.camasCasal + "C - ";
        q += this.luxo ? "Luxo" : "Standart";

        return q;
    }
}
