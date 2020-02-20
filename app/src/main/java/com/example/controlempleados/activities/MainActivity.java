package com.example.controlempleados.activities;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

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


        /*
        TODO
        * 1. Mejorar aspecto recyclerview
        * 2. Iconos en menu superior
        3. Terminar añadir empleado con la BBDD- > notificar el adater que los datos han cambiado y ademas meterlo en la bbdd
        4. añadir supportbuttonbar para ir atras



        TODO hcer un estilo para meter bordes a las filas del recycler
        * */

        final DataBaseWorkers dbw = new DataBaseWorkers(this, "Prueba", null, 1);

        datos = dbw.getEmpleados();

        for (int x = 0; x < datos.size(); x++) {
            Log.d(TAG, "El adapter tiene: " + String.valueOf(datos.get(x)));
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
                //Borrarlo de la base de datos
                Empleado e = datos.get(viewHolder.getAdapterPosition());
                dbw.borrarEmpleado(e);
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.has_eliminado_empleado) + e.toString(), Toast.LENGTH_SHORT).show();
                //Borrarlo de los datos
                datos.remove(viewHolder.getAdapterPosition());
                adaptador.notifyDataSetChanged();
            }


            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                final ColorDrawable background = new ColorDrawable(Color.RED);

                background.setBounds(0,
                        viewHolder.itemView.getTop(),
                        (int) (viewHolder.itemView.getLeft() + dX),
                        viewHolder.itemView.getBottom());

                background.draw(c);


                Drawable icon = ContextCompat.getDrawable(getApplicationContext(), R.drawable.remove_employee);
                //TODO no se ve el icono
                icon.setBounds(viewHolder.itemView.getRight() , 0, viewHolder.itemView.getRight() - 10, 0 + icon.getIntrinsicHeight());
                icon.draw(c);

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

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
        //para finish todas las actividades anteriores
        Intent intent2 = new Intent(getApplicationContext(), LoginActivity.class);
        intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent2);
    }

    private void limpiarSharedPreference() {
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
