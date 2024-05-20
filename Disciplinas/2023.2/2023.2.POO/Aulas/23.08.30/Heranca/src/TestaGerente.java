public class TestaGerente {
    public static void main(String[] args) {
        Gerente gerente = new Gerente();
        gerente.setNome("J");
        gerente.setDepartamento("Fin");
        gerente.setSalario(500);

        System.out.println(gerente.getNome());
        System.out.println(gerente.getBonificacao());

    }
}
