package com.beyondthecode.tdoscharff.tdoxpress.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.beyondthecode.tdoscharff.tdoxpress.R;
import com.beyondthecode.tdoscharff.tdoxpress.modelo.Contact;

import java.util.List;

/**
 * Created by Computer on 15/03/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<Contact> mContactList;

    public RecyclerViewAdapter(Context mContext, List<Contact> mContactList) {
        this.mContext = mContext;
        this.mContactList = mContactList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_contact,parent,false);
        MyViewHolder vHolder = new MyViewHolder(v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
/*
        holder.txt_telefono.setText(mContactList.get(position).getNombre());
        holder.txt_nombre.setText(mContactList.get(position).getTelefono());
       // holder.img_contacto.setImageResource(mContactList.get(position).getImagen());
*/
    }

    @Override
    public int getItemCount() {
        return mContactList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView txt_nombre;
        public TextView txt_telefono;
        public ImageView img_contacto;

        public MyViewHolder(View itemView) {
            super(itemView);

            txt_nombre = itemView.findViewById(R.id.txt_contact_nombre);
            txt_telefono = itemView.findViewById(R.id.txt_contact_fono);
            img_contacto = itemView.findViewById(R.id.img_contact);
        }
    }
}
