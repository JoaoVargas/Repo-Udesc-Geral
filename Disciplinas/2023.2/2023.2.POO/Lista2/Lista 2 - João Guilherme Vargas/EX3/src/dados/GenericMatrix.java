package dados;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class GenericMatrix<T> {
    private int numLinhas;
    private int numColunas;
    private T[][] data;

    public enum Quadrante {
        PRIMEIRO, SEGUNDO, TERCEIRO, QUARTO
    }

    public GenericMatrix(Class<T> classe, int numLinhas, int numColunas) {
        this.numLinhas = numLinhas;
        this.numColunas = numColunas;

        this.data = (T[][]) Array.newInstance(classe, numLinhas, numColunas);
    }

    public int getNumLinhas() {
        return numLinhas;
    }
    public int getNumColunas() {
        return numColunas;
    }

    public void set(T item, int i, int j){
//        Array.set(this.data[i],j, item);
        this.data[i][j] = item;
    }
    public T get(int i, int j){
//        return (T) Array.get(this.data[i],j);
        return data[i][j];
    }

    public List<T> getLinha(int linha){
        List<T> linhaAux = new ArrayList<>(this.numLinhas);

        for (int i = 0; i < this.numColunas; i++) {
            linhaAux.add(data[linha][i]);
        }

        return linhaAux;
    }
    public List<T> getColuna(int coluna){
        List<T> colunaAux = new ArrayList<>(this.numLinhas);

        for (int i = 0; i < this.numLinhas; i++) {
            colunaAux.add(data[i][coluna]);
        }

        return colunaAux;
    }

    public List<T> getElementosQuadrante(Quadrante quadrante){
        List<T> quadranteAux = new ArrayList<>();

        switch (quadrante){
            case PRIMEIRO -> {
                for (int i = 0; i < numLinhas/2 + 1; i++) {
                    for (int j = 0; j < numColunas/2 + 1; j++) {
                        quadranteAux.add(data[i][j]);
                    }
                }
            }
            case SEGUNDO -> {
                for (int i = 0; i < numLinhas/2 + 1; i++) {
                    for (int j = numColunas/2 + 1; j < numColunas; j++) {
                        quadranteAux.add(data[i][j]);
                    }
                }
            }
            case TERCEIRO -> {
                for (int i = numLinhas/2 + 1; i < numLinhas; i++) {
                    for (int j = 0; j < numColunas/2 + 1; j++) {
                        quadranteAux.add(data[i][j]);
                    }
                }
            }
            case QUARTO -> {
                for (int i = numLinhas/2 + 1; i < numLinhas; i++) {
                    for (int j = numColunas/2 + 1; j < numColunas; j++) {
                        quadranteAux.add(data[i][j]);
                    }
                }
            }
            default -> {
                return null;
            }
        }

        return quadranteAux;
    }
}
