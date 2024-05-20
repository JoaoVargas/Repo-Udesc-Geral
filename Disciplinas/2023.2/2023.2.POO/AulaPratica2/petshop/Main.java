
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) 
    {
        SistemaPetshop sp = new SistemaPetshop();
        Scanner s = new Scanner(System.in);
        int aux;
        
        while (true) 
        {
            System.out.println("Cadastrar veterinario: 1\nMostrar Veterinarios: 2\nCadastrar endereço de veterinario: 3\nCadastrar animal: 4\nMostrar animais: 5\nCadastrar dono: 6\nCadastrar endereço dono: 7\nSair: 0");
            aux = s.nextInt();

            if(aux == 1){
                System.out.println();
                sp.cadastrarVeterinario();
            }
            if(aux == 2){
                System.out.println();
                sp.mostrarVeterinarios();
            }
            if(aux == 3){
                System.out.println();
                sp.cadastrarEnderecoVeterinario();
            }
            if(aux == 4){
                System.out.println();
                sp.cadastrarAnimal();
            }
            if(aux == 5){
                System.out.println();
                sp.mostrarAnimais();
            }
            if(aux == 6){
                System.out.println();
                sp.cadastrarDono();
            }
            if(aux == 7){
                System.out.println();
                sp.cadastrarEnderecoDono();
            }
            if(aux == 0){
                System.out.println();
                break;
            }    
        }

        
        
        s.close();
    }
}