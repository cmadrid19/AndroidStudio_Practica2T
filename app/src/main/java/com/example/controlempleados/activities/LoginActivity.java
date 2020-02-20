package com.example.controlempleados.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.controlempleados.R;
import com.example.controlempleados.bean.User;
import com.example.controlempleados.dao.DataBaseUsers;
import com.example.controlempleados.utiles.InternetStatus;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    TextView usuario;
    TextView password;
    DataBaseUsers db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DataBaseUsers(this, "miBaseDatos", null, 1);
        checkLoginGuardado();

        usuario = (EditText) findViewById(R.id.edittext_usuario);
        password = (EditText) findViewById(R.id.edittext_password_logn_in);

    }

    //metodo al que llamamos cuando hacemos click en ENTRAR
    public void login(View view) {
        String nombreUsuario;
        String contrasenhaUsuario;

        nombreUsuario = usuario.getText().toString();
        contrasenhaUsuario = password.getText().toString();

        Log.d(TAG, "El usario es: " + nombreUsuario);
        Log.d(TAG, "la contraseña es: " + nombreUsuario);

        if (!nombreUsuario.equals("") && !contrasenhaUsuario.equals("")) {

            User u = db.checkLogin(nombreUsuario, contrasenhaUsuario);
            if (u != null) {
                //El usuario logeado existe en BBDD
                Log.d(TAG, getResources().getString(R.string.exito_log_in) + " " + u.toString());
                //Guardamos el Login para proximas ocaisones
                anadirUsuarioPrefs(u.getName(), u.getPassword());
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }else{
                Toast.makeText(this, getResources().getString(R.string.user_pass_incorrectos), Toast.LENGTH_SHORT).show();
            }
        }
    }


    /**
     * La idea es una vez logeado, cuando salgas de la app y vuelvas a entrar que el loging se hagas desde las SharedPreferenes.
     * Cuando quieras salir se liberaran el usuario y el pass de las SharedPreferences.
     *
     * @param name
     * @param password
     */
    public void anadirUsuarioPrefs(String name, String password) {
        CheckBox cbRecordarme = findViewById(R.id.cBoxRecuerdame);
        if (cbRecordarme.isChecked()) {
            SharedPreferences prefs;
            prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = prefs.edit();
            edit.putString("name", name);
            edit.putString("password", password);
            edit.commit();
        }
    }


    public void checkLoginGuardado() {
        SharedPreferences prefs;
        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        String contenidoUser = prefs.getString("name", "");
        String contenidoPass = prefs.getString("password", "");

        if (contenidoUser != "" && contenidoPass != "") {
            //Si hay Shared Preferences con nombre y contraseña
            //Y ,además, si existe una conicidencia en BBDD --> Login directo
            db.checkLogin(contenidoUser, contenidoPass);
            Log.d(TAG, " El usuario leido del fichero es" + contenidoUser + " " + contenidoPass);

            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
    }



    /**
     * Es el metodo al que llamamos cuando hacemos click en 'Registrarse'
     *
     * @param view
     */
    public void crearCuentaNueva(View view) {
        if (InternetStatus.haveNetwork(this) == true) {
            Intent intent = new Intent(this, RegistrarseActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, getResources().getString(R.string.sin_conexion_internet), Toast.LENGTH_SHORT).show();
        }
    }
}
