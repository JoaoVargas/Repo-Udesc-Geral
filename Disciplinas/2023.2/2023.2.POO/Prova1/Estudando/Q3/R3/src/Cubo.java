public class Cubo extends Quadrado{
    public Cubo(double lado) {
        super(lado);
    }

    @Override
    public double getArea() {
        return super.getArea() * 6;
    }
}
