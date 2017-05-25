package com.example.zumba.appendoscope;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Desiderio Ruiz Aguilar.
 */

public class RegisterActivity extends Activity {
    // MIEMBROS DE LA ACTIVITY

    // Componentes Interfaz de Usuario

    private EditText nombre;
    private EditText usuario;
    private EditText contrasenia;
    private Button btnRegister;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set View to register.xml
        setContentView(R.layout.activity_register);

        // Captura de los Controles
        nombre = (EditText) findViewById(R.id.nombre_completo);
        usuario = (EditText) findViewById(R.id.regUsuario);
        contrasenia = (EditText) findViewById(R.id.regPasword);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        // Escuchador al click de registro
        btnRegister.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                comprobarRegistro(nombre.getText().toString(), usuario.getText().toString(), contrasenia.getText().toString());
            }
        });

        TextView loginScreen = (TextView) findViewById(R.id.link_to_cam);

        // Listening to Login Screen link
        loginScreen.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // Switching to Register screen
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    public void insert(String nombre, String usuario, String pass) {

        //Conexion a la BBDD
        UsersDB conexionDB = new UsersDB(this, "Usuarios", null, 1);
        SQLiteDatabase bd = conexionDB.getWritableDatabase();

        if (bd != null) {
            //Lanzamos instrucción
            bd.execSQL("INSERT INTO Usuarios (nombre, usuario, contrasenia) " +
                    "VALUES ('" + nombre + "', '" + usuario + "', '" + pass + "')");
            Toast.makeText(this, "Datos Guardados con Éxito", Toast.LENGTH_SHORT).show();
        }
        //Cerramos conexion
        bd.close();
    }

    /*
Metodo para comprobar si existe usuario,sino existe lo crea
*/
    public void comprobarRegistro(String nombre, String usuario, String pass) {

        Cursor listaRegistros;
        String ComprobarUsuario = "";
        UsersDB UD = new UsersDB(this, "Usuarios", null, 1);
        SQLiteDatabase bd = UD.getReadableDatabase();

        listaRegistros =
                bd.rawQuery("SELECT *" + "FROM Usuarios where usuario=" + "'" + usuario + "'", null);
        if (listaRegistros.moveToFirst()) {
            do {
                ComprobarUsuario = listaRegistros.getString(0);
            } while (listaRegistros.moveToNext());
        }
        Log.i("Usuario:::", usuario);
        Log.i("ComprobarUsuario:::", ComprobarUsuario);

        if (!usuario.equals(ComprobarUsuario)) {
            insert(nombre, usuario, pass);
            Toast.makeText(getBaseContext(), "Registro completo", Toast.LENGTH_SHORT).show();
            // Switching to Register screen
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);
        } else {
            Toast.makeText(getBaseContext(), "Usuario ya existente", Toast.LENGTH_SHORT).show();
        }
    }
}
