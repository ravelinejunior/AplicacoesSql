package helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import models.Tarefas;

public class TarefaDAO implements iTarefasDAO {
    //DATA ACCESS OBJECT (salvando dados)
    //criar uma interface para implementar alguns metodos
    private final SQLiteDatabase escreveDadosTabela;
    private final SQLiteDatabase leDadosTabela;

    public TarefaDAO(Context context) {

        HelperBD helperBD = new HelperBD(context);
        escreveDadosTabela = helperBD.getWritableDatabase(); //recuperando metodo para escrever e salvar dados no banco de dados
        leDadosTabela = helperBD.getReadableDatabase(); // recuperando metodo para ler dados das tabelas no banco de dados

    }

    @Override
    public boolean salvar(Tarefas tarefas) {
        ContentValues cv = new ContentValues();
        cv.put("tarefasNome",tarefas.getTarefaNome());
        try{

            escreveDadosTabela.insert(HelperBD.TABELA_TAREFAS,null,cv);
            Log.i("INFO","Tabela criada com sucesso");

        }catch (Exception e){
            e.printStackTrace();
            Log.e("INFO","Erro ao salvar tarefa");
            return false;
        }

        return true;
    }

    @Override
    public boolean deletar(Tarefas tarefas) {
        String[] args = {tarefas.getId().toString()};
        try {
                escreveDadosTabela.delete(HelperBD.TABELA_TAREFAS,"id=?",args);
            Log.i("INFO","Dado deletado com sucesso!");

        }catch (Exception e){
            e.printStackTrace();
            Log.e("ERRO","Erro ao remover dado "+e.getMessage());
        }

        return true;
    }

    @Override
    public boolean atualizar(Tarefas tarefas) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("tarefasNome",tarefas.getTarefaNome());

        try {

            //criar array de String para passar como argumento no metodo update para recuperar valores
            String[] args = {tarefas.getId().toString()};
            escreveDadosTabela.update(HelperBD.TABELA_TAREFAS,
                    contentValues,
                    "id=?",args
                    );

            Log.i("INFO UPDATE","Tarefa atualizada");
        }catch (Exception e){
            Log.e("INFO UPDATE","Erro ao atualizar tarefa");
            return false;
        }


        return true;
    }

    @Override
    public List<Tarefas> listarTarefas() {
        List<Tarefas> listaTarefas = new ArrayList<>();

        String sql = "Select * FROM "+HelperBD.TABELA_TAREFAS+" ;"; //Listando todos os dados
        Cursor cursor = leDadosTabela.rawQuery(sql,null);

        while (cursor.moveToNext()){
            Tarefas tarefa = new Tarefas();

            Long id = cursor.getLong(cursor.getColumnIndex("id"));
            String tarefaNome = cursor.getString(cursor.getColumnIndex("tarefasNome"));

            tarefa.setId(id);
            tarefa.setTarefaNome(tarefaNome);

            listaTarefas.add(tarefa);
        }


        return listaTarefas;
    }


}
