package com.example.workspace.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.io.IOException;
import java.io.InputStream;

public class Utils {

    Context context;

    // Gestión bases de datos
    public final static String crearTabla = "CREATE TABLE bdPuntuaciones (puntos INTEGER)";
public final String tablaPuntuaciones="bdPuntuaciones";

    /**
     * Contructor de la clase Utils
     * @param context Contexto de la applicación
     */
    public Utils(Context context) {
        this.context = context;
    }

    /**
     * Método que recibe una cadena que deberá ser un archivo, la asociará a un bitmap
     * @param fichero Cadena de texto que será el nombre del archivo, ubicado en la carpeta del proyecto, 'assets'
     * @return Devuelve un bitmap
     */
    public Bitmap getBitmapFromAssets(String fichero) {
        try {
            InputStream is = context.getAssets().open(fichero);
            return BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Método que guarda las preferencias de ajustes del juego, vibración y sonido
     */
    public void guardarPreferencias() {

    }

    /**
     * Método que carga las preferencias de ajustes del juego, vibración y sonido
     * @return
     */
    public boolean[] cargarPreferencias() {
        boolean prefs[] = new boolean[2];
        SharedPreferences sharedPreferences = context.getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        prefs[0] = sharedPreferences.getBoolean("musica", true);
        prefs[1] = sharedPreferences.getBoolean("vibracion", true);
        return prefs;
    }

    public Bitmap[] tiempo(int tiempo) {
        switch (tiempo) {
//            case 0:
//                return Game.listaNumeros[0];
//            case 1:
//                return Game.listaNumeros[1];
//            case 2:
//                return Game.listaNumeros[2];
//            case 3:
//                return Game.listaNumeros[3];
//            case 4:
//                return Game.listaNumeros[4];
//            case 5:
//                return Game.listaNumeros[5];
//            case 6:
//                return Game.listaNumeros[6];
//            case 7:
//                return Game.listaNumeros[7];
//            case 8:
//                return Game.listaNumeros[8];
//            case 9:
//                return Game.listaNumeros[9];
//            case 10:
//                return Game.listaNumeros[10];
//            case 11:
//                return Game.listaNumeros[0];
//            case 12:
//                return Game.listaNumeros[0];
//            case 13:
//                return Game.listaNumeros[0];
//            case 14:
//                return Game.listaNumeros[0];
//            case 15:
//                return Game.listaNumeros[0];
//            case 16:
//                return Game.listaNumeros[0];
//            case 17:
//                return Game.listaNumeros[0];
//            case 18:
//                return Game.listaNumeros[0];

        }
        return null;
    }

}
