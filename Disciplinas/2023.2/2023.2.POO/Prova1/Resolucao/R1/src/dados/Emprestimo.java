package dados;

import java.time.LocalDateTime;

public class Emprestimo {
    private Item item;
    private Usuario user;
    private int qntRenovado;
    private LocalDateTime dataEmprestimo;
    private LocalDateTime dataDevolucao;
    private boolean devolvido;

    public Emprestimo(Usuario u, Item i) {
        this.devolvido = false;
        this.item = i;
        this.user = u;
        this.qntRenovado = 0;

        item.setEmprestado(true);

        if ((item instanceof Livro)) {
            user.setLimiteEmpLivro(user.getLimiteEmpLivro() + 1);
        } else {
            user.setLimiteEmpMono(user.getLimiteEmpMono() + 1);
        }
    }

    public Item getItem() {
        return item;
    }
    public void setItem(Item item) {
        this.item = item;
    }
    public Usuario getUser() {
        return user;
    }
    public void setUser(Usuario user) {
        this.user = user;
    }
    public int getQntRenovado() {
        return qntRenovado;
    }
    public void setQntRenovado(int qntRenovado) {
        this.qntRenovado = qntRenovado;
    }
    public LocalDateTime getDataEmprestimo() {
        return dataEmprestimo;
    }
    public void setDataEmprestimo(LocalDateTime dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }
    public LocalDateTime getDataDevolucao() {
        return dataDevolucao;
    }
    public void setDataDevolucao(LocalDateTime dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
    public boolean isDevolvido() {
        return devolvido;
    }
    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }

    public void devolver(){
        item.devolver();

        if ((item instanceof Livro)) {
            user.setLimiteEmpLivro(user.getLimiteEmpLivro() -1);
        } else {
            user.setLimiteEmpMono(user.getLimiteEmpMono() - 1);
        }

        this.devolvido = true;
    }

    public void renovar(){
        this.qntRenovado ++;

        setDataDevolucao(getDataDevolucao().plusDays(item.getTempoEmprestimo()));
    }
}
