package dfsolutions.com.tonnerdelivery.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dfsolutions.com.tonnerdelivery.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProdutosFragment extends Fragment {


    public ProdutosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_produtos, container, false);
    }

}
