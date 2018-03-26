package com.beyondthecode.tdoscharff.tdoxpress.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.beyondthecode.tdoscharff.tdoxpress.R;
import com.beyondthecode.tdoscharff.tdoxpress.interfaz.ItemClickListener;
import com.beyondthecode.tdoscharff.tdoxpress.modelo.Contact;
import com.beyondthecode.tdoscharff.tdoxpress.view.ContactViewHolder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Computer on 23/03/2018.
 */

public class ContactRecyclerViewAdapter extends RecyclerView.Adapter<ContactViewHolder> {

    Context mContext;
    List<Contact> mContactList;

    public ContactRecyclerViewAdapter(Context mContext, List<Contact> mContactList) {
        this.mContext = mContext;
        this.mContactList = mContactList;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_contact,parent,false);
        ContactViewHolder viewHolder = new ContactViewHolder(v);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ContactViewHolder holder, int position) {

        //Picasso.with(context).load(android_versions.get(i).
        // getAndroid_image_url()).resize(120, 60).into(viewHolder.img_android);
        holder.txt_nombre.setText(mContactList.get(position).getNombre());
        holder.txt_telefono.setText(String.valueOf(mContactList.get(position).getTelefono()));
        Picasso.with(mContext).load(mContactList.get(position).getImagen())
        .into(holder.img_contacto, new Callback() {
                    @Override
                    public void onSuccess() {
                        //dar forma a la imagen en circulo
                        Bitmap imageBitMap = ((BitmapDrawable)holder.img_contacto.getDrawable()).getBitmap();
                        RoundedBitmapDrawable imageDrawable = RoundedBitmapDrawableFactory.create(Resources.getSystem(),imageBitMap);

                        imageDrawable.setCircular(true);
                        imageDrawable.setCornerRadius(Math.max(imageBitMap.getWidth(),imageBitMap.getHeight()));
                        holder.img_contacto.setImageDrawable(imageDrawable);

                    }

                    @Override
                    public void onError() {

                        //viewHolder.img_contacto.setImageResource(R.drawable.default_image);
                    }
                });

            holder.setItemClicked(new ItemClickListener() {
                @Override
                public void onItemClick(View view, int posicion) {
                    Toast.makeText(mContext, "click en item", Toast.LENGTH_SHORT).show();
                }

                boolean isColored;


                @Override
                public void onFavoritoClick(int posicion) {



                    if (!isColored){

                        holder.img_favorito.setImageResource(R.drawable.ic_favorite_full_scharffcolor_24dp);
                        isColored=true;
                    }else{
                        holder.img_favorito.setImageResource(R.drawable.ic_favorite_black_24dp);
                        isColored=false;
                    }

                }




            });


    }

    @Override
    public int getItemCount() {
        return mContactList.size();
    }
}
