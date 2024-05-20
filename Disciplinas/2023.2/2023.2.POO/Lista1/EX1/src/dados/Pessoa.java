package dados;

import java.util.LinkedList;

public class Pessoa {
    private String nome;
    private String cpf;
    private String telefone;
    private String cep;
    private int idade;
    private LinkedList<Reserva> reservasPassadas = new LinkedList<Reserva>();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public LinkedList<Reserva> getReservasPassadas() {
        return this.reservasPassadas;
    }

    public void addReservasPassadas(Reserva reserva) {
        this.reservasPassadas.add(reserva);
    }

    public String toString(){
        String p = "";

        p += this.cpf + " - ";
        p += this.nome ;

        return p;
    }
}
