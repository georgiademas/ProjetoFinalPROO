package br.com.smartlifeti.estagioweb.control;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import br.com.smartlifeti.estagioweb.R;

public class PrimeiroAcessoActivity extends AppCompatActivity {
    RadioButton estudanteRB, empresaRB, coordenadorRB;
    Button proximoBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primeiro_acesso);

        estudanteRB = (RadioButton) findViewById(R.id.estudante_rb);
        empresaRB = (RadioButton) findViewById(R.id.empresa_rb);
        coordenadorRB = (RadioButton) findViewById(R.id.coordenador_rb);
        proximoBTN = (Button) findViewById(R.id.pa_proximo_btn);

        final Bundle extras = getIntent().getExtras();

        proximoBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (estudanteRB.isChecked()){
                    Intent intent = new Intent(PrimeiroAcessoActivity.this, InscreverEstudanteActivity.class);
                    intent.putExtra("login", extras.getString("login"));
                    intent.putExtra("senha", extras.getString("senha"));
                    startActivity(intent);
                } else if (empresaRB.isChecked()){
                    Intent intent = new Intent(PrimeiroAcessoActivity.this, InscreverEmpresaActivity.class);
                    intent.putExtra("login", extras.getString("login"));
                    intent.putExtra("senha", extras.getString("senha"));
                    startActivity(intent);
                } else if (coordenadorRB.isChecked()){
                    startActivity(new Intent(PrimeiroAcessoActivity.this, MainCoordenadorActivity.class));
                } else {

                }
            }
        });
    }
}
