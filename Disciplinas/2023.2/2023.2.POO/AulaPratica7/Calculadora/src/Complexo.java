import java.util.Random;

public class Complexo {
    private int real;
    private int imaginario;

    public Complexo(int real, int imaginario) {
        this.real = real;
        this.imaginario = imaginario;
    }

    public Complexo() {
        Random r = new Random();
        real = r.nextInt(100);
        imaginario = r.nextInt(100);
    }

    public int getReal() {
        return real;
    }
    public int getImaginario() {
        return imaginario;
    }

    @Override
    public String toString() {
        return """
                ( %s + %si )""".formatted(real, imaginario);
    }
}
