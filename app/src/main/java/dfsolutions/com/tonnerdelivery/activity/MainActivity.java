package dfsolutions.com.tonnerdelivery.activity;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.zip.Inflater;

import dfsolutions.com.tonnerdelivery.R;
import dfsolutions.com.tonnerdelivery.adapter.TabAdapter;
import dfsolutions.com.tonnerdelivery.config.ConfiguracaoFirebase;
import dfsolutions.com.tonnerdelivery.helper.SlidingTabLayout;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private FirebaseAuth autenticacao;

    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autenticacao = ConfiguracaoFirebase.getAutenticacaoFirebase();

        toolbar = (Toolbar) findViewById(R.id.toolbar_id);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.stl_tabs_id);
        viewPager = (ViewPager) findViewById(R.id.vp_pagina_id);

        //Configurando o SlidingTab layout
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.colorAccent));

        //configurando o TabAdapter
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter( tabAdapter );

        slidingTabLayout.setViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater(); //Retorna um objeto MenuInflater com o contexto da aplicação
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_sair:
                deslogarUsuario();
                return true;
            case R.id.action_settings:
                return true;
            case R.id.action_add_produto:
                abrirCadastroProduto();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void deslogarUsuario(){
        autenticacao.signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void abrirCadastroProduto(){
        Intent intent = new Intent(MainActivity.this, CadastroProduto.class);
        startActivity(intent);
    }
}
