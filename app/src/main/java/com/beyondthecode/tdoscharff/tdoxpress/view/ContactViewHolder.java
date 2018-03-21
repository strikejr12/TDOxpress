package com.beyondthecode.tdoscharff.tdoxpress.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.beyondthecode.tdoscharff.tdoxpress.R;
import com.beyondthecode.tdoscharff.tdoxpress.interfaz.ItemClickListener;

/**
 * Created by Computer on 19/03/2018.
 */

public class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView txt_nombre;
    public TextView txt_telefono;
    public ImageView img_contacto;
    public ImageView icon_favorito;

    private ItemClickListener favoritoListener;



    public ContactViewHolder(View itemView) {
        super(itemView);

        txt_nombre = itemView.findViewById(R.id.txt_contact_nombre);
        txt_telefono = itemView.findViewById(R.id.txt_contact_fono);
        img_contacto = itemView.findViewById(R.id.img_contact);
        icon_favorito = itemView.findViewById(R.id.iv_favorito);


        icon_favorito.setOnClickListener(this);


    }

    public void setOnFavoriteListener(ItemClickListener favoritoListener){
        this.favoritoListener = favoritoListener;

    }


    @Override
    public void onClick(View v) {
        favoritoListener.onFavoritoClick(v,getAdapterPosition());
    }
}
