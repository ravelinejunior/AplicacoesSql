package helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class HelperBD extends SQLiteOpenHelper {
    public static int VERSION = 4;
    public static String NOME_BANCO_DB = "DB_TAREFAS";
    public static String TABELA_TAREFAS = "tarefas";

    public HelperBD(Context context) {
        super(context, NOME_BANCO_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //acessando as funções do banco de dados
        //criar tabelas
        String sql = "CREATE TABLE IF NOT EXISTS "+ TABELA_TAREFAS +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " tarefasNome VARCHAR )";


        try {

            db.execSQL(sql);
            Log.i("INFO DB","Tabela "+TABELA_TAREFAS+" criada");

        }catch (Exception e){
            e.printStackTrace();
            Log.e("INFO DB","erro ao criar tabela "+e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { //recupera as versões, toda vez que alterar as tabelas

        String alterarTabela = "ALTER TABLE "+TABELA_TAREFAS+" ADD COLUMN STATUS VARCHAR(255) NOT NULL";
        String droparTabela = "DROP TABLE IF EXISTS "+TABELA_TAREFAS+" ;";

        try {
            db.execSQL(droparTabela); // dropando as tabelas existentes (para teste)
            onCreate(db); // criando tabela

            Log.i("INFO DB","Tabela "+TABELA_TAREFAS+" atualizada");
        }catch (Exception e){
            e.printStackTrace();
            Log.e("INFO DB","erro ao criar tabela "+e.getMessage());
        }


    }
}





















