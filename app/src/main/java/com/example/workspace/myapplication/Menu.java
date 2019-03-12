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

//public class Menu extends Escena implements SensorEventListener {
public class Menu extends Escena {

    /**
     * Rects asociados a los botones
     */
    Rect rectJuego, rectOpciones, rectLogros, rectAyuda, rectCreditos, rectCierre;
    /**
     * Bitmaps y recursos graficos
     */
    Bitmap juego, opciones, creditos, leader, ayuda, cierre, down, up, downGrande, upGrande;
    /**
     * Proporciones para el dibujado en la clase menú
     */
    private int proporcionAlto, proporcionAncho;
    /**
     * Pincel para la clase menú
     */
    private Paint paint = new Paint();
    /**
     * Tipología de fuente
     */
    Typeface faw;
    public static MediaPlayer mediaPlayer; // gestión de música
    public static AudioManager audioManager; // gestión de música
    private SoundPool efectos; // efectos cortos de audio
    float luz = -1; // muestra la cantidad de luz expresada en lux (lúmenes)
    /**
     * Entero que indice la cantidad de volumen
     */
    public int volumen; // Volumen del menú
    /**
     * Cadenas de texto para recursos
     */
    String nombreJuego, nombreVersion, strOpciones, strRecords, strAyuda, strCreditos; // recursos de texto para distintos idiomas
    private boolean boolJuego, boolOpciones, boolRecords, boolCreditos, boolAyuda;
    public boolean[] arrayBooleanas;

