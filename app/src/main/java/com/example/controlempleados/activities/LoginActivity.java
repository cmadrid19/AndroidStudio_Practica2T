package com.example.controlempleados.activities;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.controlempleados.R;

public class LoginActivity extends AppCompatActivity {


    private static final String TAG = "LoginActivity";

    TextView usuario;
    TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        verificaIntent();
        usuario = (EditText) findViewById(R.id.edittext_usuario);
        password = (EditText) findViewById(R.id.edittext_password);

        /*
        password.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    //TODO
                    //login(usuario);
                }
                return false;
            }
        });
         */
    }


    public void verificaIntent() {
        Intent intent = getIntent();
        String clave = intent.getStringExtra("clave");
        String email = intent.getStringExtra("email");
        String pass = intent.getStringExtra("password");
        if (clave == null) {
            checkUsuarioFichero();
        } else if (clave.equals("ko")) {
            Toast.makeText(this, "El usuario o la contraseña no son válidos", Toast.LENGTH_SHORT).show();
        } else if (clave.equals("ok")) {
            anadirUsuarioPrefs(email, pass);
            Toast.makeText(this, "Se ha iniciado sesión exitosamente", Toast.LENGTH_SHORT);
            startActivity(new Intent(this, MainActivity.class));
            this.finish();

        }
    }

    //metodo al que llamamos cuando hacemos click en ENTRAR
    public void login(View view) {
        String recoge_usuario;
        String recoge_password;

        recoge_usuario = usuario.getText().toString();
        recoge_password = password.getText().toString();

        Log.d(TAG, "la contraseña es " + recoge_usuario);

        //de momento directamente entra en la actividad pero para hacer pruebas, despues hay que quitarlo.
        //startActivity(new Intent(LoginActivity.this, PrincipalActivity.class));
        //hacerLlamada(recoge_usuario, recoge_password);

        ProgressBar barra = findViewById(R.id.progressBar);
        barra.setVisibility(View.VISIBLE);
    }


    /**
     *Es el metodo al que llamamos cuando hacemos click en 'Registrarse'
     * @param view
     */
    public void crearCuentaNueva(View view) {
        Intent intent = new Intent(this, RegistrarseActivity.class);
        startActivity(intent);

    }

    public boolean checkUsuarioFichero() {
        SharedPreferences prefs;
        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        String contenidoUser = prefs.getString("email", "");
        String contenidoPass = prefs.getString("password", "");
        Log.d(TAG, "Se saca la contraseña" + contenidoPass);
        Log.d(TAG, " El usuario leido del fichero es" + contenidoUser + " " + contenidoPass);

        if (contenidoUser != "" && contenidoPass != "") {

            //Lamada login, si existe una conicidencia en sql se entra
            //ClienteHTTP.llamadaLogin(contenidoUser, contenidoPass, this);


            Log.d(TAG, "Habia un usuario en fichero");
            return true;
        } else {
            return false;
        }
    }

    public void anadirUsuarioPrefs(String email, String password) {
        SharedPreferences prefs;
        prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = prefs.edit();
        edit.putString("email", email);
        edit.putString("password", password);
        Log.d(TAG, "Se guarda la contraseña:" + password);
        edit.commit();
    }


    //es el metodo al que llamamos cuando hacemos click en ¿Has olvidado tu contraseña?
    public void restablecerPassword(View view) {
    }
}
