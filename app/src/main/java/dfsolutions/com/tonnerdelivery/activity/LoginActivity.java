package dfsolutions.com.tonnerdelivery.activity;

import android.Manifest;
import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;

import dfsolutions.com.tonnerdelivery.R;
import dfsolutions.com.tonnerdelivery.config.ConfiguracaoFirebase;
import dfsolutions.com.tonnerdelivery.helper.Permissoes;
import dfsolutions.com.tonnerdelivery.helper.Preferencias;
import dfsolutions.com.tonnerdelivery.model.Usuario;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;


public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText senha;
    private Button botaoLogar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;
    private ProgressDialog mProgressDialog;

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

        verificarUsuarioLogado();

        mProgressDialog = new ProgressDialog(this);

        email = (EditText) findViewById(R.id.email_id);
        senha = (EditText) findViewById(R.id.senha_id);
        botaoLogar = (Button) findViewById(R.id.botao_logar_id);

        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(email.getText().toString().isEmpty() || senha.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Preencher campos Login e Senha", Toast.LENGTH_SHORT).show();
                }else{
                    usuario = new Usuario();
                    String emailUsuario = email.getText().toString();
                    usuario.setEmail(emailUsuario);
                    usuario.setSenha(senha.getText().toString());

                    mProgressDialog.setMessage("Validando usuário...");
                    mProgressDialog.show();

                    //Salvando dados em Preferencias (SharedPreference)
                    Preferencias preferencias = new Preferencias(getApplicationContext());
                    preferencias.salvarUsuarioPreferencias(emailUsuario, usuario.getNome());

                    validarLogin();

                    //HashMap<String, String> usuario = preferencias.getDadosUsuario();
                    //Log.i("Email: ", usuario.get("email"));
                }
            }
        });

    }

    private void verificarUsuarioLogado(){
        autenticacao = ConfiguracaoFirebase.getAutenticacaoFirebase();
        if(autenticacao.getCurrentUser() != null){
            abrirTelaPrincipal();
        }
    }

    private void validarLogin(){

        autenticacao = ConfiguracaoFirebase.getAutenticacaoFirebase();
        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    mProgressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Usuário logado com sucesso", Toast.LENGTH_SHORT).show();
                    abrirTelaPrincipal();
                }else{
                    String erroExecption = "";
                    try {
                        throw task.getException();
                    }catch (FirebaseAuthInvalidUserException e){
                        erroExecption = "Email não cadastrado ou o usuário foi bloqueado";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        erroExecption = "Senha incorreta. Tente novamente";
                    }catch (Exception e){
                        erroExecption = "Erro ao logar usuário";
                        e.printStackTrace();
                    }
                    Toast.makeText(LoginActivity.this, erroExecption, Toast.LENGTH_SHORT).show();
                }
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

    private void abrirTelaPrincipal(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void abrirCadastroUsuario(View view){

        Intent intent = new Intent(LoginActivity.this, CadastroUsuario.class);
        startActivity( intent );

    }

}
