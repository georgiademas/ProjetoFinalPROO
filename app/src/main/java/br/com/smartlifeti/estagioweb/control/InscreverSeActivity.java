package br.com.smartlifeti.estagioweb.control;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.smartlifeti.estagioweb.R;
import br.com.smartlifeti.estagioweb.model.vo.Usuario;
import br.com.smartlifeti.estagioweb.model.bo.UsuarioBO;

public class InscreverSeActivity extends ActionBarActivity {
    AutoCompleteTextView nomeEditText;
    EditText mLoginView, mSenhaView;
    Button mCadastrarView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscrever_se);

        nomeEditText = (AutoCompleteTextView) findViewById(R.id.nome_edit);
        mLoginView = (EditText) findViewById(R.id.login_edit);
        mSenhaView = (EditText) findViewById(R.id.senha_edit);
        mCadastrarView = (Button) findViewById(R.id.cadastrar_usuario);

        mCadastrarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = cadastrarUsuario(nomeEditText.getText().toString(), mLoginView.getText().toString(), mSenhaView.getText().toString());
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String cadastrarUsuario(String nome, String login, String senha){
        try {
            Usuario usuario = new Usuario(nome, login, senha);
            return new UsuarioBO(InscreverSeActivity.this).cadastrar(usuario);
        }catch (Exception erro){
            return erro.getMessage();
        }
    }
}
