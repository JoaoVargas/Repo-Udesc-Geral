package FormasGeometricas;

import static java.lang.Math.pow;

public class Circulo extends FormaGeometrica{
    public void setRaio(int valor){
        this.medida1 = valor;
        this.medida2 = valor * 2;
    }

    @Override
    public int calculaArea() {
        return (int) (Math.PI * pow(this.medida1, 2));
    }

    @Override
    public int calculaPerimetro() {
        return (int) (Math.PI * this.medida2);
    }

    @Override
    public String toString() {
        return """
                Circulo:
                Raio: %s
                Área: %s
                Perímetro: %s""".formatted(this.medida1, calculaArea(), calculaPerimetro());
    }
}