    /**
     * Contructor que inicializa las propiedas de la clase menu
     *
     * @param context       Contexto de la applicacion
     * @param idEscena      Numero asociado a una escena de la aplicacion
     * @param anchoPantalla Ancho pantalla del dispositivo
     * @param altoPantalla  Alto pantalla del dispositivo
     */
    public Menu(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);
        // Fondo menú principal
        bitmapFondo = utils.setFondo(anchoPantalla, altoPantalla, esDeDia);
        // scroll de nubes
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
        // ajustes de texto y recursos de texto para distintos idiomas
        paint.setColor(Color.TRANSPARENT);
        faw = Typeface.createFromAsset(context.getAssets(), "fonts/Moonlight.ttf");
        paintTexto.setColor(Color.YELLOW);
        paintTexto.setTypeface(faw);
        nombreJuego = context.getString(R.string.app_name);
        nombreVersion = context.getString(R.string.nombreversion);
        strOpciones = context.getString(R.string.opciones);
        strRecords = context.getString(R.string.logros);
        strAyuda = context.getString(R.string.ayuda);
        strCreditos = context.getString(R.string.creditos);
        // booleanas para detección de pulsación y pintar texto
        boolJuego = false;
        boolOpciones = false;
        boolRecords = false;
        boolCreditos = false;
        boolAyuda = false;
        arrayBooleanas = new boolean[]{boolJuego = false, boolOpciones = false, boolRecords = false, boolCreditos = false, boolAyuda = false};
    }

    /**
     * Rutina donde actualizamos la fisica de los elementos en pantalla
     */
    public void actualizarFisica() {
        fondoNubes.mover();
    }

    /**
     * Rutina de dibujo en el lienzo. Se le llamara desde el hilo juego
     *
     * @param c Canvas de la aplicacion
     */
    public void dibujar(Canvas c) {
        try {
            // fondo escena
            c.drawBitmap(bitmapFondo, 0, 0, null);
            // scroll
            fondoNubes.dibujar(c);
            // bitmaps
            c.drawBitmap(down, proporcionAncho * 6, proporcionAlto * 1, null);
            c.drawBitmap(up, proporcionAncho * 6, proporcionAlto * 2, null);
            c.drawBitmap(downGrande, proporcionAncho * 5, proporcionAlto * 5, null);
            c.drawBitmap(upGrande, proporcionAncho * 5, proporcionAlto * 6, null);
            // bitsmaps con sus respectivos rects
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
            // ajuste de texto
            paintTexto.setTextSize(80);
            c.drawText(nombreJuego, proporcionAncho * 6 + proporcionAncho / 3, proporcionAlto * 2 + proporcionAlto / 3, paintTexto);
            paintTexto.setTextSize(65);
            c.drawText(nombreVersion, proporcionAncho * 5 + proporcionAncho / 3, proporcionAlto * 6 + proporcionAlto / 3, paintTexto);
            paintTexto.setTextSize((35));
            if (boolJuego) {
                c.drawText(nombreJuego, anchoPantalla / 2 + proporcionAncho, 0 + proporcionAlto / 2, paintTexto);
            } else if (boolOpciones) {
                c.drawText(strOpciones, anchoPantalla / 2 + proporcionAncho, 0 + proporcionAlto / 2, paintTexto);
            } else if (boolRecords) {
                c.drawText(strRecords, anchoPantalla / 2 + proporcionAncho, 0 + proporcionAlto / 2, paintTexto);
            } else if (boolCreditos) {
                c.drawText(strCreditos, anchoPantalla / 2 + proporcionAncho, 0 + proporcionAlto / 2, paintTexto);
            } else if (boolAyuda) {
                c.drawText(strAyuda, anchoPantalla / 2 + proporcionAncho, 0 + proporcionAlto / 2, paintTexto);
            }
        } catch (NullPointerException e) {
            Log.i("Error al dibujar", e.getLocalizedMessage());
        }
    }

    /**
     * Gestiona el tipo de pulsacion mediante la deteccion de un evento, con el cual devolvera un idEscena
     *
     * @param event Conseguimos el tipo de evento que sucede
     * @return Devuelve un entero identificando la escena
     */
    @Override
    public int onTouchEvent(MotionEvent event) {
        //synchronized (surfaceHolder) {
        int pointerIndex = event.getActionIndex();        //Obtenemos el índice de la acción
        int pointerID = event.getPointerId(pointerIndex); //Obtenemos el Id del pointer asociado a la acción
        int accion = event.getActionMasked();             //Obtenemos el tipo de pulsación
        switch (accion) {
            case MotionEvent.ACTION_DOWN:           // Primer dedo toca
                refreshBooleanasTexto();
//                if (pulsa(rectJuego, event)) {
//                    boolJuego = true;
//                } else if (pulsa(rectOpciones, event)) {
//                    boolOpciones = true;
//                } else if (pulsa(rectLogros, event)) {
//                    boolRecords = true;
//                } else if (pulsa(rectAyuda, event)) {
//                    boolAyuda = true;
//                } else if (pulsa(rectCreditos, event)) {
//                    boolCreditos = true;
//                } else if (pulsa(rectCierre, event)) {
//                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:  // Segundo y siguientes tocan
                refreshBooleanasTexto();
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
                } else if (pulsa(rectCierre, event)) {
                    return 6;
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

    /**
     * Consigue el nivel de luz del dispositivo
     *
     * @return devuelve el nivel de luz
     */
    public float getLuz() {
        return luz;
    }

    /**
     * Establece el nivel de luz del dispositivo
     *
     * @param luz nivel de luz expresado en unidades de medida lux
     */
    public void setLuz(float luz) {
        this.luz = luz;
        if (luz < 4) {
            esDeDia = false;
        } else {
            esDeDia = true;
        }
        bitmapFondo = utils.setFondo(anchoPantalla, altoPantalla, esDeDia); // cada vez que asigna una valor de lúmenes, se comprueba que fondo escoger entre dia/noche
    }

    /**
     * Reinicia un el array de booleanas de la clase
     */
    public void refreshBooleanasTexto() {
        for (int i = 0; i < arrayBooleanas.length; i++) {
            arrayBooleanas[i] = false;
        }
    }

    public void setBoolJuego(boolean boolJuego) {
        this.boolJuego = boolJuego;
    }

    public void setBoolOpciones(boolean boolOpciones) {
        this.boolOpciones = boolOpciones;
    }

    public void setBoolRecords(boolean boolRecords) {
        this.boolRecords = boolRecords;
    }

    public void setBoolCreditos(boolean boolCreditos) {
        this.boolCreditos = boolCreditos;
    }

    public void setBoolAyuda(boolean boolAyuda) {
        this.boolAyuda = boolAyuda;
    }
}
