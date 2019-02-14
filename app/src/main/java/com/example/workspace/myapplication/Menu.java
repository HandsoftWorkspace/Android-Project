package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.workspace.myapplication.MainActivity;
import com.example.workspace.myapplication.R;

public class Menu extends Escena {

    Rect ayuda, opciones, juego, game, logros, records, creditos;

    int alto, ancho;
    Paint brocha = new Paint();

    public MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    private SoundPool efectos;
    private int sonidoWoosh, sonidoPajaro, sonidoExplosion;
    private int maxSonidosSimul = 10;
    private SoundPool soundPool;
    Fondo parallax;

    // SEGUNDA VERSION
    String strJugar = context.getText(R.string.jugar).toString();
    String strOpciones = context.getText(R.string.opciones).toString();
    String strLogros = context.getText(R.string.logros).toString();
    String strAyuda = context.getText(R.string.ayuda).toString();
    String strCreditos = context.getText(R.string.creditos).toString();

    public Menu(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);

        //        PRIMERA VERSION MENÚ
        fondo = BitmapFactory.decodeResource(context.getResources(), R.drawable.fondo);
        fondo = Bitmap.createScaledBitmap(fondo, anchoPantalla, altoPantalla, false);

        // Proporciones pantalla
        alto = altoPantalla / 5;
        ancho = anchoPantalla / 7;

        // Left, Top,   Right, Botton
        juego = new Rect(ancho * 2, alto * 1, ancho * 5, alto * 2);
        opciones = new Rect(ancho * 1, alto * 3, ancho * 3, alto * 4);
        logros = new Rect(ancho * 4, alto * 3, ancho * 6, alto * 4);
        creditos = new Rect(0, 0, ancho * 1, alto * 1);
        ayuda = new Rect(ancho * 6, 0, ancho * 7, alto * 1);

        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mediaPlayer = MediaPlayer.create(context, R.raw.acoustic);
        int v = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        mediaPlayer.setVolume(v / 2, v / 2);
        mediaPlayer.start();

    }

    // Actualizamos la física de los elementos en pantalla
    public void actualizarFisica() {

    }

    // Rutina de dibujo en el lienzo. Se le llamará desde el hilo juego

    /**
     *
     * @param c
     */
    public void dibujar(Canvas c) {
        try {
            c.drawBitmap(fondo, 0, 0, brocha);
            brocha.setAntiAlias(true);
            brocha.isAntiAlias();

            brocha.setColor(Color.DKGRAY);
            c.drawRect(juego, brocha);

            brocha.setColor(Color.DKGRAY);
            c.drawRect(opciones, brocha);

            brocha.setColor(Color.DKGRAY);
            c.drawRect(logros, brocha);

            brocha.setColor(Color.DKGRAY);
            c.drawRect(ayuda, brocha);

            brocha.setColor(Color.DKGRAY);
            c.drawRect(creditos, brocha);

            pTexto.setColor(Color.WHITE);
            pTexto.setTextSize(200);
//            c.drawText(strJugar, juego.centerX(), juego.centerY(), pTexto);

            pTexto.setColor(Color.WHITE);
            pTexto.setTextSize(50);
            c.drawText(strOpciones, opciones.centerX(), opciones.centerY(), pTexto);

            pTexto.setColor(Color.WHITE);
            pTexto.setTextSize(50);
            c.drawText(strLogros, logros.centerX(), logros.centerY(), pTexto);

            pTexto.setColor(Color.WHITE);
            pTexto.setTextSize(50);
            c.drawText(strCreditos, creditos.centerX(), creditos.centerY(), pTexto);

            pTexto.setColor(Color.WHITE);
            pTexto.setTextSize(50);
            c.drawText(strAyuda, ayuda.centerX(), ayuda.centerY(), pTexto);

        } catch (NullPointerException e) {
            Log.i("Error al dibujar", e.getLocalizedMessage());
        }
    }

    @Override
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
                if (pulsa(juego, event)) {
                    mediaPlayer.stop();
                    return 1;
                } else if (pulsa(opciones, event)) {
                    return 2;
                } else if (pulsa(records, event)) {
                    return 3;
                } else if (pulsa(ayuda, event)) {
                    return 4;
                } else if (pulsa(creditos, event)) {
                    return 5;
                }
                break;

            case MotionEvent.ACTION_MOVE: // Se mueve alguno de los dedos

                break;
            default:
                Log.i("Otra acción", "Acción no definida: " + accion);
        }

        return idEscena;
    }
}
