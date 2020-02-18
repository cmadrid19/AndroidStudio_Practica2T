package com.example.controlempleados.utiles;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.controlempleados.R;
import com.example.controlempleados.bean.Empleado;

public class EmpleadoViewHolder extends RecyclerView.ViewHolder {

    private TextView tvNombreEmpleado;
    private TextView tvDepartamentoEmpleado;
    private TextView tvIdEmpleado;
    private TextView tvEmailEmpleado;

    public EmpleadoViewHolder(View itemView) {
        super(itemView);
        tvNombreEmpleado = (TextView) itemView.findViewById(R.id.txtViewNombreEmpleado);
        tvDepartamentoEmpleado = (TextView) itemView.findViewById(R.id.txtViewDepartamentoEmpleado);
        tvIdEmpleado = (TextView) itemView.findViewById(R.id.txtViewIdEmpleado);
        tvEmailEmpleado = (TextView) itemView.findViewById(R.id.txtViewEmailEmpleado);
    }


    public void cargarEmpleadoEnHolder(Empleado e) {
        tvNombreEmpleado.setText(e.getFirstName()+" "+e.getLast_name());
        tvDepartamentoEmpleado.setText(e.getDepartment());
        tvEmailEmpleado.setText(e.getEmail());
        tvIdEmpleado.setText(String.valueOf(e.getId()));
    }
}
