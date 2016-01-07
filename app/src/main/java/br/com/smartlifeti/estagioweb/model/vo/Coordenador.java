package br.com.smartlifeti.estagioweb.model.vo;

import java.util.ArrayList;
import java.util.HashMap;

public class Coordenador extends Usuario {
    private ArrayList<Empresa> empresasAceitas;
    private HashMap<Estagio, Convenio> convenios;

    public Coordenador(String nome, String login, String senha) {
        super(nome, login, senha);
        empresasAceitas = new ArrayList<Empresa>();
    }
}
