package com.example.zumba.appendoscope;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Desiderio Ruiz Aguilar.
 */

public class LoginActivity extends Activity {
    // MIEMBROS DE LA ACTIVITY

    // Componentes Interfaz de Usuario
    private EditText usuario;
    private EditText contrasenia;
    private Button iniciar;

    /**
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Captura de los Controles
        usuario = (EditText) findViewById(R.id.Usuario);
        contrasenia = (EditText) findViewById(R.id.Password);
        iniciar = (Button) findViewById(R.id.btnLogin);

        TextView registerScreen = (TextView) findViewById(R.id.link_to_register);
        TextView CamScreen = (TextView) findViewById(R.id.link_to_init);

        // Escuchador al click de registro
        registerScreen.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Switching to Register screen
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });

        // Escuchador al click de la cámara
        CamScreen.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Switching to Register screen
                Intent m = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(m);
            }
        });

        // Escuchador al click de la cámara
        iniciar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                comprobarContrasenia(usuario.getText().toString(), contrasenia.getText().toString());
            }
        });

    }

    /**
     * @param usuario
     * @param contrasenia
     */
    public void comprobarContrasenia(String usuario, String contrasenia) {

        Cursor listaRegistros;
        String Usuario = "";
        String Contrasenia = "";

        UsersDB UD = new UsersDB(this, "Usuarios", null, 1);
        SQLiteDatabase bd = UD.getReadableDatabase();

        listaRegistros = bd.rawQuery("SELECT *" + "FROM Usuarios;", null);

        if (listaRegistros.moveToFirst()) {
            do {
                Usuario = listaRegistros.getString(1);
                Contrasenia = listaRegistros.getString(2);

            } while (listaRegistros.moveToNext());
            if (Usuario.equals(usuario) && Contrasenia.equals(contrasenia)) {
                Intent intent =
                        new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(getBaseContext(), "Usuario registrado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getBaseContext(), " Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
            }
        }
        bd.close();
    }
}
