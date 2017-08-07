package dfsolutions.com.tonnerdelivery.helper;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Daniel on 06/08/2017.
 */
public class Permissoes {

    public static boolean validaPermissoes(int requestCode, Activity activity, String[] permissoes){

        if(Build.VERSION.SDK_INT >= 23){

            List<String> listaPermissoes = new ArrayList<String>();

            /*Percorre as permissoes passadas, verificando
            * se determinada permissão ja foi liberada*/
            for(String permissao: permissoes){
                Boolean validaPermissao = ContextCompat.checkSelfPermission(activity, permissao) == PackageManager.PERMISSION_GRANTED;
                if(!validaPermissao) listaPermissoes.add(permissao);
            }

            /*Caso a lista esteja vazia, naão é necessário solicitar permissão*/
            if(listaPermissoes.isEmpty()) return true;

            //Solicita Permissao
            String[] novasPermissoes = new String[listaPermissoes.size()];
            listaPermissoes.toArray(novasPermissoes);
            ActivityCompat.requestPermissions(activity, novasPermissoes, requestCode);

        }

        return true;
    }


}
