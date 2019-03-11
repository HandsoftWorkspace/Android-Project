package com.example.workspace.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;

public class Escena {

    Bitmap bitmapFondo, volverMenu; // fondo y botones
    Fondo fondoNubes; // objeto 'fondo' que hará efecto de scroll
    Boolean esDeDia; // booleana que índice si es día o de noche, para escoger un fondo u otro
    Rect rectVolverMenu; // sirve para detectar la pulsaciones para volver al menú
    Context context; // contexto de la aplicación
    int idEscena; // número que identifica a las distintas escenas escenas
    static int anchoPantalla, altoPantalla; // tamaños de pantalla del dispositivo
    public Paint p, pTexto, paintTexto; // pinceles para gestionar los distintos ajustes a la hora de pintar, recs, textos, bitmaps...
    Typeface faw; // tipología de fuente para los textos
    Utils utils; // objeto de la clase 'utils'

    /**
     * Método contructor que inicializa las propiedades de la clase 'escena'
     *
     * @param context
     * @param idEscena
     * @param anchoPantalla
     * @param altoPantalla
     */
    public Escena(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        this.context = context;
        this.idEscena = idEscena;
        this.anchoPantalla = anchoPantalla;
        this.altoPantalla = altoPantalla;
        utils = new Utils(context);
        faw = Typeface.createFromAsset(context.getAssets(), "fonts/Moonlight.ttf");
        esDeDia = true;
        p = new Paint();
        pTexto = new Paint();
        paintTexto = new Paint();
    }

    /**
     * Actualizamos la física de los elementos en pantalla
     */
    public void actualizarFisica() {
//        fondoNubes.mover();
    }

    /**
     * Rutina de dibujo en el lienzo. Se le llamará desde el hilo juego
     *
     * @param c canvas de la aplicación
     */
    public void dibujar(Canvas c) {
        try {
            //if(idEscena!=0 &&)
        } catch (Exception e) {
            Log.i("Error al dibujar", e.getLocalizedMessage());
        }
    }

    public boolean pulsa(Rect boton, MotionEvent event) {
        if (boton.contains((int) event.getX(), (int) event.getY())) {
            return true;
        }
        return false;
    }

    /**
     * Controla y gestiona las pulsaciones y gestos en la pantalla
     *
     * @param event Tipo de evento táctil que sucede
     * @return Devuelve un entero que índice el número de escena
     */
    public int onTouchEvent(MotionEvent event) {
        //synchronized (surfaceHolder) {
        int pointerIndex = event.getActionIndex();        //Obtenemos el índice de la acción
        int pointerID = event.getPointerId(pointerIndex); //Obtenemos el Id del pointer asociado a la acción
        int accion = event.getActionMasked();             //Obtenemos el tipo de pulsación
        switch (accion) {
            case MotionEvent.ACTION_DOWN:           // Primer dedo toca
            case MotionEvent.ACTION_POINTER_DOWN:  // Segundo y siguientes tocan
                break;

            case MotionEvent.ACTION_UP:                     // Al levantar el último dedo
            case MotionEvent.ACTION_POINTER_UP:  // Al levantar un dedo que no es el último
                break;

            case MotionEvent.ACTION_MOVE: // Se mueve alguno de los dedos

                break;
            default:
                Log.i("Otra acción", "Acción no definida: " + accion);
        }
        return idEscena;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getIdEscena() {
        return idEscena;
    }

    public void setIdEscena(int idEscena) {
        this.idEscena = idEscena;
    }

    public int getAnchoPantalla() {
        return anchoPantalla;
    }

    public void setAnchoPantalla(int anchoPantalla) {
        this.anchoPantalla = anchoPantalla;
    }

    public int getAltoPantalla() {
        return altoPantalla;
    }

    public void setAltoPantalla(int altoPantalla) {
        this.altoPantalla = altoPantalla;
    }

    public Bitmap getFondo() {
        return bitmapFondo;
    }

    public void setFondo(Bitmap fondo) {
        this.bitmapFondo = fondo;
    }
}
