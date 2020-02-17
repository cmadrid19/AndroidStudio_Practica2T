package com.example.controlempleados.utiles;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.controlempleados.R;
import com.example.controlempleados.bean.Empleado;

public class EmpleadoViewHolder extends RecyclerView.ViewHolder {

    private TextView text_view_titulo;
    private TextView text_view_autor;

    public EmpleadoViewHolder(View itemView) {
        super(itemView);
    }


    public void cargarEmpleadoEnHolder(Empleado e) {
        //text_view_titulo.setText(e.getTitulo());
        //text_view_autor.setText(e.getAutor());
    }
}
