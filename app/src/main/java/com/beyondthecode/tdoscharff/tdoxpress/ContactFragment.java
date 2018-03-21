package com.beyondthecode.tdoscharff.tdoxpress;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.beyondthecode.tdoscharff.tdoxpress.interfaz.ItemClickListener;
import com.beyondthecode.tdoscharff.tdoxpress.modelo.Contact;
import com.beyondthecode.tdoscharff.tdoxpress.view.ContactViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Computer on 15/03/2018.
 */

public class ContactFragment extends Fragment {

    View v;
    private RecyclerView mRecyclerView;
    private List<Contact> lstContacto;




    public ContactFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_contact, container, false);

        mRecyclerView = v.findViewById(R.id.rcv_contacto);
        //RecyclerViewAdapter mRecyclerViewAdapter = new RecyclerViewAdapter(getContext(),lstContacto);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        //  mRecyclerView.setAdapter(mRecyclerViewAdapter);


        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        cargarContacto(v);

        return v;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //cargarContacto();

    }

    void cargarContacto(View v) {

        DatabaseReference contacto;
        FirebaseDatabase database;

        database = FirebaseDatabase.getInstance();
        contacto = database.getReference("Contactos");

        FirebaseRecyclerAdapter<Contact, ContactViewHolder> adaptador;

        adaptador = new FirebaseRecyclerAdapter<Contact, ContactViewHolder>(Contact.class,
                R.layout.item_contact,
                ContactViewHolder.class,
                contacto) {
            @Override
            protected void populateViewHolder(final ContactViewHolder viewHolder, Contact contacto, int position) {

                viewHolder.txt_nombre.setText(contacto.getNombre());
                viewHolder.txt_telefono.setText(String.valueOf(contacto.getTelefono()));
                Picasso.with(getActivity().getBaseContext()).load(contacto.getImagen())
                        .into(viewHolder.img_contacto);


                viewHolder.setItemClicked(new ItemClickListener() {
                    @Override
                    public void onItemClick(View view, int posicion) {
                        Toast.makeText(getActivity(), "click en item", Toast.LENGTH_SHORT).show();
                    }

                    boolean isColored;


                    @Override
                    public void onFavoritoClick(int posicion) {



                        if (!isColored){



                            viewHolder.img_favorito.setImageResource(R.drawable.ic_favorite_full_scharffcolor_24dp);
                            isColored=true;
                        }else{
                            viewHolder.img_favorito.setImageResource(R.drawable.ic_favorite_black_24dp);
                            isColored=false;
                        }

                    }




                });
/*
                viewHolder.img_favorito.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Toast.makeText(getActivity(), "holi favor", Toast.LENGTH_SHORT).show();
                    }
                });*/
            }
        };

        mRecyclerView.setAdapter(adaptador);


    }

}