package br.com.smartlifeti.estagioweb.integracao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import br.com.smartlifeti.estagioweb.model.vo.Coordenador;
import br.com.smartlifeti.estagioweb.model.vo.Empresa;
import br.com.smartlifeti.estagioweb.model.vo.Estudante;
import br.com.smartlifeti.estagioweb.model.vo.Usuario;

public class Banco extends SQLiteOpenHelper{

    private Context context;
    final private String sql_cria_tbUsuarios = "CREATE TABLE IF NOT EXISTS usuarios " +
            "(nome TEXT NOT NULL, " +
            "login TEXT NOT NULL primary key, " +
            "primeiroacesso TEXT NOT NULL, " +
            "senha TEXT NOT NULL)";

    final private String sql_cria_tbEstudantes = "CREATE TABLE IF NOT EXISTS estudantes " +
            "(login TEXT NOT NULL, " +
            "email TEXT NOT NULL primary key, " +
            "instituicao TEXT NOT NULL)";

    final private String sql_cria_tbEmpresas = "CREATE TABLE empresas " +
            "(login TEXT NOT NULL, " +
            "endereco TEXT NOT NULL, " +
/*
            "bairro TEXT NOT NULL,\n" +
            "numero TEXT NOT NULL,\n" +
            "estado TEXT NOT NULL,\n" +
            "cidade TEXT NOT NULL,\n" +
            "cep TEXT NOT NULL,\n" +
*/
            "telefone TEXT NOT NULL, " +
            "nomedorepresentante TEXT NOT NULL, " +
            "email TEXT NOT NULL, " +
            "cnpj TEXT NOT NULL primary key, " +
            "areadeatuacao TEXT NOT NULL)";

    final private String sql_cria_tbEstagios = "CREATE TABLE IF NOT EXISTS estagios " +
            "(cnpj TEXT NOT NULL primary key," +
            "numvagas TEXT NOT NULL, " +
            "duracao TEXT NOT NULL, " +
            "salario TEXT NOT NULL)";

    final private String sql_cria_tbConvenios = "CREATE TABLE IF NOT EXISTS convenios " +
            "(id INT IDENTITY primary key," +
            "assinatura TEXT NOT NULL)";

    public Banco(Context context) {
        super(context, "patrimonio", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        criarTabelaUsuarios(db);
        criarTabelaEmpresas(db);
        criarTabelaEstudantes(db);
        criarTabelaEstagios(db);
        criarTabelaConvenios(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void criarTabelaUsuarios(SQLiteDatabase db) {
        db.execSQL(sql_cria_tbUsuarios);
    }

    private void criarTabelaEmpresas(SQLiteDatabase db) {
        db.execSQL(sql_cria_tbEmpresas);
    }

    private void criarTabelaEstudantes(SQLiteDatabase db) {
        db.execSQL(sql_cria_tbEstudantes);
    }

    private void criarTabelaEstagios(SQLiteDatabase db) {
        db.execSQL(sql_cria_tbEstagios);
    }

    private void criarTabelaConvenios(SQLiteDatabase db) {
        db.execSQL(sql_cria_tbConvenios);
    }

    protected String inserirDados(String nome_tabela, String[] campos, String[] valores){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        for(int i=0;i<campos.length;i++) {
            cv.put(campos[i],valores[i]);
        }
        db.insert(nome_tabela, null, cv);
        return "Cadastro realizado com sucesso!";
    }

    protected void primeiroAcesso(Usuario usuario){
        if (usuario instanceof Estudante){
            Estudante estudante = (Estudante) usuario;
            String[] valores = {estudante.getEmail(), estudante.getInstituicao()};
            inserirDados("estudantes", new String[]{"login","email", "instituicao"}, valores);
        } else if (usuario instanceof Empresa){
            Empresa empresa = (Empresa) usuario;
            String[] valores = {empresa.getEndereco(),empresa.getTelefone(),empresa.getNomeRepresentante(),
                    empresa.getEmail(),empresa.getCNPJ(),empresa.getArea()};
            inserirDados("estudantes", new String[]{"login","endereco", "telefone", "nomedorepresentante", "email," +
                    "cnpj","areadeatuacao"}, valores);
        } else if (usuario instanceof Coordenador){

        }
        SQLiteDatabase db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("login", usuario.getLogin());
        valores.put("senha", usuario.getSenha());
        valores.put("primeiroacesso", Boolean.valueOf(usuario.getPrimeiroAcesso()).toString());
        db.update("usuarios", valores, "primeiroacesso = false", null);
        db.close();
    }

    protected ArrayList<String> listarTabela(String nome_tabela, String coluna_Tabela){
        SQLiteDatabase db = getReadableDatabase(); //abra o banco de dados no modo leitura
        Cursor c = db.rawQuery("select * from "+nome_tabela, null); //faça uma busca na tabela desejada e guarde no cursor
        ArrayList<String> retorno = new ArrayList<String>();
        if(c.getCount()>0){ //se a consulta retornar algum valor
            c.moveToFirst(); //mova o cursor para a primeira linha do registro
            do{
                retorno.add(c.getString(c.getColumnIndex(coluna_Tabela)));
            }while (c.moveToNext());
        }else{
            retorno.clear();
        }
        return retorno;
    }

    protected String getTabela(String nome_tabela){
        SQLiteDatabase db = getReadableDatabase(); //abra o banco de dados no modo leitura
        Cursor c = db.rawQuery("select * from " + nome_tabela, null); //faça uma busca na tabela desejada e guarde no cursor
        String retorno ="{ \"" + nome_tabela + "\": [";
        if(c.getCount()>0){ //se a consulta retornar algum valor
            c.moveToFirst(); //mova o cursor para a primeira linha do registro
            do{
                int i=0;
                for (i=0;i<c.getColumnCount()-1;i++){  //pega o numero de colunas da tabela
                    retorno += "{ \"" + c.getColumnName(i) + "\": \"" + c.getString(i) + "\",";
                }
                retorno += "{ \"" + c.getColumnName(i) + "\": \"" + c.getString(i) + "\"},";
            }while (c.moveToNext());
            retorno += "] }";
        }
        return retorno;
    }

    protected String buscar(String tabela, String[] camposRetornados, String campoBuscado, String valor) throws Exception{
        String campos = "";
        String retorno = "";
        if(campoBuscado.isEmpty()){
            retorno = "digite um nome a buscar!";
        }else{
            for(int i=0;i<camposRetornados.length;i++) { //pega o array "camposRetornados" e organiza em uma linha só
                campos += camposRetornados[i];
                if (i<camposRetornados.length-1) {//
                    campos += ",";
                }
            }
            SQLiteDatabase db = getReadableDatabase(); //abra o banco de dados no modo leitura
            Cursor c = db.rawQuery("select "+campos+" from "+tabela+" where "+campoBuscado+" like '%"+valor+"%'", null); //faça uma busca na tabela desejada e guarde no cursor

            if(c.getCount()>0){ //se a consulta retornar algum valor
                c.moveToFirst(); //mova o cursor para a primeira linha do registro
                do{
                    for (int i=0;i<c.getColumnCount();i++){  //pega o numero de colunas da tabela
                        retorno+=c.getString(i);
                    }
                }while (c.moveToNext());
            }else{
                retorno = "nada encontrado!";
            }
        }

        return retorno;
    }
}
