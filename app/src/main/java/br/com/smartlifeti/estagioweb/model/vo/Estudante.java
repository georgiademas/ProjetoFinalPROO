package br.com.smartlifeti.estagioweb.model.vo;

public class Estudante extends Usuario {
    private String email, instituicao;
    private Estagio atual;

    public Estudante(String nome, String login, String senha, String email, String instituicao, Estagio atual) {
        super(nome, login, senha);
        this.email = email;
        this.instituicao = instituicao;
        this.atual = atual;
    }

    public Estudante(String nome, String login, String senha, String email, String instituicao) {
        super(nome, login, senha);
        this.email = email;
        this.instituicao = instituicao;
        this.atual = null;
    }

    public Estagio getAtual() {
        if (atual == null){
            throw new NullPointerException("O aluno não possui nenhum estágio!");
        } else {
            return atual;
        }
    }

    public String getEmail() {
        return email;
    }

    public String getInstituicao() {
        return instituicao;
    }
}
