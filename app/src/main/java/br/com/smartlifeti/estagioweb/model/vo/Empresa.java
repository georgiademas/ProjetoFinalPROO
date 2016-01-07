package br.com.smartlifeti.estagioweb.model.vo;

import java.util.ArrayList;

public class Empresa extends Usuario {
//    private Endereco endereco;
    private String endereco;
    private String telefone, nomeRepresentante, email, CNPJ, area;
    private ArrayList estagios;

    public Empresa(String nome, String login, String senha, String endereco, String telefone, String nomeRepresentante,
                   String email, String CNPJ, String area) {
        super(nome, login, senha);
        this.endereco = endereco;
        this.telefone = telefone;
        this.nomeRepresentante = nomeRepresentante;
        this.email = email;
        this.CNPJ = CNPJ;
        this.area = area;
        this.estagios = new ArrayList<Estagio>();
    }

    public void addEstagio(Estagio estagio){
        this.estagios.add(estagio);
    }

    public ArrayList<Estagio> getEstagios() {
        return estagios;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getNomeRepresentante() {
        return nomeRepresentante;
    }

    public String getEmail() {
        return email;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public String getArea() {
        return area;
    }
}
