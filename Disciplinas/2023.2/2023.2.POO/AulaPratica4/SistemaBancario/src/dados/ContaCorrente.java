package dados;

public class ContaCorrente extends ContaBancaria {
    public ContaCorrente() {
        super();
    }

    public ContaCorrente(int cpf, float saldo) {
        super(cpf, saldo);
    }

    public boolean depositar(float valor){
        setSaldo(getSaldo() + valor);
        return true;
    }

    public String gerarExtrato(){
        return ("""
                Conta Corrente:
                CPF: %s
                Extrato: %s""").formatted(this.getCpf(), super.gerarExtrato());
    }

    public String toString(){
        return ("""
                Conta Corrente:
                %s""").formatted(super.toString());
    }
}
