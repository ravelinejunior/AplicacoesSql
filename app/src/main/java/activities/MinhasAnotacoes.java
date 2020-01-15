package activities;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;

import br.com.aplicacoessql.R;
import models.PreferenciasUsuario;

public class MinhasAnotacoes extends AppCompatActivity {

    private PreferenciasUsuario preferenciasUsuario;
    private EditText anotacaoDigitada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_anotacoes);
        anotacaoDigitada = findViewById(R.id.notas_digitadas_id_minhas_anotacoes);


        //recuperando os valores de contexto para passar para o models
        preferenciasUsuario = new PreferenciasUsuario(getApplicationContext());

        FloatingActionButton fab = findViewById(R.id.fab_minhas_anotacoes);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //validar para verificar se algo foi digitado
                String valorDigitado = anotacaoDigitada.getText().toString();
                if (valorDigitado.equals("")){
                    Snackbar.make(view, "Digite alguma coisa", Snackbar.LENGTH_LONG)
                            .setAction("Animal", null).show();
                }else{
                    preferenciasUsuario.salvarAnotacoes(valorDigitado);
                    Snackbar.make(view, "Anotação salva", Snackbar.LENGTH_LONG)
                            .setAction("Animal", null).show();
                }


            }
        });
        String anotacaoRecuperada = preferenciasUsuario.recuperarAnotacaoSalva();
        if (!anotacaoRecuperada.equals("")){
            anotacaoDigitada.setText(anotacaoRecuperada);
        }
    }

}
