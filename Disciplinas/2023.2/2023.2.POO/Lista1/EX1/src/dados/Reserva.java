package dados;

import dados.Quarto;
import dados.Pessoa;
import java.util.LinkedList;

public class Reserva {
    private int numReserva;
    private Pessoa responsavel;
    private Quarto quarto;
    private LinkedList<Pessoa> agregados = new LinkedList<Pessoa>();

    public int getNumReserva() {
        return numReserva;
    }

    public void setNumReserva(int numReserva) {
        this.numReserva = numReserva;
    }

    public Pessoa getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Pessoa responsavel) {
        this.responsavel = responsavel;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public LinkedList<Pessoa> getAgregados() {
        return this.agregados;
    }

    public void addAgregados(Pessoa pessoa) {
        this.agregados.add(pessoa);
    }

    public String toString(){
        String r = "";

        r += getNumReserva() + " - ";
        r += getResponsavel().getNome() + " - ";
        r += getQuarto().toString();

        return r;
    }
}
