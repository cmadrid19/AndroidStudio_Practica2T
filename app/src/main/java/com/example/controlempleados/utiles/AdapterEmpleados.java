package com.example.controlempleados.utiles;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.example.controlempleados.R;
import com.example.controlempleados.bean.Empleado;

import java.util.ArrayList;

public class AdapterEmpleados extends RecyclerView.Adapter<EmpleadoViewHolder> {
    private ArrayList<Empleado> datos;

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

        Empleado emp = datos.get(position);
        holder.cargarEmpleadoEnHolder(emp);

    }

    @Override
    public int getItemCount() {
            return datos.size();
    }

    public AdapterEmpleados(ArrayList<Empleado> emlpeados)
    {
        this.datos = emlpeados;
    }
}
