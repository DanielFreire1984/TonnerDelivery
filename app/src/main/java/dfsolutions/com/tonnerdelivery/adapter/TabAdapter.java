package dfsolutions.com.tonnerdelivery.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import dfsolutions.com.tonnerdelivery.fragment.PedidosFragment;
import dfsolutions.com.tonnerdelivery.fragment.ProdutosFragment;

/**
 * Created by Daniel on 14/08/2017.
 */
public class TabAdapter extends FragmentStatePagerAdapter {

    private String[] tituloAbas = {"PRODUTOS","PEDIDOS"};

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new ProdutosFragment();
                break;
            case 1:
                fragment = new PedidosFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return tituloAbas.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tituloAbas[position];
    }
}
