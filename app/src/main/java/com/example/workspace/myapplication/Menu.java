package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;

public class Menu extends Escena {

    Rect rectJuego, rectOpciones, rectLogros, rectAyuda, rectCreditos, rectCierre, rectBtnOk, rectBtnCancel;

    Bitmap juego, opciones, creditos, leader, ayuda, cierre, btnOk, btnCancel;

    Bitmap down, up, downGrande, upGrande;

    private ArrayList<Fondo> parallax; // Array de objetos 'fondo' para realizar el parallax


    private int proporcionAlto, proporcionAncho;
    private Paint paint = new Paint();
    private Paint paintTexto = new Paint();
    Typeface faw;

    public static MediaPlayer mediaPlayer;
    public static AudioManager audioManager;
    private SoundPool efectos;
    float luz = -1;
    private int sonidoWoosh, sonidoPajaro, sonidoExplosion;
    private int maxSonidosSimul = 10;
    private SoundPool soundPool;

    public int volumen; // Volumen del menú

    String nombreJuego;
    String nombreVersion;

    // Constructor de la escena MENÚ

    /**
     * Contructor que inicializa las propiedas de la clase
     *
     * @param context       contexto de la applicación
     * @param idEscena      número asociado a una escena de la aplicación
     * @param anchoPantalla ancho pantalla del dispositivo
     * @param altoPantalla  alto pantalla del dispositivo
     */
    public Menu(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);

        if (luz < 2) {
            esDeDia = false;
        } else {
            esDeDia = true;
        }

        // Fondo menú principal
        bitmapFondo = utils.setFondo(anchoPantalla, altoPantalla, esDeDia);
        fondoNubes = new Fondo(utils.setNubes(anchoPantalla, altoPantalla), anchoPantalla, 6);

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
        rectCierre = new Rect(0, 0, proporcionAncho * 2, proporcionAlto * 2);

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

        cierre = BitmapFactory.decodeResource(context.getResources(), R.drawable.cancel);
        cierre = Bitmap.createScaledBitmap(cierre, proporcionAncho * 2, proporcionAlto * 2, false);

        // Controles de audio y musica
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mediaPlayer = MediaPlayer.create(context, R.raw.laciudadperdida);
        volumen = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        mediaPlayer.setVolume(volumen / 2, volumen / 2);
        mediaPlayer.start(); // Se arranca la secuencia musical
        mediaPlayer.seekTo(2000); // Se adelanta para no causar un vacio
        mediaPlayer.setLooping(true); // Se fuerza a que se repita la secuencia musical

        paint.setColor(Color.TRANSPARENT);
        faw = Typeface.createFromAsset(context.getAssets(), "fonts/Moonlight.ttf");
        nombreJuego = context.getString(R.string.app_name);
        nombreVersion = context.getString(R.string.nombreversion);
    }

    /**
     * Actualizamos la física de los elementos en pantalla
     */
    public void actualizarFisica() {
        fondoNubes.mover();
    }

    /**
     * Rutina de dibujo en el lienzo. Se le llamará desde el hilo juego
     *
     * @param c
     */
    public void dibujar(Canvas c) {
        try {

            //c.drawBitmap(fondo, 0, 0, brocha);
            c.drawBitmap(bitmapFondo, 0, 0, null);
//            c.drawBitmap(nubes, 0, 0, null);
            fondoNubes.dibujar(c);


            c.drawBitmap(down, proporcionAncho * 6, proporcionAlto * 1, null);
//            c.drawText("DR YONES", proporcionAlto * 7, proporcionAlto * 1, p);
            c.drawBitmap(up, proporcionAncho * 6, proporcionAlto * 2, null);
            c.drawBitmap(downGrande, proporcionAncho * 5, proporcionAlto * 5, null);
            c.drawBitmap(upGrande, proporcionAncho * 5, proporcionAlto * 6, null);

            c.drawRect(rectJuego, paint);
            c.drawBitmap(juego, proporcionAncho * 8, proporcionAlto * 3, null);

            c.drawRect(rectOpciones, paint);
            c.drawBitmap(opciones, proporcionAncho * 3, proporcionAlto * 3, null);

            c.drawRect(rectLogros, paint);
            c.drawBitmap(leader, proporcionAncho * 13, proporcionAlto * 3, null);

            c.drawRect(rectAyuda, paint);
            c.drawBitmap(ayuda, proporcionAncho * 16, proporcionAlto * 6, null);

            c.drawRect(rectCreditos, paint);
            c.drawBitmap(creditos, proporcionAncho * 0, proporcionAlto * 6, null);

            c.drawRect(rectCierre, paint);
            c.drawBitmap(cierre, proporcionAncho * 0, proporcionAlto * 0, null);
//            paint.setColor(Color.GREEN);

            paintTexto.setColor(Color.YELLOW);
            paintTexto.setTextSize(80);
            paintTexto.setTypeface(faw);
            c.drawText(nombreJuego, proporcionAncho * 6 + proporcionAncho / 3, proporcionAlto * 2 + proporcionAlto / 3, paintTexto);
            paintTexto.setTextSize(65);
            c.drawText(nombreVersion, proporcionAncho * 5 + proporcionAncho / 3, proporcionAlto * 6 + proporcionAlto / 3, paintTexto);

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
                    // efecto pulsación
                    return 2;
                } else if (pulsa(rectLogros, event)) {
                    return 3;
                } else if (pulsa(rectAyuda, event)) {
                    return 4;
                } else if (pulsa(rectCreditos, event)) {
                    return 5;
                } else if (pulsa(rectCierre, event)) {
                    return 6;  // TODO al crear el constructor no se ve nada en pantalla
                }
                break;
            case MotionEvent.ACTION_MOVE: // Se mueve alguno de los dedos
                break;
            default:
                Log.i("Otra acción", "Acción no definida: " + accion);
        }

        return idEscena;
    }

    /**
     * Establece el nivel de luz del dispositivo
     *
     * @param luz nivel de luz expresado en unidades de medida lux
     */
    public void setLuz(float luz) {
        this.luz = luz;
        if (luz < 2) {
            esDeDia = false;
        } else {
            esDeDia = true;
        }
    }

    /**
     * Consigue el nivel de luz del dispositivo
     *
     * @return devuelve el nivel de luz
     */
    public float getLuz() {
        return luz;
    }

    /**
     * Método que para la música
     */
    public void pararMusica() {
        mediaPlayer.pause();
    }

    /**
     * Método que reanuda la música
     */
    public void reanudarMusica() {
        mediaPlayer.start();
    }

}
