package FormasGeometricas;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Losango extends FormaGeometrica{
    public void setD(int valor){
        this.medida1 = valor;
    }
    public void setd(int valor){
        this.medida2 = valor;
    }

    @Override
    public int calculaArea() {
        return (int) ((this.medida1 * this.medida2)/2.0);
    }

    @Override
    public int calculaPerimetro() {
        return (int) (4 * calculaLado());
    }

    private double calculaLado(){
        return sqrt((pow(this.medida1, 2)/4) + pow(this.medida2, 2)/4);
    }

    @Override
    public String toString() {
        return """
                Losango:
                Lados: %s
                Área: %s
                Perímetro: %s""".formatted((int)calculaLado(), calculaArea(), calculaPerimetro());
    }
}
