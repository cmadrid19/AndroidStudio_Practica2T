package com.example.controlempleados.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.controlempleados.R;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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


    private void readFile() {

        TextView tv1 = (TextView) findViewById(R.id.tv1);
        tv1.setText("");
        try {
            //Abre un flujo desde la carpeta raw donde se encuentra un fichero.txt
            BufferedReader fichero = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.fichero)));
            String lectura = fichero.readLine();

            //Le su contenido y lo mete en un StringBuilder
            StringBuilder sb = new StringBuilder();
            while (lectura != null) {
                sb.append(lectura + "\n");
                lectura = fichero.readLine();
            }

            //muestra el stringBuilder
            tv1.setText(sb);
            //Cierrra el flujo/fichero
            fichero.close();
        } catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}
