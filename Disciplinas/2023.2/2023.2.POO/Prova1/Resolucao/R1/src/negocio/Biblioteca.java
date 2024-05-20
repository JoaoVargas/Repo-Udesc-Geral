package negocio;

import dados.*;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Objects;

public class Biblioteca {
    private LinkedList<Usuario> usuarios;
    private LinkedList<Item> itens;
    private LinkedList<Emprestimo> emprestimos;
    private int numItens;
    private int numUsers;

    public int getnumItens(){
        return numItens;
    }
    public void aumentarnumItens(){
        numItens ++;
    }
    public int getnumnumUsers(){
        return numUsers;
    }
    public void aumentarnumUsers(){
        numUsers ++;
    }


    public Biblioteca() {
        usuarios = new LinkedList<Usuario>();
        itens = new LinkedList<Item>();
        emprestimos = new LinkedList<Emprestimo>();
    }

    public void cadastrarUsuario(Usuario u){
        u.setId(numUsers);
        this.usuarios.add(u);
        aumentarnumUsers();
    }
    public void cadastrarItem(Item i){
        i.setId(numItens);
        this.itens.add(i);
        aumentarnumItens();
    }

    public LinkedList<Item> getItens() {
        return itens;
    }

    public LinkedList<Usuario> getUsuarios(){
        return usuarios;
    }

    public LinkedList<Emprestimo> getEmprestimos(Item i){
        LinkedList<Emprestimo> listaE = new LinkedList<Emprestimo>();

        for (Emprestimo e : this.emprestimos) {
            if (e.getItem().getId() == i.getId()){
                listaE.add(e);
            }
        }

        return listaE;
    }
    public LinkedList<Emprestimo> getEmprestimos(Usuario u){
        LinkedList<Emprestimo> listaE = new LinkedList<Emprestimo>();

        for (Emprestimo e : this.emprestimos) {
            if (Objects.equals(e.getUser().getLogin(), u.getLogin())){
                listaE.add(e);
            }
        }

        return listaE;

    }

    public boolean emprestar(Usuario u, Item i){
        try{
            if (i.estaEmprestado()){
                return false;
            }

            if ((i instanceof Livro)) {
                if(!u.podeEmprLivro()){
                    return false;
                }
                emprestimos.add(emprestarAux(u, i));
            } else {
                if(!u.podeEmprMono()){
                    return false;
                }
                emprestimos.add(emprestarAux(u, i));
            }
            return true;
        } catch (Error err){
            return false;
        }
    }

    private Emprestimo emprestarAux(Usuario u, Item i){
        Emprestimo e = new Emprestimo(u, i);
        e.setDataEmprestimo(LocalDateTime.now());
        e.setDataEmprestimo(LocalDateTime.now().plusDays(i.getTempoEmprestimo()));

        return e;
    }

    public void devolver(Emprestimo e){
        e.devolver();
        emprestimos.remove(e);
    }

    public void renovar(Emprestimo e){
        e.renovar();

    }
}
