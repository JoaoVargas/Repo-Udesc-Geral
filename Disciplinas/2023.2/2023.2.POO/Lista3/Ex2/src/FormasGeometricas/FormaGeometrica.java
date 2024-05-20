package FormasGeometricas;

public abstract class FormaGeometrica {
    protected int medida1;
    protected int medida2;

    public FormaGeometrica() {
    }

    public abstract int calculaArea();
    public abstract int calculaPerimetro();
}
