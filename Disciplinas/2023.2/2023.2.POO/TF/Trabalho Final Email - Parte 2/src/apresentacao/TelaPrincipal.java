package apresentacao;

import dados.Email;
import dados.Usuario;
import negocio.Sistema;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal extends JFrame{
    private static final Sistema sys = new Sistema();


    public static void main(String[] args) {
        TelaPrincipal frame = new TelaPrincipal();
        frame.setVisible(true);
    }

    public TelaPrincipal(){
        JPanel telaPreLoging = new JPanel();
        JPanel telaLogin = new JPanel();
        JPanel telaCadastro = new JPanel();
        JPanel telaEmail = new JPanel();

        configurarTelaPrelogin(telaPreLoging, telaLogin, telaCadastro, telaEmail);

        configurarTelaLogin(telaPreLoging, telaLogin, telaCadastro, telaEmail);

        configurarTelaCadastro(telaPreLoging, telaLogin, telaCadastro, telaEmail);

        configurarTelaEmail(telaPreLoging, telaLogin, telaCadastro, telaEmail);

        setContentPane(telaPreLoging);
    }

    private void configurarTelaPre(){
        setTitle("Sistema de Gestão de Email");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,600);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void configurarTelaBase(){
        setSize(1280,720);
        setMinimumSize(new Dimension(1280,720));

        setResizable(true);
        setLocationRelativeTo(null);
    }

    private void configurarTelaPrelogin(JPanel telaPreLoging, JPanel telaLogin , JPanel telaCadastro, JPanel telaEmail){
        configurarTelaPre();

        telaPreLoging.setLayout(new GridLayout(3, 1, 0, 30));
        telaPreLoging.setBorder(BorderFactory.createEmptyBorder(180, 50, 180, 50));


        JButton preloginBtnEntrar = new JButton("Entrar");
        telaPreLoging.add(preloginBtnEntrar);
        preloginBtnEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                setContentPane(telaLogin);
                invalidate();
                validate();
            }
        });

        JButton preloginBtnCadastrar = new JButton("Cadastrar-se");
        telaPreLoging.add(preloginBtnCadastrar);
        preloginBtnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                setContentPane(telaCadastro);
                invalidate();
                validate();
            }
        });

        JButton preloginBtnSair = new JButton("Sair");
        telaPreLoging.add(preloginBtnSair);
        preloginBtnSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
    }

    private void configurarTelaLogin(JPanel telaPreLoging, JPanel telaLogin, JPanel telaCadastro, JPanel telaEmail) {
        telaLogin.setLayout(new GridLayout(2, 1, 0, 50));
        telaLogin.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JPanel loginBaseCima = new JPanel(new GridLayout(2, 1, 0, 10));

        JPanel loginBaseCimaEmail = new JPanel(new GridLayout(2, 1, 0, -25));
        JLabel entradaEmailLable = new JLabel("Email:");
        loginBaseCimaEmail.add(entradaEmailLable);
        JTextField entradaEmail = new JTextField();
        entradaEmail.setToolTipText("Email");
        loginBaseCimaEmail.add(entradaEmail);
        loginBaseCima.add(loginBaseCimaEmail);

        JPanel loginBaseCimaSenha = new JPanel(new GridLayout(2, 1, 0, -25));
        JLabel entradaSenhaLable = new JLabel("Senha:");
        loginBaseCimaSenha.add(entradaSenhaLable);
        JPasswordField entradaSenha = new JPasswordField();
        entradaSenha.setToolTipText("Senha");
        loginBaseCimaSenha.add(entradaSenha);
        loginBaseCima.add(loginBaseCimaSenha);

        telaLogin.add(loginBaseCima);


        JPanel loginBaseBaixo = new JPanel(new GridLayout(1, 2, 25, 0));
        loginBaseBaixo.setBorder(BorderFactory.createEmptyBorder(20, 0, 150, 0));

        JButton loginBaseBaixoBtnVoltar = new JButton("Voltar");
        loginBaseBaixo.add(loginBaseBaixoBtnVoltar);
        loginBaseBaixoBtnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                setContentPane(telaPreLoging);
                invalidate();
                validate();
            }
        });

        JButton loginBaseBaixoBtnEntrar = new JButton("Entrar");
        loginBaseBaixoBtnEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if (logarUsuario(entradaEmail.getText(), entradaSenha.getText())){
                    JOptionPane.showMessageDialog(loginBaseBaixoBtnEntrar, "Login realizado com sucesso.");
                    entradaEmail.setText(null);
                    entradaSenha.setText(null);

                    setContentPane(telaEmail);
                    invalidate();
                    configurarTelaBase();
                    validate();
                } else {
                    JOptionPane.showMessageDialog(loginBaseBaixoBtnEntrar, "Erro ao realizar o login");
                    entradaEmail.setText(null);
                    entradaSenha.setText(null);

                    setContentPane(telaPreLoging);
                    invalidate();
                    validate();
                }
            }
        });
        loginBaseBaixo.add(loginBaseBaixoBtnEntrar);

        telaLogin.add(loginBaseBaixo);
    }

    private void configurarTelaCadastro(JPanel telaPreLoging, JPanel telaLogin, JPanel telaCadastro, JPanel telaEmail) {
        telaCadastro.setLayout(new GridLayout(2, 1, 0, 50));
        telaCadastro.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JPanel cadastroBaseCima = new JPanel(new GridLayout(3, 1, 0, 0));

        JPanel cadstroBaseCimaNome = new JPanel(new GridLayout(2, 1, 0, -10));
        JLabel cadastroNomeLable = new JLabel("Nome:");
        cadstroBaseCimaNome.add(cadastroNomeLable);
        JTextField cadastroNome = new JTextField();
        cadastroNome.setToolTipText("Nome");
        cadstroBaseCimaNome.add(cadastroNome);
        cadastroBaseCima.add(cadstroBaseCimaNome);

        JPanel cadastroBaseCimaEmail = new JPanel(new GridLayout(2, 1, 0, -10));
        JLabel cadastroEmailLable = new JLabel("Email:");
        cadastroBaseCimaEmail.add(cadastroEmailLable);
        JTextField cadastroEmail = new JTextField();
        cadastroEmail.setToolTipText("Email");
        cadastroBaseCimaEmail.add(cadastroEmail);
        cadastroBaseCima.add(cadastroBaseCimaEmail);

        JPanel cadastroBaseCimaSenha = new JPanel(new GridLayout(2, 1, 0, -10));
        JLabel cadastroSenhaLable = new JLabel("Senha:");
        cadastroBaseCimaSenha.add(cadastroSenhaLable);
        JPasswordField cadastroSenha = new JPasswordField();
        cadastroSenha.setToolTipText("Senha");
        cadastroBaseCimaSenha.add(cadastroSenha);
        cadastroBaseCima.add(cadastroBaseCimaSenha);

        telaCadastro.add(cadastroBaseCima);


        JPanel cadastroBaseBaixo = new JPanel(new GridLayout(1, 2, 25, 0));
        cadastroBaseBaixo.setBorder(BorderFactory.createEmptyBorder(20, 0, 150, 0));
        JButton cadastroBaseBaixoBtnVoltar = new JButton("Voltar");
        cadastroBaseBaixo.add(cadastroBaseBaixoBtnVoltar);
        cadastroBaseBaixoBtnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                setContentPane(telaPreLoging);
                invalidate();
                validate();
            }
        });
        JButton cadastroBaseBaixoBtnCadastrar = new JButton("Cadastrar");
        cadastroBaseBaixo.add(cadastroBaseBaixoBtnCadastrar);
        cadastroBaseBaixoBtnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if (cadastrarUsuario(cadastroNome.getText(), cadastroEmail.getText(),  cadastroSenha.getText())){
                    JOptionPane.showMessageDialog(cadastroBaseBaixoBtnCadastrar, "Cadastro realizado com sucesso.");
                } else {
                    JOptionPane.showMessageDialog(cadastroBaseBaixoBtnCadastrar, "Erro ao realizar o cadastro");
                }

                cadastroNome.setText(null);
                cadastroEmail.setText(null);
                cadastroSenha.setText(null);

                setContentPane(telaPreLoging);
                invalidate();
                validate();
            }
        });

        telaCadastro.add(cadastroBaseBaixo);
    }

    private void configurarTelaEmail(JPanel telaPreLoging, JPanel telaLogin, JPanel telaCadastro, JPanel telaEmail) {
        telaEmail.setBorder(BorderFactory.createEmptyBorder(10, 50, 50, 50));
        telaEmail.setLayout(new GridLayout(2, 1, 0, 30));

        JTabbedPane baseTelaEmailCima = new JTabbedPane();

        JList<String> listaEmailsRecebidos = new JList<String>();
        listaEmailsRecebidos.setMinimumSize(new Dimension(300,0));
        listaEmailsRecebidos.setListData(sys.verEmailsEnviadosResumo().toArray(new String[0]));

        JPanel emailRecebido = new JPanel();
        JSplitPane emailsRecebidos = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listaEmailsRecebidos, emailRecebido);
        baseTelaEmailCima.add("Emails Recebidos", emailsRecebidos);


        JList<String> listaEmailsEnviados = new JList<String>();
        listaEmailsEnviados.setMinimumSize(new Dimension(300,0));
        listaEmailsEnviados.setListData(sys.verEmailsEnviadosResumo().toArray(new String[0]));

        JPanel emailEnviado = new JPanel();
        emailEnviado.setMinimumSize(new Dimension(600, 400));

        JSplitPane emailsEnviados = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listaEmailsEnviados, emailEnviado);
        baseTelaEmailCima.add("Emails Enviados", emailsEnviados);

        telaEmail.add(baseTelaEmailCima);

        JPanel baseTelaEmailBaixo = new JPanel(new GridLayout(1, 4, 25, 0));

        JButton btnEscreverEmail = new JButton("Escrever Email");
        baseTelaEmailBaixo.add(btnEscreverEmail);
        btnEscreverEmail.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(btnEscreverEmail, "Login realizado com sucesso.");
            }
        });

        JButton btnResponderEmail = new JButton("Responder Email");
        baseTelaEmailBaixo.add(btnResponderEmail);
        btnResponderEmail.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        JButton btnExcluirEmail = new JButton("Excluir Email");
        baseTelaEmailBaixo.add(btnExcluirEmail);
        btnExcluirEmail.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        JButton btnDeslogar = new JButton("Deslogar");
        baseTelaEmailBaixo.add(btnDeslogar);
        btnDeslogar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                setContentPane(telaPreLoging);
                sys.deslogarUsuario();
                invalidate();
                configurarTelaPre();
                validate();
            }
        });


        telaEmail.add(baseTelaEmailBaixo);
    }

    private static boolean cadastrarUsuario(String nome, String email, String senha){
        Usuario u = new Usuario();

        u.setNome(nome);
        u.setEmail(email);
        u.setSenha(senha);

        return sys.cadastrarUsuario(u);
    }

    private static boolean logarUsuario(String email, String senha){
        return  sys.logarUsuario(email, senha);
    }
}
