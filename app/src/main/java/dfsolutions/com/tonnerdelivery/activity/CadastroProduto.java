package dfsolutions.com.tonnerdelivery.activity;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dfsolutions.com.tonnerdelivery.R;
import dfsolutions.com.tonnerdelivery.model.Produtos;

public class CadastroProduto extends AppCompatActivity {

    private Toolbar toolbar;
    private Spinner spinnerProduto, spinnerMarca;
    private String selecaoItemSpinnerProduto, selecaoItemSpinnerMarca;
    private ArrayList<String> listItemTipo;
    private ArrayList<String> listItemMarca;
    private EditText titulo, descricao, valor, saldo;
    private Button botaoCadastrarProduto;
    private Produtos produtos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);

        toolbar         = (Toolbar) findViewById(R.id.toolbar_cadastro_produto_id);
        spinnerProduto  = (Spinner) findViewById(R.id.spinner_tipo_produto_id);
        spinnerMarca    = (Spinner) findViewById(R.id.spinner_marca_produto_id);
        titulo          = (EditText) findViewById(R.id.tv_titulo_produto_id);
        descricao       = (EditText) findViewById(R.id.tv_descricao_produto_id);
        valor           = (EditText) findViewById(R.id.tv_valor_produto_id);
        saldo           = (EditText) findViewById(R.id.tv_qtd_produto_id);
        botaoCadastrarProduto = (Button) findViewById(R.id.bt_cadastrar_produto_id);


        //Configurando a toolbar
        toolbar.setTitle(R.string.tb_title_cadastro_produto);
        toolbar.setNavigationIcon(R.drawable.ic_action_arrow_left);
        setSupportActionBar(toolbar);

        //Configurando os Spinners e os Adapters
        ArrayAdapter<String> spinnerAdapterProduto = new ArrayAdapter<String>(CadastroProduto.this,
                R.layout.snipper_item, getResources().getStringArray(R.array.spinner_produto_options));
        spinnerAdapterProduto.createFromResource(this, R.array.spinner_produto_options, R.layout.snipper_item);
        spinnerProduto.setAdapter(spinnerAdapterProduto);

        ArrayAdapter<String> spinnerAdapterMarca = new ArrayAdapter<String>(CadastroProduto.this,
                R.layout.snipper_item, getResources().getStringArray(R.array.spinner_marca_options));
        spinnerAdapterMarca.createFromResource(this, R.array.spinner_marca_options, R.layout.snipper_item);
        spinnerMarca.setAdapter(spinnerAdapterMarca);

        //Tratando a selecao do Snipper tipo de Produto
        spinnerProduto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //listItemTipo = new ArrayList<String>();
                List<String> listItemTipo = Arrays.asList(getResources().getStringArray(R.array.spinner_produto_options));
                //Resources res = getResources();
                //String[] headers = res.getStringArray(R.array.spinner_produto_options);
                //listItemTipo = Arrays.asList(headers);
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
                //listItemMarca = new ArrayList<String>();
                //Resources res = getResources();
                //String[] headers = res.getStringArray(R.array.spinner_produto_options);
                //listItemMarca = (ArrayList)Arrays.asList(headers);
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

                    Toast.makeText(CadastroProduto.this, "Preencha todos os campos para cadastrar!!", Toast.LENGTH_LONG).show();

                }else{
                    CadastrarProduto();
                }

            }
        });

    }

    private void CadastrarProduto(){
        produtos.salvar();
    }

}
