package com.example.zumba.appendoscope;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Desiderio Ruiz Aguilar.
 */

public class UsersDB extends SQLiteOpenHelper {
    //Sentencia SQL para crear la BBDD
    Context contexto;
    String SentenciaSQL = "Create table Usuarios (nombre TEXT, usuario TEXT, contrasenia TEXT)";

    //Costructor
    public UsersDB(Context context, String name, SQLiteDatabase.CursorFactory
            factory, int version) {
        super(context, name, factory, version);
        contexto = context;
    }

    //Método de Creación
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Ejecutar la Sentencia SQL
        db.execSQL(SentenciaSQL);
    }

    //Método de Actualización
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insert(String nombre, String usuario, String pass) {

        //Conexion a la BBDD
        UsersDB conexionDB = new UsersDB(contexto, "Usuarios", null, 1);
        SQLiteDatabase bd = conexionDB.getWritableDatabase();

        if (bd != null) {
            //Lanzamos instrucción
            bd.execSQL("INSERT INTO Usuarios (nombre, usuario, contrasenia) " +
                    "VALUES ('" + nombre + "', '" + usuario + "', '" + pass + "')");
            Toast.makeText(contexto, "Datos Guardados con Éxito", Toast.LENGTH_SHORT).show();
        }
        //Cerramos conexion
        bd.close();
    }

    public void update(String nombre, String pass) {
        //Conexion a la BBDD
        UsersDB conexionDB = new UsersDB(contexto, "Usuarios", null, 1);
        SQLiteDatabase bd = conexionDB.getWritableDatabase();

        if (bd != null) {
            //Lanzamos instrucción
            bd.execSQL("UPDATE Usuarios SET contrasenia=" + pass + " where nombre='" + nombre + "';");
            Toast.makeText(contexto, "Datos Actualizados con Éxito", Toast.LENGTH_SHORT).show();
        }
        //Cerramos conexion
        bd.close();
    }

    public String select(String nombre) {
        Cursor lista;
        String nombre1 = "";
        //Conexion a BBDD
        UsersDB conexionDB = new UsersDB(contexto, "Usuarios", null, 1);
        SQLiteDatabase bd = conexionDB.getWritableDatabase();

        if (bd != null) {
            //Lanzamos instrucción
            lista = bd.rawQuery("select *" + "from Usuarios where nombre='" + nombre + "'", null);
            Toast.makeText(contexto, "Datos leidos con Éxito", Toast.LENGTH_SHORT).show();
            int datos = lista.getCount();
            String[] array = new String[datos];

            int i = 0;

            if (lista.moveToFirst()) {
                do {

                    nombre1 = lista.getString(0);
                    String usuarios = lista.getString(1);
                    String pass = lista.getString(2);
                    array[i] = nombre1 + " " + usuarios + " " + pass;
                    i++;
                } while (lista.moveToNext());
            }
        }
        //Cerramos conexion
        bd.close();
        return nombre1;
    }
}
