package com.example.controlempleados.activities;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.controlempleados.R;
import com.example.controlempleados.bean.Empleado;
import com.example.controlempleados.dao.DataBaseWorkers;
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

        //TODO HAY QUE CONVERTIR el fichero 'Workers.sql' que se encuentra en la raiz del proyecto a .db  ;Creo que se puede de csv a SQLite
        /*
           - Modelo Usuario Registrarse y entrar.
        * 1. Recycler view: handleview, modelo,
        * 2. BBDD

        TODO hcer un estilo para meter bordes a las filas del recycler
        * */

        DataBaseWorkers dbw = new DataBaseWorkers(this, "Prueba", null, 1);
        //TODO workers esta mal, se debe quitar el id
        dbw.insertarEmploeado("insert into WORKERS (first_name, last_name, email, gender, phone, location, avatar, department, language, hiring_date, birth_date) values ('Natale', 'Lagneaux', 'nlagneaux0@accuweather.com', 'Male', '437-271-7140', '870 Messerschmidt Parkway', 'https://robohash.org/totamvoluptatemvoluptate.bmp?size=50x50&set=set1', 'Accounting', 'New Zealand Sign Language', '2/2/2016', '10/18/1984')");
        dbw.insertarEmploeado("insert into WORKERS (first_name, last_name, email, gender, phone, location, avatar, department, language, hiring_date, birth_date) values ('Felipa', 'Mottershead', 'fmottershead2@examiner.com', 'Female', '552-211-4791', '90 Mallory Place', 'https://robohash.org/quocorporisoccaecati.png?size=50x50&set=set1', 'Accounting', 'West Frisian', '5/14/2011', '1/19/2000')");
        dbw.insertarEmploeado("insert into WORKERS (first_name, last_name, email, gender, phone, location, avatar, department, language, hiring_date, birth_date) values ('Tomi', 'Peele', 'tpeele3@wp.com', 'Female', '439-262-8999', '24 Anthes Crossing', 'https://robohash.org/impediteanemo.jpg?size=50x50&set=set1', 'Training', 'Czech', '7/20/2011', '2/21/1980')");
        dbw.insertarEmploeado("insert into WORKERS (first_name, last_name, email, gender, phone, location, avatar, department, language, hiring_date, birth_date) values ('Gustaf', 'Maundrell', 'gmaundrell4@flickr.com', 'Male', '770-596-4414', '37416 Wayridge Junction', 'https://robohash.org/utetconsequatur.png?size=50x50&set=set1', 'Marketing', 'Gujarati', '10/1/2011', '6/22/1985')");
        dbw.insertarEmploeado("insert into WORKERS (first_name, last_name, email, gender, phone, location, avatar, department, language, hiring_date, birth_date) values ('Mae', 'Dyka', 'mdyka5@shutterfly.com', 'Female', '402-326-5393', '15049 Ludington Crossing', 'https://robohash.org/auttemporefuga.jpg?size=50x50&set=set1', 'Sales', 'Czech', '11/16/2010', '5/3/1995')");

        datos = dbw.getEmpleados();

        for (int x = 0; x < datos.size(); x++) {
            Log.d(TAG, "El adapter tiene: "+String.valueOf(datos.get(x)));
        }

        recView = (RecyclerView) findViewById(R.id.lista_empleados);
        recView.setLayoutManager(new LinearLayoutManager(this));
        adaptador = new AdapterEmpleados(datos, this);
        recView.setAdapter(adaptador);


    }

    //ESTE MÉTODO SE INVOCA AL TOCAR UNA OPCIÓN DEL MENÚ
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_anhadir_empleado:
                Intent intent = new Intent(this, AnhadirEmpleadoActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_acerca_de:
                Intent intent1 = new Intent(this, AcercaDeActivity.class);
                startActivity(intent1);
                break;
            case R.id.menu_salir:
                // Eliminamos preferencias de login
                SharedPreferences prefs;
                prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = prefs.edit();
                edit.putString("name", "");
                edit.putString("password", "");
                edit.commit();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //debo sobreescribir este método para cargar mi menú
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mi = getMenuInflater();//este objeto será el encargado de cargar/inlfar mi menú
        mi.inflate(R.menu.menu_opciones, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
