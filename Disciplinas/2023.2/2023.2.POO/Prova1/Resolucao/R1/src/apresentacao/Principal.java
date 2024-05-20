package apresentacao;

import dados.*;
import negocio.*;
import java.util.Scanner;

public class Principal {
    private static final Biblioteca sys = new Biblioteca();
    private static final Scanner s = new Scanner(System.in);

    private static final int SAIR = 0;
    private static final int ADICIONARUSER = 1;
    private static final int ADICIONARITEM = 2;
    private static final int MOSTRAUSERS = 3;
    private static final int MOSTRARITENS = 4;
    private static final int MOSTRAREMPRES = 5;
    private static final int MOSTRAREMPRESUSER = 6;
    private static final int MOSTRAREMPRESITENS = 7;
    private static final int EMPRESTAR = 8;
    private static final int DEVOLVER = 9;
    private static final int RENOVAR = 10;
    private static final int VERATRASO = 11;

    public static void main(String[] args) {
        int op = -1;

        while (true){
            imprimeMenu();
            op = tryParseInt(s.nextLine());

            switch (op){
                default -> System.out.println("Digite um valor válido");
                case SAIR -> System.out.println("Adeus");
                case ADICIONARUSER -> adicionarUsuario();
                case ADICIONARITEM -> adicionarItem();
                case MOSTRAUSERS -> mostrarUsuarios();
                case MOSTRARITENS -> mostrarItens();
//                case MOSTRAREMPRES -> ;
//                case MOSTRAREMPRESUSER -> ;
//                case MOSTRAREMPRESITENS -> ;
//                case EMPRESTAR -> ;
//                case DEVOLVER -> ;
//                case RENOVAR -> ;
//                case VERATRASO -> ;
            }


        }
    }

    private static void limparTela(){
        for (int i = 0; i < 40; i++) {
            System.out.println();
        }
    }

    private static int tryParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }

    private static void imprimeMenu(){
        System.out.println("""
                Menu:
                0 - Sair
                1 - Adicionar Usuario
                2 - Adicionar Item
                3 - Mostrar Usuarios
                4 - Mostrar Itens
                5 - Mostrar Emprestimos
                6 - Mostrar Emprestimos de um Usuario
                7 - Mostrar Emprestimos de um Item
                8 - Emprestar um Item
                9 - Devolver um Item
                10 - Renovar um Emprestimo
                11 - Ver Emprestimos em atraso""");
    }

    private static void adicionarUsuario(){
        Usuario u = new Usuario();

        System.out.println("Digite o login do usuario");
        u.setLogin(s.nextLine());
        System.out.println("Digite a senha do usuario");
        u.setSenha(s.nextLine());

        sys.cadastrarUsuario(u);
    }

    private static void adicionarItem(){
        System.out.println("""
                1 -Livro
                2 - Monografia""");

        int op = tryParseInt(s.nextLine());

        while (op != 1 || op != 2){
            System.out.println("Digite um valor válido");
            System.out.println("""
                1 -Livro
                2 - Monografia""");
            op = tryParseInt(s.nextLine());
        }

        if (op == 1){
            adicionarLivro();
        } else {
            adicionarMonografia();
        }
    }

    private static void adicionarLivro(){
        Livro l = new Livro();

        System.out.println("Digite o titulo do livro");
        l.setTitulo(s.nextLine());
        System.out.println("Digite o nome do autor do livro");
        l.setNomeAutor(s.nextLine());
        System.out.println("Digite o ano do livro");
        l.setAno(tryParseInt(s.nextLine()));

        sys.cadastrarItem(l);
    }
    private static void adicionarMonografia(){
        Monografia m = new Monografia();

        System.out.println("Digite o titulo da monografia");
        m.setTitulo(s.nextLine());
        System.out.println("Digite o nome do autor da monografia");
        m.setNomeAutor(s.nextLine());
        System.out.println("Digite o ano da monografia");
        m.setAno(tryParseInt(s.nextLine()));

        sys.cadastrarItem(m);
    }
    
    private static void mostrarUsuarios(){
        for (Usuario u : sys.getUsuarios()) {
            System.out.println(u.toString());
        }
    }

    private static void mostrarItens(){
        for (Item i : sys.getItens()    ) {
            System.out.println(i.toString());

        }

    }
}
