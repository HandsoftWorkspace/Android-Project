package com.example.workspace.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;

import com.example.workspace.myapplication.Juego;


public class BaseDatos extends android.database.sqlite.SQLiteOpenHelper {
    // Querys
    /**
     * String para insertar puntuaciones en la tablas de datos
     */
    private final String crearTabla = "CREATE TABLE puntuaciones (id INTEGER PRIMARY KEY AUTOINCREMENT,puntos INTEGER)";
    /**
     * String con el nombre de la tabla
     */
    private final String tablaPuntuaciones = "puntuaciones";

    public BaseDatos(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(Utils.crearTabla);
        db.execSQL(this.crearTabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS puntuaciones");
//        db.execSQL(Utils.crearTabla);
        db.execSQL(this.crearTabla);
    }
}
