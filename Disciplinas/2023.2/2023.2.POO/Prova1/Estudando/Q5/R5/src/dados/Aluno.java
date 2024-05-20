package dados;

public class Aluno extends Pessoa{
    private String curso;


    public Aluno(String nome, Integer numero, String curso) {
        super(nome, numero);
        this.curso = curso;
    }

    public String getCurso() {
        return this.curso;
    }
    public void setCurso(String curso) {
        this.curso = curso;
    }
}
