package com.example.controlempleados.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.controlempleados.R;
import com.example.controlempleados.bean.User;
import com.example.controlempleados.dao.DataBase;
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

<<<<<<< HEAD
=======
    public static final String PREFS_NAME = "MisPreferencias";
>>>>>>> 0ae4d7b99808c6e84cf5e8f989f461a75eb56ca1
    private static final String TAG = "CrearCuentaNuevaActivit";
    private static DataBase db = null;


    EditText ediTxRecogeUsuario;
    EditText editTextPassword;
    EditText editTextRepeatPassword;
    EditText ediTxPlannedDatePicker;
    RadioGroup radioSexo;

    private Spinner spinner;
    private ArrayList paises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        db = new DataBase(this, "miBaseDatos", null, 1);


        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

        radioSexo = findViewById(R.id.sexo_grupo);
        ediTxRecogeUsuario = (EditText) findViewById(R.id.edittext_usuario);
        editTextPassword = (EditText) findViewById(R.id.edittext_password_sign_in);
        editTextRepeatPassword = (EditText) findViewById(R.id.edittext_repeat_password_sign_in);

        //Spinner contries
        spinner = findViewById(R.id.paises_spinner);
        paises = new ArrayList<String>();
        paises.add(getResources().getString(R.string.pais_no_seleccionado));
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,paises);
        spinner.setAdapter(adapter);
        new GetCountryNames().execute();

        ediTxPlannedDatePicker = (EditText) findViewById(R.id.edittext_recoge_fecha_nacimiento);
        ediTxPlannedDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.edittext_recoge_fecha_nacimiento:
                        showDatePickerDialog();
                        break;
                }
            }
        });
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

    private boolean validarFechaNacimiento(){
        Boolean fechaEscogida = false;

        String fecha = ediTxPlannedDatePicker.getText().toString();
        if (!fecha.matches("")){
            fechaEscogida = true;
        }

        return fechaEscogida;
    }

    private boolean validarSexo() {
        Boolean elegido = false;
        int selectedId = radioSexo.getCheckedRadioButtonId();
        if (selectedId != -1) {
            elegido = true;
        }
        return elegido;
    }

    private Boolean validarSpinner() {
        Boolean paisSeleccionado = false;
        String text = spinner.getSelectedItem().toString();
        if (text.equals(this.getResources().getString(R.string.pais_no_seleccionado))){
            //Pais no seleccionado, lanzar toast
        }{
            paisSeleccionado = true;
            Log.d(TAG, "El país seleccionado es: "+text);
        }
        return paisSeleccionado;
    }


    private boolean validarEmail(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean comprobarPassword() {
        Boolean correcta = false;
        if (editTextPassword.getText().toString().equals(editTextRepeatPassword.getText().toString())){
            correcta = true;
        } else {
            Toast.makeText(this, getResources().getString(R.string.contrasenha_no_coincide), Toast.LENGTH_SHORT).show();
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
                && validarFechaNacimiento()) {

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

            User user = new User(email,usuario, pass, fechaNacimiento, sexo, nacionalidad);

            
            db.insertarUser(user);

            Toast.makeText(this, "Usuario " + user.getName() + " registrado!", Toast.LENGTH_LONG).show();

            Intent intent = (new Intent(this, MainActivity.class));
            startActivity(intent);
            //añadir preferencias

        } else {
            Toast.makeText(this, getResources().getString(R.string.rellena_campos), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, LoginActivity.class));
        this.finish();
    }


    class GetCountryNames extends AsyncTask<String, Void, Void> {
        private ArrayAdapter<String> adapter;
        private Boolean error = false;

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
                Log.d(TAG, sb.toString()+"" );
                br.close();
                resultado = sb.toString();

                // Se carga el resultado como un objeto JSON
                json = new JSONObject(resultado);

                // Obtiene el JSON como un array de datos
                jsonArray = json.getJSONArray("result");
                Log.d(TAG, jsonArray.toString()+"" );
                AbstractQueue<String> listaDatos = null;
                for (int i = 0; i < jsonArray.length(); i++) {
                    Log.d(TAG, "pais recibido: " + jsonArray.getJSONObject(i).getString("name"));
                    paises.add(jsonArray.getJSONObject(i).getString("name"));
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
                ioe.printStackTrace();
                error = true;
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if(urlConnection != null){
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
            if (error) {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_mensage), Toast.LENGTH_SHORT).show();
            } else {
                // Actualiza los cambios en ListView
                this.adapter.notifyDataSetChanged();
                adapter = new ArrayAdapter<String> (getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, paises);
                spinner.setAdapter(adapter);
            }
        }
    }


}


