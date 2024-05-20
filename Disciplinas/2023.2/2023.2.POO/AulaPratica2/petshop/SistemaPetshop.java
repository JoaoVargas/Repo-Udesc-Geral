import java.util.Scanner;

public class SistemaPetshop 
{
    private int quantVet=0;
    private Veterinario[] veterinarios = new Veterinario[50];
    Scanner s = new Scanner(System.in);

    public void cadastrarVeterinario()
    {   
        Veterinario v = new Veterinario();
        System.out.println("Nome veterinario:");
        v.setNome(s.next());
        System.out.println("Salario veterinario:");
        v.setSalario(s.nextFloat());
        System.out.println();
        veterinarios[quantVet] = v;
        quantVet++;
    }

    public void mostrarVeterinarios()
    {
        for(int i=0; i<quantVet; i++) {
            System.out.println("Posição: "+i);
            System.out.println("Nome: "+ veterinarios[i].getNome());
            System.out.println("Salario: "+ veterinarios[i].getSalario());
            Endereco e = new Endereco();

            if(veterinarios[i].getEndereco()!= null){
                e = veterinarios[i].getEndereco();
                System.out.println("Rua "+ e.getRua()+", "+ e.getNumero());
                System.out.println("Bairro: "+ e.getBairro()+", Cidade: "+ e.getCidade()+", Estado: "+ e.getEstado());
                System.out.println("Cep: "+ e.getCep()+"\n");

            }else{
                System.out.println();
            }
            
        }
    }

    public void cadastrarEnderecoVeterinario()
    {
        int aux;
        
        for(int i=0; i<quantVet; i++) {
            System.out.println("Posição: "+i);
            System.out.println("Nome: "+ veterinarios[i].getNome());
        }

        System.out.println("Digite a posição do veterinario: ");
        aux = s.nextInt();
        
        Endereco e = new Endereco();
        System.out.println("Rua: ");
        e.setRua(s.next());
        System.out.println("Numero: ");
        e.setNumero(s.nextInt());
        System.out.println("Bairro: ");
        e.setBairro(s.next());
        System.out.println("Cidade: ");
        e.setCidade(s.next());
        System.out.println("Estado: ");
        e.setEstado(s.next());
        System.out.println("CEP: ");
        e.setCep(s.nextInt());

        veterinarios[aux].setEndereco(e);
    }

    public void cadastrarAnimal()
    {
        int aux;

        for(int i=0; i<quantVet; i++) {
            System.out.println("Posição: "+i);
            System.out.println("Nome: "+ veterinarios[i].getNome());
        }

        System.out.println("Digite a posição do veterinario: ");
        aux = s.nextInt();

        Animal a = new Animal();
        System.out.println("Nome: ");
        a.setNome(s.next());
        System.out.println("Especie: ");
        a.setEspecie(s.next());
        System.out.println("Descrição: ");
        a.setDescricao(s.next());

        veterinarios[aux].setAnimais(a);
    }

    public void mostrarAnimais()
    {
        int aux;

        for(int i=0; i<quantVet; i++) {
            System.out.println("Posição: "+i);
            System.out.println("Nome: "+ veterinarios[i].getNome());
        }

        System.out.println("Digite a posição do veterinario: ");
        aux = Integer.parseInt(s.next());

        Animal[] a = new Animal[veterinarios[aux].getQuantidadeAnimais()];
        a = veterinarios[aux].getAnimais();

        for(int i=0; i<veterinarios[aux].getQuantidadeAnimais(); i++){
            System.out.println("Animal numero: "+i);
            System.out.println("Nome: "+a[i].getNome()+"\n");
        }
    }

    public void cadastrarDono()
    {
        int auxa, auxb;

        for(int i=0; i<quantVet; i++)
        {
            auxa=i;Animal[] a = new Animal[veterinarios[i].getQuantidadeAnimais()];
            a = veterinarios[i].getAnimais();
            
            for(int j=0; j<veterinarios[i].getQuantidadeAnimais(); j++)
            {
                auxb=j;
                System.out.println("Codigo do anima a:"+auxa+" b:"+auxb);
                System.out.println("Nome: "+a[i].getNome()+"\n");
            }
        }
    }

    public void cadastrarEnderecoDono()
    {

    }
    
}
