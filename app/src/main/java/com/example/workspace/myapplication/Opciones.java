package com.example.workspace.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.PopupMenu;

import static com.example.workspace.myapplication.Menu.mediaPlayer;

public class Opciones extends Escena {
    Canvas c;
    Typeface faw;
    Utils utils;
    Paint paintTexto = new Paint();

    private int proporcionAlto, proporcionAncho; // Divisores del tamaño de la pantalla, para adaptar los distintos objetos a diferentes resoluciones
    private int anchoPantalla, altoPantalla;

    private Rect rectMusic, rectMusicoff, rectVibracion, getRectVibracionoff, rectVolverMenu; // Rectangulos que nos serviran para detectar pulsaciones en la pantalla del dispositivo
    private float x;
    private Bitmap volverMenu, music, musicoff, vibrate, vibrateoff;

    boolean musicaActiva = true; // Sirve para mostrar un btn de música activada
    boolean volumen = true; // Índice si hay música o no, se utilizará para las preferencias de ajustes
    boolean vibracionActiva = true; // Sirve para mostrar un btn de musica desactivada
    boolean vibracion = true; // Índica si hay vibración o no, se utilizará para las preferencias de ajustes

    String nombreOpciones;

    /**
     * Contructor que inicializa las propiedas de la clase
     *
     * @param context       Contexto de la aplicación
     * @param idEscena      Número que identifica la escena actual
     * @param anchoPantalla Ancho de la pantalla del dispositivo
     * @param altoPantalla  Alto de la pantalla del dispositivo
     */
    public Opciones(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);
        this.anchoPantalla = altoPantalla;
        this.anchoPantalla = anchoPantalla;

        proporcionAncho = anchoPantalla / 18;
        proporcionAlto = altoPantalla / 9;

        fondo = BitmapFactory.decodeResource(context.getResources(), R.drawable.backgroundmountains);
        fondo = Bitmap.createScaledBitmap(fondo, anchoPantalla, altoPantalla, false);

        volverMenu = BitmapFactory.decodeResource(context.getResources(), R.drawable.close2);
        volverMenu = Bitmap.createScaledBitmap(volverMenu, proporcionAncho * 2, proporcionAlto * 2, false);

        music = BitmapFactory.decodeResource(context.getResources(), R.drawable.music);
        music = Bitmap.createScaledBitmap(music, proporcionAncho * 2, proporcionAlto * 2, false);

        musicoff = BitmapFactory.decodeResource(context.getResources(), R.drawable.musicoff);
        musicoff = Bitmap.createScaledBitmap(musicoff, proporcionAncho * 2, proporcionAlto * 2, false);

        vibrate = BitmapFactory.decodeResource(context.getResources(), R.drawable.vibrate);
        vibrate = Bitmap.createScaledBitmap(vibrate, proporcionAncho * 2, proporcionAlto * 2, false);

        vibrateoff = BitmapFactory.decodeResource(context.getResources(), R.drawable.cancel);
        vibrateoff = Bitmap.createScaledBitmap(vibrateoff, proporcionAncho * 2, proporcionAlto * 2, false);

        rectVolverMenu = new Rect(0, 0, proporcionAncho * 2, proporcionAlto * 2);
        rectMusic = new Rect(proporcionAncho * 6, proporcionAlto * 3, proporcionAncho * 8, proporcionAlto * 5);
//        rectMusicoff = new Rect(proporcionAncho * 9, proporcionAlto * 3, proporcionAncho * 11, proporcionAlto * 5);
        rectVibracion = new Rect(proporcionAncho * 6, proporcionAlto * 6, proporcionAncho * 8, proporcionAlto * 8);
//        rectMusicoff = new Rect(proporcionAncho * 9, proporcionAlto * 6, proporcionAncho * 11, proporcionAlto * 8);

        // Fuentes
        faw = Typeface.createFromAsset(context.getAssets(), "fonts/Moonlight.ttf");

        utils = new Utils(context);
        nombreOpciones = context.getString(R.string.opciones);

    }

