public class CalculadoraComplexos implements OperacoesBasicas<Complexo>{
    @Override
    public Complexo soma(Complexo operador1, Complexo operador2) {
        return new Complexo(operador1.getReal() + operador2.getReal(), operador1.getImaginario() + operador2.getImaginario());
    }

    @Override
    public Complexo subtracao(Complexo operador1, Complexo operador2) {
        return new Complexo(operador1.getReal() - operador2.getReal(), operador1.getImaginario() - operador2.getImaginario());
    }
}
