package apresentacao;

import java.util.Scanner;

import dados.Contato;
import exceptions.ErroContatoDuplicadoException;
import exceptions.ErroContatoNaoCadastradoException;
import exceptions.ErroArquivoGravacaoException;
import exceptions.ErroArquivoLeituraException;
import negocio.ListaTelefonica;

public class Principal {
    private static Scanner s = new Scanner(System.in);
    private static ListaTelefonica lista = new ListaTelefonica();

    public static void main(String[] args) {

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("""
                    Escolha uma opção:
                    0 - Sair
                    1 - Cadastrar um contato
                    2 - Remover um contato
                    3 - Mostrar todos os contatos""");

            opcao = s.nextInt();

            switch (opcao) {
                case 0:
                    break;
                case 1:
                    Contato contato = novoContato();
                    try {
                        lista.adicionaContato(contato);
                    } catch (ErroArquivoLeituraException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Caminho informado: " + e.getCaminho());
                    } catch (ErroArquivoGravacaoException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Caminho informado: " + e.getCaminho());
                    } catch (ErroContatoDuplicadoException e) {
                        System.out.println(e);
                    }
                    break;
                case 2:
                    removerContato();
                    break;
                case 3:
                    exibirContatos();
                    break;
                default:
                    break;
            }
        }
    }

    public static Contato novoContato() {
        System.out.println("Digite o nome do contato:");
        String nome = s.nextLine();

        System.out.println("Digite o telefone do contato:");
        int telefone = Integer.parseInt(s.nextLine());

        Contato c = new Contato();
        c.setNome(nome);
        c.setTelefone(telefone);

        return c;
    }

    public static void exibirContatos() {
        try {
            lista.buscarContatos().forEach((chave, lista) -> {
                System.out.println(chave + ":");
                for (Contato contato : lista) {
                    System.out.println("  " + contato.toString());
                }
            });
        } catch (ErroArquivoLeituraException e) {
            System.out.println(e.getMessage());
            System.out.println("Caminho informado: " + e.getCaminho());
        }

    }

    public static void removerContato() {
        System.out.println("Escolha uma letra que deseja remover:");
        char entrada = s.nextLine().toUpperCase().toCharArray()[0];

//        try {
//            if (lista.buscarContatos(entrada).size() > 0) {
//                exibirContatos(entrada);
//                System.out.println("Escolha um contato para remover:");
//                int index = s.nextInt();
//                if (index < lista.buscarContatos(entrada).size()) {
//                    lista.removeContato(lista.buscarContatos(entrada).get(index));
//                }
//            } else {
//                System.out.println("Não existem contatos para serem removidos");
//            }
//        } catch (ErroArquivoLeituraException | ErroArquivoGravacaoException e) {
//            System.out.println(e.getMessage());
//            System.out.println("Caminho informado: " + e.getCaminho());
//        } catch (ErroContatoNaoCadastradoException e) {
//            System.out.println(e.getMessage());
//        }
    }
}
