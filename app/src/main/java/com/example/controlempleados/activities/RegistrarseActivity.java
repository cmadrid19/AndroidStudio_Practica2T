package com.example.controlempleados.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.controlempleados.R;
import com.example.controlempleados.bean.User;
import com.example.controlempleados.dao.DataBaseUsers;
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
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegistrarseActivity extends AppCompatActivity {

    private static final String TAG = RegistrarseActivity.class.getSimpleName();
    private static DataBaseUsers db = null;

    EditText ediTxRecogeUsuario;
    EditText editTextPassword;
    EditText editTextRepeatPassword;
    EditText ediTxPlannedDatePicker;
    RadioGroup radioSexo;

    private Spinner spinner;
    private ArrayList paises;

    private String error = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        db = new DataBaseUsers(this, getResources().getString(R.string.bd_nombre_usuarios), null, 1);

        radioSexo = findViewById(R.id.sexo_grupo);
        ediTxRecogeUsuario = (EditText) findViewById(R.id.edittext_usuario);
        editTextPassword = (EditText) findViewById(R.id.edittext_password_sign_in);
        editTextRepeatPassword = (EditText) findViewById(R.id.edittext_repeat_password_sign_in);
        ediTxPlannedDatePicker = findViewById(R.id.edittext_recoge_fecha_nacimiento);

        //Spinner contries
        spinner = findViewById(R.id.paises_spinner);
        paises = new ArrayList<String>();
        paises.add(getResources().getString(R.string.pais_no_seleccionado));
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, paises);
        spinner.setAdapter(adapter);
        new GetCountryNames().execute();


    }

    public void escogerFecha(View view) {
        //Para que no se abra el teclado
        try{
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            showDatePickerDialog();
        }catch (IllegalStateException e){
            e.getMessage();
        }

    }


    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero
                final String selectedDate = year + "-" + (month + 1) + "-" + day;
                ediTxPlannedDatePicker.setText(selectedDate);
            }
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    private Boolean validarUsuario() {
        Boolean rellenado = false;

        String usuario = ediTxRecogeUsuario.getText().toString();
        if (!usuario.matches("")) {
            rellenado = true;
        } else {
            error = getResources().getString(R.string.usuario_no_introducido);
        }
        return rellenado;
    }

    private boolean validarFechaNacimiento() {
        Boolean fechaEscogida = false;

        String fecha = ediTxPlannedDatePicker.getText().toString();
        if (!fecha.matches("")) {
            fechaEscogida = true;
        } else {
            error = getResources().getString(R.string.fecha_no_escogida);
        }
        return fechaEscogida;
    }

    private boolean validarSexo() {
        Boolean elegido = false;
        int selectedId = radioSexo.getCheckedRadioButtonId();
        if (selectedId != -1) {
            elegido = true;
        } else {
            error = getResources().getString(R.string.genero_no_escogido);
        }
        return elegido;
    }

    private Boolean validarSpinner() {
        Boolean paisSeleccionado = false;
        String text = spinner.getSelectedItem().toString();
        if (text.equals(this.getResources().getString(R.string.pais_no_seleccionado))) {
            error = getResources().getString(R.string.pais_no_seleccionado);
        }
        {
            paisSeleccionado = true;
            Log.d(TAG, "El país seleccionado es: " + text);
        }
        return paisSeleccionado;
    }


    private boolean validarEmail(String email) {
        Boolean emailCheck = false;
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            emailCheck = true;
        } else {
            error = getResources().getString(R.string.email_formato_incorrecto);
        }
        return emailCheck;
    }

    public boolean comprobarPassword() {
        Boolean correcta = false;
        if (editTextPassword.getText().toString().equals(editTextRepeatPassword.getText().toString())) {
            correcta = true;
        } else {
            error = getResources().getString(R.string.contrasenha_no_coincide);
        }
        return correcta;
    }

    public void aceptarCrearNuevaCuenta(View view) {
        EditText editTxRecogeEmail = (EditText) findViewById(R.id.editText_email);
        String email = editTxRecogeEmail.getText().toString();

        if (validarSexo()
                && validarEmail(email)
                && comprobarPassword()
                && validarSpinner()
                && validarFechaNacimiento()
                && validarUsuario()) {

            String usuario = ediTxRecogeUsuario.getText().toString();
            String pass = editTextPassword.getText().toString();
            String nacionalidad = spinner.getSelectedItem().toString();
            String fechaNacimiento = ediTxPlannedDatePicker.getText().toString();

            RadioButton radioButton = (RadioButton) findViewById(radioSexo.getCheckedRadioButtonId());
            char sexo = radioButton.getText().toString().charAt(0);

            Log.d(TAG, "el email es: " + email);
            Log.d(TAG, "el usuario es: " + usuario);
            Log.d(TAG, "la Contraseña del usuario es: " + pass);
            Log.d(TAG, "la fecha de nacimiento es: " + fechaNacimiento);
            Log.d(TAG, "el sexo del usuario es: " + sexo);
            Log.d(TAG, "La nacionalidad es: " + nacionalidad);

            User user = new User(email, usuario, pass, fechaNacimiento, sexo, nacionalidad);
            db.insertarUser(user);

            Intent intent = (new Intent(this, LoginActivity.class));
            this.finish();
            startActivity(intent);
        } else {
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //para finish todas las actividades anteriores
        Intent intent2 = new Intent(getApplicationContext(), LoginActivity.class);
        intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent2);
        this.finish();
    }


    class GetCountryNames extends AsyncTask<String, Void, Void> {
        private ArrayAdapter<String> adapter;
        private Boolean errorBool = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            adapter = (ArrayAdapter<String>) spinner.getAdapter();
        }

        @Override
        protected Void doInBackground(String... urls) {

            String resultado = null;
            JSONObject json = null;
            JSONArray jsonArray = null;
            HttpURLConnection urlConnection = null;
            try {
                // Conecta con la URL y obtenemos el fichero con los datos
                URL url = new URL(getApplicationContext().getResources().getString(R.string.ruta_paises));
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                // Lee el fichero y genera una cadena de texto con el JSON en resultado
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                StringBuilder sb = new StringBuilder();
                String linea = null;
                while ((linea = br.readLine()) != null) {
                    sb.append(linea + "\n");
                }
                Log.d(TAG, sb.toString() + "");
                br.close();
                resultado = sb.toString();

                // Se carga el resultado como un objeto JSON
                json = new JSONObject(resultado);

                // Obtiene el JSON como un array de datos
                jsonArray = json.getJSONArray("result");
                Log.d(TAG, jsonArray.toString() + "");
                AbstractQueue<String> listaDatos = null;
                for (int i = 0; i < jsonArray.length(); i++) {
                    Log.d(TAG, "pais recibido: " + jsonArray.getJSONObject(i).getString("name"));
                    paises.add(jsonArray.getJSONObject(i).getString("name"));
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
                ioe.printStackTrace();
                errorBool = true;
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            return null;
        }


        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        protected void onProgressUpdate(Void progreso) {
            super.onProgressUpdate(progreso);
        }

        @Override
        protected void onPostExecute(Void resultado) {
            super.onPostExecute(resultado);
            if (errorBool) {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_mensage), Toast.LENGTH_SHORT).show();
            } else {
                // Actualiza los cambios en ListView
                this.adapter.notifyDataSetChanged();
                adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, paises);
                spinner.setAdapter(adapter);
            }
        }
    }


}


