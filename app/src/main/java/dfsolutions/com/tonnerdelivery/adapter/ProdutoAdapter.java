package dfsolutions.com.tonnerdelivery.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import dfsolutions.com.tonnerdelivery.R;
import dfsolutions.com.tonnerdelivery.config.ConfiguracaoFirebase;
import dfsolutions.com.tonnerdelivery.helper.Base64Custom;
import dfsolutions.com.tonnerdelivery.model.Produtos;

/**
 * Created by Daniel on 26/08/2017.
 */
public class ProdutoAdapter extends ArrayAdapter<Produtos> {

    private ArrayList<Produtos> produtos;
    private Context context;
    private StorageReference storageFirebase;
    private Uri uri;

    public ProdutoAdapter(Context c, ArrayList<Produtos> objects) {
        super(c, 0, objects);
        this.context = c;
        this.produtos = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;

        storageFirebase = ConfiguracaoFirebase.referenciaStorage();

        //verifica se a lista de produtos está vazia
        if(produtos != null){

            //Inicializa o objeto para a montagem da view
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

            //Monta a view a partir do xml
            view = inflater.inflate(R.layout.lista_produtos, parent, false);

            //Recupera o elemento para exibição
            TextView tituloProduto = (TextView)view.findViewById(R.id.tv_titulo_id);
            TextView valorProduto = (TextView) view.findViewById(R.id.tv_valor_fragment_produtod_id);

            Produtos produto = produtos.get(position);
            tituloProduto.setText(produto.getTitulo());
            valorProduto.setText("R$ " + produto.getValor());

            //Recuperando a imagem do produto
            String pathFotoId = produto.getId();
            storageFirebase = ConfiguracaoFirebase.referenciaStorage().child("fotos").child(pathFotoId);
            ImageView imagemProduto = (ImageView) view.findViewById(R.id.iv_imagem_produto_fragment_id);
            imagemProduto.setImageURI(Uri.parse(pathFotoId));

        }

        return view;
    }
}
