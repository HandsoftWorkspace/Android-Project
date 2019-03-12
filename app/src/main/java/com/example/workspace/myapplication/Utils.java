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
    /**
     * Contexto de la aplicacion
     */
    Context context;

    /**
     * Contructor de la clase Utils
     *
     * @param context Contexto de la applicacion
     */
    public Utils(Context context) {
        this.context = context;
    }

    /**
     * Metodo que recibe una cadena que debera ser un archivo, la asociara a un bitmap
     *
     * @param fichero Cadena de texto que sera el nombre del archivo, ubicado en la raiz de la carpeta del proyecto, 'assets'
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
     * Método que guarda las preferencias de ajustes del juego, vibracion y sonido
     */
    public void guardarPreferencias() {

    }

    /**
     * Selecciona un fondo para la escena
     *
     * @param anchoPantalla Ancho pantalla del dispositivo
     * @param altoPantalla  Alto pantalla del dispositivo
     * @param dia           Booleana que indica si el sensor de luz está activado o no
     * @return Devuelve el bitmap dependiendo de si la booleana es true o false
     */
    public Bitmap setFondo(int anchoPantalla, int altoPantalla, boolean dia) {
        Bitmap fondo;
        if (dia) {
            fondo = getBitmapFromAssets("varios/bgmenudia.png");
        } else {
            fondo = getBitmapFromAssets("varios/bgmenunoche.png");
        }
        fondo = Bitmap.createScaledBitmap(fondo, anchoPantalla, altoPantalla, false);
        return fondo;
    }

    public Bitmap setNubes(int anchoPantalla, int altoPantalla) {
        Bitmap nubes;
        nubes = getBitmapFromAssets("varios/nubes.png");
        nubes = Bitmap.createScaledBitmap(nubes, anchoPantalla, altoPantalla, false);
        return nubes;
    }

}
