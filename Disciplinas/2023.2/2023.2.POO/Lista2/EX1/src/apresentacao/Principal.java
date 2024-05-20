package apresentacao;

import dados.*;
import java.util.Scanner;


public class Principal {
    private static final Scanner s = new Scanner(System.in);

    private static final Pessoa headCriancas = new Pessoa();
    private static final Pessoa headAdolescentes = new Pessoa();
    private static final Pessoa headJovems = new Pessoa();
    private static final Pessoa headAdultos = new Pessoa();
    private static final Pessoa headIdosos = new Pessoa();

    public static void main(String[] args) {
        String nome;

        while (true){
            mostrarPessoas();
            nome = s.nextLine();

            if (tryParseInt(nome) == -1){
                break;
            }

            criarPessoa(nome);
        }
    }

    public static int tryParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch(NumberFormatException nfe) {
            return 0;
        }
    }

    public static void mostrarPessoas(){
        System.out.println("1 até 12, crianças:");
        printPessoaTails(headCriancas);
        System.out.println("13 até 18, adolescentes:");
        printPessoaTails(headAdolescentes);
        System.out.println("19 até 25, jovens:");
        printPessoaTails(headJovems);
        System.out.println("26 até 59, adultos:");
        printPessoaTails(headAdultos);
        System.out.println("60 ou mais, idosos:");
        printPessoaTails(headIdosos);
        System.out.println("\n");
    }

    public static boolean verifiSeProx(Pessoa p){
        return p.getProx() != null;
    }

    public static void printPessoaTails (Pessoa head){
        if (!verifiSeProx(head)){
            return;
        }

        do {
            System.out.println(head.getProx().toString());
            head = head.getProx();
        } while (verifiSeProx(head));
    }

    public static void criarPessoa(String nome){
        Pessoa p = new Pessoa();

        p.setNome(nome);
        System.out.println("Idade:");
        p.setIdade(tryParseInt(s.nextLine()));
        System.out.println("CPF:");
        p.setCpf(tryParseInt(s.nextLine()));
        System.out.println("Cidade:");
        p.setCidade(s.nextLine());

        if (p.getIdade() <= 12){
            adicionarPessoa(p, headCriancas);
        } else if (p.getIdade() <= 18) {
            adicionarPessoa(p, headAdolescentes);
        } else if (p.getIdade() <= 25) {
            adicionarPessoa(p, headJovems);
        } else if (p.getIdade() <= 59) {
            adicionarPessoa(p, headAdultos);
        } else {
            adicionarPessoa(p, headIdosos);
        }
    }

    private static void adicionarPessoa(Pessoa p, Pessoa h) {
        if (!verifiSeProx(h)){
            h.setProx(p);
            p.setAnte(h);
        } else {
            Pessoa aux = new Pessoa();
            aux.setAnte(h);
            aux.setProx(h.getProx());

            while (true){
                if (aux.getProx() == null){
                    aux.getAnte().setProx(p);
                    p.setAnte(aux.getAnte());
                    p.setProx(null);
                    break;
                }
                if (aux.getProx().getNome().compareToIgnoreCase(p.getNome()) > 0){
                    aux.getAnte().setProx(p);
                    aux.getProx().setAnte(p);
                    p.setAnte(aux.getAnte());
                    p.setProx(aux.getProx());
                    break;
                }

                aux.setAnte(aux.getProx());
                aux.setProx(aux.getProx().getProx());
            }
        }
    }
}
