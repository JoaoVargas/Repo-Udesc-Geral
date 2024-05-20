package dados;

public class Professor extends Pessoa{
    private String[] materias = new String[3];

    public Professor(String nome, Integer numero, String[] materias) {
        super(nome, numero);
        this.materias = materias;
    }

    public String[] getMaterias() {
        return this.materias;
    }
    public void setMaterias(String[] materias) {
        this.materias = materias;
    }
}