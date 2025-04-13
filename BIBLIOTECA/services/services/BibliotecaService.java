package services;

import models.Livro;

import java.io.*;
import java.util.ArrayList;

public class BibliotecaService {
    private ArrayList<Livro> livros;
    private static final String FILE_PATH = "livros.dat";

    public BibliotecaService() {
        livros = carregarLivros();
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
        salvarLivros();
    }

    public ArrayList<Livro> getLivros() {
        return livros;
    }

    public void emprestarLivro(int index) {
        Livro livro = livros.get(index);
        if (!livro.isEmprestado()) {
            livro.emprestar();
            salvarLivros();
        }
    }

    public void devolverLivro(int index) {
        Livro livro = livros.get(index);
        if (livro.isEmprestado()) {
            livro.devolver();
            salvarLivros();
        }
    }

    private void salvarLivros() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(livros);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private ArrayList<Livro> carregarLivros() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (ArrayList<Livro>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
