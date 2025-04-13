package models;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String username;
    private String senha;

    public Usuario(String username, String senha) {
        this.username = username;
        this.senha = senha;
    }

    public String getUsername() { return username; }
    public String getSenha() { return senha; }
}


