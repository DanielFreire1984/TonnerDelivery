package dfsolutions.com.tonnerdelivery.helper;

import android.util.Base64;

/**
 * Created by Daniel on 15/08/2017.
 */
public class Base64Custom {

    public static String codificarBase64 (String email){

        return Base64.encodeToString(email.getBytes(), Base64.DEFAULT).replaceAll("(\\n|\\r)", "");

    }

    public static String decodificarBase64 (String emailCodificado){

        return new String(Base64.decode(emailCodificado, Base64.DEFAULT));

    }
}
