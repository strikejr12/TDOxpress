package com.beyondthecode.tdoscharff.tdoxpress;


import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.beyondthecode.tdoscharff.tdoxpress.adapters.ContactRecyclerViewAdapter;
import com.beyondthecode.tdoscharff.tdoxpress.database.SqlHelper;
import com.beyondthecode.tdoscharff.tdoxpress.interfaz.ItemClickListener;
import com.beyondthecode.tdoscharff.tdoxpress.modelo.Contact;
import com.beyondthecode.tdoscharff.tdoxpress.view.ContactViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Computer on 15/03/2018.
 */

public class ContactFragment extends Fragment {

    private static final String TAG = ContactFragment.class.getName();

    View v;
    private RecyclerView mRecyclerView;
    private List<Contact> lstContacto;

    //aca
    ArrayList<Contact>  mContactArrayList;
    Contact mContact;

    Long idContacto;
    String nombre;
    Long telefono;
    String area;
    String imagen;
    String sede;



    SqlHelper mSqlHelper;


    public ContactFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_contact, container, false);

        mSqlHelper = new SqlHelper(getActivity());

        cargarContactRecyclerViewxSQLite();

        /* quitar comentario si se desea cargar datos por Firebase
        inicializarRecyclerViewxFirebase();
        cargarContactoRecyclerViewxFireBase();
        */


        return v;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG,"en metodo onCreate");
        obtenerContactosDeFirebaseYgrabarEnLocal();




        //cargarContacto();

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG,"en metodo onPause");
        obtenerContactosDeFirebaseYgrabarEnLocal();


    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"en metodo onResume");
        cargarContactRecyclerViewxSQLite();



    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"en metodo onDestroy");
        mSqlHelper.close();
    }

    private void obtenerContactosDeFirebaseYgrabarEnLocal(){

        DatabaseReference mDatabaseReference;
        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mDatabaseReference.child("Contactos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {



                Iterable<DataSnapshot> contactChildren =  dataSnapshot.getChildren();

                mContactArrayList = new ArrayList<>();

                for(DataSnapshot mContactDataSnapShot : contactChildren){

                    mContact =  mContactDataSnapShot.getValue(Contact.class);
                    Log.d("contact en Firebase:: ", mContact.getIdContacto()+" "+mContact.getNombre()+" "+mContact.getTelefono()+" "+mContact.getArea());
                    mContactArrayList.add(mContact);


                    //se obtiene cada nodo del padre contacto
                    idContacto = Long.parseLong(mContactDataSnapShot.getKey());

                    nombre = mContactDataSnapShot.child("nombre").getValue(String.class);
                    telefono = mContactDataSnapShot.child("telefono").getValue(Long.class);
                    area = mContactDataSnapShot.child("area").getValue(String.class);
                    imagen = mContactDataSnapShot.child("imagen").getValue(String.class);
                    sede = mContactDataSnapShot.child("sede").getValue(String.class);


                    if(!mSqlHelper.existeContacto(idContacto)){



                        agregarContactoBdLocal();

                       // Toast.makeText(getActivity(), "Se agregó contacto de codigo "+idContacto, Toast.LENGTH_SHORT).show();
                    }else {

                        actualizarContactoBdLocal();
                       // Toast.makeText(getActivity(), "existe contacto de codigo "+idContacto, Toast.LENGTH_SHORT).show();
                    }





                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void actualizarContactoBdLocal(){


        mSqlHelper.actualizarCamposContact(
                idContacto,
                nombre,
                telefono,
                area,
                imagen,
                sede
        );

    }

    private void agregarContactoBdLocal() {

        //se graba localmente en sqlite para luego mostrar cuando esta offline



            mSqlHelper.agregarContacto(
                    new Contact(
                            idContacto,
                            nombre,
                            telefono,
                            area,
                            imagen,
                            sede
                    ),getActivity()
            );

    }

    private void cargarContactRecyclerViewxSQLite(){

        mRecyclerView = v.findViewById(R.id.rcv_contacto);
        //RecyclerViewAdapter mRecyclerViewAdapter = new RecyclerViewAdapter(getContext(),lstContacto);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        //  mRecyclerView.setAdapter(mRecyclerViewAdapter);


        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));


        ContactRecyclerViewAdapter mContactRecyclerViewAdapter;

        //se llama el metood getLStcontacto para setearlo al adapter
        mContactRecyclerViewAdapter = new ContactRecyclerViewAdapter(getActivity(),getLstContacto());

        mRecyclerView.setAdapter(mContactRecyclerViewAdapter);
    }

    /*Se obtiene lista de contactos de la bd local*/
    private List<Contact> getLstContacto(){

        List<Contact> mContactList;




        mContactList = mSqlHelper.obtenerContactos();

        return mContactList;
    }

    private void inicializarRecyclerViewxFirebase(){

        mRecyclerView = v.findViewById(R.id.rcv_contacto);
        //RecyclerViewAdapter mRecyclerViewAdapter = new RecyclerViewAdapter(getContext(),lstContacto);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        //  mRecyclerView.setAdapter(mRecyclerViewAdapter);


        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
    }

    private void cargarContactoRecyclerViewxFireBase() {

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
            protected void populateViewHolder(final ContactViewHolder viewHolder, final Contact contacto, int position) {

                viewHolder.txt_nombre.setText(contacto.getNombre());
                viewHolder.txt_telefono.setText(String.valueOf(contacto.getTelefono()));
               /* Picasso.with(getActivity().getBaseContext()).load(contacto.getImagen())
                        .into(viewHolder.img_contacto);   */

                Picasso.with(getActivity()).load(contacto.getImagen()).resize(600,200).centerInside()
                        .into(viewHolder.img_contacto, new Callback() {
                            @Override
                            public void onSuccess() {

                                //dar forma a la imagen en circulo
                                Bitmap imageBitMap = ((BitmapDrawable)viewHolder.img_contacto.getDrawable()).getBitmap();
                                RoundedBitmapDrawable imageDrawable = RoundedBitmapDrawableFactory.create(getResources(),imageBitMap);

                                imageDrawable.setCircular(true);
                                imageDrawable.setCornerRadius(Math.max(imageBitMap.getWidth(),imageBitMap.getHeight()));
                                viewHolder.img_contacto.setImageDrawable(imageDrawable);
                            }

                            @Override
                            public void onError() {

                                //viewHolder.img_contacto.setImageResource(R.drawable.default_image);

                            }
                        });




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