package br.com.smartlifeti.estagioweb.model.vo;

public class Usuario {
    private String nome, login, senha;
    private boolean primeiroAcesso;

    public Usuario(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.primeiroAcesso = true;
    }

    public String getNome() {
        return nome;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public boolean getPrimeiroAcesso() {
        return primeiroAcesso;
    }
}
