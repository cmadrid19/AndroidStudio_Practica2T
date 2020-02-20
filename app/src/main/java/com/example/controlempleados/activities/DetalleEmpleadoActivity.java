package com.example.controlempleados.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.controlempleados.R;
import com.example.controlempleados.bean.Empleado;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DetalleEmpleadoActivity extends AppCompatActivity {

    private static final String TAG = DetalleEmpleadoActivity.class.getSimpleName();
    private ImageView img;
    private ProgressBar progressImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_empleado);

        Empleado emp = (Empleado) getIntent().getSerializableExtra("emp");
        ;

        Log.d(TAG, "El empleado a enseñar es: " + emp.toString());

        //Cargar datos
        TextView tvID = findViewById(R.id.tvID);
        TextView tvFName = findViewById(R.id.tvFirstName);
        TextView tvLName = findViewById(R.id.tvLastName);
        TextView tvEmail = findViewById(R.id.tvEmail);
        TextView tvGender = findViewById(R.id.tvGender);
        TextView tvPhone = findViewById(R.id.tvPhone);
        TextView tvLocation = findViewById(R.id.tvLocation);
        TextView tvDepartment = findViewById(R.id.tvDepartment);
        TextView tvLanguage = findViewById(R.id.tvLanguage);
        TextView tvHiringDate = findViewById(R.id.tvHiringDate);
        TextView tvBirthDate = findViewById(R.id.tvBirthDate);

        tvID.setText(String.valueOf(emp.getId()));
        tvFName.setText(emp.getFirstName());
        tvLName.setText(emp.getLast_name());
        tvEmail.setText(emp.getEmail());
        tvGender.setText(emp.getGender());
        tvPhone.setText(String.valueOf(emp.getPhone()));
        tvLocation.setText(emp.getLocation());
        tvDepartment.setText(emp.getDepartment());
        tvLanguage.setText(emp.getLanguage());
        tvHiringDate.setText(emp.getHiringDate());
        tvBirthDate.setText(emp.getBirthDate());

        //Cargar la foto
        img = findViewById(R.id.imgAvatar);
        progressImg = findViewById(R.id.progressImg);
        progressImg.setIndeterminate(true);

        new DescargarImagen().execute(emp.getAvatar());
    }

        class DescargarImagen extends AsyncTask<String, Void, Void> {
            private Boolean error = false;
            Bitmap bitmap;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                img.setVisibility(View.GONE);
                progressImg.setVisibility(View.VISIBLE);
            }

            @Override
            protected Void doInBackground(String... urls) {

                InputStream in = null;
                int respuesta = -1;
                HttpURLConnection httpConn = null;
                URL url = null;

                try {
                    url = new URL(urls[0]);
                    httpConn = (HttpURLConnection) url.openConnection();
                    respuesta = httpConn.getResponseCode();
                    if (respuesta == HttpURLConnection.HTTP_OK) {
                        in = httpConn.getInputStream();
                        bitmap = BitmapFactory.decodeStream(in);
                        in.close();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    error = true;
                }
                return null;
            }


            @Override
            protected void onCancelled() {
                super.onCancelled();
            }

            protected void onProgressUpdate(Void progreso) {
                super.onProgressUpdate(progreso);
            }

            @Override
            protected void onPostExecute(Void resultado) {
                super.onPostExecute(resultado);
                if (error) {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.error_mensage), Toast.LENGTH_SHORT).show();
                } else {
                    img.setImageBitmap(bitmap);
                    img.setVisibility(View.VISIBLE);
                    progressImg.setVisibility(View.GONE);
                }
            }
        }

}
