package activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

import br.com.aplicacoessql.R;

public class MainActivity extends AppCompatActivity {
    private TextInputEditText textoNomeDigitado;
    private Button botaoSalvarDados;
    private TextView textoNomeExibido;
    private static final String NOME_ARQUIVO = "nome";
    private Button botaoNavigationActivity;
    private Button botaoMinhasAnotacoesActivity;
    private Button botaoSqlLiteActivity;
    private Button botaoListadeTarefasActivity;




    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carregarElementos();

        FloatingActionButton fab = findViewById(R.id.fab_minhas_anotacoes);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //salvar dados
                SharedPreferences sharedPreferences = getSharedPreferences(NOME_ARQUIVO,MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String nomeDigitado = textoNomeDigitado.getText().toString();

                //antes de tudo, validar o nome caso usuario nao digite nada
                if(nomeDigitado.equals("")){
                    Snackbar.make(view,"Digite seu nome.",Snackbar.LENGTH_LONG).show();

                } else{
                    editor.putString("nome",nomeDigitado);
                    editor.commit();
                    textoNomeExibido.setText("Olá, "+nomeDigitado);
                    Snackbar.make(view,"Bem vindo "+nomeDigitado,Snackbar.LENGTH_LONG).show();
                    textoNomeDigitado.setText("");
                    textoNomeDigitado.clearFocus();
                }

            }
        });

  /*      botaoSalvarDados.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                //salvar dados
                SharedPreferences sharedPreferences = getSharedPreferences(NOME_ARQUIVO,MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String nomeDigitado = textoNomeDigitado.getText().toString();

                //antes de tudo, validar o nome caso usuario nao digite nada
                if(nomeDigitado.equals("")){
                    Snackbar.make(v,"Digite seu nome animal.",Snackbar.LENGTH_LONG).show();
                } else{
                    editor.putString("nome",nomeDigitado);
                    editor.commit();
                    textoNomeExibido.setText("Olá, "+nomeDigitado);
                    textoNomeDigitado.setText("");
                }

            }
        });  */

        //recuperar dados que foram salvos
        SharedPreferences sharedPreferences = getSharedPreferences(NOME_ARQUIVO,0);

        //validar se existe algo para ser compartilhado nas preferencias
        if (sharedPreferences.contains("nome")){
            String nomeDigitado = sharedPreferences.getString("nome","Usuario invalido");
            textoNomeExibido.setText("Olá, "+nomeDigitado);
            Toast.makeText(this, "Bem vindo, "+nomeDigitado, Toast.LENGTH_SHORT).show();

        }
        else{
            Toast.makeText(this, "Olá. Qual seu nome?", Toast.LENGTH_SHORT).show();
        }

        botaoNavigationActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),NavigationActivity.class);
                startActivity(i);
            }
        });

        botaoMinhasAnotacoesActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MinhasAnotacoes.class);
                startActivity(i);
            }
        });

        botaoSqlLiteActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SqlLiteActivity.class);
                startActivity(i);
            }
        });

        botaoListadeTarefasActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ListaDeTarefasActivity.class);
                startActivity(i);
            }
        });
    }





    public void carregarElementos(){
        textoNomeDigitado = findViewById(R.id.edit_text_input_id_nome);
        botaoSalvarDados = findViewById(R.id.botao_salvar_id_dados);
        textoNomeExibido = findViewById(R.id.nome_resultado_text_id);
        botaoNavigationActivity = findViewById(R.id.acessar_navigation_act);
        botaoMinhasAnotacoesActivity = findViewById(R.id.botao_minhas_anotacoes_act);
        botaoSqlLiteActivity = findViewById(R.id.botao_sql_lite_act);
        botaoListadeTarefasActivity = findViewById(R.id.botao_lista_de_tarefas_id_act);
    }


}