//    @Override
//    public boolean pulsa(Rect boton, MotionEvent event) {
//        return super.pulsa(boton, event);
//    }

    /**
     * @param event
     * @return
     */
    @Override
    public int onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        int pointerIndex = event.getActionIndex();        //Obtenemos el índice de la acción
        int pointerID = event.getPointerId(pointerIndex); //Obtenemos el Id del pointer asociado a la acción
        int accion = event.getActionMasked();

        switch (accion) {
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:


                if (pulsa(rectVolverMenu, event)) {
                    return 0;
                } else if (pulsa(rectMusic, event)) {
                    if (musicaActiva) {
                        mediaPlayer.start();
                        musicaActiva = false;
                        volumen = true;
                    } else {
                        mediaPlayer.pause();
                        musicaActiva = true;
                        volumen = false;
                    }
                    break;
                } else if (pulsa(rectVibracion, event)) {
                    if (vibracionActiva) {
                        Game.vibrator.cancel();
                        vibracionActiva = false;
                        vibracion = true;
                    } else {
                        vibracionActiva = true;
                        vibracion = false;
                    }
                }


//                if (pulsa(rectVolverMenu, event)) {
//                    return 0;
//                } else if (pulsa(rectMusic, event)) {
//                    if (!volumen) {
//                        mediaPlayer.start();
//                        volumen = true;
//                    } else {
//                        volumen = false;
//                        mediaPlayer.pause();
//                    }
//                    break;
//                }


//                if (pulsa(rectVolverMenu, event)) {
//                    return 0;
//                } else if (pulsa(rectMusic, event)) {
//                    Opciones.audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
//                    mediaPlayer.start();
//                    volumen = true;
//                    break;
//                } else if (pulsa(rectMusicoff, event)) {
//                    volumen = !volumen;
//                    mediaPlayer.stop();
//                    break;
//                }


//                else if (pulsa(rectMusic, event)) {
//                    if (musicaActiva) {
//                        volumen = !volumen;
//                        mediaPlayer.stop();
//                        musicaActiva = false;
//                        break;
//                    } else if (!musicaActiva) {
//                        volumen = true;
//                        mediaPlayer.start();
//                        musicaActiva = true;
//                        break;
//                    }
//                    break;


//                else if (pulsa(rectMusic, event)) {
//                    if (musicaActiva) {
//                    volumen = !volumen;
//                    mediaPlayer.stop();
//                    musicaActiva = false;
//                    break;
//                } else if (!musicaActiva) {
//                    volumen = true;
//                    mediaPlayer.start();
//                    musicaActiva = true;
//                    break;
//                }
//                break;


//        } else if (pulsa(rectVibracion, event)) {
//            vibracion = !vibracion;
//        }
//                else if (pulsa(rectMusicoff, event)) {
//                    mediaPlayer.stop();
//                    break;
//                }
//        break;
        }
        return idEscena;
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
//            c.drawBitmap(music, proporcionAncho * 6, proporcionAlto * 3, null);
//            c.drawBitmap(musicoff, proporcionAncho * 9, proporcionAlto * 3, null);
//            c.drawBitmap(vibracion, proporcionAncho * 6, proporcionAlto * 6, null);
//            c.drawBitmap(musicoff, proporcionAncho * 9, proporcionAlto * 6, null);
//            p.setColor(Color.GREEN);
//            c.drawRect(rectVolverMenu, p);
//            c.drawRect(rectMusic, p);
//            c.drawRect(rectMusicoff, p);

            paintTexto.setColor(Color.YELLOW); //
            paintTexto.setTextSize(100); //
            paintTexto.setTypeface(faw);//
            c.drawText(nombreOpciones + "", proporcionAncho * 4, proporcionAlto * 2, paintTexto); //

            if (volumen) {
                c.drawBitmap(music, proporcionAncho * 6, proporcionAlto * 3, null);
            } else {
                c.drawBitmap(musicoff, proporcionAncho * 6, proporcionAlto * 3, null);
            }
            if (vibracionActiva) {
                c.drawBitmap(vibrate, proporcionAncho * 6, proporcionAlto * 6, null);
            } else {
                c.drawBitmap(vibrateoff, proporcionAncho * 6, proporcionAlto * 6, null);
            }


//            if (musicaActiva) {
//            c.drawBitmap(music, proporcionAncho * 6, proporcionAlto * 3, null);
//            c.drawText("ACTIVA", proporcionAncho * 9, proporcionAncho * 17, proporcionAncho * 5, proporcionAlto * 1, p);
//            }
//            if (!musicaActiva) {
//                c.drawBitmap(musicoff, proporcionAncho * 6, proporcionAlto * 3, null);
//                c.drawText("DESACTIVA", proporcionAncho * 9, proporcionAncho * 17, proporcionAncho * 5, proporcionAlto * 1, p);
//            }
        } catch (NullPointerException e) {
            Log.d("Error", "Dibujado canvas Opciones");
        }
    }

    private void guardarPreferencias() {
        SharedPreferences preferences = context.getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        if (musicaActiva) {
            editor.putBoolean("musica", true);
        } else {
            editor.putBoolean("musica", false);
        }
        if (vibracionActiva) {
            editor.putBoolean("vibracion", true);
        } else {
            editor.putBoolean("vibracion", false);
        }

        editor.commit();
    }
}
