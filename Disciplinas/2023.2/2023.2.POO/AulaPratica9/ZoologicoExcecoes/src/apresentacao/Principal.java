package apresentacao;

import java.util.Scanner;

import dados.*;
import negocio.*;

public class Principal {
    private static final Sistema sys = new Sistema();
    private static final Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        int op = -1;

        while (op != 0){
            imprimeMenu();
            op = Integer.parseInt(s.nextLine());

            switch (op){
                default -> System.out.println("Adeus");
                case 1 -> cadastrarAnimal();
                case 2 -> cadastrarViveiro();
                case 3 -> mostrarAnimais();
                case 4 -> mostrarViveiros();
            }
        }
    }

    private static void imprimeMenu() {
        System.out.println("""
                
                
                Escolha uma opcao:
                0 - Sair
                1 - Cadastrar Animal
                2 - Cadastrar Viveiro
                3 - Mostrar todos os Animais
                4 - Mostrar todos os viveiros""");
    }

    private static void cadastrarAnimal() {
        System.out.println("""
                Digite qual tipo de Animal:
                1 - Animal Generico
                2 - Animal Peixe""");

        int op = Integer.parseInt(s.nextLine());

        switch (op) {
            case 1 -> sys.cadastrarAnimal(novoAnimal());
            case 2 -> sys.cadastrarAnimal(novoPeixe());
            default -> System.out.println("Escolha inválida!");
        }
    }

    private static Animal novoAnimal() {
        Animal novoAnimal = new Animal();

        System.out.println("Digite o nome:");
        novoAnimal.setNome(s.nextLine());
        System.out.println("Digite a especie:");
        novoAnimal.setEspecie(s.nextLine());
        System.out.println("Digite a idade:");
        novoAnimal.setIdade(Integer.parseInt(s.nextLine()));
        System.out.println("Digite o cor:");
        novoAnimal.setCor(s.nextLine());
        System.out.println("Digite a largura:");
        novoAnimal.setLargura(Float.parseFloat(s.nextLine()));
        System.out.println("Digite a comprimento:");
        novoAnimal.setComprimento(Float.parseFloat(s.nextLine()));
        System.out.println("Digite a altura:");
        novoAnimal.setAltura(Float.parseFloat(s.nextLine()));

        mostrarApenasViveiros();
        System.out.println("\nDigite o número do viveiro:");
        int viveiroEscolhido= Integer.parseInt(s.nextLine());

        while(viveiroEscolhido >= sys.getViveiros().length) {
            System.out.println("Erro: Digite o número do viveiro:");
            viveiroEscolhido= Integer.parseInt(s.nextLine());
        }

        while(!sys.alocarAnimal(novoAnimal, sys.getViveiros()[viveiroEscolhido])) {
            System.out.println("Erro: Nao foi possivel alocar");

            mostrarApenasViveiros();

            System.out.println("Digite o número do viveiro:");
            viveiroEscolhido= Integer.parseInt(s.nextLine());

            while(viveiroEscolhido >= sys.getViveiros().length) {
                System.out.println("Erro: Digite o número do viveiro:");
                viveiroEscolhido= Integer.parseInt(s.nextLine());
            }
        }

        return novoAnimal;
    }

    public static void mostrarApenasViveiros() {
        Viveiro[] viveirosApenas = sys.getViveiros();

        for (int i = 0; i < viveirosApenas.length; i++) {
            System.out.printf("[%s] - %s%n", i, viveirosApenas[i].getNome());
        }
    }

    private static Peixe novoPeixe() {

        Peixe peixe = new Peixe();

        System.out.println("Digite o nome:");
        peixe.setNome(s.nextLine());
        System.out.println("Digite o cor:");
        peixe.setCor(s.nextLine());
        System.out.println("Digite a especie:");
        peixe.setEspecie(s.nextLine());
        System.out.println("Digite a idade:");
        peixe.setIdade(Integer.parseInt(s.nextLine()));
        System.out.println("Digite a largura:");
        peixe.setLargura(Float.parseFloat(s.nextLine()));
        System.out.println("Digite a comprimento:");
        peixe.setComprimento(Float.parseFloat(s.nextLine()));
        System.out.println("Digite a altura:");
        peixe.setAltura(Float.parseFloat(s.nextLine()));
        System.out.println("Digite a temperatura ideal:");
        peixe.setTemperaturaIdeal(Float.parseFloat(s.nextLine()));

        mostrarApenasAquarios();

        System.out.println("Digite o número do aquario:");
        int aquarioEscollhido = Integer.parseInt(s.nextLine());

        while(aquarioEscollhido >= sys.getAquarios().length || aquarioEscollhido < 0) {
            System.out.println("Número inválido!");
            System.out.println("Digite o número do aquario:");
            aquarioEscollhido = Integer.parseInt(s.nextLine());
        }

        while(!sys.alocarAnimal(peixe, sys.getAquarios()[aquarioEscollhido])) {
            System.out.println("Não foi possível alocar nesse aquario");

            mostrarApenasAquarios();

            System.out.println("Digite o número do aquario");
            aquarioEscollhido = Integer.parseInt(s.nextLine());
            while(aquarioEscollhido >= sys.getAquarios().length || aquarioEscollhido < 0) {
                System.out.println("Número inválido!");
                System.out.println("Digite o número do aquario:");
                aquarioEscollhido = Integer.parseInt(s.nextLine());
            }
        }

        return peixe;
    }

    public static void mostrarApenasAquarios() {
        Aquario[] aquariosApenas = sys.getAquarios();

        for (int i = 0; i < aquariosApenas.length; i++) {
            System.out.printf("[%s] - %s%n", i, aquariosApenas[i].getNome());
        }
    }

    private static void cadastrarViveiro(){
        System.out.println("""
                Qual o tipo do viveiro:
                1 - Viveiro Generico
                2 - Viveiro Aquario""");

        int tipoViveiro = Integer.parseInt(s.nextLine());

        switch (tipoViveiro){
            default -> System.out.println("Erro: Digite um tipo valido");
            case 1 -> sys.cadastrarViveiro(novoViveiro());
            case 2 -> sys.cadastrarViveiro(novoAquario());
        }


    }

    private static Viveiro novoViveiro(){
        Viveiro viveiro = new Viveiro();

        System.out.println("Digite o nome:");
        viveiro.setNome(s.nextLine());
        System.out.println("Digite o comprimento:");
        viveiro.setComprimento(Float.parseFloat(s.nextLine()));
        System.out.println("Digite a largura:");
        viveiro.setLargura(Float.parseFloat(s.nextLine()));

        return viveiro;
    }

    private static Aquario novoAquario(){
        Aquario aquario = new Aquario();

        System.out.println("Digite o nome:");
        aquario.setNome(s.nextLine());
        System.out.println("Digite o comprimento:");
        aquario.setComprimento(Float.parseFloat(s.nextLine()));
        System.out.println("Digite a largura:");
        aquario.setLargura(Float.parseFloat(s.nextLine()));
        System.out.println("Digite a altura:");
        aquario.setAltura(Float.parseFloat(s.nextLine()));
        System.out.println("Digite a temperatura:");
        aquario.setTemperatura(Float.parseFloat(s.nextLine()));

        return aquario;
    }

    private static void mostrarAnimais() {

        Animal[] animaisLista = sys.getAnimaisTotal();

        if (sys.getNumAnimaisTotais() > 0){
            for (int i = 0; i < sys.getNumAnimaisTotais(); i++) {
                System.out.println(animaisLista[i].toString());
            }
        } else {
            System.out.println("Nenhum animal cadastrado");
        }

    }

    private static void mostrarViveiros() {

        Viveiro[] viveirosLista = sys.getViveirosTotal();

        if (sys.getNumViveirosTotais() > 0) {
            for (int i = 0; i < sys.getNumViveirosTotais(); i++) {
                System.out.println(viveirosLista[i].toString());
            }
        } else {
            System.out.println("Nenhum viveiro cadstrado");
        }

    }


}
