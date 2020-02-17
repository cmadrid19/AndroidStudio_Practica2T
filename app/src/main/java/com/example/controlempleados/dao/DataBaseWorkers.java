package com.example.controlempleados.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import com.example.controlempleados.bean.Empleado;
import com.example.controlempleados.bean.User;

import java.util.ArrayList;
import java.util.List;

public class DataBaseWorkers extends SQLiteOpenHelper {


    private static String DB_NAME = "prueba.db";
    private static String DB_PATH = "";
    public static final int DATABASE_VERSION = 1;


    public DataBaseWorkers(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String WORKERS_DB_NAME = "WORKERS";
        final String WORKER_ID = "id";
        final String WORKER_NAME = "name";
        final String WORKER_SURNAME = "sur_name";
        final String WORKER_EMAIL = "email";
        final String WORKER_GENDER = "gender";
        final String WORKER_PHONE = "phone";
        final String WORKER_LOCATION = "location";
        final String WORKER_AVATAR = "avatar";
        final String WORKER_DEPARTMENT = "department";
        final String WORKER_LANGUAGE = "language";
        final String WORKER_HIRING_DATE = "hiring_date";
        final String WORKER_BIRTH_DATE = "birth_date";
        final String WORKER_NATIONALITY = "nationality";

        final String SQL_TABLE_WORKERS = " CREATE TABLE " + WORKERS_DB_NAME
                + " ( "
                + WORKER_ID + " INTEGER PRIMARY KEY, "
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
                + WORKER_BIRTH_DATE + " TEXT,"
                + WORKER_NATIONALITY + " TEXT"
                + ")";

        db.execSQL(SQL_TABLE_WORKERS);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Borrar tabla si ya existe

    }

    private void cerrarBaseDatos(SQLiteDatabase database) {
        database.close();
    }

    public List<Empleado> getEmpleados() {
        List<Empleado> listaEmpleados = null;
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
        String nationality = "";

        String consulta = "SELECT * FROM WORKERS_DB_NAME";

        SQLiteDatabase basedatos = this.getReadableDatabase();
        Cursor cursor = basedatos.rawQuery(consulta, null);


        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            listaEmpleados = new ArrayList<Empleado>(cursor.getCount());
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
                nationality = cursor.getString(12);

                empleado = new Empleado(id, name, surname, email, gender, phone, location, avatar,
                        department, language, hiringDate, birthDate, nationality);

                listaEmpleados.add(empleado);

            } while (cursor.moveToNext());
            cursor.close();
        }
        this.cerrarBaseDatos(basedatos);
        return listaEmpleados;
    }

    public void SetTablaEmpleados() {
        String ListaQueries = "";

        List<Empleado> listaEmpleados = null;
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
        String nationality = "";

        String consulta = "SELECT * FROM WORKERS_DB_NAME";

        SQLiteDatabase basedatos = this.getReadableDatabase();
        Cursor cursor = basedatos.rawQuery(consulta, null);


        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            listaEmpleados = new ArrayList<Empleado>(cursor.getCount());
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
                nationality = cursor.getString(12);

                empleado = new Empleado(id, name, surname, email, gender, phone, location, avatar,
                        department, language, hiringDate, birthDate, nationality);

                listaEmpleados.add(empleado);

            } while (cursor.moveToNext());
            cursor.close();
        }
        this.cerrarBaseDatos(basedatos);
    }

    private void

}
