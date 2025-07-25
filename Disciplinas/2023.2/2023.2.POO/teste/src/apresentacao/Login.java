package apresentacao;

import dados.Usuario;
import negocio.Sistema;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Login extends JFrame {
    private final JTextField loginField = new JTextField();
    private final JPasswordField passwordField1 = new JPasswordField();
    private final JButton criarContaButton = new JButton();
    private final JButton entrarNaContaButton = new JButton();
    private final JPanel Login = new JPanel();
    private final JTextField nascField = new JTextField();
//    private JLabel a = new JLabel();
    private final JLabel contaCriada = new JLabel();
    private JLabel erroLogin = new JLabel();
    private JLabel loginSucesso = new JLabel();
    private JButton deletarContaButton = new JButton();
    private JLabel ContaDeletada = new JLabel();
    private Sistema sist = new Sistema();

    public Login() {
        setContentPane(Login);
        setTitle("Login");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        contaCriada.setVisible(false);
        erroLogin.setVisible(false);
        loginSucesso.setVisible(false);
        ContaDeletada.setVisible(false);
        criarContaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = loginField.getText();
                String senha = new String(passwordField1.getPassword());
                String nasc = nascField.getText();
                if (login.length() == 0 || senha.length() == 0 || nasc.length() == 0) {
                    erroLogin.setVisible(true);
                    return;
                }
                try {
                    sist.criaUsuario(login, senha, nasc);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                contaCriada.setVisible(true);
            }
        });
        entrarNaContaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = loginField.getText();
                String senha = new String(passwordField1.getPassword());
                Usuario user = null;
                try {
                    user = sist.loginUsuario(login, senha);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                if (user != null) {
                    loginSucesso.setVisible(true);
                    try {
                        TelaPrincipal telaMain = new TelaPrincipal(sist, user);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    dispose();
                } else {
                    erroLogin.setVisible(true);
                }
            }
        });
    }

    public Login(Sistema sistema) {
        sist = sistema;
        setContentPane(Login);
        setTitle("Login");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        contaCriada.setVisible(false);
        erroLogin.setVisible(false);
        loginSucesso.setVisible(false);
        criarContaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = loginField.getText();
                String senha = new String(passwordField1.getPassword());
                String nasc = nascField.getText();
                if (login.length() == 0 || senha.length() == 0 || nasc.length() == 0) {
                    erroLogin.setVisible(true);
                    return;
                }
                try {
                    sist.criaUsuario(login, senha, nasc);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                contaCriada.setVisible(true);
            }
        });
        entrarNaContaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = loginField.getText();
                String senha = new String(passwordField1.getPassword());
                Usuario user = null;
                try {
                    user = sist.loginUsuario(login, senha);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                if (user != null) {
                    loginSucesso.setVisible(true);
                    try {
                        TelaPrincipal telaMain = new TelaPrincipal(sist, user);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    dispose();
                } else {
                    erroLogin.setVisible(true);
                }
            }
        });
        deletarContaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = loginField.getText();
                String senha = new String(passwordField1.getPassword());
                try {
                    for (Usuario us : sist.getUsers())
                        if (us.getNome().equals(login) && us.getSenha().equals(senha)) {
                            sist.deletarUsuario(us);
                            ContaDeletada.setVisible(true);
                            break;
                        }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}