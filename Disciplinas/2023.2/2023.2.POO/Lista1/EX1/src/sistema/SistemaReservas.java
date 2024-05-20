package sistema;

import java.util.LinkedList;
import dados.Quarto;
import dados.Pessoa;
import dados.Reserva;

public class SistemaReservas {
    private LinkedList<Quarto> listaQuartos = new LinkedList<Quarto>();
    private LinkedList<Pessoa> listaPessoas = new LinkedList<Pessoa>();
    private LinkedList<Reserva> listaReservas = new LinkedList<Reserva>();
    private int numReservas = 0;

    public LinkedList<Quarto> getListaQuartos() {
        return this.listaQuartos;
    }

    public void addListaQuartos(Quarto quarto) {
        this.listaQuartos.add(quarto);
    }

    public LinkedList<Pessoa> getListaPessoas() {
        return this.listaPessoas;
    }

    public void addListaPessoas(Pessoa pessoa) {
        this.listaPessoas.add(pessoa);
    }

    public LinkedList<Reserva> getListaReservas() {
        return this.listaReservas;
    }

    public void addListaReservas(Reserva reserva) {
        this.listaReservas.add(reserva);
    }

    public void aumentarNumReservas(){
        this.numReservas++;
    }

    public boolean checkQuarto(Quarto quarto){
        for (Quarto listaQuarto : listaQuartos) {
            if (listaQuarto == quarto) {
                return true;
            }
        }
        return false;
    }

    public boolean checkPessoa(Pessoa pessoa){
        for (Pessoa listaPessoa : listaPessoas) {
            if (listaPessoa == pessoa) {
                return true;
            }
        }
        return false;
    }

    public void realizarReserva(Pessoa pessoa, Quarto quarto, Reserva reserva){
        reserva.setNumReserva(this.numReservas);
        aumentarNumReservas();
        if (!checkQuarto(quarto)){
            addListaQuartos(quarto);
        }
        if (!checkPessoa(pessoa)){
            addListaPessoas(pessoa);
        }

        reserva.setQuarto(quarto);
        reserva.setResponsavel(pessoa);

        addListaReservas(reserva);
    }

    public int getNumReservas() {
        return numReservas;
    }
}


