package dfsolutions.com.tonnerdelivery.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dfsolutions.com.tonnerdelivery.R;
import dfsolutions.com.tonnerdelivery.config.ConfiguracaoFirebase;
import dfsolutions.com.tonnerdelivery.helper.Base64Custom;
import dfsolutions.com.tonnerdelivery.model.Produtos;

public class CadastroProduto extends AppCompatActivity {

    private Toolbar toolbar;
    private Spinner spinnerProduto, spinnerMarca;
    private String selecaoItemSpinnerProduto, selecaoItemSpinnerMarca;
    private ArrayList<String> listItemTipo;
    private ArrayList<String> listItemMarca;
    private EditText titulo, descricao, valor, saldo;
    private Button botaoCadastrarProduto;
    private ImageView imagemProduto;
    private Produtos produtos;
    private static final int GALLERY_INTENT = 2;
    private static Uri uri;
    private ProgressDialog mProgressCadastrar;
    private ProgressDialog mProgressDialog;

    private StorageReference storageFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);

        storageFirebase = ConfiguracaoFirebase.referenciaStorage();

        mProgressDialog = new ProgressDialog(this);

        toolbar         = (Toolbar) findViewById(R.id.toolbar_cadastro_produto_id);
        spinnerProduto  = (Spinner) findViewById(R.id.spinner_tipo_produto_id);
        spinnerMarca    = (Spinner) findViewById(R.id.spinner_marca_produto_id);
        titulo          = (EditText) findViewById(R.id.tv_titulo_produto_id);
        descricao       = (EditText) findViewById(R.id.tv_descricao_produto_id);
        valor           = (EditText) findViewById(R.id.tv_valor_produto_id);
        saldo           = (EditText) findViewById(R.id.tv_qtd_produto_id);
        imagemProduto   = (ImageView) findViewById(R.id.img_produto_id);
        botaoCadastrarProduto = (Button) findViewById(R.id.bt_cadastrar_produto_id);

        //Setando ProgressDialog
        mProgressCadastrar = new ProgressDialog(this);

        //Configurando a toolbar
        toolbar.setTitle(R.string.tb_title_cadastro_produto);
        toolbar.setNavigationIcon(R.drawable.ic_action_arrow_left);
        setSupportActionBar(toolbar);

        //Configurando os Spinners e os Adapters
        ArrayAdapter<String> spinnerAdapterProduto = new ArrayAdapter<String>(CadastroProduto.this,
                R.layout.spinner_item, getResources().getStringArray(R.array.spinner_produto_options));
        spinnerAdapterProduto.createFromResource(this, R.array.spinner_produto_options, R.layout.spinner_item);
        spinnerProduto.setAdapter(spinnerAdapterProduto);

        ArrayAdapter<String> spinnerAdapterMarca = new ArrayAdapter<String>(CadastroProduto.this,
                R.layout.spinner_item, getResources().getStringArray(R.array.spinner_marca_options));
        spinnerAdapterMarca.createFromResource(this, R.array.spinner_marca_options, R.layout.spinner_item);
        spinnerMarca.setAdapter(spinnerAdapterMarca);

        //Tratando a selecao do Snipper tipo de Produto
        spinnerProduto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                List<String> listItemTipo = Arrays.asList(getResources().getStringArray(R.array.spinner_produto_options));

                switch (i){
                    case 0:
                        selecaoItemSpinnerProduto = listItemTipo.get(0);
                        break;
                    case 1:
                        selecaoItemSpinnerProduto = listItemTipo.get(1);
                        break;
                    case 2:
                        selecaoItemSpinnerProduto = listItemTipo.get(2);
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Tratando a selecao do Snipper tipo de Produto
        spinnerMarca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                List<String> listItemMarca = Arrays.asList(getResources().getStringArray(R.array.spinner_marca_options));

                switch (i){
                    case 0:
                        selecaoItemSpinnerMarca = listItemMarca.get(0);
                        break;
                    case 1:
                        selecaoItemSpinnerMarca = listItemMarca.get(1);
                        break;
                    case 2:
                        selecaoItemSpinnerMarca = listItemMarca.get(2);
                        break;
                    case 3:
                        selecaoItemSpinnerMarca = listItemMarca.get(3);
                        break;
                    case 4:
                        selecaoItemSpinnerMarca = listItemMarca.get(4);
                        break;
                    case 5:
                        selecaoItemSpinnerMarca = listItemMarca.get(5);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        botaoCadastrarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                produtos = new Produtos();
                produtos.setTipo(selecaoItemSpinnerProduto);
                produtos.setMarca(selecaoItemSpinnerMarca);
                produtos.setTitulo(titulo.getText().toString());
                produtos.setDescricao(descricao.getText().toString());
                produtos.setValor(valor.getText().toString());
                produtos.setQtd(saldo.getText().toString());
                Log.i("SALDO: ", produtos.getQtd());

                if(selecaoItemSpinnerProduto.equals("Selecione o tipo de produto") ||
                        selecaoItemSpinnerMarca.equals("Selecione a marca") ||
                        produtos.getTitulo().isEmpty() ||
                        produtos.getDescricao().isEmpty() ||
                        produtos.getValor().isEmpty() ||
                        produtos.getQtd().isEmpty()){

                    Toast.makeText(CadastroProduto.this, R.string.msg_alerta_cadastrar_produto, Toast.LENGTH_LONG).show();

                }else{
                    CadastrarProduto();
                }

            }
        });

        imagemProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, GALLERY_INTENT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GALLERY_INTENT && resultCode == RESULT_OK){

            uri = data.getData();
            imagemProduto.setImageURI(uri);

        }
    }

    private void CadastrarProduto(){

        mProgressDialog.setMessage("Carregando dados...");
        mProgressDialog.show();

        String idProduto = Base64Custom.codificarBase64(produtos.getTitulo());
        produtos.setId(idProduto);
        String idFotoProduto = produtos.getId();

        StorageReference filePath = storageFirebase.child("Fotos").child(idFotoProduto);
        filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                mProgressDialog.dismiss();
                Toast.makeText(CadastroProduto.this, "Upload completo", Toast.LENGTH_SHORT).show();
                finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CadastroProduto.this, "Falha ao carregar a imagem. Tente novamente", Toast.LENGTH_LONG).show();
            }
        });

        produtos.salvar();
    }

}
