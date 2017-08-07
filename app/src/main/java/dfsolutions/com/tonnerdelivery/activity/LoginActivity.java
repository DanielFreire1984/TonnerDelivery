package dfsolutions.com.tonnerdelivery.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;

import dfsolutions.com.tonnerdelivery.R;
import dfsolutions.com.tonnerdelivery.helper.Permissoes;
import dfsolutions.com.tonnerdelivery.helper.Preferencias;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText senha;
    private Button botaoLogar;

    //lista de permissoes necessárias para utilizar o app
    private String[] permissoesNecessarias = {
            Manifest.permission.SEND_SMS,
            //Manifest.permission.CAMERA
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Permissoes.validaPermissoes(1, this, permissoesNecessarias);

        email = (EditText) findViewById(R.id.email_id);
        senha = (EditText) findViewById(R.id.senha_id);
        botaoLogar = (Button) findViewById(R.id.botao_logar_id);

        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailUsuario = email.getText().toString();

                //Salvando dados em Preferencias (SharedPreference)
                Preferencias preferencias = new Preferencias(getApplicationContext());
                preferencias.salvarUsuarioPreferencias(emailUsuario);

                //HashMap<String, String> usuario = preferencias.getDadosUsuario();
                //Log.i("Email: ", usuario.get("email"));
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for(int resultado: grantResults){

            if(resultado == PackageManager.PERMISSION_DENIED){
                alertaValidacaoPermissao();
            }

        }
    }

    private void alertaValidacaoPermissao(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.titulo_permissao_neg);
        builder.setMessage(R.string.msg_permissao_neg);

        builder.setPositiveButton("CONFIRMAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void abrirCadastroUsuario(View view){

        Intent intent = new Intent(LoginActivity.this, CadastroUsuario.class);
        startActivity( intent );

    }



}
