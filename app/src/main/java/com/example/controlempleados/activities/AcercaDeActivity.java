package com.example.controlempleados.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.controlempleados.R;

import org.w3c.dom.Text;

public class AcercaDeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);

        TextView textView = findViewById(R.id.info);



        //TODO
        /*
        1. Añadiir un file interno con información del proyecto
        2. Leer el fichero y meterlo en el Text View
         */



        textView.setText("");

    }
}
