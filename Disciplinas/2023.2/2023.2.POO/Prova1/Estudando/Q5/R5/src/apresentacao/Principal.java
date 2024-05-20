package apresentacao;

import dados.*;
import sistema.*;

import java.util.Scanner;

public class Principal {
    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        Sistema app = new Sistema();
        int opcao = -1;

        while(opcao != 0){

            System.out.println("");
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Ver alunos");
            System.out.println("2 - Ver professores");
            System.out.println("3 - Adicionar aluno");
            System.out.println("4 - Adicionar professor");
            System.out.println("5 - Remover aluno");
            System.out.println("6 - Remover professor");
            System.out.println("0 - Sair");
            System.out.println("");

            opcao = Integer.parseInt(scan.nextLine());

            switch(opcao){
                case 0:
                    break;

                case 1:
                    app.clearScreen();
                    app.mostraAlunos();
                    System.out.println("");
                    break;

                case 2:
                    app.clearScreen();
                    app.mostraProfessores();
                    System.out.println("");
                    break;

                case 3:
                    app.clearScreen();
                    //cria um aluno
                    System.out.println("Digite o nome do aluno:");
                    String nomeA = scan.nextLine();
                    System.out.println("Digite o numero do aluno:");
                    Integer numeroA = Integer.parseInt(scan.nextLine());
                    System.out.println("Digite o curso do aluno:");
                    String curso = scan.nextLine();
                    Aluno aluno = new Aluno(nomeA, numeroA, curso);

                    //registra o aluno
                    app.cadastraAluno(aluno);

                    System.out.println("");
                    break;

                case 4:
                    app.clearScreen();
                    //cria um professor
                    System.out.println("Digite o nome do professor:");
                    String nomeP = scan.nextLine();
                    System.out.println("Digite o numero do professor:");
                    Integer numeroP = Integer.parseInt(scan.nextLine());
                    String[] materias = new String[3];
                    System.out.println("Digite a primeira materia do professor:(só enter caso não possua)");
                    materias[0] = scan.nextLine();
                    System.out.println("Digite a segunda materia do professor:(só enter caso não possua)");
                    materias[1] = scan.nextLine();
                    System.out.println("Digite a terceira materia do professor:(só enter caso não possua)");
                    materias[2] = scan.nextLine();
                    Professor professor = new Professor(nomeP, numeroP, materias);

                    //registra o professor
                    app.cadastraProfessor(professor);

                    System.out.println("");
                    break;

                case 5:
                    app.clearScreen();

                    System.out.println("Digite o nome do aluno:");
                    String nomeArem = scan.nextLine();
                    Pessoa remAluno = new Pessoa(nomeArem, 0);
                    app.exclui(remAluno);

                    System.out.println("");
                    break;

                case 6:
                    app.clearScreen();

                    System.out.println("Digite o nome do aluno:");
                    String nomePrem = scan.nextLine();
                    Pessoa remProfessor = new Pessoa(nomePrem, 0);
                    app.exclui(remProfessor);
                    System.out.println("");
                    break;

            }
        }
        scan.close();
    }

}
