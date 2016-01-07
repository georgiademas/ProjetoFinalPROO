package br.com.smartlifeti.estagioweb.model.bo;

import java.util.ArrayList;

import br.com.smartlifeti.estagioweb.model.vo.Usuario;

/**
 * Created by Eric Cerqueira on 07/01/2016.
 */
public interface UsuarioInterfaceBO {
    public String cadastrar(Usuario usuario) throws Exception;
    public boolean existe(String login) throws Exception;
    public ArrayList<String> getColuna(String coluna);
    public ArrayList<Usuario> getTodos();
}
