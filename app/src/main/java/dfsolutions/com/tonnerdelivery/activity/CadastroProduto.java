package dfsolutions.com.tonnerdelivery.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import dfsolutions.com.tonnerdelivery.R;

public class CadastroProduto extends AppCompatActivity {

    private Toolbar toolbar;
    private Spinner spinnerProduto;
    private Spinner spinnerMarca;
    private String selecaoItemSpinnerProduto;
    private String selecaoItemSpinnerMarca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);

        toolbar = (Toolbar) findViewById(R.id.toolbar_cadastro_produto_id);
        spinnerProduto = (Spinner) findViewById(R.id.spinner_tipo_produto_id);
        spinnerMarca = (Spinner) findViewById(R.id.spinner_marca_produto_id);

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



    }
}
