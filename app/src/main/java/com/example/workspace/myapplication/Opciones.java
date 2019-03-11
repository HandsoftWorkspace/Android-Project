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
import android.preference.PreferenceManager;
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

    private Rect rectMusic, rectMusicoff, rectVibracion, getRectVibracionoff; // Rectangulos que nos serviran para detectar pulsaciones en la pantalla del dispositivo
    private float x;
    private Bitmap music, musicoff, vibrate, vibrateoff;

    boolean musicaActiva = true; // Sirve para mostrar un btn de música activada
    public static boolean volumen = true; // Índice si hay música o no, se utilizará para las preferencias de ajustes
    boolean vibracionActiva = true; // Sirve para mostrar un btn de musica desactivada
    public static boolean vibracion = true; // Índica si hay vibración o no, se utilizará para las preferencias de ajustes

    String nombreOpciones, strMusica, strVibracion;

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
        this.altoPantalla = altoPantalla;
        this.anchoPantalla = anchoPantalla;

        proporcionAncho = anchoPantalla / 18;
        proporcionAlto = altoPantalla / 9;

        utils = new Utils(context);

        bitmapFondo = utils.setFondo(anchoPantalla, altoPantalla, esDeDia);
        fondoNubes = new Fondo(utils.setNubes(anchoPantalla, altoPantalla), anchoPantalla, 6);

        volverMenu = BitmapFactory.decodeResource(context.getResources(), R.drawable.close2);
        volverMenu = Bitmap.createScaledBitmap(volverMenu, proporcionAncho * 2, proporcionAlto * 2, false);

        music = BitmapFactory.decodeResource(context.getResources(), R.drawable.music);
        music = Bitmap.createScaledBitmap(music, proporcionAncho * 2, proporcionAlto * 2, false);

        musicoff = BitmapFactory.decodeResource(context.getResources(), R.drawable.musicoff);
        musicoff = Bitmap.createScaledBitmap(musicoff, proporcionAncho * 2, proporcionAlto * 2, false);

        vibrate = BitmapFactory.decodeResource(context.getResources(), R.drawable.vibracion);
        vibrate = Bitmap.createScaledBitmap(vibrate, proporcionAncho * 2, proporcionAlto * 2, false);

        vibrateoff = BitmapFactory.decodeResource(context.getResources(), R.drawable.sinvibracion);
        vibrateoff = Bitmap.createScaledBitmap(vibrateoff, proporcionAncho * 2, proporcionAlto * 2, false);

        rectVolverMenu = new Rect(0, 0, proporcionAncho * 2, proporcionAlto * 2);
        rectMusic = new Rect(proporcionAncho * 8, proporcionAlto * 3, proporcionAncho * 10, proporcionAlto * 5);
//        rectMusicoff = new Rect(proporcionAncho * 9, proporcionAlto * 3, proporcionAncho * 11, proporcionAlto * 5);
        rectVibracion = new Rect(proporcionAncho * 8, proporcionAlto * 6, proporcionAncho * 12, proporcionAlto * 8);
//        rectMusicoff = new Rect(proporcionAncho * 9, proporcionAlto * 6, proporcionAncho * 11, proporcionAlto * 8);


        // Fuentes
        faw = Typeface.createFromAsset(context.getAssets(), "fonts/Moonlight.ttf");

        nombreOpciones = context.getString(R.string.opciones);
        strMusica = context.getString(R.string.musica);
        strVibracion = context.getString(R.string.vibracion);

    }

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
                        vibracionActiva = false;
                        vibracion = true;
                    } else {
                        vibracionActiva = true;
                        vibracion = false;
                    }
                }
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
            c.drawBitmap(bitmapFondo, 0, 0, null);
            fondoNubes.dibujar(c);
            c.drawBitmap(volverMenu, 0, proporcionAlto * 0, null);

            paintTexto.setColor(Color.YELLOW); //
            paintTexto.setTextSize(50); //
            paintTexto.setTypeface(faw);//
//            c.drawText(nombreOpciones + "", proporcionAncho * 4, proporcionAlto * 2, paintTexto); //
            c.drawText(nombreOpciones, proporcionAncho * 2 + proporcionAncho / 3, proporcionAlto, paintTexto);
            c.drawText(strMusica, proporcionAncho * 3 + proporcionAncho / 2, proporcionAlto * 4, paintTexto);
            c.drawText(strVibracion, proporcionAncho * 3 + proporcionAncho / 2, proporcionAlto * 7, paintTexto);
            if (volumen) {
                c.drawBitmap(music, proporcionAncho * 8, proporcionAlto * 3, null);
            } else {
                c.drawBitmap(musicoff, proporcionAncho * 8, proporcionAlto * 3, null);
            }
            if (vibracionActiva) {
                c.drawBitmap(vibrate, proporcionAncho * 8, proporcionAlto * 6, null);
            } else {
                c.drawBitmap(vibrateoff, proporcionAncho * 8, proporcionAlto * 6, null);
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

    /**
     * Actualizamos la física de los elementos en pantalla
     */
    public void actualizarFisica() {
        bitmapFondo = utils.setFondo(anchoPantalla, altoPantalla, esDeDia);
        fondoNubes.mover();
    }

    public void guardarPreferencias() {
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences preferences = context.getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

//        if (volumen) {
////            editor.putBoolean("musica", true);
//            editor.putBoolean("musica", volumen);
//        } else {
////            editor.putBoolean("musica", false);
//            editor.putBoolean("musica", volumen);
//        }
//        if (vibracion) {
//            editor.putBoolean("vibracion", true);
//        } else {
//            editor.putBoolean("vibracion", false);
//        }
        editor.putBoolean("musica", volumen);
        editor.putBoolean("vibracion", vibracion);
        editor.commit();
        editor.apply(); // Se ejecuta en el OnPause, tanto al ejecutarse por primera vez como cúando se pausa
    }

    /**
     * Método que carga las preferencias de ajustes del juego, vibración y sonido
     *
     * @return
     */
    public void cargarPreferencias() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        musicaActiva = sharedPreferences.getBoolean("musica", true);
        vibracionActiva = sharedPreferences.getBoolean("vibracion", true);
    }
}
