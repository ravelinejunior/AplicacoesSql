package activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import br.com.aplicacoessql.R;
import helper.TarefaDAO;
import models.Tarefas;

public class AdicionarTarefaActivity extends AppCompatActivity {
    private TextInputEditText tarefaDigitadaTextInputAdicionarTarefa;
    private Tarefas tarefaClicada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);
        tarefaDigitadaTextInputAdicionarTarefa = findViewById(R.id.tarefa_adicionar_text_input_id);

        //recuperar tarefa para edição
        tarefaClicada = (Tarefas) getIntent().getSerializableExtra("tarefaSelecionada");

        //configurar caixa de texto
        if (tarefaClicada != null){
            tarefaDigitadaTextInputAdicionarTarefa.setText(tarefaClicada.getTarefaNome());
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista_tarefas,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.opcao_salvar_id_menu:


                //Salvando dados
                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());

                if (tarefaClicada != null){ //edição de tarefa

                    String tarefasNomeDigitado = tarefaDigitadaTextInputAdicionarTarefa.getText().toString();
                    if (!tarefasNomeDigitado.isEmpty()){

                        Tarefas tarefas = new Tarefas();
                        tarefas.setTarefaNome(tarefaClicada.getTarefaNome());
                        tarefas.setId(tarefaClicada.getId());

                        //atualizar dados no banco de dados
                        if (tarefaDAO.atualizar(tarefas)){
                            Toast.makeText(this, "Atualizada a tarefa com sucesso!", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(this, "Erro ao atualizar!", Toast.LENGTH_SHORT).show();
                        }

                    }

                }else{
                    String tarefasNomeDigitado = tarefaDigitadaTextInputAdicionarTarefa.getText().toString();
                    if(!tarefasNomeDigitado.isEmpty()) { //salvar tarefa nova
                        Tarefas tarefas = new Tarefas();
                        tarefas.setTarefaNome(tarefasNomeDigitado);
                        boolean salvouTarefa = tarefaDAO.salvar(tarefas); //retorna um boolean, verificar se deu certo
                        if (salvouTarefa){
                            Toast.makeText(this, "Tarefa salva com sucesso!", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            Toast.makeText(this, "Erro ao salvar tarefa.", Toast.LENGTH_SHORT).show();
                        }


                    }
                }

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
