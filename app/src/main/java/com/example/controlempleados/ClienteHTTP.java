package com.example.controlempleados;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.example.controlempleados.utiles.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.AbstractQueue;

public class ClienteHTTP{

    /*
    private static final String TAG = "ClienteHTTP";

    public static void GetCounrtiesNames(final Context ctx) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection myConnection = null;
                InputStream responseBody = null;
                String resultado = null;
                JSONObject json = null;
                JSONArray jsonArray = null;
                HttpURLConnection urlConnection = null;


                try {
                    URL url = new URL(Constants.COUNTRIES_NAMES_URL);
                    Log.d(TAG, "conectando a " + url.toString());
                    myConnection = (HttpURLConnection) url.openConnection();

                    urlConnection = (HttpURLConnection) url.openConnection();
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    // Lee el fichero y genera una cadena de texto con el JSON en resultado
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    StringBuilder sb = new StringBuilder();
                    String linea = null;
                    while ((linea = br.readLine()) != null) {
                        sb.append(linea + "\n");
                    }
                    br.close();
                    resultado = sb.toString();
                    // Se carga el resultado como un objeto JSON
                    json = new JSONObject(resultado);
                    // Obtiene el JSON como un array de datos
                    jsonArray = json.getJSONArray("");

                    AbstractQueue<String> listaDatos = null;
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Log.d(TAG, "dato recibido: "+jsonArray.get(i).toString());

                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
*/


}
