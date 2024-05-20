package dados;

public class Pessoa {
    private String nome;
    private int idade;
    private int cpf;
    private String cidade;
    private Pessoa ante;
    private Pessoa prox;

    public Pessoa() {
        this.prox = null;
        this.ante = null;
    }
    public Pessoa(Pessoa prox) {
        this.prox = prox;
    }

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
    public int getCpf() {
        return cpf;
    }
    public void setCpf(int cpf) {
        this.cpf = cpf;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public Pessoa getProx() {
        return prox;
    }
    public void setProx(Pessoa prox) {
        this.prox = prox;
    }
    public Pessoa getAnte() {
        return ante;
    }
    public void setAnte(Pessoa ante) {
        this.ante = ante;
    }

    public String toString(){
        return "  - %s, %s anos, CPF: %s, %s".formatted(this.nome, this.idade, this.cpf, this.cidade);
    }
}
