package com.beyondthecode.tdoscharff.tdoxpress;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Computer on 15/03/2018.
 */

public class FavoritoFragment extends Fragment{

    View v;

    public FavoritoFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_favorito,container,false);
        return v;
    }
}
