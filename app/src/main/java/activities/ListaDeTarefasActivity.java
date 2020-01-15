package activities;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import helper.HelperBD;
import helper.ClickListener;
import adapter.ListaTarefasAdapter;
import br.com.aplicacoessql.R;
import helper.TarefaDAO;
import models.Tarefas;

public class ListaDeTarefasActivity extends AppCompatActivity {
    private RecyclerView recyclerViewListaTarefas;
    private ListaTarefasAdapter adapterListaTarefas;
    private List<Tarefas> listaTarefas = new ArrayList<>();
    private Tarefas tarefaSelecionada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_tarefas);


        //configurando RecyclerView
        recyclerViewListaTarefas = findViewById(R.id.recyclerView_lista_tarefas_content);

        //instanciando valores do banco de dados
        HelperBD db = new HelperBD(getApplicationContext());

        //criar estrutura para salvar dados
        ContentValues cv = new ContentValues(); // permite definir itens como arrays
        //cv.put("tarefasNome","Teste");

        db.getWritableDatabase().insert("tarefas",null,cv); // serve para poder escrever no banco de dados (salvar dados)

        //adicionar evento de clique
        recyclerViewListaTarefas.addOnItemTouchListener(new ClickListener(getApplicationContext(),
                recyclerViewListaTarefas,
                new ClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //recuperar tarefa para edição
                        Tarefas tarefaSelecionada = listaTarefas.get(position);

                        //enviar tarefa para outra activity
                        Intent i = new Intent(getApplicationContext(),AdicionarTarefaActivity.class);
                        i.putExtra("tarefaSelecionada",tarefaSelecionada);
                        startActivity(i);
                    }

                    @Override
                    public void onLongItemClick(final View view, int position) {

                        //remover a tarefa cadastrada com um alert dialog
                        AlertDialog.Builder dialog = new AlertDialog.Builder(
                                ListaDeTarefasActivity.this
                        );

                        //recuperar tarefa selecionada
                        tarefaSelecionada = listaTarefas.get(position);
                        String tarefaSelecionadaExcluida = tarefaSelecionada.getTarefaNome();


                        dialog.setTitle("Confirmar exclusão da tarefa");
                        dialog.setMessage("Deseja realmente excluir a tarefa:\n"+tarefaSelecionadaExcluida+" ?");
                        dialog.setIcon(getResources().getDrawable(R.drawable.ic_menu_manage));
                        dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Snackbar.make(view,"Exclusão de tarefa cancelada",Snackbar.LENGTH_LONG)
                                        .setActionTextColor(getResources().getColor(android.R.color.holo_red_dark))
                                       .setAction("Confirmar", null).show();
                            }
                        });

                        dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
                                //verificar se metodo deletar funcionou
                                boolean deletarRegistro = tarefaDAO.deletar(tarefaSelecionada);
                                if (deletarRegistro){
                                    Snackbar.make(view,"Tarefa excluida com sucesso!",Snackbar.LENGTH_LONG).show();
                                    //lista precisa ser recarregada novamente
                                    carregarListaTarefas();

                                }
                            }
                        });

                        dialog.create();
                        dialog.show();

                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }));



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(),AdicionarTarefaActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    protected void onStart() { // colocar no onstart pois cada vez que o usuario sair da activity o metodo deve ser chamado.
        carregarListaTarefas();
        super.onStart();
    }

    public void carregarListaTarefas(){
    //listar tarefas

    TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext()); //para recuperar a lista de tarefas
        listaTarefas = tarefaDAO.listarTarefas();
    //exibir lista de tarefas no recycler view

        //configurar adapter
        adapterListaTarefas = new ListaTarefasAdapter(listaTarefas);

    //configurar recycler view
    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
    recyclerViewListaTarefas.setLayoutManager(layoutManager);
    recyclerViewListaTarefas.setHasFixedSize(true);
    recyclerViewListaTarefas.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));

    recyclerViewListaTarefas.setAdapter(adapterListaTarefas);
    }

}
