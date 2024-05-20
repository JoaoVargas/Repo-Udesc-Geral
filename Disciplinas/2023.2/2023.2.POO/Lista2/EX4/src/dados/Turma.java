package dados;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Turma {
    private int numAlunos;
    private LinkedList<Aluno> listaAlunos;

    public int getNumAlunos() {
        return numAlunos;
    }
    public LinkedList<Aluno> getListaAlunos() {
        return listaAlunos;
    }

    public Turma() {
        listaAlunos = new LinkedList<>();
        numAlunos = 0;
    }

    public void adicionarAluno(Aluno aluno){
        listaAlunos.add(aluno);
        numAlunos ++;
    }

    public void ordenarAlunosPorMedia(){
        listaAlunos.sort(new Comparator<Aluno>(){
            public int compare(Aluno a, Aluno b){
                if (a.calcularMedia() - b.calcularMedia() == 0){
                    return 0;
                } else if (a.calcularMedia() - b.calcularMedia() < 0) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
    }

//    public List<Equipe<Aluno>> separarEmEquipes(){
//
//    }
}
