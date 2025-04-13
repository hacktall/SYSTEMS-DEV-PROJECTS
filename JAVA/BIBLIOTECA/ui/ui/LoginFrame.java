package ui;

import services.UsuarioService;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnLogin, btnRegistrar;
    private UsuarioService usuarioService;

    public LoginFrame(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setBounds(10, 10, 80, 25);
        add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(100, 10, 160, 25);
        add(txtUsuario);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(10, 40, 80, 25);
        add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(100, 40, 160, 25);
        add(txtSenha);

        btnLogin = new JButton("Entrar");
        btnLogin.setBounds(30, 90, 100, 25);
        btnLogin.addActionListener(this::login);
        add(btnLogin);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(140, 90, 100, 25);
        btnRegistrar.addActionListener(this::registrar);
        add(btnRegistrar);
    }

    private void login(ActionEvent e) {
        String usuario = txtUsuario.getText();
        String senha = new String(txtSenha.getPassword());

        if (usuarioService.autenticar(usuario, senha)) {
            JOptionPane.showMessageDialog(this, "Bem-vindo, " + usuario + "!");
            new BibliotecaFrame().setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos.");
        }
    }

    private void registrar(ActionEvent e) {
        String usuario = JOptionPane.showInputDialog(this, "Novo nome de usuário:");
        if (usuario == null || usuario.isBlank()) return;
        String senha = JOptionPane.showInputDialog(this, "Nova senha:");
        if (senha == null || senha.isBlank()) return;

        if (usuarioService.cadastrarUsuario(usuario, senha)) {
            JOptionPane.showMessageDialog(this, "Usuário registrado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Nome de usuário já existe!");
        }
    }
}
