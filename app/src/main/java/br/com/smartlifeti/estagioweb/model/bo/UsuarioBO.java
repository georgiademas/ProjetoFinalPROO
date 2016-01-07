package br.com.smartlifeti.estagioweb.model.bo;

import android.content.Context;

import java.util.ArrayList;

import br.com.smartlifeti.estagioweb.integracao.Banco;
import br.com.smartlifeti.estagioweb.model.vo.Usuario;

public class UsuarioBO extends Banco implements UsuarioInterfaceBO {
    private static String TABELA = "usuarios";
    private static String[] CAMPOS_TABELA =  {"nome", "login", "senha","primeiroacesso"};

    public UsuarioBO(Context context) {
        super(context);
    }

    public boolean makeLogin(String login, String senha) {
        String result = null;
        try {
            result = buscar(TABELA, new String[]{"login", "senha"}, "login", login);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result.equals(login+senha)){
            return true;
        } else {
            return false;
        }
    }

    public boolean isPrimeiroAcesso(String login) throws Exception {
        String result = buscar(TABELA, new String[]{"primeiroacesso"}, "login", login);
        if (result.equals("true")){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String cadastrar(Usuario usuario) throws Exception{
        String nome = usuario.getNome();
        String login = usuario.getLogin();
        String senha = usuario.getSenha();
        Boolean primeiroAcesso = usuario.getPrimeiroAcesso();
        String[] valores = {nome, login, senha, Boolean.valueOf(primeiroAcesso).toString()};
        return inserirDados(TABELA, CAMPOS_TABELA, valores);
    }

    @Override
    public boolean existe(String login) throws Exception {
        String result = buscar(TABELA, new String[]{"login"}, "login", login);
        if(result.equals("nada encontrado!")){
            return false;
        } else{
            return true;
        }
    }

    @Override
    public ArrayList<String> getColuna(String coluna) {
        return listarTabela(TABELA, coluna);
    }

    @Override
    public ArrayList<Usuario> getTodos() {
        getTabela(TABELA);
        return new ArrayList<Usuario>();
    }
}
