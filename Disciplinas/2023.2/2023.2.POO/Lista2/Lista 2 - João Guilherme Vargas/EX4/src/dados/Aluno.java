package dados;

public class Aluno {
    private String nome;
    private int idade;
    private double[] notas;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getIdade() {
        return idade;
    }
    public void setIdade(int idade) {
        this.idade = idade;
    }
    public double[] getNotas() {
        return notas;
    }
    public void setNotas(double[] notas) {
        this.notas = notas;
    }

    public Aluno() {
        notas = new double[5];
    }

    public double calcularMedia(){
        return (this.notas[0] + this.notas[1] + this.notas[2] + this.notas[3] + this.notas[4]) / 5.0;
    }

    public String toString(){
        String aluno = "";

        aluno += "%s - Media %s (%s, %s, %s, %s, %s)".formatted(this.nome, calcularMedia(), this.notas[0], this.notas[1], this.notas[2], this.notas[3], this.notas[4]);

        return aluno;
    }
}
