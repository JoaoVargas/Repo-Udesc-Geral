package FormasGeometricas;

import static java.lang.Math.sqrt;
import static java.lang.Math.pow;

public class TrinaguloEquilatero extends FormaGeometrica{
    public void setLado(int valor){
        this.medida1 = valor;
    }

    @Override
    public int calculaArea() {
        return (int) ((sqrt(3)/4)*pow(this.medida1, 2));
    }

    @Override
    public int calculaPerimetro() {
        return 3*this.medida1;
    }

    @Override
    public String toString() {
        return """
                Triangulo Equilátero:
                Lados: %s
                Área: %s
                Perímetro: %s""".formatted(this.medida1, calculaArea(), calculaPerimetro());
    }
}
