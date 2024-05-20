public class Gerente extends Funcionario{
    private String departamento;
    private String nomeSecretaria;

    public Gerente(){
    }

    public Gerente(String departamento, String nomeSecretaria, String nome, String cpf, double salario) {
        super(nome, cpf, salario);
        this.departamento = departamento;
        this.nomeSecretaria = nomeSecretaria;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public double getBonificacao(){
        return super.getBonificacao() + 1000;
    }
}
