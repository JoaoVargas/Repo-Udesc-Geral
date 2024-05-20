public class Bicicleta extends Veiculo{
    private int numeroMarchas;

    public int getNumeroMarchas() {
        return numeroMarchas;
    }
    public void setNumeroMarchas(int numeroMarchas) {
        this.numeroMarchas = numeroMarchas;
    }

    public String info(){
        return """
                Bicicleta:
                    Cor: %s
                    Nº de portas: %s""".formatted(this.getCor(), this.numeroMarchas);
    }
}
