package sistema;

import dados.*;

import java.util.List;
import java.util.ArrayList;


public class Sistema {
    static List<Professor> professores = new ArrayList<Professor>();
    static List<Aluno> alunos = new ArrayList<Aluno>();


    public void cadastraAluno(Aluno aluno){
        alunos.add(aluno);
    }
    public void cadastraProfessor(Professor professor){
        professores.add(professor);
    }

    public void exclui(Pessoa pessoa) {
        for(int i=0; i < alunos.size(); i++){
            if (alunos.get(i).getNome().equals(pessoa.getNome())) {
                alunos.remove(alunos.get(i));
            }
        }
        for(int i=0; i < professores.size(); i++){
            if (professores.get(i).getNome().equals(pessoa.getNome())) {
                professores.remove(professores.get(i));
            }
        }
    }

    public void mostraAlunos(){
        if(alunos.size()>0){
            for(int i=0; i<alunos.size(); i++){
                System.out.println("Aluno "+ (i+1) + ": ");
                System.out.println("Nome: " + alunos.get(i).getNome());
                System.out.println("Numero: " + alunos.get(i).getNumero());
                System.out.println("Curso: " + alunos.get(i).getCurso());
                System.out.println("");
            }
        }else{
            System.out.println("Sem alunos cadastrados ainda!");
        }
    }
    public  void mostraProfessores(){
        if(professores.size()>0){
            for(int i=0; i<professores.size(); i++){
                System.out.println("Professor "+ (i+1) + ": ");
                System.out.println("Nome: " + professores.get(i).getNome());
                System.out.println("Numero: " + professores.get(i).getNumero());
                String mat[] = professores.get(i).getMaterias();
                System.out.println("Materias: " + mat[0] + ", "+ mat[1] + ", "+ mat[2]);
                System.out.println("");
            }

        }else{
            System.out.println("Sem professores cadastrados ainda!");
        }
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
