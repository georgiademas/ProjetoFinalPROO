package br.com.smartlifeti.estagioweb.control;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.com.smartlifeti.estagioweb.model.vo.Empresa;
import br.com.smartlifeti.estagioweb.R;
import br.com.smartlifeti.estagioweb.model.vo.Usuario;
import br.com.smartlifeti.estagioweb.model.bo.EmpresaBO;

public class InscreverEmpresaActivity extends AppCompatActivity {
    EditText mEnderecoView, mTelefoneView, mNomeRepresentanteView, mEmailView, mCNPJView, mAreaView;
    Button mCadastrarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscrever_empresa);

        mEnderecoView = (EditText) findViewById(R.id.endereco_edit);
        mTelefoneView = (EditText) findViewById(R.id.telefone_edit);
        mNomeRepresentanteView = (EditText) findViewById(R.id.nome_repre_edit);
        mEmailView = (EditText) findViewById(R.id.email_edit);
        mCNPJView = (EditText) findViewById(R.id.cnpj_edit);
        mAreaView = (EditText) findViewById(R.id.area_edit);
        mCadastrarView = (Button) findViewById(R.id.cadastrar_empresa_btn);

        Bundle extras = getIntent().getExtras();
        final Empresa empresa = new Empresa("", extras.getString("login"), extras.getString("senha"),
                mEnderecoView.getText().toString(), mTelefoneView.getText().toString(),
                mNomeRepresentanteView.getText().toString(), mEmailView.getText().toString(),
                mCNPJView.getText().toString(), mAreaView.getText().toString());

        mCadastrarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String s = cadastrar(empresa);
                //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(InscreverEmpresaActivity.this, MainEmpresaActivity.class));
            }
        });

    }

    public String cadastrar(Usuario usuario){
        try {
            return new EmpresaBO(InscreverEmpresaActivity.this).cadastrar(usuario);
        }catch (Exception erro){
            return erro.getMessage();
        }
    }
}
