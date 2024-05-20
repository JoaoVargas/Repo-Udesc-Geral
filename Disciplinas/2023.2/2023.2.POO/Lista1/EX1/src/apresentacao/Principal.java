package apresentacao;

import dados.*;
import sistema.*;

import java.util.Objects;
import java.util.Scanner;

public class Principal {
    private static Scanner s = new Scanner(System.in);
    private static SistemaReservas sistema = new SistemaReservas();

    public static void main(String[] args) {
        menuPrincipal();
    }

    public static void exibeMenuPrincipal() {
        System.out.println();
        System.out.println();
        System.out.println("Escolha uma opção:");
        System.out.println("0 - Encerrar");
        System.out.println("1 - Cadastrar Pessoa");
        System.out.println("2 - Cadastrar Quarto");
        System.out.println("3 - Realizar Reserva");
        System.out.println("4 - Mostrar Pessoas");
        System.out.println("5 - Mostrar Quartos");
        System.out.println("6 - Mostrar Reservas");
    }

    public static void menuPrincipal() {
        int opcao = -1;

        while (opcao != 0) {
            exibeMenuPrincipal();
            opcao = s.nextInt();

            switch (opcao) {
                case 1 -> {
                    System.out.println("Cadastrar Pessoa");
                    cadastrarPessoa();
                }
                case 2 -> {
                    System.out.println("Cadastrar Quarto");
                    cadastrarQuarto();
                }
                case 3 -> {
                    System.out.println("Realizar Reserva");
                    realizarReserva();
                }
                case 4 -> {
                    System.out.println("Mostrar Pessoas");
                    mostrarPessoas();
                }
                case 5 -> {
                    System.out.println("Mostrar Quartos");
                    mostrarQuartos();
                }
                case 6 -> {
                    System.out.println("Mostrar Reservas");
                    mostrarReservas();
                }
                default -> System.out.println("Número inválido");
            }
        }
    }

    private static void cadastrarPessoa() {
        sistema.addListaPessoas(criarPessoa());
    }

    private static Pessoa criarPessoa() {
        Pessoa p = new Pessoa();

        s.nextLine();

        System.out.println("Digite o cpf: ");
        p.setCpf(s.nextLine());

        System.out.println("Digite o nome: ");
        p.setNome(s.nextLine());

        System.out.println("Digite a idade: ");
        p.setIdade(s.nextInt());
        s.nextLine();

        System.out.println("Digite o cep: ");
        p.setCep(s.nextLine());

        System.out.println("Digite o telefone: ");
        p.setTelefone(s.nextLine());

        return p;
    }

    private static void mostrarPessoas() {
        int i = 1;
        for (Pessoa listaPessoa : sistema.getListaPessoas()) {
            System.out.println(i + " - " + listaPessoa.toString());
            i++;
        }
    }

    private static void cadastrarQuarto() {
        sistema.addListaQuartos(criarQuarto());
    }

    private static Quarto criarQuarto(){
        Quarto q = new Quarto();

        s.nextLine();

        System.out.println("Digite o numero do bloco: ");
        q.setBloco(s.nextInt());
        s.nextLine();

        System.out.println("Digite o numero: ");
        q.setNumero(s.nextInt());
        s.nextLine();

        System.out.println("Digite a quantidade de camas de solteiro: ");
        q.setCamasSolteiro(s.nextInt());
        s.nextLine();

        System.out.println("Digite a quantidade de camas de casal: ");
        q.setCamasCasal(s.nextInt());
        s.nextLine();

        System.out.println("Digite L se a classe do quarto é Luxo: ");
        q.setLuxo(Objects.equals(s.nextLine(), "L"));

        return q;
    }

    private static void mostrarQuartos(){
        int i = 1;
        for (Quarto listaQuarto : sistema.getListaQuartos()) {
            System.out.println(i + " - " + listaQuarto.toString());
            i++;
        }
    }

    private static void realizarReserva(){
        sistema.addListaReservas(criarReserva());
    }

    private static Reserva criarReserva(){
        Reserva r = new Reserva();
        Pessoa p = null;
        Quarto q = null;

        int escolhaQuarto = -1;
        int escolhaPessoa = -1;

        while (q == null){
            System.out.println("Escolha um quarto para a reserva:");
            System.out.println("0 - Criar quarto");
            mostrarQuartos();

            s.nextLine();
            escolhaQuarto = s.nextInt();
            s.nextLine();


            if (escolhaQuarto == 0){
                q = criarQuarto();
                sistema.addListaQuartos(q);
            } else {
                q = sistema.getListaQuartos().get(escolhaQuarto - 1);
            }
        }
        r.setQuarto(q);

        while (p == null){
            System.out.println("Escolha um responsavel para a reserva:");
            System.out.println("0 - Criar pessoa");
            mostrarPessoas();

            s.nextLine();
            escolhaPessoa = s.nextInt();
            s.nextLine();


            if (escolhaPessoa == 0){
                p = criarPessoa();
                sistema.addListaPessoas(p);
            } else {
                p = sistema.getListaPessoas().get(escolhaQuarto - 1);
            }
        }
        r.setResponsavel(p);

        r.setNumReserva(sistema.getNumReservas());
        sistema.aumentarNumReservas();

        return r;
    }

    private static void mostrarReservas(){
        int i = 1;
        for (Reserva listaReserva : sistema.getListaReservas()) {
            System.out.println(i + " - " + listaReserva.toString());
            i++;
        }
    }


}
