package com.example.controlempleados.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.widget.Adapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.controlempleados.R;
import com.example.controlempleados.utiles.DatePickerFragment;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.AbstractQueue;
import java.util.regex.Pattern;


public class RegistrarseActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "MisPreferencias";
    private static final String TAG = "CrarCuentaNuevaActivity";


    EditText ediTxRecogeEmail;
    EditText ediTxRecogeUsuario;
    EditText ediTxRecogePassword;
    EditText ediTxRecogeRepeatPassword;
    EditText ediTxPlannedDatePicker;

    private String usuario;
    private String password;
    private String repeatPassword;
    private String sexo;
    private String correctPassword = null;

    // TODO Añadir listener al spinner de  paises
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        usuario = "";

        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        ediTxPlannedDatePicker = (EditText) findViewById(R.id.edittext_recoge_fecha_nacimiento);
        ediTxRecogeEmail = (EditText) findViewById(R.id.editText_email);
        ediTxRecogeUsuario = (EditText) findViewById(R.id.edittext_usuario);
        ediTxRecogePassword = (EditText) findViewById(R.id.edittext_password);
        ediTxRecogeRepeatPassword = (EditText) findViewById(R.id.edittext_repeat_password);
        Spinner paises = findViewById(R.id.paises_spinner);


        //todo setSpinnerPais();
    }


    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero
                final String selectedDate = year + "." + (month + 1) + "." + day;
                ediTxPlannedDatePicker.setText(selectedDate);
            }
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private boolean validarOpcionSexo() {
        RadioGroup grupo_sexo = findViewById(R.id.sexo_grupo);
        int id = grupo_sexo.getCheckedRadioButtonId();
        View eleccion = findViewById(id);
        int idx = grupo_sexo.indexOfChild(eleccion);

        if (idx == 0) {
            sexo = "f";
            return true;
        } else if (idx == 1) {
            sexo = "m";
            return true;
        } else {
            sexo = null;
            Toast.makeText(this, "Debes elegir un sexo", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public boolean campoObligatorio(EditText editText) {

        //Si no rellenas los campos aparece (*)
        if (editText.getText().toString().isEmpty()) {
            editText.setHint("");
            editText.setHintTextColor(ColorStateList.valueOf(Color.RED));
            return false;
        } else {
            return true;
        }
    }

    private boolean validarDatosEntrada() {
        return (campoObligatorio(ediTxRecogeEmail) &&
                campoObligatorio(ediTxRecogeUsuario) &&
                campoObligatorio(ediTxRecogePassword) &&
                campoObligatorio(ediTxRecogeRepeatPassword));
    }

    private String getValorSpinner() {
        //Pedir lista de paises para proveer el spiner

        /*
        AreaClass usuarioCaracter = new AreaClass();
        Spinner mySpinner = (Spinner) findViewById(R.id.paises_spinner);
        String text = mySpinner.getSelectedItem().toString();
        String usuarioJson = "";
        Log.d(TAG, text);
        for (int i = 0; i < areaLista.size(); ++i) {
            if (areaLista.get(i).getName().contains(text)) {
                usuarioCaracter = areaLista.get(i);
                Log.d(TAG, areaLista.get(i).getCountryCode());
                Log.d(TAG, areaLista.get(i).getCountryName());
                Log.d(TAG, areaLista.get(i).getName());
                Log.d(TAG, areaLista.get(i).getGMToffset());
                Log.d(TAG, areaLista.get(i).getId());
            }
        }

        Gson gson = new Gson();
        usuarioJson = gson.toJson(usuarioCaracter);

        return usuarioCaracter.getId();
        */
        return "";
    }

    private boolean validarSpinner(String valorSpinner) {
        Log.d(TAG, "El valor que le pasamos al validar es " + valorSpinner);
        return (valorSpinner == "");
    }

    private boolean validarEmail(String email) {
        email = ediTxRecogeEmail.getText().toString();
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        if (!pattern.matcher(email).matches()) {
            Toast toast = Toast.makeText(this, "El formato del email debe ser correcto", Toast.LENGTH_SHORT);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            v.setTextColor(Color.RED);
            toast.setGravity(Gravity.CENTER | Gravity.TOP, 0, 0);
            toast.show();
            ediTxRecogeEmail.getText().clear();
            campoObligatorio(ediTxRecogeEmail);
            return false;
        } else return true;
    }

    public boolean comprobarPassword() {
        password = ediTxRecogePassword.getText().toString();
        repeatPassword = ediTxRecogeRepeatPassword.getText().toString();
        if (password.equals(repeatPassword)) {
            correctPassword = password;
            return true;

        } else {
            correctPassword = null;
            Toast.makeText(this, "las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void aceptarCrearNuevaCuenta(View view) {
        EditText text = findViewById(R.id.editText_email);
        //TODO Pasar los datos del area en formato para que el servidor lo lea, preguntar a Eugenio características
        if (validarOpcionSexo() && validarDatosEntrada() && validarEmail(text.getText().toString()) && comprobarPassword()) {

            String email;

            usuario = ediTxRecogeUsuario.getText().toString();
            correctPassword = ediTxRecogePassword.getText().toString();
            email = ediTxRecogeEmail.getText().toString();
            String fechaNacimiento = ediTxPlannedDatePicker.getText().toString();

            Log.d(TAG, "el email es: " + email);
            Log.d(TAG, "el usuario es: " + usuario);
            Log.d(TAG, "la Contraseña del usuario es: " + password);
            Log.d(TAG, "la  repeticion de contraseña del usuario es: " + repeatPassword);
            Log.d(TAG, "la  contraseña definitiva es: " + correctPassword);
            Log.d(TAG, "la fecha de nacimiento es: " + fechaNacimiento);
            Log.d(TAG, "el sexo del usuario es: " + sexo);


            //TODO registrar usuario, realizar intent

            Intent intent = (new Intent(this, MainActivity.class));
            startActivity(intent);
        } else {
            Toast.makeText(this, "Introduce los datos correctamente en los campos", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, LoginActivity.class));
        this.finish();

    }
}


/**
 * Método para proveer al spinner de paises en 'RegistrarseActivity'.
 */
class GetCountryNames extends AsyncTask<String, Void, Void> {
    // En este método se debe escribir el código de la tarea que se ejecuta en segundo plano.
    // Android no nos permitirá acceder a ningún componente de la UI desde este método


    public static final String COUNTRIES_NAMES_URL = "https://api.printful.com/countries";
    private static final String TAG = "Async task: GetCounrtyNames";
    private Boolean error = false;

    private Context context;
    private Adapter adapter;

    public GetCountryNames(Context ctx, Adapter adapter) {
        this.context = ctx;
        this.adapter = adapter;

    }

    @Override
    protected Void doInBackground(String... urls) {
        String resultado = null;
        JSONObject json = null;
        JSONArray jsonArray = null;
        HttpURLConnection urlConnection = null;
        try {
            // Conecta con la URL y obtenemos el fichero con los datos
            URL url = new URL(urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            // Lee el fichero y genera una cadena de texto con el JSON en resultado
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String linea = null;
            while ((linea = br.readLine()) != null) {
                sb.append(linea + "\n");
            }
            br.close();
            resultado = sb.toString();
            // Se carga el resultado como un objeto JSON
            json = new JSONObject(resultado);
            // Obtiene el JSON como un array de datos
            jsonArray = json.getJSONArray("");
            AbstractQueue<String> listaDatos = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                Log.d(TAG, "dato recibido: " + jsonArray.get(i).toString());
                //TODO
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            error = true;
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return null;
    }

    // Este método se ejecuta cuando se cancela la tarea, permite interactuar con la UI
    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    // Este método se ejecuta a medida que avanza la tarea. Permite actualizar parte
    // de la UI para que el usuario pueda ver el avance de la misma
    protected void onProgressUpdate(Void progreso) {
        super.onProgressUpdate(progreso);
    }

    // Este método se ejecuta automáticamente cuando la tarea
    // termina (cuando termina el método ''doInBackground'')
    // Permite interactuar con la GUI con lo que podemos comunicar
    // al usuario la finalización de la tarea o mensajes de error
    @Override
    protected void onPostExecute(Void resultado) {
        super.onPostExecute(resultado);
        if (error) {
            Toast.makeText(context, context.getResources().getString(R.string.error_message), Toast.LENGTH_SHORT).show();
            return;
        }

        // context.adapter.notifyDataSetChanged(); // Actualiza los cambios en ListView
        Toast.makeText(context, context.getResources().getString(R.string.datos_message), Toast.LENGTH_SHORT).show();
    }
}