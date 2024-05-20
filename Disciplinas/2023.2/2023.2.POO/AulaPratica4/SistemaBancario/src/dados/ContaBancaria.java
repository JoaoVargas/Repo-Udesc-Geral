package dados;

public class ContaBancaria {
    private int cpf;
    private float saldo;

    public ContaBancaria() {
        this.saldo = 0;
    }

    public ContaBancaria(int cpf, float saldo) {
        this.cpf = cpf;
        this.saldo = saldo;
    }

    public int getCpf() {
        return cpf;
    }

    public void setCpf(int cpf) {
        this.cpf = cpf;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float sacar(float valor){
        this.saldo -= valor;
        return valor;
    }

    public String gerarExtrato(){
        return "Saldo disponível: R$%s".formatted(this.saldo);
    }

    public String toString(){
        return "CPF: %s".formatted(this.cpf);
    }
}
