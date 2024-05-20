package dados;

public class Usuario {
    private int id;
    private String login;
    private String senha;
    private int limiteEmpLivro;
    private int limiteEmpMono;
    private int tempoPenalidade;

    public Usuario() {
        this.limiteEmpLivro = 0;
        this.limiteEmpMono = 0;
        this.tempoPenalidade = 0;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public int getLimiteEmpLivro() {
        return limiteEmpLivro;
    }
    public void setLimiteEmpLivro(int limiteEmpLivro) {
        this.limiteEmpLivro = limiteEmpLivro;
    }
    public int getLimiteEmpMono() {
        return limiteEmpMono;
    }
    public void setLimiteEmpMono(int limiteEmpMono) {
        this.limiteEmpMono = limiteEmpMono;
    }
    public int getTempoPenalidade() {
        return tempoPenalidade;
    }
    public void setTempoPenalidade(int tempoPenalidade) {
        this.tempoPenalidade = tempoPenalidade;
    }

    public boolean podeEmprLivro(){
        return !(this.tempoPenalidade > 0) && !(this.limiteEmpLivro > 0);
    }

    public boolean podeEmprMono(){
        return !(this.tempoPenalidade > 0) && !(this.limiteEmpMono > 0);
    }


}
