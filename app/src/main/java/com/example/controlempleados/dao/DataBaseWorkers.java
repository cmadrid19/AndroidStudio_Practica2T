package com.example.controlempleados.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.controlempleados.R;
import com.example.controlempleados.bean.Empleado;
import com.example.controlempleados.bean.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DataBaseWorkers extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    final String WORKERS_DB_NAME = "WORKERS";
    private Context context;
    private SQLiteDatabase db;

    public DataBaseWorkers(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

        this.context = context;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);

        this.db = db;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String WORKER_ID = "id";
        final String WORKER_NAME = "first_name";
        final String WORKER_SURNAME = "last_name";
        final String WORKER_EMAIL = "email";
        final String WORKER_GENDER = "gender";
        final String WORKER_PHONE = "phone";
        final String WORKER_LOCATION = "location";
        final String WORKER_AVATAR = "avatar";
        final String WORKER_DEPARTMENT = "department";
        final String WORKER_LANGUAGE = "language";
        final String WORKER_HIRING_DATE = "hiring_date";
        final String WORKER_BIRTH_DATE = "birth_date";


        final String SQL_TABLE_WORKERS = " CREATE TABLE " + WORKERS_DB_NAME
                + " ( "
                + WORKER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + WORKER_NAME + " TEXT, "
                + WORKER_SURNAME + " TEXT,"
                + WORKER_EMAIL + " TEXT,"
                + WORKER_GENDER + " TEXT,"
                + WORKER_PHONE + " INTEGER,"
                + WORKER_LOCATION + " TEXT,"
                + WORKER_AVATAR + " TEXT,"
                + WORKER_DEPARTMENT + " TEXT,"
                + WORKER_LANGUAGE + " TEXT,"
                + WORKER_HIRING_DATE + " TEXT,"
                + WORKER_BIRTH_DATE + " TEXT"
                + ")";

        db.execSQL(SQL_TABLE_WORKERS);

        this.importarEmpleados(this.db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Borrar tabla si ya existe
    }

    private void cerrarBaseDatos(SQLiteDatabase database) {
        database.close();
    }

    public  ArrayList<Empleado> getEmpleados() {
        ArrayList<Empleado> arrListaEmpleados = null;
        Empleado empleado = null;

        int id = -1;
        String name = "";
        String surname = "";
        String email = "";
        String gender = "";
        int phone = -1;
        String location = "";
        String avatar = "";
        String department = "";
        String language = "";
        String hiringDate = "";
        String birthDate = "";

        String consulta = "SELECT * FROM "+ WORKERS_DB_NAME;

        SQLiteDatabase basedatos = this.getReadableDatabase();
        Cursor cursor = basedatos.rawQuery(consulta, null);


        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            arrListaEmpleados = new ArrayList<Empleado>(cursor.getCount());
            do {
                id = cursor.getInt(0);
                name = cursor.getString(1);
                surname = cursor.getString(2);
                email = cursor.getString(3);
                gender = cursor.getString(4);
                phone = cursor.getInt(5);
                location = cursor.getString(6);
                avatar = cursor.getString(7);
                department = cursor.getString(8);
                language = cursor.getString(9);
                hiringDate = cursor.getString(10);
                birthDate = cursor.getString(11);


                empleado = new Empleado(id, name, surname, email, gender, phone, location, avatar,
                        department, language, hiringDate, birthDate);

                arrListaEmpleados.add(empleado);

            } while (cursor.moveToNext());
            cursor.close();
        }
        this.cerrarBaseDatos(basedatos);
        return arrListaEmpleados;
    }

    private void importarEmpleados(SQLiteDatabase db){
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(this.context.getResources().openRawResource(R.raw.workers)));

            String line = "";
            String query = "";
            int count = 0;

            while ((line = br.readLine()) != null) {
                db.execSQL(line);
                count++;
            }

            Toast.makeText(context, "Insertados: " + count, Toast.LENGTH_LONG);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Empleado getEmpleado(String nombre){
        Empleado e = null;

        String consulta = "SELECT * FROM WORKERS WHERE first_name LIKE "+"\""+ nombre +"\"";

        SQLiteDatabase basedatos = this.getReadableDatabase();
        Cursor cursor = basedatos.rawQuery(consulta, null);

        if( cursor != null && cursor.getCount() >0)
        {
            cursor.moveToFirst();

            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String surname = cursor.getString(2);
            String email = cursor.getString(3);
            String gender = cursor.getString(4);
            int phone = cursor.getInt(5);
            String location = cursor.getString(6);
            String avatar = cursor.getString(7);
            String department = cursor.getString(8);
            String language = cursor.getString(9);
            String hiringDate = cursor.getString(10);
            String birthDate = cursor.getString(11);

            e = new Empleado(id,name, surname, email, gender, phone, location, avatar, department, language, hiringDate, birthDate);

            cursor.close();
        }

        this.cerrarBaseDatos(basedatos);

        return e;
    }

    public void insertarWoker(Empleado e){
        SQLiteDatabase database = this.getWritableDatabase();

        SQLiteStatement query = database.compileStatement("INSERT INTO WORKERS (first_name, last_name,email, gender, phone, location, avatar, department" +
                ",language, hiring_date, birth_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        query.bindString(1, e.getFirstName());
        query.bindString(2, e.getLast_name());
        query.bindString(3, e.getEmail());
        query.bindString(4, e.getGender());
        query.bindString(5, String.valueOf(e.getPhone()));
        query.bindString(6, e.getLocation());
        query.bindString(7, e.getAvatar());
        query.bindString(8, e.getDepartment());
        query.bindString(9, e.getLanguage());
        query.bindString(10, e.getHiringDate());
        query.bindString(11, e.getBirthDate());

        query.executeInsert();

        this.cerrarBaseDatos(database);
    }


    public void insertarEmploeado(String insert) {
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL(insert);
        this.cerrarBaseDatos(database);
    }
}
