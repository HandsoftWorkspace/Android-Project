package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;

public class Menu extends Escena {

    Rect rectJuego, rectOpciones, rectLogros, rectLeader, rectAyuda, rectCreditos;

    Bitmap juego, opciones, creditos, leader, ayuda;

    Bitmap fondo0, fondo1, fondo2, fondo3, fondo4, fondo5, down, up, downGrande, upGrande;

    private ArrayList<Fondo> parallax; // Array de objetos 'fondo' para realizar el parallax


    private int proporcionAlto, proporcionAncho;
    private Paint brocha = new Paint();

    public static MediaPlayer mediaPlayer;
    private AudioManager audioManager;
    private SoundPool efectos;
    private int sonidoWoosh, sonidoPajaro, sonidoExplosion;
    private int maxSonidosSimul = 10;
    private SoundPool soundPool;

    public int volumen; // Volumen del menú

    // Constructor de la escena MENÚ

    /**
     * @param context
     * @param idEscena
     * @param anchoPantalla
     * @param altoPantalla
     */
    public Menu(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);

        // Fondo menú principal
//        fondo = BitmapFactory.decodeResource(context.getResources(), R.drawable.fondo);
//        fondo = Bitmap.createScaledBitmap(fondo, anchoPantalla, altoPantalla, false);
        fondo0 = BitmapFactory.decodeResource(context.getResources(), R.drawable.sky0);
        fondo0 = Bitmap.createScaledBitmap(fondo0, anchoPantalla, altoPantalla, false);
        fondo1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.sky1);
        fondo1 = Bitmap.createScaledBitmap(fondo1, anchoPantalla, altoPantalla, false);
        fondo2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.sky3);
        fondo2 = Bitmap.createScaledBitmap(fondo2, anchoPantalla, altoPantalla, false);
        fondo3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.sky4);
        fondo3 = Bitmap.createScaledBitmap(fondo3, anchoPantalla, altoPantalla, false);
        fondo4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.sky5);
        fondo4 = Bitmap.createScaledBitmap(fondo4, anchoPantalla, altoPantalla, false);
        // Se añade la imagen a cada posición de la colección
