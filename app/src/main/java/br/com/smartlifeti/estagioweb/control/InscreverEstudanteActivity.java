package br.com.smartlifeti.estagioweb.control;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.smartlifeti.estagioweb.model.vo.Estudante;
import br.com.smartlifeti.estagioweb.R;
import br.com.smartlifeti.estagioweb.model.vo.Usuario;
import br.com.smartlifeti.estagioweb.model.bo.EstudanteBO;

public class InscreverEstudanteActivity extends AppCompatActivity {
    EditText mEmailView, mInstituicaoView;
    Button mCadastrarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscrever_estudante);

        mEmailView = (EditText) findViewById(R.id.email_estudante_edit);
        mInstituicaoView = (EditText) findViewById(R.id.instituicao_edit);
        mCadastrarView = (Button) findViewById(R.id.cadastrar_estudante_btn);

        Bundle extras = getIntent().getExtras();
        final Estudante estudante = new Estudante("", extras.getString("login"), extras.getString("senha"),
                mEmailView.getText().toString(), mInstituicaoView.getText().toString());

        mCadastrarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cadastrar(estudante);
                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(InscreverEstudanteActivity.this, MainEstudanteActivity.class));
            }
        });

    }

    public String cadastrar(Usuario usuario){
        try {
            return new EstudanteBO(InscreverEstudanteActivity.this).cadastrar(usuario);
        }catch (Exception erro){
            return erro.getMessage();
        }
    }
}
