package com.beyondthecode.tdoscharff.tdoxpress.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

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

    private static final String TAG = SqlHelper.class.getName();

    public SqlHelper(Context context){
        super(context,NOMBRE_BD,null,VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlCreate =
                "CREATE TABLE " + TABLA_CONTACTO +
                        "("+CAMPO_CODIGO + " STRING PRIMARY KEY,"+
                        CAMPO_NOMBRE + " TEXT," +
                        CAMPO_TELEFONO + " LONG,"+
                        CAMPO_AREA + " TEXT," +
                        CAMPO_IMAGEN + " TEXT)";

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

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLA_CONTACTO,null,contentValues);

        db.close();
    }

    public List<Contact> obtenerContactos(){

        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {CAMPO_CODIGO,CAMPO_NOMBRE,
                CAMPO_TELEFONO,CAMPO_AREA,CAMPO_IMAGEN};

        String sqlTable = TABLA_CONTACTO;

        qb.setTables(sqlTable);
        Cursor cursor = qb.query(db,sqlSelect,null,null,null,null,null);

        List<Contact> listaContacto = new ArrayList<>();

        if (cursor.moveToFirst()){

            do {
                listaContacto.add(
                        new Contact(
                                cursor.getString(cursor.getColumnIndex(CAMPO_CODIGO)),
                                cursor.getString(cursor.getColumnIndex(CAMPO_NOMBRE)),
                                cursor.getLong(cursor.getColumnIndex(CAMPO_TELEFONO)),
                                cursor.getString(cursor.getColumnIndex(CAMPO_AREA)),
                                cursor.getString(cursor.getColumnIndex(CAMPO_IMAGEN))

                        ));
            }while (
                        cursor.moveToNext()
                        );

        }

            cursor.close();
            db.close();

        return listaContacto;

    }

}


