package com.example.workspace.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

//import static com.example.workspace.myapplication.Utils.crearTabla;

public class BaseDatos extends android.database.sqlite.SQLiteOpenHelper {

    public BaseDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utils.crearTabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS bdPuntuaciones");
        db.execSQL(Utils.crearTabla);
    }
}
