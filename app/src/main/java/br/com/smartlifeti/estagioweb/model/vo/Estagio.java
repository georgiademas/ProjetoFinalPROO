package br.com.smartlifeti.estagioweb.model.vo;

public class Estagio {
    private int numeroVagas, duracao;
    private Double salario;

    public Estagio(int numeroVagas, int duracao, Double salario) {
        this.numeroVagas = numeroVagas;
        this.duracao = duracao;
        this.salario = salario;
    }

    public int getNumeroVagas() {
        return numeroVagas;
    }

    public int getDuracao() {
        return duracao;
    }

    public Double getSalario() {
        return salario;
    }
}
