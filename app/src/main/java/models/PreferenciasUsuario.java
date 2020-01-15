package models;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenciasUsuario {
    private Context context;
    private SharedPreferences sharedPreferences;
    private final static String NOME_ARQUIVO = "minhas.anotacoes";
    private final static String CHAVE_NOME_ARQUIVO = "chave.anotacao";
    SharedPreferences.Editor editor;

    //passando valor Context para recuperar um contexto
    public PreferenciasUsuario(Context c) {
        this.context = c;
        sharedPreferences = context.getSharedPreferences(NOME_ARQUIVO,Context.MODE_PRIVATE);
         editor = sharedPreferences.edit();
    }

    public void salvarAnotacoes(String anotacao){
        editor.putString(CHAVE_NOME_ARQUIVO, anotacao);
        editor.commit();
    }

    public String recuperarAnotacaoSalva(){
        sharedPreferences = context.getSharedPreferences(NOME_ARQUIVO,Context.MODE_PRIVATE);
        String valorDigitado = sharedPreferences.getString(CHAVE_NOME_ARQUIVO,"");
        return valorDigitado;
    }
}
