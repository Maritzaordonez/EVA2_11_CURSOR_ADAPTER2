package edu.tectii.eva2_11_cursor_adapter;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.io.File;

public class Principal extends AppCompatActivity {

    ListView listaDatos;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        listaDatos = findViewById(R.id.listaDatos);
        //CORRESPONDIENTE A LA BASE DE DATOS
        String sRutaSD = Environment.getExternalStorageDirectory().getPath();//Ruta directa de la SDCard
        String sDir = "eva2_11_directory";
        File fRuta = new File(sRutaSD + "/" + sDir + "/");
        if (!fRuta.exists()) {//¿Existe la ruta? Si la ruta no existe...
            fRuta.mkdir();
        }
        String rutaBD = sRutaSD + "/" + sDir + "/" + "mi_base_datos";
        sqLiteDatabase = SQLiteDatabase.openDatabase(rutaBD, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        //CREAR LA TABLA
        try {
            sqLiteDatabase.execSQL("create table datos("+
                    "id integer primary key autoincrement, " +
                    "nombre text, "+
                    "apellido text);"
            );
        } catch (SQLiteException x) {x.printStackTrace();}
        //AGREGAR DATOS
        sqLiteDatabase.execSQL("insert into datos(nombre, apellido) values ('Maritza', 'Ordoñez')" );
        sqLiteDatabase.execSQL("insert into datos(nombre, apellido) values ('Fatima', 'Ordoñez')" );
        //CREAR CURSOR
        Cursor c1 = sqLiteDatabase.rawQuery("select id as _id, nombre, apellido from datos;", null);
        MiCursorAdapter mcaDatos = new MiCursorAdapter(this, c1);
        listaDatos.setAdapter(mcaDatos);


    }
}

