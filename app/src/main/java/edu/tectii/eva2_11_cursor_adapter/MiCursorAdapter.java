package edu.tectii.eva2_11_cursor_adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;


public class MiCursorAdapter extends CursorAdapter {


    public MiCursorAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.layout_datos, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nombre, apellido;
        nombre = view.findViewById(R.id.nombre);
        apellido = view.findViewById(R.id.apellido);
        nombre.setText(cursor.getString(cursor.getColumnIndex("nombre")));
        apellido.setText(cursor.getString(cursor.getColumnIndex("apellido")));
    }
}
