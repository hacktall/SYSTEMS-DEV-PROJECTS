package ui;

import models.Livro;
import services.BibliotecaService;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class BibliotecaFrame extends JFrame {
    private BibliotecaService bibliotecaService = new BibliotecaService();
    private DefaultListModel<String> listModel = new DefaultListModel<>();
    private JList<String> listaLivros;

    public BibliotecaFrame() {
        setTitle("Sistema de Biblioteca");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JButton btnAdd = new JButton("Adicionar Livro");
        btnAdd.setBounds(10, 10, 150, 25);
        btnAdd.addActionListener(this::adicionarLivro);
        add(btnAdd);

        JButton btnEmprestar = new JButton("Emprestar");
        btnEmprestar.setBounds(170, 10, 100, 25);
        btnEmprestar.addActionListener(this::emprestarLivro);
        add(btnEmprestar);

        JButton btnDevolver = new JButton("Devolver");
        btnDevolver.setBounds(280, 10, 100, 25);
        btnDevolver.addActionListener(this::devolverLivro);
        add(btnDevolver);

        listaLivros = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(listaLivros);
        scrollPane.setBounds(10, 50, 370, 200);
        add(scrollPane);
    }

    private void atualizarLista() {
        listModel.clear();
        for (Livro l : bibliotecaService.getLivros()) {
            listModel.addElement(l.toString());
        }
    }

    private void adicionarLivro(ActionEvent e) {
        String titulo = JOptionPane.showInputDialog(this, "Título do livro:");
        String autor = JOptionPane.showInputDialog(this, "Autor do livro:");
        if (titulo != null && autor != null) {
            bibliotecaService.adicionarLivro(new Livro(titulo, autor));
            atualizarLista();
        }
    }

    private void emprestarLivro(ActionEvent e) {
        int index = listaLivros.getSelectedIndex();
        if (index >= 0) {
            bibliotecaService.emprestarLivro(index);
            atualizarLista();
        }
    }

    private void devolverLivro(ActionEvent e) {
        int index = listaLivros.getSelectedIndex();
        if (index >= 0) {
            bibliotecaService.devolverLivro(index);
            atualizarLista();
        }
    }
}
