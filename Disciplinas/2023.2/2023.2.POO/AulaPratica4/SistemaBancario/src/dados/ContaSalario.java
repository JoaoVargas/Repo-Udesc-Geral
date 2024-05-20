package dados;

public class ContaSalario extends ContaBancaria{
    private int cnpjEmpresa;

    public ContaSalario() {
        super();
    }

//    public ContaSalario(int cpf, float saldo, int cnpjEmpresa) {
//        super(cpf, saldo);
//        this.cnpjEmpresa = cnpjEmpresa;
//    }
//
//    public int getCnpjEmpresa() {
//        return cnpjEmpresa;
//    }
//
//    public void setCnpjEmpresa(int cnpjEmpresa) {
//        this.cnpjEmpresa = cnpjEmpresa;
//    }

    public boolean depositar(float valor, int cnpjEmpresa){
        if (cnpjEmpresa == this.cnpjEmpresa){
            setSaldo(getSaldo() + valor);
            return true;
        }
        return false;
    }

    public  String gerarExtrato(){
        return ("""
                Conta Salario:
                CNPJ da empresa: %s
                Extrato: %s""").formatted(this.cnpjEmpresa, super.gerarExtrato());
    }

    public  String toString(){
        return ("""
                Conta Salario:
                CNPJ da empresa: %s
                %s""").formatted(this.cnpjEmpresa, super.toString());
    }
}
