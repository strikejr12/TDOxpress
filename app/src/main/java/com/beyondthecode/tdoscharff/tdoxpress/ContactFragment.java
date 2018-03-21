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

import com.beyondthecode.tdoscharff.tdoxpress.adapters.RecyclerViewAdapter;
import com.beyondthecode.tdoscharff.tdoxpress.interfaz.ItemClickListener;
import com.beyondthecode.tdoscharff.tdoxpress.modelo.Contact;
import com.beyondthecode.tdoscharff.tdoxpress.view.ContactViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Computer on 15/03/2018.
 */

public class ContactFragment extends Fragment{

    View v;
    private RecyclerView mRecyclerView;
    private List<Contact> lstContacto;




    public ContactFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_contact,container,false);

        mRecyclerView = v.findViewById(R.id.rcv_contacto);
        //RecyclerViewAdapter mRecyclerViewAdapter = new RecyclerViewAdapter(getContext(),lstContacto);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
      //  mRecyclerView.setAdapter(mRecyclerViewAdapter);


        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),LinearLayoutManager.VERTICAL));
        cargarContacto(v);

        return v;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



       //cargarContacto();

    }

    void cargarContacto(View v){

        DatabaseReference contacto;
        FirebaseDatabase database;

        database = FirebaseDatabase.getInstance();
        contacto = database.getReference("Contactos");

        FirebaseRecyclerAdapter<Contact,ContactViewHolder> adaptador;

        adaptador = new FirebaseRecyclerAdapter<Contact, ContactViewHolder>(Contact.class,
                R.layout.item_contact,
                ContactViewHolder.class,
                contacto) {
            @Override
            protected void populateViewHolder(ContactViewHolder viewHolder, Contact contacto, int position) {

                viewHolder.txt_nombre.setText(contacto.getNombre());
                viewHolder.txt_telefono.setText(String.valueOf(contacto.getTelefono()));
                Picasso.with(getActivity().getBaseContext()).load(contacto.getImagen())
                        .into(viewHolder.img_contacto);

            }
        };

        mRecyclerView.setAdapter(adaptador);
        mRecyclerView.setOnClickListener(new ContactViewHolder(v).setOnFavoriteListener(){

        };


    }
}
