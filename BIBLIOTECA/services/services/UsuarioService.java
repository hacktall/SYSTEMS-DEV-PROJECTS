package services;

import models.Usuario;

import java.io.*;
import java.util.ArrayList;

public class UsuarioService {
    private ArrayList<Usuario> usuarios;
    private static final String FILE_PATH = "usuarios.dat";

    public UsuarioService() {
        usuarios = carregarUsuarios();
        if (usuarios.isEmpty()) {
            usuarios.add(new Usuario("admin", "1234")); // usuário padrão
            salvarUsuarios();
        }
    }

    public boolean autenticar(String username, String senha) {
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(username) && u.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }

    public boolean cadastrarUsuario(String username, String senha) {
        if (existeUsuario(username)) return false;
        usuarios.add(new Usuario(username, senha));
        salvarUsuarios();
        return true;
    }

    private boolean existeUsuario(String username) {
        return usuarios.stream().anyMatch(u -> u.getUsername().equals(username));
    }

    private void salvarUsuarios() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private ArrayList<Usuario> carregarUsuarios() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (ArrayList<Usuario>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}

