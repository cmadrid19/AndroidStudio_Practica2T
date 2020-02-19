package com.example.controlempleados.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.controlempleados.R;
import com.example.controlempleados.bean.Empleado;

public class AnhadirEmpleadoActivity extends AppCompatActivity {

    private String error = "";

    EditText edFechaNacimiento;
    EditText edFechaContratacin;
    RadioGroup radioGroupSexo;
    EditText edNombreEmpleado;
    EditText edApellidoEmpleado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anhadir_empleado_actiivity);

        edFechaContratacin = findViewById(R.id.edTxFechaContratacion);
        edFechaNacimiento = findViewById(R.id.edTxFechaNacimiento);
        radioGroupSexo = findViewById(R.id.sexo_grupo);
        edNombreEmpleado = findViewById(R.id.edTxNombre);
        edApellidoEmpleado = findViewById(R.id.edTxApellido);

    }


    public void introducirEmpleado(View view) {
        if (validarFechaContratación()
                && validarFechaNacimiento()
                && validarSexo()
                && validarNombreEmpleado()) {
            //TODO insertar empleado en BBDD
            Empleado e = null;

        } else {
            Toast.makeText(this, error, Toast.LENGTH_SHORT);
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
        Boolean elegido = false;


        //TODO tipo
        if () {
            elegido = true;
        } else {
            error = getResources().getString(R.string.genero_no_escogido);
        }
        return elegido;
    }

    private Boolean validarNombreEmpleado() {
        Boolean rellenado = false;

        String nombre = edNombreEmpleado.getText().toString();
        String apellido = edApellidoEmpleado.getText().toString();
        if (!nombre.matches("") && apellido.matches("")) {
            rellenado = true;
        } else {
            error = getResources().getString(R.string.usuario_no_introducido);
        }
        return rellenado;
    }

    public void escogerFechaContratación(View view) {

    }

    public void escogerFechaNacimiento(View view) {

    }
}
