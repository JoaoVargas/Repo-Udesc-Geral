package apresentacao;

import dados.Email;
import dados.Usuario;
import execoes.*;
import negocio.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class Apresentacao extends JFrame{
    private static final Sistema sys;
    static {
        try {
            sys = new Sistema("230505");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (SelectException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Apresentacao frame = new Apresentacao();
        frame.setVisible(true);
    }

    public Apresentacao(){
        configurarInicializacao();
        JPanel telaInicial = new JPanel();
        JPanel telaLogin = new JPanel();
        JPanel telaCadastro = new JPanel();
        JPanel telaSistema = new JPanel();
        JPanel telaEscreverEmail = new JPanel();

        configurarTelaInicial(telaInicial, telaLogin, telaCadastro);
        configurarTelaLogin(telaInicial, telaLogin, telaCadastro, telaSistema, telaEscreverEmail);
        configurarTelaCadastro(telaInicial, telaLogin, telaCadastro, telaSistema, telaEscreverEmail);
//        configurarTelaSistema(telaInicial, telaLogin, telaCadastro, telaSistema, telaEscreverEmail);
//        configurarTelaEscreverEmail(telaInicial, telaLogin, telaCadastro, telaSistema, telaEscreverEmail);

        setContentPane(telaInicial);
    }


    private void configurarInicializacao(){
        setTitle("Sistema de Gestão de Email");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,450);
        setResizable(false);
        setLocationRelativeTo(null);
    }
    private void configurarSistema(){
        setSize(1280,720);
//        setMinimumSize(new Dimension(1280,720));

//        setResizable(true);
        setLocationRelativeTo(null);
    }

    private void configurarTelaInicial(JPanel telaInicial, JPanel telaLogin, JPanel telaCadastro) {
        telaInicial.setLayout(null);

        JButton botaoEntrar = new JButton ("Entrar");
        botaoEntrar.setBounds (35, 135, 230, 60);
        JButton botaoCadastrar = new JButton ("Cadastrar");
        botaoCadastrar.setBounds (35, 205, 230, 60);
        JButton botaoSair = new JButton ("Sair");
        botaoSair.setBounds (80, 375, 150, 40);

        botaoEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                setContentPane(telaLogin);
                invalidate();
                validate();
            }
        });
        botaoCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                setContentPane(telaCadastro);
                invalidate();
                validate();
            }
        });
        botaoSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        telaInicial.add(botaoEntrar);
        telaInicial.add(botaoCadastrar);
        telaInicial.add(botaoSair);
    }
    private void configurarTelaCadastro(JPanel telaInicial, JPanel telaLogin, JPanel telaCadastro, JPanel telaSistema, JPanel telaEscreverEmail) {
        telaCadastro.setLayout(null);

        JLabel labelNome = new JLabel("Nome:");
        labelNome.setBounds(35, 90, 100, 25);
        JLabel labelEmail = new JLabel("Email:");
        labelEmail.setBounds(35, 160, 100, 25);
        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setBounds(35, 230, 100, 25);

        JTextField inputNome = new JTextField(1);
        inputNome.setBounds(35, 115, 230, 25);
        JTextField inputEmail = new JTextField(1);
        inputEmail.setBounds(35, 185, 230, 25);
        JPasswordField inputSenha = new JPasswordField(1);
        inputSenha.setBounds(35, 255, 230, 25);

        JButton botaoVoltar = new JButton ("Voltar");
        botaoVoltar.setBounds (35, 375, 110, 40);
        JButton botaoCadastrar = new JButton ("Cadastrar");
        botaoCadastrar.setBounds (155, 375, 110, 40);


        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                setContentPane(telaInicial);
                invalidate();
                validate();
            }
        });
        botaoCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Usuario u = new Usuario(0, inputEmail.getText(),  inputSenha.getText(), inputNome.getText());

                if (sys.cadastrarUsuario(u)){
                    JOptionPane.showMessageDialog(botaoCadastrar, "Cadastro realizado com sucesso.");
                    setContentPane(telaLogin);
                } else {
                    JOptionPane.showMessageDialog(botaoCadastrar, "Erro ao realizar o cadastro");
                    setContentPane(telaCadastro);
                }

                invalidate();
                validate();
                inputNome.setText(null);
                inputEmail.setText(null);
                inputSenha.setText(null);

            }
        });

        telaCadastro.add(labelNome);
        telaCadastro.add(inputNome);
        telaCadastro.add(labelEmail);
        telaCadastro.add(inputEmail);
        telaCadastro.add(labelSenha);
        telaCadastro.add(inputSenha);
        telaCadastro.add(botaoVoltar);
        telaCadastro.add(botaoCadastrar);
    }
    private void configurarTelaLogin(JPanel telaInicial, JPanel telaLogin, JPanel telaCadastro, JPanel telaSistema, JPanel telaEscreverEmail) {
        telaLogin.setLayout(null);

        JLabel labelEmail = new JLabel("Email:");
        labelEmail.setBounds(35, 130, 100, 25);
        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setBounds(35, 190, 100, 25);

        JTextField inputEmail = new JTextField(1);
        inputEmail.setBounds(35, 155, 230, 25);
        JPasswordField inputSenha = new JPasswordField(1);
        inputSenha.setBounds(35, 215, 230, 25);

        JButton botaoVoltar = new JButton ("Voltar");
        botaoVoltar.setBounds (35, 375, 110, 40);
        JButton botaoEntrar = new JButton ("Entrar");
        botaoEntrar.setBounds (155, 375, 110, 40);


        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                setContentPane(telaInicial);
                invalidate();
                validate();
            }
        });
        botaoEntrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if (sys.logarUsuario(inputEmail.getText(), inputSenha.getText())){
                    configurarTelaSistema(telaInicial, telaLogin, telaCadastro, telaSistema, telaEscreverEmail);
                    configurarSistema();
                    setContentPane(telaSistema);
                    invalidate();
                    validate();

                } else {
                    JOptionPane.showMessageDialog(botaoEntrar, "Erro ao realizar o login");
                    setContentPane(telaLogin);
                    invalidate();
                    validate();
                }

                inputEmail.setText(null);
                inputSenha.setText(null);
            }
        });

        telaLogin.add(labelEmail);
        telaLogin.add(inputEmail);
        telaLogin.add(labelSenha);
        telaLogin.add(inputSenha);
        telaLogin.add(botaoVoltar);
        telaLogin.add(botaoEntrar);
    }
    private void configurarTelaSistema(JPanel telaInicial, JPanel telaLogin, JPanel telaCadastro, JPanel telaSistema, JPanel telaEscreverEmail) {
        telaSistema.setLayout(new BorderLayout());
        telaSistema.setBorder(BorderFactory.createEmptyBorder(35,35,35,35));

        JTabbedPane painelAbas = new JTabbedPane(SwingConstants.TOP);

        JList<String> listaEmailsRecebidos = new JList<String>();
        listaEmailsRecebidos.setListData(sys.listarEmailsRecebidosLinha().toArray(new String[0]));
        listaEmailsRecebidos.setMinimumSize(new Dimension(300,0));
        JPanel emailRecebido = new JPanel(new BorderLayout());
        JLabel textoRecebido = new JLabel();
        emailRecebido.add(textoRecebido);
        emailRecebido.setMinimumSize(new Dimension(600,0));
        JSplitPane abaRecebidos = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listaEmailsRecebidos, emailRecebido);
        painelAbas.add("Emails Recebidos", abaRecebidos);

        JList<String> listaEmailsEnviados = new JList<String>();
        listaEmailsEnviados.setListData(sys.listarEmailsEnviadosLinha().toArray(new String[0]));
        listaEmailsEnviados.setMinimumSize(new Dimension(300,0));
        JPanel emailEnviado = new JPanel(new BorderLayout());
        JLabel textoEnviado = new JLabel();
        emailEnviado.add(textoEnviado);
        emailEnviado.setMinimumSize(new Dimension(600,0));
        JSplitPane abaEnviados = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listaEmailsEnviados, emailEnviado);
        painelAbas.add("Emails Enviados", abaEnviados);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JPanel painelBaixo = new JPanel(new GridLayout(1,4, 10, 0));
        painelBaixo.setBorder(BorderFactory.createEmptyBorder(35,35,0,35));

        JButton botaoDeslogar = new JButton("Deslogar");
        botaoDeslogar.setPreferredSize(new Dimension(110, 40));
        JButton botaoExcluir = new JButton("Excluir");
        botaoExcluir.setPreferredSize(new Dimension(110, 40));
        JButton botaoResponder = new JButton("Responder");
        botaoResponder.setPreferredSize(new Dimension(110, 40));
        JButton botaoEnviar = new JButton("Enviar");
        botaoEnviar.setPreferredSize(new Dimension(110, 40));

        botaoDeslogar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                configurarInicializacao();
                setContentPane(telaInicial);
                sys.deslogarUsuario();
                invalidate();
                validate();
            }
        });
        botaoEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JTextField destinatario = new JTextField(1);
                JTextField assunto = new JTextField(1);
                JTextField mensagem = new JTextField(1);

                JPanel painelDialogo = new JPanel(new GridLayout(7,1));
                painelDialogo.add(new JLabel("Destinatario:"));
                painelDialogo.add(destinatario);
                painelDialogo.add(new JLabel("Assunto:"));
                painelDialogo.add(assunto);
                painelDialogo.add(new JLabel("Mensagem:"));
                painelDialogo.add(mensagem);
                painelDialogo.add(Box.createHorizontalStrut(15)); // a spacer

                int check = JOptionPane.showInternalConfirmDialog(
                        null,
                        painelDialogo,
                        "Escrever Email",
                        JOptionPane.OK_CANCEL_OPTION
                );

                if (check == JOptionPane.OK_OPTION) {
                    if (!sys.enviarEmail(new Email(0, sys.getEmailUsuarioLogado(), destinatario.getText(),assunto.getText(), mensagem.getText()))) {
                        JOptionPane.showMessageDialog(null, "Erro: Escrever Email");
                    }


                    listaEmailsEnviados.setListData(sys.listarEmailsEnviadosLinha().toArray(new String[0]));
                    listaEmailsRecebidos.setListData(sys.listarEmailsRecebidosLinha().toArray(new String[0]));
                }

            }
        });
        MouseListener clickRecebidos = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    Email selectedItem = sys.listarEmailsRecebidos().get(listaEmailsRecebidos.getSelectedIndex());
                    textoRecebido.setText(selectedItem.toStringBloco());

                }
            }
        };
        MouseListener clickEnviados = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    Email selectedItem = sys.listarEmailsEnviados().get(listaEmailsEnviados.getSelectedIndex());
                    textoEnviado.setText(selectedItem.toStringBloco());

                }
            }
        };

        painelBaixo.add(botaoDeslogar);
        painelBaixo.add(botaoExcluir);
        painelBaixo.add(botaoResponder);
        painelBaixo.add(botaoEnviar);

        listaEmailsRecebidos.addMouseListener(clickRecebidos);
        listaEmailsEnviados.addMouseListener(clickEnviados);

        telaSistema.add(painelAbas, BorderLayout.CENTER);
        telaSistema.add(painelBaixo, BorderLayout.SOUTH);


    }
}