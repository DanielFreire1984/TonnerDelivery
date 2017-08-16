package dfsolutions.com.tonnerdelivery.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import dfsolutions.com.tonnerdelivery.R;
import dfsolutions.com.tonnerdelivery.config.ConfiguracaoFirebase;
import dfsolutions.com.tonnerdelivery.helper.Base64Custom;
import dfsolutions.com.tonnerdelivery.model.Usuario;

public class CadastroUsuario extends AppCompatActivity {

    private EditText nome;
    private EditText email;
    private EditText senha;
    private Button botaoCadastrar;
    private Usuario usuario;

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        nome = (EditText) findViewById(R.id.cadastro_nome_id);
        email = (EditText) findViewById(R.id.cadastro_email_id);
        senha = (EditText) findViewById(R.id.cadastro_senha_id);
        botaoCadastrar = (Button) findViewById(R.id.botao_cadastrar_id);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(nome.getText().toString().isEmpty() || email.getText().toString().isEmpty() || senha.getText().toString().isEmpty()){
                    Toast.makeText(CadastroUsuario.this, "Preecher todos os campos", Toast.LENGTH_SHORT).show();
                }else{
                    usuario = new Usuario();
                    usuario.setNome(nome.getText().toString());
                    usuario.setEmail(email.getText().toString());
                    usuario.setSenha(senha.getText().toString());
                    cadastrarUsuario();
                }
            }
        });
    }

    private void cadastrarUsuario() {

        autenticacao = ConfiguracaoFirebase.getAutenticacaoFirebase();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(CadastroUsuario.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(CadastroUsuario.this, "Usuário Cadastrado com Sucesso!", Toast.LENGTH_SHORT).show();
                    //Recuperando dados do usuario criado
                    String identificadorUsuario = Base64Custom.codificarBase64(usuario.getEmail().toString());
                    usuario.setId(identificadorUsuario);
                    usuario.salvar();
                    abrirLoginUsuario();
                }else{

                    String erroExcecao = "";

                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        erroExcecao = "Digite uma senha mais forte com mínimo 6 caracteres";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        erroExcecao = "Digite um email válido";
                    }catch (FirebaseAuthUserCollisionException e){
                        erroExcecao = "Email já cadastrado. Digite outro email";
                    }catch (Exception e){
                        erroExcecao = "Ao cadastrar usuário";
                        e.printStackTrace();
                    }
                    Toast.makeText(CadastroUsuario.this, "Erro: " + erroExcecao, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void abrirLoginUsuario(){
        Intent intent = new Intent(CadastroUsuario.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


}
