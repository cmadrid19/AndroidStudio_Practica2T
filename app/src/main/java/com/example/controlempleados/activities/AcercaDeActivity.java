package com.example.controlempleados.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.controlempleados.R;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AcercaDeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);

        TextView textView = findViewById(R.id.info);
        textView.setText(leerFichero());
    }

    private String leerFichero(){
        String info = "";
        try {
            //Abre un flujo desde la carpeta raw donde se encuentra un fichero.txt
            BufferedReader fichero = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.info)));
            String lectura = fichero.readLine();

            //Le su contenido y lo mete en un StringBuilder
            StringBuilder sb = new StringBuilder();
            while (lectura != null) {
                sb.append(lectura + "\n");
                lectura = fichero.readLine();
            }
            info = sb.toString();
            //Cierrra el flujo/fichero
            fichero.close();
        } catch (IOException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return info;
    }

    public void escogerFechaNacimiento(View view) {
    }
}
