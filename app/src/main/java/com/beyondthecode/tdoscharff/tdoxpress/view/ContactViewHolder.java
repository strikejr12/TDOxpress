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

public class ContactViewHolder extends RecyclerView.ViewHolder{

    public TextView txt_nombre;
    public TextView txt_telefono;
    public ImageView img_contacto;
    public ImageView img_favorito;

    private ItemClickListener itemEscogido;



    public ContactViewHolder(View itemView) {
        super(itemView);

        txt_nombre = itemView.findViewById(R.id.txt_contact_nombre);
        txt_telefono = itemView.findViewById(R.id.txt_contact_fono);
        img_contacto = itemView.findViewById(R.id.img_contact);
        img_favorito = itemView.findViewById(R.id.iv_favorito);


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                itemEscogido.onItemClick(view,getAdapterPosition());
            }
        });

        img_favorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                itemEscogido.onFavoritoClick(getAdapterPosition());
            }
        });


    }

    public void setItemClicked(ItemClickListener itemEscogido){
        this.itemEscogido = itemEscogido;

    }



}
