package apresentacao;

import java.util.Scanner;

import dados.*;
import negocio.*;

public class Principal {
    private static Sistema sys = new Sistema();
    private static Scanner s = new Scanner(System.in);

    private static ContaCorrente novaContaCorrente(){
        ContaCorrente contaCorrente = new ContaCorrente();

        System.out.println("Digite o CPF: ");
        contaCorrente.setCpf(s.nextInt());
        s.nextLine();

        return contaCorrente;
    }

    private static ContaSalario novaContaSalario(){
        ContaSalario contaSalario = new ContaSalario();

        System.out.println("Digite o CPF: ");
        contaSalario.setCpf(s.nextInt());
        s.nextLine();

        System.out.println("Digite o CNPJ da empresa: ");
        contaSalario.setCpf(s.nextInt());
        s.nextLine();

        return contaSalario;
    }

    private static void cadastrarConta(){
        System.out.println("""
                Digite o tipo de conta que deseja cadastrar:
                1 - Conta Corrente
                2 - Conta Salario""");

        int escolha = s.nextInt();
        s.nextLine();

        switch (escolha) {
            case 1 -> sys.cadastrarConta(novaContaCorrente());
            case 2 -> sys.cadastrarConta(novaContaSalario());
            default -> System.out.println("Escolha inválida");
        }

    }

    private static void exibirContas(){
        for (int i = 0; i < sys.getNumeroContasBancarias(); i++) {
            System.out.println("""
                    Conta [%s]:
                    %s
                    """.formatted(i, sys.getContasBancaria()[i].toString()));
        }
    }

    private static ContaBancaria escolherContaBancaria(){
        exibirContas();

        System.out.println("Escolha uma conta:");

        int contaEscolhida = s.nextInt();
        s.nextLine();

        if (contaEscolhida < sys.getNumeroContasBancarias()){
            return sys.getContasBancaria()[contaEscolhida];
        }

        return null;
    }

    private static void realizarSaque(){
        ContaBancaria contaBancaria = escolherContaBancaria();

        if (contaBancaria != null) {
            System.out.println("Digite o valor a ser sacado:");
            int valorEscolhido = s.nextInt();
            s.nextLine();

            System.out.println(sys.realizarSaque(contaBancaria, valorEscolhido));
        }
    }

    private static void realizarDeposito(){
        ContaBancaria contaBancaria = escolherContaBancaria();

        if (contaBancaria != null) {
            System.out.println("Digite um valor a ser depositado:");
            int valorEscolhido = s.nextInt();
            s.nextLine();

            if (contaBancaria instanceof ContaCorrente){
                sys.realizarDeposito((ContaCorrente) (contaBancaria), valorEscolhido);
                System.out.println("Deposito realizado com sucesso");
                System.out.println(sys.obterExtrato((ContaCorrente) (contaBancaria)));

            } else {
                System.out.println("Digite o CNPJ da empresa que está depositando:");
                int cnpjEscolhido = s.nextInt();
                s.nextLine();

                if (sys.realizarDeposito((ContaSalario) (contaBancaria), valorEscolhido, cnpjEscolhido)){
                    System.out.println("Deposito realizado com sucesso");
                    System.out.println(sys.obterExtrato((ContaSalario) (contaBancaria)));

                } else {
                    System.out.println("Falha ao depositar");
                }
            }
        }
    }

    private static void mostrarExtrato(){
        ContaBancaria contaBancaria = escolherContaBancaria();

        if (contaBancaria != null) {
            System.out.println(sys.obterExtrato(contaBancaria));
        }
    }

    public static void imprimeMenu(){
        System.out.println("""
                Escolha uma opcao:
                0 - Sair
                1 - Cadastrar Conta
                2 - Realizar Saque
                3 - Realizar Deposito
                4 - Exibir Extrato
                """);
    }

    public static void main(String[] args) {
        int opcao = -1;

        while (opcao != 0){
            System.out.println("\n\n");
            imprimeMenu();
            opcao = s.nextInt();
            s.nextLine();

            switch (opcao){
                case 0 -> System.out.println("Adeus!");
                case 1 -> cadastrarConta();
                case 2 -> realizarSaque();
                case 3 -> realizarDeposito();
                case 4 -> mostrarExtrato();
                default -> System.out.println("Erro");
            }
        }
    }


}
