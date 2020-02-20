package com.example.controlempleados.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.controlempleados.R;
import com.example.controlempleados.bean.Empleado;
import com.example.controlempleados.dao.DataBaseWorkers;
import com.example.controlempleados.utiles.DatePickerFragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnhadirEmpleadoActivity extends AppCompatActivity {

    private static final String TAG = AnhadirEmpleadoActivity.class.getSimpleName();
    private String error = "";

    EditText edFechaNacimiento;
    EditText edFechaContratacin;
    RadioGroup radioGroupSexo;
    EditText edNombreEmpleado;
    EditText edApellidoEmpleado;
    EditText edAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anhadir_empleado_actiivity);

        edFechaContratacin = findViewById(R.id.edTxFechaContratacion);
        edFechaNacimiento = findViewById(R.id.edTxFechaNacimiento1);
        radioGroupSexo = findViewById(R.id.sexo_grupo);
        edNombreEmpleado = findViewById(R.id.edTxNombre);
        edApellidoEmpleado = findViewById(R.id.edTxApellido);
        edAvatar = findViewById(R.id.edTxAvatar);

        //Flecha atras
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void introducirEmpleado(View view) {
        EditText edEmail = findViewById(R.id.edTxEmail);
        String email = edEmail.getText().toString();

        Log.d("Aña", error);

        if (validarFechaContratación()
                && validarFechaNacimiento()
                && validarSexo()
                && validarNombreEmpleado()
                && validarAvatar()
                && validarEmail(email)) {


            RadioButton rb = findViewById(radioGroupSexo.getCheckedRadioButtonId());
            EditText edPhone = findViewById(R.id.edTxTlfn);
            EditText edDepartamento = findViewById(R.id.edTxDepartamento);
            EditText edLengua = findViewById(R.id.edTxLengua);
            EditText edDireccion = findViewById(R.id.edTxDireccion);

            String nombre =edNombreEmpleado.getText().toString();
            String apellido = edApellidoEmpleado.getText().toString();
            //Email
            String gender = rb.getText().toString();
            String phone = edPhone.getText().toString();
            String direccion = edDireccion.getText().toString();
            String avatar = edAvatar.getText().toString();
            String departamento = edDepartamento.getText().toString();
            String lengua = edLengua.getText().toString();
            String fechaContra = edFechaContratacin.getText().toString();
            String fechaNaci = edFechaNacimiento.getText().toString();

            Empleado e = new Empleado(nombre, apellido, email, gender, phone, direccion, avatar, departamento, lengua, fechaContra, fechaNaci);

            //Insertando empleado en la BBDD
            DataBaseWorkers dbw = new DataBaseWorkers(this, "Prueba", null, 1);
            dbw.insertarWoker(e);
            Log.d(TAG, "hola");
            Log.d(TAG, "Empleado añadidio: "+dbw.getEmpleado(e.getFirstName()));

            //notificar adapter
            new MainActivity().updateAdatperEmplepadoNuevo(e);

        } else {
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validarFechaNacimiento() {
        Boolean fechaEscogida = false;

        String fecha = edFechaNacimiento.getText().toString();
        if (!fecha.matches("")) {
            fechaEscogida = true;
        } else {
            error = getResources().getString(R.string.fecha_no_escogida);
        }
        return fechaEscogida;
    }

    private boolean validarFechaContratación() {
        Boolean fechaEscogida = false;

        String fecha = edFechaContratacin.getText().toString();
        if (!fecha.matches("")) {
            fechaEscogida = true;
        } else {
            error = getResources().getString(R.string.fecha_no_escogida);
        }
        return fechaEscogida;
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

    private boolean validarSexo() {
        Boolean elegido = false;
        int selectedId = radioGroupSexo.getCheckedRadioButtonId();
        if (selectedId != -1) {
            elegido = true;
        } else {
            error = getResources().getString(R.string.genero_no_escogido);
        }
        return elegido;
    }

    private boolean validarAvatar() {
        Boolean correcto = false;
        String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(edAvatar.getText().toString());
        if (matcher.matches()) {
            correcto = true;
        } else {
            error = getResources().getString(R.string.avatar_incorrecto);
        }
        return correcto;
    }

    private Boolean validarNombreEmpleado() {
        Boolean rellenado = false;

        String nombre = edNombreEmpleado.getText().toString();
        String apellido = edApellidoEmpleado.getText().toString();
        if (!nombre.matches("") && !apellido.matches("")) {
            rellenado = true;
        } else {
            error = getResources().getString(R.string.usuario_no_introducido);
        }
        return rellenado;
    }


    public void escogerFechaContratación(View view) {
        mantenerTecladoCerrado();
        showDatePickerDialog(edFechaContratacin);
    }

    public void escogerFechaNacimiento(View view) {
        mantenerTecladoCerrado();
        showDatePickerDialog(edFechaNacimiento);
    }

    private void mantenerTecladoCerrado() {
        //Para que no se abra el teclado
        try{
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }catch (IllegalStateException e){
            Log.d(TAG, e.getMessage());
        }
    }

    private void showDatePickerDialog(final EditText editText) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero
                final String selectedDate =  (month + 1) + "/" + day + "/" + year ;
                editText.setText(selectedDate);
            }
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}
