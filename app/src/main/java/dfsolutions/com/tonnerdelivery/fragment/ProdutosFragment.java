package dfsolutions.com.tonnerdelivery.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import dfsolutions.com.tonnerdelivery.R;
import dfsolutions.com.tonnerdelivery.adapter.ProdutoAdapter;
import dfsolutions.com.tonnerdelivery.config.ConfiguracaoFirebase;
import dfsolutions.com.tonnerdelivery.model.Produtos;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProdutosFragment extends Fragment {

    private ListView listView;
    private ArrayAdapter adapter;
    private ArrayList<Produtos> produtosList;
    private DatabaseReference firebase;
    private ValueEventListener valueEventListenerProduto;


    public ProdutosFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        firebase.addValueEventListener(valueEventListenerProduto);
    }

    @Override
    public void onStop() {
        super.onStop();
        firebase.removeEventListener(valueEventListenerProduto);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Instancia objetos
        produtosList = new ArrayList<>();


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_produtos, container, false);

        //Monta listview e o adapter
        listView = (ListView) view.findViewById(R.id.lv_produtos_id);
        /*adapter = new ArrayAdapter(
                getActivity(),
                R.layout.lista_produtos,
                produtosList
        ); */
        adapter = new ProdutoAdapter(getActivity(), produtosList);
        listView.setAdapter(adapter);

        //Recuperando produtos do Firebase database
        firebase = ConfiguracaoFirebase.getFirebase().child("produtos");

        //Listener para recuperar contatos
        valueEventListenerProduto = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //Limpar a lista
                produtosList.clear();

                //listar o dados do nó especifado no firebase
                for(DataSnapshot dados: dataSnapshot.getChildren()){

                    Produtos produtos = dados.getValue(Produtos.class);
                    produtosList.add(produtos);

                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        return  view;
    }

}