//        parallax.add(new Fondo(fondo0, 0, 0));
//        parallax.add(new Fondo(fondo1, 0, 0));
//        parallax.add(new Fondo(fondo2, 0, 0));
//        parallax.add(new Fondo(fondo3, 0, 0));
//        parallax.add(new Fondo(fondo4, 0, 0));

        // Proporciones pantalla, se divide el alto y ancho de la pantalla del dispositivo, para conseguir asi nuestras proporciones
        // para que se adapten asi a distintos dispositivos
        proporcionAncho = anchoPantalla / 18;
        proporcionAlto = altoPantalla / 9;

        // Se dibujan unos rectangulos, que será donde posiciones nuestros iconos de menú,
        // para asi poder detectar la pulsación de cada uno de ellos
        rectJuego = new Rect(proporcionAncho * 8, proporcionAlto * 3, proporcionAncho * 10, proporcionAlto * 5);
        rectOpciones = new Rect(proporcionAncho * 3, proporcionAlto * 3, proporcionAncho * 5, proporcionAlto * 5);
        rectLogros = new Rect(proporcionAncho * 13, proporcionAlto * 3, proporcionAncho * 15, proporcionAlto * 5);
        rectCreditos = new Rect(0, proporcionAlto * 6, proporcionAncho * 2, proporcionAlto * 8);
        rectAyuda = new Rect(proporcionAncho * 16, 6, proporcionAncho * 18, proporcionAlto * 8);

        // Bitmaps de iconos del menú, se asocia la imagen PNG y se le da el tamaño, en este caso usaremos proporciones
        // Iconos
        juego = BitmapFactory.decodeResource(context.getResources(), R.drawable.play);
        juego = Bitmap.createScaledBitmap(juego, proporcionAncho * 2, proporcionAlto * 2, false);

        opciones = BitmapFactory.decodeResource(context.getResources(), R.drawable.options);
        opciones = Bitmap.createScaledBitmap(opciones, proporcionAncho * 2, proporcionAlto * 2, false);

        ayuda = BitmapFactory.decodeResource(context.getResources(), R.drawable.help2);
        ayuda = Bitmap.createScaledBitmap(ayuda, proporcionAncho * 2, proporcionAlto * 2, false);

        leader = BitmapFactory.decodeResource(context.getResources(), R.drawable.logros);
        leader = Bitmap.createScaledBitmap(leader, proporcionAncho * 2, proporcionAlto * 2, false);

        creditos = BitmapFactory.decodeResource(context.getResources(), R.drawable.info);
        creditos = Bitmap.createScaledBitmap(creditos, proporcionAncho * 2, proporcionAlto * 2, false);

        down = BitmapFactory.decodeResource(context.getResources(), R.drawable.down);
        down = Bitmap.createScaledBitmap(down, proporcionAncho * 6, proporcionAlto * 1, false);

        up = BitmapFactory.decodeResource(context.getResources(), R.drawable.up);
        up = Bitmap.createScaledBitmap(up, proporcionAncho * 6, proporcionAlto * 1, false);

        downGrande = BitmapFactory.decodeResource(context.getResources(), R.drawable.down);
        downGrande = Bitmap.createScaledBitmap(downGrande, proporcionAncho * 8, proporcionAlto * 1, false);

        upGrande = BitmapFactory.decodeResource(context.getResources(), R.drawable.up);
        upGrande = Bitmap.createScaledBitmap(upGrande, proporcionAncho * 8, proporcionAlto * 1, false);

        // Controles de audio y musica
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mediaPlayer = MediaPlayer.create(context, R.raw.acoustic);
        volumen = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        mediaPlayer.setVolume(volumen / 2, volumen / 2);
        mediaPlayer.start(); // Se arranca la secuencia musical
        mediaPlayer.seekTo(7000); // La secuencia empieza en el segundo 7, por lo tanto se adelanta para no causar una espera
        mediaPlayer.setLooping(true); // Se fuerza a que se repita la secuencia musical

        brocha.setColor(Color.TRANSPARENT);

    }

    /**
     * Actualizamos la física de los elementos en pantalla
     */
    public void actualizarFisica() {
//        parallax.get(0).mover(4);
//        parallax.get(1).mover(6);
//        parallax.get(2).mover(8);
//        parallax.get(3).mover(10);
//        parallax.get(4).mover(12);
    }

    /**
     * Rutina de dibujo en el lienzo. Se le llamará desde el hilo juego
     *
     * @param c
     */
    public void dibujar(Canvas c) {
        try {
//            for (Fondo f : parallax) {
//                f.dibujar(c);
//            }

            //c.drawBitmap(fondo, 0, 0, brocha);
            c.drawBitmap(fondo0, 0, 0, null);
            c.drawBitmap(fondo1, 0, 0, null);
            c.drawBitmap(fondo2, 0, 0, null);
            c.drawBitmap(fondo3, 0, 0, null);
            c.drawBitmap(fondo4, 0, 0, null);

            c.drawBitmap(down, proporcionAncho * 6, proporcionAlto * 1, null);
            c.drawBitmap(up, proporcionAncho * 6, proporcionAlto * 2, null);
            c.drawBitmap(downGrande, proporcionAncho * 5, proporcionAlto * 5, null);
            c.drawBitmap(upGrande, proporcionAncho * 5, proporcionAlto * 6, null);

            c.drawRect(rectJuego, brocha);
            c.drawBitmap(juego, proporcionAncho * 8, proporcionAlto * 3, null);

            c.drawRect(rectOpciones, brocha);
            c.drawBitmap(opciones, proporcionAncho * 3, proporcionAlto * 3, null);

            c.drawRect(rectLogros, brocha);
            c.drawBitmap(leader, proporcionAncho * 13, proporcionAlto * 3, null);

            c.drawRect(rectAyuda, brocha);
            c.drawBitmap(ayuda, proporcionAncho * 16, proporcionAlto * 6, null);

            c.drawRect(rectCreditos, brocha);
            c.drawBitmap(creditos, proporcionAncho * 0, proporcionAlto * 6, null);

        } catch (NullPointerException e) {
            Log.i("Error al dibujar", e.getLocalizedMessage());
        }
    }

    /**
     * Gestiona el tipo de pulsación mediante la detección de un evento, con el cual devolvera un idEscena
     *
     * @param event conseguimos el tipo de evento que sucede
     * @return
     */
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
                if (pulsa(rectJuego, event)) {
                    mediaPlayer.stop();
                    return 1;
                } else if (pulsa(rectOpciones, event)) {
                    return 2;
                } else if (pulsa(rectLogros, event)) {
                    return 3;
                } else if (pulsa(rectAyuda, event)) {
                    return 4;
                } else if (pulsa(rectCreditos, event)) {
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
