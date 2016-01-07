package br.com.smartlifeti.estagioweb.model.bo;

import android.content.Context;

import java.util.ArrayList;

import br.com.smartlifeti.estagioweb.integracao.Banco;
import br.com.smartlifeti.estagioweb.model.vo.Usuario;

public class EmpresaBO extends Banco implements UsuarioInterfaceBO {
    private static String TABELA = "empresas";
    private static String[] CAMPOS_TABELA = {"login", "endereco", "telefone", "nomedorepresentante", "email," +
            "cnpj", "areadeatuacao"};

    public EmpresaBO(Context context) {
        super(context);
    }

    @Override
    public String cadastrar(Usuario usuario) {
        primeiroAcesso(usuario);
        return null;
    }

    @Override
    public boolean existe(String login){
        String result = "";
        try {
            result = buscar(TABELA, new String[]{"login"}, "login", login);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result.equals("nada encontrado!")) {
            return false;
        } else {
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