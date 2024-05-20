package negocio;

import dados.*;

public class Sistema {
    private ContaBancaria[] listaContasBancaria = new ContaBancaria[100];
    private int numeroContasBancarias = 0;

    public void cadastrarConta(ContaBancaria conta){
        if (numeroContasBancarias < 100){
            this.listaContasBancaria[numeroContasBancarias] = conta;
            numeroContasBancarias ++;
        }
    }

    public String realizarSaque(ContaBancaria conta, float valor){
        conta.sacar(valor);
        return this.obterExtrato(conta);
    }

    public boolean realizarDeposito(ContaCorrente conta, float valor){
        return conta.depositar(valor);
    }

    public boolean realizarDeposito(ContaSalario conta, float valor, int cnpj){
        return conta.depositar(valor, cnpj);
    }

    public ContaCorrente[] getContasCorrente(){
        int numeroContasCorrentes = 0;

        for (int i = 0; i < numeroContasBancarias; i++) {
            if (listaContasBancaria[i] instanceof ContaCorrente){
                numeroContasCorrentes ++;
            }
        }

        ContaCorrente[] listaContasCorrentes = new ContaCorrente[numeroContasCorrentes];
        int contadorContasCorrentes = 0;

        for (int i = 0; i < numeroContasBancarias; i++) {
            if (listaContasBancaria[i] instanceof ContaCorrente){
                listaContasCorrentes[contadorContasCorrentes] = (ContaCorrente) (listaContasBancaria[i]);
                contadorContasCorrentes ++;
            }
        }

        return listaContasCorrentes;
    }

    public ContaSalario[] getContasSalario(){
        int numeroContasSalario = 0;

        for (int i = 0; i < numeroContasBancarias; i++) {
            if (listaContasBancaria[i] instanceof ContaSalario){
                numeroContasSalario ++;
            }
        }

        ContaSalario[] listaContasSalario = new ContaSalario[numeroContasSalario];
        int contadorContasSalario = 0;

        for (int i = 0; i < numeroContasBancarias; i++) {
            if (listaContasBancaria[i] instanceof ContaSalario){
                listaContasSalario[contadorContasSalario] = (ContaSalario) (listaContasBancaria[i]);
                contadorContasSalario ++;
            }
        }

        return listaContasSalario;
    }

    public ContaBancaria[] getContasBancaria(){
        return listaContasBancaria;
    }

    public int getNumeroContasBancarias() {
        return numeroContasBancarias;
    }

    public String obterExtrato(ContaBancaria conta){
        return conta.gerarExtrato();
    }

}
