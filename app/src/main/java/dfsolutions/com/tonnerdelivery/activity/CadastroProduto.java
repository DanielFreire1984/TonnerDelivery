package dfsolutions.com.tonnerdelivery.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import dfsolutions.com.tonnerdelivery.R;

public class CadastroProduto extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produto);

        toolbar = (Toolbar) findViewById(R.id.toolbar_cadastro_produto_id);

        //Configurando a toolbar
        toolbar.setTitle(R.string.tb_title_cadastro_produto);
        toolbar.setNavigationIcon(R.drawable.ic_action_arrow_left);
        setSupportActionBar(toolbar);

    }
}
