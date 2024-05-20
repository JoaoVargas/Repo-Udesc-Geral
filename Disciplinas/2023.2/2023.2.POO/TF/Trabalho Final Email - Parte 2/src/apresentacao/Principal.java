//package apresentacao;
//
//import negocio.*;
//import dados.*;
//
//import java.security.NoSuchAlgorithmException;
//import java.util.Objects;
//import java.util.Scanner;
//
//public class Principal {
//    private static final Sistema sys = new Sistema();
//    private static final Scanner s = new Scanner(System.in);
//    private static final java.io.PrintStream so = System.out;
//
//    private static final int SAIR = 0;
//    private static final int ENVIADOS = 1;
//    private static final int RECEBIDOS = 2;
//    private static final int ENVIAR = 3;
//    private static final int EXCLUIR = 4;
//    private static final int RESPONDER = 5;
//    private static final int CADASTRAR = 6;
//    private static final int LOGAR = 7;
//    private static final int ABRIR = 7;
//
//
//    public static void main(String[] args) {
//        int op = -1;
//        int tipoCaixaEntrada = RECEBIDOS;
//        String mensagem = "";
//
//        while (true){
//            if (sys.getUsuarioLogado()){
//                while (op != 0){
//                    limparTela();
//
//                    imprimeCaixaEntrada(tipoCaixaEntrada);
//                    imprimeOpcoesCaixaEntrada();
//                    System.out.println(mensagem);
//                    mensagem = "";
//
//                    op = traduzirOpcao(s.nextLine());
//                    System.out.println(op);
//
//                    switch (op){
//                        case SAIR -> {
//                            System.out.println("sair");
//                            sys.deslogarUsuario();
//                        }
//                        case ENVIADOS -> tipoCaixaEntrada = ENVIADOS;
//                        case RECEBIDOS -> tipoCaixaEntrada = RECEBIDOS;
//                        case ENVIAR -> mensagem = enviarEmail() ? "Email enviado com sucesso" : "Falha ao enviar Email";
//                        case EXCLUIR -> mensagem = excluirEmail() ? "Email excluido com sucesso" : "Falha ao excluir Email";
//                        case RESPONDER -> mensagem = responderEmail() ? "Email respondido com sucesso" : "Falha ao responder Email";
//                        case ABRIR -> abrirEmail(tipoCaixaEntrada);
//                        default -> mensagem = "Opção inválida";
//                    }
//                }
//            } else {
//                while (op != 0){
//                    limparTela();
//
//                    imprimeNaoLogado();
//                    System.out.println(mensagem);
//                    mensagem = "";
//
//                    op = traduzirOpcao(s.nextLine());
//                    System.out.println(op);
//
//                    switch (op){
//                        case SAIR -> {
//                            System.out.println("sair");
//                            return;
//                        }
//                        case CADASTRAR -> {
//                            mensagem = cadastrarUsuario() ? "Usuario cadastrado com sucesso" : "Falha ao cadastrar usuario";
//
//                        }
//                        case LOGAR -> {
//                            mensagem = logarUsuario() ? "" : "Erro ao realizar login";
//                            op = sys.getUsuarioLogado() ? SAIR : -1;
//                        }
//                        default -> mensagem = "Opção inválida";
//                    }
//                }
//            }
//
//            op = -1;
//        }
//    }
//
//    private static int traduzirOpcao(String s) {
//        if (Objects.equals(s, "s")){
//            return SAIR;
//        } else if (Objects.equals(s, "e")){
//            return ENVIADOS;
//        } else if (Objects.equals(s, "r")){
//            return RECEBIDOS;
//        } else if (Objects.equals(s, "w")){
//            return ENVIAR;
//        } else if (Objects.equals(s, "x")){
//            return EXCLUIR;
//        } else if (Objects.equals(s, "d")){
//            return RESPONDER;
//        } else if (Objects.equals(s, "c")){
//            return CADASTRAR;
//        } else if (Objects.equals(s, "l")){
//            return LOGAR;
//        } else if (Objects.equals(s, "a")){
//            return ABRIR;
//        } else {
//            return -1;
//        }
//    }
//
//    private static void limparTela(){
//        for (int i = 0; i < 40; i++) {
//            so.println();
//        }
//    }
//
//    private static int tryParseInt(String value) {
//        try {
//            return Integer.parseInt(value);
//        } catch (NumberFormatException nfe) {
//            return -1;
//        }
//    }
//
//    private static void imprimeCaixaEntrada(int tipoCaixaEntrada) {
//        if (tipoCaixaEntrada == ENVIADOS){
//            System.out.println("Caixa de Entrada - Enviados");
//            System.out.println(sys.verEmailsEnviados());
//        } else if (tipoCaixaEntrada == RECEBIDOS) {
//            System.out.println("Caixa de Entrada - Recebidos");
//            System.out.println(sys.verEmailsRecebidos());
//        }
//    }
//
//    private static void imprimeOpcoesCaixaEntrada() {
//        System.out.println("""
//                -----------------------------------------------------------------------
//                e - Caixa de Entrada Enviados   w - Escrever Email  d - Responder Email
//                r - Caixa de Entrada Recebidos  x - Excluir Email   a - Abrir Email
//                s - Sair""");
//
//    }
//
//    private static void imprimeNaoLogado(){
//        System.out.println("""
//                Sistema de Gerenciamento de Emails
//
//                l - Login           s - Sair
//                c - Cadastrar-se""");
//    }
//
//    private static boolean cadastrarUsuario(){
//        limparTela();
//
//        Usuario u = new Usuario();
//
//        System.out.println("Digite o nome do Usuario:");
//        u.setNome(s.nextLine());
//        System.out.println("Digite o email do Usuario:");
//        u.setEmail(s.nextLine());
//        System.out.println("Digite a senha do Usuario:");
//        u.setSenha(s.nextLine());
//
//        return sys.cadastrarUsuario(u);
//    }
//
//    private static boolean logarUsuario(){
//        limparTela();
//
//        System.out.println("Digite o email do Usuario:");
//        String email = s.nextLine();
//
//        System.out.println("Digite a senha do Usuario:");
//        String senha = s.nextLine();
//
//        return  sys.logarUsuario(email, senha);
//    }
//
//    private static boolean enviarEmail(){
//        limparTela();
//
//        Email e = new Email();
//
//        System.out.println("Digite o destinatario do Email:");
//        e.setDestinatario(s.nextLine());
//        System.out.println("Digite o assunto do Email:");
//        e.setAssunto(s.nextLine());
//        System.out.println("Digite a mensagem do Email:");
//        e.setMensagem(s.nextLine());
//        e.setDataHora();
//
//        return sys.enviarEmail(e);
//    }
//
//    private static boolean excluirEmail(){
//        limparTela();
//
//        System.out.println("Digite o id do Email:");
//
//        return sys.excluirEmailRecebido(tryParseInt(s.nextLine()));
//    }
//
//    private static void abrirEmail(int tipo){
//        limparTela();
//
//        System.out.println("Digite o id do Email:");
//        int id = tryParseInt(s.nextLine());
//
//        limparTela();
//
//        System.out.println(sys.verEmailDetalhes(id, tipo));
//
//        System.out.println("s - Sair");
//
//        while (true){
//            if (traduzirOpcao(s.nextLine()) == SAIR){
//                break;
//            }
//            else {
//                limparTela();
//
//                System.out.println(sys.verEmailDetalhes(id, tipo));
//
//                System.out.println("s - Sair");
//                System.out.println("Digite uma opção válida");
//            }
//        }
//
//
//    }
//
//    private static boolean responderEmail(){
//        limparTela();
//
//        Email e = new Email();
//
//        System.out.println("Digite o id do Email a responder:");
//        int id = tryParseInt(s.nextLine());
//        System.out.println("Digite o assunto do Email:");
//        e.setAssunto(s.nextLine());
//        System.out.println("Digite a mensagem do Email:");
//        e.setMensagem(s.nextLine());
//        e.setDataHora();
//
//        return sys.responderEmail(id, e);
//    }
//
//}
