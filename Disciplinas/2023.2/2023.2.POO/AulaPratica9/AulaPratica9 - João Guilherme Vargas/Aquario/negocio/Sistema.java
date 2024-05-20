package negocio;

import dados.*;

public class Sistema {
    private Animal animais[] = new Animal[100];
    private Viveiro viveiros[] = new Viveiro[50];
    private int numViveirosTotais = 0;
    private int numAnimaisTotais = 0;

    public Animal[] getAnimaisTotal() {
        return animais;
    }
    public Viveiro[] getViveirosTotal() {
        return viveiros;
    }
    public int getNumViveirosTotais() {
        return numViveirosTotais;
    }
    public int getNumAnimaisTotais() {
        return numAnimaisTotais;
    }

    public void cadastrarViveiro(Viveiro viveiro) {
        viveiros[numViveirosTotais] = viveiro;
        numViveirosTotais++;
    }
    public void cadastrarAnimal(Animal animal) {
        animais[numAnimaisTotais] = animal;
        numAnimaisTotais++;
    }

    public Viveiro[] getViveiros(){
        int numViveiros = 0;

        for (int i = 0; i < numViveirosTotais; i++) {
            if (!(viveiros[i] instanceof Aquario)){
                numViveiros ++;
            }
        }

        Viveiro[] viveirosApenas = new Viveiro[numViveiros];

        numViveiros = 0;

        for (int i = 0; i < numViveirosTotais; i++) {
            if (!(viveiros[i] instanceof Aquario)){
                viveirosApenas[numViveiros] = viveiros[i];
                numViveiros++;
            }
        }

        return viveirosApenas;
    }

    public Aquario[] getAquarios(){
        int numAquarios = 0;

        for (int i = 0; i < numViveirosTotais; i++) {
            if (viveiros[i] instanceof Aquario){
                numAquarios ++;
            }
        }

        Aquario[] aquariosApenas = new Aquario[numAquarios];

        numAquarios = 0;

        for (int i = 0; i < numViveirosTotais; i++) {
            if (viveiros[i] instanceof Aquario){
                aquariosApenas[numAquarios] = (Aquario) viveiros[i];
                numAquarios++;
            }
        }

        return aquariosApenas;
    }

    public boolean alocarAnimal(Animal animal, Viveiro viveiro) {
        if (viveiro instanceof Aquario) {
            if (animal instanceof Peixe) {
                Aquario aquario = (Aquario) viveiro;
                Peixe peixe = (Peixe) animal;

                return aquario.adicionarAnimal(peixe);
            } else {
                return false;
            }
        } else {
            if (animal instanceof Peixe) {
                return false;
            } else {
                return viveiro.adicionarAnimal(animal);
            }
        }
    }
}
