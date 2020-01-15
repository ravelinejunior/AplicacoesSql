package activities;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import br.com.aplicacoessql.R;

public class  SqlLiteActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_lite);

try {


    //criar banco de dados
    SQLiteDatabase banco = openOrCreateDatabase("app", MODE_PRIVATE, null);
    //criando tabelas
    banco.execSQL("CREATE TABLE IF NOT EXISTS humanos(id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR(50), idade INT(3)) ");


   // banco.execSQL("DROP TABLE humanos"); DROPANDO TABELA


//inserindo dados dentro das tabelas
/*
    banco.execSQL("INSERT INTO humanos(nome,idade) VALUES('Franklin',23)");
    banco.execSQL("INSERT INTO humanos(nome,idade) VALUES('Marina',25)");
    banco.execSQL("INSERT INTO humanos(nome,idade) VALUES('Thiago',20)");
    banco.execSQL("INSERT INTO humanos(nome,idade) VALUES('Velhoret',12)");
    banco.execSQL("INSERT INTO humanos(nome,idade) VALUES('Mazsa',20)");
    banco.execSQL("INSERT INTO humanos(nome,idade) VALUES('Meliodas',30)");
    banco.execSQL("INSERT INTO humanos(nome,idade) VALUES('Sabatina',18)");

    */

    //recuperar dados da tabela ou exclui-los

    String consulta = " SELECT * FROM humanos order by id,idade";

    String consultaComFiltros = "SELECT * FROM humanos " +
            "WHERE IDADE < 25";

    String deleteHumanos = "DELETE FROM humanos where id = null";

    //banco.execSQL("UPDATE humanos set nome = 'Updated' WHERE idade < 25");  update no banco de dados
   // banco.execSQL(deleteHumanos);// delete no banco

    Cursor cursor = banco.rawQuery(consulta,null); // cursor serve para percorrer registros
    cursor.moveToFirst();

    //recuperar indices das tabelas
    int indiceTabelaId = cursor.getColumnIndex("id");
    int indiceTabelaNome = cursor.getColumnIndex("nome");
    int indiceTabelaIdade = cursor.getColumnIndex("idade");
    //percorrer cursor
    while (cursor != null){
        Log.i("Resultado - id ",cursor.getString(indiceTabelaId));
        Log.i("Resultado - nome ",cursor.getString(indiceTabelaNome));
        Log.i("Resultado - idade ",cursor.getString(indiceTabelaIdade));
        cursor.moveToNext();
    }

}catch (Exception e){
    e.printStackTrace();
}

    }
}
