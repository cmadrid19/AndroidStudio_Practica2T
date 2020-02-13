package com.example.controlempleados.activities;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.example.controlempleados.R;
import com.example.controlempleados.bean.Empleado;
import com.example.controlempleados.utiles.AdapterEmpleados;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";

    private RecyclerView recView;
    private ArrayList<Empleado> datos;
    private AdapterEmpleados adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO
        /*
           - Modelo Usuario Registrarse y entrar.
        * 1. Recycler view: handleview, modelo,
        * 2. BBDD
        * 3.
        * */

        recView = (RecyclerView) findViewById(R.id.recycler_view);


    }
}
