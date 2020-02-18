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

    public EmpleadoViewHolder(View itemView) {
        super(itemView);
        tvNombreEmpleado = (TextView) itemView.findViewById(R.id.txtViewNombreEmpleado);
        tvDepartamentoEmpleado = (TextView) itemView.findViewById(R.id.txtViewDepartamentoEmpleado);
        tvIdEmpleado = (TextView) itemView.findViewById(R.id.txtViewIdEmpleado);
    }


    public void cargarEmpleadoEnHolder(Empleado e) {
        tvNombreEmpleado.setText(e.getFirstName());
        tvDepartamentoEmpleado.setText(e.getDepartment());
        tvIdEmpleado.setText(e.getId());
    }
}
