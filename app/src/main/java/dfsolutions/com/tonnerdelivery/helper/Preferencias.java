package dfsolutions.com.tonnerdelivery.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.StringRes;

import java.util.HashMap;

/**
 * Created by Daniel on 06/08/2017.
 */
public class Preferencias {

    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private static final String ARQUIVO_PREFERENCIA = "TonnerDelivery.Preferencias";
    private static final int ARQUIVO_MODE = 0; ///Mode = 0 -> Apenas seu App terá acesso ao arquivo
    private static final String CHAVE_NOME = "nome";
    private static final String CHAVE_EMAIL = "email";

    //Construtor
    public Preferencias(Context contextParametros){

        context = contextParametros;
        preferences = context.getSharedPreferences(ARQUIVO_PREFERENCIA, ARQUIVO_MODE);
        editor = preferences.edit();

    }

    public void salvarUsuarioPreferencias(String email){

        editor.putString(CHAVE_EMAIL, email);
        editor.commit();

    }

    public void salvarUsuarioPreferencias(String email, String nome){

        editor.putString(CHAVE_EMAIL, email);
        editor.putString(CHAVE_NOME, nome);
        editor.commit();

    }

    public HashMap<String, String> getDadosUsuario(){

        HashMap<String, String> dadosUsuario = new HashMap<>();

        dadosUsuario.put(CHAVE_EMAIL, preferences.getString(CHAVE_EMAIL, null));
        dadosUsuario.put(CHAVE_NOME, preferences.getString(CHAVE_NOME, null));

        return dadosUsuario;

    }
}
