package com.beyondthecode.tdoscharff.tdoxpress.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.beyondthecode.tdoscharff.tdoxpress.modelo.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Computer on 21/03/2018.
 */

public class SqlHelper extends SQLiteOpenHelper {

    private static final String NOMBRE_BD = "TDOxpressSQLite";
    private static final int    VERSION_BD = 1;

    private static final String TABLA_CONTACTO = "Contacto";
    private static final String CAMPO_CODIGO = "idContacto";
    private static final String CAMPO_NOMBRE = "nombre";
    private static final String CAMPO_TELEFONO = "telefono";
    private static final String CAMPO_AREA = "area";
    private static final String CAMPO_IMAGEN = "imagen";
    private static final String CAMPO_SEDE = "sede";

    private static final String TAG = SqlHelper.class.getName();

    public SqlHelper(Context context){
        super(context,NOMBRE_BD,null,VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlCreate =
                "CREATE TABLE IF NOT EXISTS " + TABLA_CONTACTO +
                        "("+CAMPO_CODIGO + " LONG PRIMARY KEY,"+
                        CAMPO_NOMBRE + " TEXT," +
                        CAMPO_TELEFONO + " LONG,"+
                        CAMPO_AREA + " TEXT," +
                        CAMPO_IMAGEN + " TEXT," +
                        CAMPO_SEDE + " TEXT)";

        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void agregarContacto(Contact contacto, Context context){

        ContentValues contentValues = new ContentValues();
        contentValues.put(CAMPO_CODIGO, contacto.getIdContacto());
        contentValues.put(CAMPO_NOMBRE, contacto.getNombre());
        contentValues.put(CAMPO_TELEFONO, contacto.getTelefono());
        contentValues.put(CAMPO_AREA, contacto.getArea());
        contentValues.put(CAMPO_IMAGEN, contacto.getImagen());
        contentValues.put(CAMPO_SEDE, contacto.getSede());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLA_CONTACTO,null,contentValues);

        db.close();
    }

    public List<Contact> obtenerContactos(){

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();



        String[] sqlSelect = {CAMPO_CODIGO,CAMPO_NOMBRE,
                CAMPO_TELEFONO,CAMPO_AREA,CAMPO_IMAGEN,CAMPO_SEDE};

        String sqlTable = TABLA_CONTACTO;

        qb.setTables(sqlTable);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);

        List<Contact> listaContacto = new ArrayList<>();

        if (cursor.moveToFirst()){

            do {
                listaContacto.add(
                        new Contact(
                                cursor.getLong(cursor.getColumnIndex(CAMPO_CODIGO)),
                                cursor.getString(cursor.getColumnIndex(CAMPO_NOMBRE)),
                                cursor.getLong(cursor.getColumnIndex(CAMPO_TELEFONO)),
                                cursor.getString(cursor.getColumnIndex(CAMPO_AREA)),
                                cursor.getString(cursor.getColumnIndex(CAMPO_IMAGEN)),
                                cursor.getString(cursor.getColumnIndex(CAMPO_SEDE))

                        ));
            }while (
                    cursor.moveToNext()
                    );

        }

        cursor.close();
        db.close();

        return listaContacto;

    }

    public boolean existeContacto(Long idContacto){

        SQLiteDatabase db = getReadableDatabase();
        String selectString =

                "SELECT * FROM "
                        + TABLA_CONTACTO +
                        " WHERE "+
                        CAMPO_CODIGO +
                        " =?";
        Cursor cursor = db.rawQuery(selectString,new String[]{String.valueOf(idContacto)});

        boolean xtienecontacto = false;

        if(cursor.moveToFirst()){
            xtienecontacto = true;

           // int count = 0;
            int count = 1;

            /*
            while (cursor.moveToNext()){
                count++;
            }*/

            Log.d(TAG,String.format("%d registro encontrado",count));
        }

        cursor.close();
        db.close();

        return xtienecontacto;
    }

    public void actualizarCamposContact(Long xidcontacto, String xnombre, Long xtelefono, String xarea, String ximagen, String xsede){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CAMPO_NOMBRE,xnombre);
        values.put(CAMPO_TELEFONO,xtelefono);
        values.put(CAMPO_AREA,xarea);
        values.put(CAMPO_IMAGEN,ximagen);
        values.put(CAMPO_SEDE,xsede);

        String selection = CAMPO_CODIGO + " = "+xidcontacto;

        db.update(TABLA_CONTACTO,values,selection,null);
        db.close();

    }


    public boolean existeCambiosEnCamposContacto(Long xidcontacto, String xnombre, Long xtelefono, String xarea, String ximagen, String xsede){

        SQLiteDatabase db = getReadableDatabase();
        String selectString =

                "SELECT * FROM "
                        + TABLA_CONTACTO +
                        " WHERE "+
                        CAMPO_CODIGO + " =? "+
                        "AND (" +
                        CAMPO_NOMBRE + " !=? "+
                        "OR "+
                        CAMPO_TELEFONO + " !=? "+
                        "OR "+
                        CAMPO_AREA + " !=? "+
                        "OR "+
                        CAMPO_IMAGEN + " !=? "+
                        "OR "+
                        CAMPO_SEDE + " !=? )";

        Cursor cursor = db.rawQuery(selectString,new String[]
                                                {String.valueOf(xidcontacto),
                                                xnombre,
                                                String.valueOf(xtelefono),
                                                xarea,
                                                ximagen,
                                                xsede}
                                                );

        boolean existeCambiosEnCampos = false;

        if(cursor.moveToFirst()){
            existeCambiosEnCampos = true;

            int count = 0;
            //int count = 1;


            while (cursor.moveToNext()){
                count++;
            }

            Log.d(TAG,String.format("%d registro encontrado",count));
        }

        cursor.close();

        return existeCambiosEnCampos;

    }

}

