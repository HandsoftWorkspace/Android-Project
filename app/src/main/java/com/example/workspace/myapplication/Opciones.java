package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

public class Opciones extends Escena {
    Canvas c;

    private int proporcionAlto, proporcionAncho; // Divisores del tamaño de la pantalla, para adaptar los distintos objetos a diferentes resoluciones

    private Rect rectMusic, rectMusicoff, rectVibracion, rectVolverMenu; // Rectangulos que nos serviran para detectar pulsaciones en la pantalla del dispositivo
    private float x;
    private Bitmap volverMenu, music, musicoff;

    /**
     * @param context       Contexto de la aplicación
     * @param idEscena      Número que identifica la escena actual
     * @param anchoPantalla Ancho de la pantalla del dispositivo
     * @param altoPantalla  Alto de la pantalla del dispositivo
     */
    public Opciones(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);
        proporcionAncho = anchoPantalla / 18;
        proporcionAlto = altoPantalla / 9;

        fondo = BitmapFactory.decodeResource(context.getResources(), R.drawable.fondo);
        fondo = Bitmap.createScaledBitmap(fondo, anchoPantalla, altoPantalla, false);

        volverMenu = BitmapFactory.decodeResource(context.getResources(), R.drawable.close2);
        volverMenu = Bitmap.createScaledBitmap(volverMenu, proporcionAncho * 2, proporcionAlto * 2, false);

        music = BitmapFactory.decodeResource(context.getResources(), R.drawable.music);
        music = Bitmap.createScaledBitmap(music, proporcionAncho * 2, proporcionAlto * 2, false);

        musicoff = BitmapFactory.decodeResource(context.getResources(), R.drawable.musicoff);
        musicoff = Bitmap.createScaledBitmap(musicoff, proporcionAncho * 2, proporcionAlto * 2, false);

        rectVolverMenu = new Rect(0, 0, proporcionAncho * 2, proporcionAlto * 2);
        rectMusic = new Rect(proporcionAncho * 6, proporcionAlto * 3, proporcionAncho * 8, proporcionAlto * 5);
        rectMusicoff = new Rect(proporcionAncho * 10, proporcionAlto * 3, proporcionAncho * 12, proporcionAlto * 5);
    }

    @Override
    public boolean pulsa(Rect boton, MotionEvent event) {
        return super.pulsa(boton, event);
    }

    /**
     * @param event
     * @return
     */
    @Override
    public int onTouchEvent(MotionEvent event) {
        int pointerIndex = event.getActionIndex();        //Obtenemos el índice de la acción
        int pointerID = event.getPointerId(pointerIndex); //Obtenemos el Id del pointer asociado a la acción
        int accion = event.getActionMasked();

        switch (accion) {
            case MotionEvent.ACTION_DOWN:           // Primer dedo toca
                break;
            case MotionEvent.ACTION_POINTER_DOWN:  // Segundo y siguientes tocan
                break;
            case MotionEvent.ACTION_UP:                     // Al levantar el últ
                break;
            case MotionEvent.ACTION_POINTER_UP:  // Al levantar un dedo que no es
                if (pulsa(rectVolverMenu, event)) {
                    Juego juego = new Juego(context);
                    juego.setKeepScreenOn(true);
                    return 0;
                } else if (pulsa(rectMusic, event)) {
//                    volumen = !volumen;
                    return 95;
                } else if (pulsa(rectVibracion, event)) {
//                    vibracion = !vibracion;
                } else if (pulsa(rectVolverMenu, event)) {
                    return -1;
                }
                break;
            case MotionEvent.ACTION_MOVE: // Se mueve alguno de los dedos
                break;
        }
        return super.onTouchEvent(event);

    }

    /**
     * @param c
     */
    @Override
    public void dibujar(Canvas c) {
        try {
            super.dibujar(c);
            c.drawBitmap(fondo, 0, 0, null);

            c.drawBitmap(volverMenu, 0, proporcionAlto * 0, null);
            c.drawBitmap(music, proporcionAncho * 6, proporcionAlto * 3, null);
            c.drawBitmap(musicoff, proporcionAncho * 10, proporcionAlto * 3, null);
            p.setColor(Color.GREEN);
            c.drawRect(rectVolverMenu, p);
            p.setColor(Color.GREEN);
            c.drawRect(rectMusic, p);
            p.setColor(Color.GREEN);
            c.drawRect(rectMusicoff, p);
        } catch (Exception e) {
        }
    }

}
