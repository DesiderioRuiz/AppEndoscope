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
                Intent m = new Intent(getApplicationContext(), CameraActivity.class);
                startActivity(m);
            }
        });

        // Escuchador al click de la cámara
        iniciar.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                if (findUser(usuario.getText().toString(), contrasenia.getText().toString())) {
                    Intent intent =
                            new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    /**
     * Método para comprobar el usuario existente en la BBDD
     *
     * @param usuario
     * @param contrasenia
     */
    public boolean findUser(String usuario, String contrasenia) {

        String Usuario = "";
        String Contrasenia = "";
        Cursor lista;
        boolean ok = false;

        UsersDB BBDD = new UsersDB(this, "Usuarios", null, 1);
        SQLiteDatabase bd = BBDD.getReadableDatabase();

        lista = bd.rawQuery("SELECT *" + "FROM Usuarios;", null);

        if (lista.moveToFirst()) {
            do {
                Usuario = lista.getString(1);
                Contrasenia = lista.getString(2);
            } while (lista.moveToNext());
            if (Usuario.equals(usuario) && Contrasenia.equals(contrasenia)) {
                ok = true;
                Toast.makeText(getBaseContext(), "Usuario registrado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getBaseContext(), " Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
            }
        }
        bd.close();
        return ok;
    }
}
