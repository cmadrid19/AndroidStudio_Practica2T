package com.example.controlempleados.utiles;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.example.controlempleados.R;
import com.example.controlempleados.activities.DetalleEmpleadoActivity;
import com.example.controlempleados.bean.Empleado;

import java.util.ArrayList;

public class AdapterEmpleados extends RecyclerView.Adapter<EmpleadoViewHolder> {

    private ArrayList<Empleado> datos;
    Context ctx;


    public AdapterEmpleados(ArrayList<Empleado> emlpeados, Context ctx) {
        this.ctx = ctx;
        this.datos = emlpeados;
    }

    //Creo la vista, con el Holder dentro
    @Override
    public EmpleadoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        EmpleadoViewHolder empleadoViewHolder = null;

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View itemView = inflater.inflate(R.layout.layout_empleado_row, parent, false);

        empleadoViewHolder = new EmpleadoViewHolder(itemView);

        return empleadoViewHolder;
    }

    //Al holder, le meto la info del empleado que toca
    @Override
    public void onBindViewHolder(EmpleadoViewHolder holder, int position) {

        final Empleado emp = datos.get(position);
        holder.cargarEmpleadoEnHolder(emp);

        //Onclick de la fila recycler
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (InternetStatus.haveNetwork(ctx) == true) {
                    Intent intent = new Intent(ctx, DetalleEmpleadoActivity.class);
                    intent.putExtra("emp", emp);
                    ctx.startActivity(intent);
                } else {
                    Toast.makeText(ctx, ctx.getResources().getString(R.string.sin_conexion_internet), Toast.LENGTH_SHORT).show();
                }
            }
        });

        //TODO Slide a la derecha para borrar empleado


    }


    @Override
    public int getItemCount() {
        return datos.size();
    }


}
