package com.example.controlempleados.activities;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
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
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

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

        datos = dbw.getEmpleados();

        for (int x = 0; x < datos.size(); x++) {
            Log.d(TAG, "El adapter tiene: "+String.valueOf(datos.get(x)));
        }

        recView = (RecyclerView) findViewById(R.id.lista_empleados);
        recView.setLayoutManager(new LinearLayoutManager(this));
        adaptador = new AdapterEmpleados(datos, this);

        //Animación
        final LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(this, R.anim.cascada);
        recView.setLayoutAnimation(controller);

        //cargar datos
        recView.setAdapter(adaptador);

        //Slide raws - Borrar empleado
        ItemTouchHelper.SimpleCallback itemTouchHelperCallBack = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                datos.remove(viewHolder.getAdapterPosition());
                adaptador.notifyDataSetChanged();

            }
        };
        new ItemTouchHelper(itemTouchHelperCallBack).attachToRecyclerView(recView);
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
                limpiarSharedPreference();
                //para finish todas las actividades anteriores
                Intent intent2 = new Intent(getApplicationContext(), LoginActivity.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        limpiarSharedPreference();
    }

    private void limpiarSharedPreference(){
        //Borramos el usuario guardado
        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
        editor.apply();
    }

    //TODO es necesario?
    public void updateAdatperEmplepadoNuevo(Empleado e) {
        this.datos.add(e);
        this.adaptador.notifyDataSetChanged();
    }

}
