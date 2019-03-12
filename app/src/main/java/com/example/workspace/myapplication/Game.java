package com.example.workspace.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;

public class Game extends Escena implements Runnable {
    /**
     * Proporciones de pantalla a partide del tamanho de la pantalla del dispotiviso
     */
    private int proporcionAncho, proporcionAlto;
    /**
     * Pinceles
     */
    private Paint p = new Paint();
    /**
     * Pincel para textos
     */
    private Paint paintTexto = new Paint();
    /**
     * Bitmaps para los botones e iconos
     */
    private Bitmap btnA, btnB, btnDisparo;
    private Bitmap heart, lose, win;
    /**
     * Array con donde se almacenaran los objetos enemigos
     */
    private ArrayList<Enemigo> listaEnemigos = new ArrayList<>();
    /**
     * Array con donde se almacenaran los objetos enemigos
     */
    private ArrayList<Caliz> listaCaliz = new ArrayList<>();
    /**
     * Rects para los botones
     */
    Rect rectBtnA, rectBtnB, rectBtnDisparo;
    /**
     * Indica si se está disparando el latigo
     */
    private boolean dispara = false;
    /**
     * Indica si se ha perdido la partida
     */
    public boolean gameOver = false;
    /**
     * Indica si se ha ganado la partida
     */
    public boolean gameWin = false;
    /**
     * Indica si el caballero ha sido impactado por el latigo
     */
    private boolean caballeroHerido = false;
    /**
     * Booleana que indica si se ha pulsado el boton volver
     */
    boolean pusaldo = false;
    /**
     * Entero que indice volumen
     */
    private int volumen;
    /**
     * Entero que seran las vidas de la partida
     */
    private int vidas = 1;
    /**
     * Puntos con los que se inicia
     */
    private int puntuacion = 0;
    /**
     * Ronda desde la que se empieza
     */
    private int ronda = 1;
    /**
     * Strings de recursos para la gestion de los textos
     */
    private String strVidas = "";
    private String strPuntuacion = "";
    private String strPuntos = "";
    private String strRonda;
    // Control de audio
    public MediaPlayer mediaPlayer;
    public static AudioManager audioManager;
    // Vibracion
    public static Vibrator vibrator;
    DrYones drYones;
    CaballeroDos caballeroDos;
    Enemigo enemigo;
    Latigo latigo;
    Caliz caliz;

    /**
     * Constructor que inicializa las propiedades de la clase
     *
     * @param context       Contexto de la app
     * @param idEscena      Escena asociado a un número
     * @param anchoPantalla ancho pantalla del dispositivo
     * @param altoPantalla  alto pantalla del dispositivo
     */
    public Game(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);
        this.proporcionAncho = anchoPantalla / 18;
        this.proporcionAlto = altoPantalla / 9;
        // Inicialización de los personajes
        drYones = new DrYones(context, anchoPantalla / 2, altoPantalla, anchoPantalla, altoPantalla, 10); // le estas pone
        latigo = new Latigo(context, drYones.getPosX(), drYones.getPosY() - drYones.getRun()[0].getHeight() / 2, anchoPantalla, altoPantalla);
//        caballeroDos = new CaballeroDos(context, anchoPantalla, proporcionAlto / 9 * 2, anchoPantalla, altoPantalla, 3);
        // se establece el objeto vibrador
        vibrator = (Vibrator) getContext().getSystemService(context.VIBRATOR_SERVICE);
        // Se asocia cada bitmap a una imagen png
        // Fondo
        bitmapFondo = BitmapFactory.decodeResource(context.getResources(), R.drawable.backgroundnight);
        bitmapFondo = Bitmap.createScaledBitmap(bitmapFondo, anchoPantalla, altoPantalla, false);
        // scroll
        fondoNubes = new Fondo(utils.setNubes(anchoPantalla, altoPantalla), anchoPantalla, 6); // desactivado, ralentiza
        // Botones
        btnA = BitmapFactory.decodeResource(context.getResources(), R.drawable.btn);
        btnA = Bitmap.createScaledBitmap(btnA, proporcionAncho * 1 + proporcionAlto / 2, proporcionAlto * 1 + proporcionAlto / 2, false);
        btnB = BitmapFactory.decodeResource(context.getResources(), R.drawable.btn);
        btnB = Bitmap.createScaledBitmap(btnB, proporcionAncho * 1 + proporcionAlto / 2, proporcionAlto * 1 + proporcionAlto / 2, false);
        btnDisparo = BitmapFactory.decodeResource(context.getResources(), R.drawable.mira);
        btnDisparo = Bitmap.createScaledBitmap(btnDisparo, proporcionAncho * 1, proporcionAlto * 1, false);
        volverMenu = BitmapFactory.decodeResource(context.getResources(), R.drawable.close2);
        volverMenu = Bitmap.createScaledBitmap(volverMenu, proporcionAncho * 2, proporcionAlto * 2, false);
        // Rect botones
        rectBtnA = new Rect(0, proporcionAlto * 6, proporcionAncho + proporcionAncho / 2, proporcionAlto * 7 + proporcionAlto / 2);
        rectBtnB = new Rect(proporcionAncho * 17 - proporcionAncho / 2, proporcionAlto * 6, proporcionAncho * 18, proporcionAlto * 7 + proporcionAlto / 2);
        rectBtnDisparo = new Rect(proporcionAncho * 17, proporcionAlto * 4, proporcionAncho * 18, proporcionAlto * 5);
        rectVolverMenu = new Rect(0, 0, proporcionAncho * 2, proporcionAlto * 2);
        // PERSONAJES
        p.setColor(ContextCompat.getColor(context, R.color.colorBackGr));
        drYones.enAvance = true;
        drYones.seMueve = false;
        // Creación de 10 objetos tipo enemigo, con parámetros aleatorios
        for (int i = 0; i < 7; i++) {
            int auxPosX = (int) (proporcionAncho * Math.random() * 18 + 1);
            int auxVelocidad = (int) Math.random() * 30 + 15;
            enemigo = new Enemigo(context, auxPosX, (int) (Math.random() * -150), anchoPantalla, altoPantalla, auxVelocidad);
            listaEnemigos.add(enemigo);
        }
        // Creación de calizez
        for (int i = 0; i < 3; i++) {
            int auxPosX = (int) (proporcionAncho * Math.random() * 18 + 1);
            int auxVelocidad = (int) Math.random() * 30 + 15;
            caliz = new Caliz(context, auxPosX, (int) (Math.random() * -150), anchoPantalla, altoPantalla, auxVelocidad);
            listaCaliz.add(caliz);
        }
        heart = utils.getBitmapFromAssets("varios/heart.png");
        heart = Bitmap.createScaledBitmap(heart, proporcionAncho * 1, proporcionAlto * 1, false);
        lose = utils.getBitmapFromAssets("varios/lose.png");
        lose = Bitmap.createScaledBitmap(lose, proporcionAncho * 8, proporcionAlto * 4, false);
        win = utils.getBitmapFromAssets("varios/win.png");
        win = Bitmap.createScaledBitmap(win, proporcionAncho * 4, proporcionAlto * 2, false);
        faw = Typeface.createFromAsset(context.getAssets(), "fonts/Moonlight.ttf");
        // recursos de texto
        strPuntos = context.getString(R.string.puntos);
        strRonda = context.getString(R.string.ronda);
        // Controles de audio y musica
        audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        mediaPlayer = MediaPlayer.create(context, R.raw.ambienteselva);
        volumen = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        if (!Opciones.volumen) {
            mediaPlayer.setVolume(0, 0);
        } else {
            mediaPlayer.setVolume(volumen / 2, volumen / 2);
        }
        mediaPlayer.start(); // Se arranca la secuencia musical
        mediaPlayer.seekTo(2000); // Se adelanta para no causar un vacio
        mediaPlayer.setLooping(true); // Se fuerza a que se repita la secuencia musical
    }

    /**
     * Actualiza fisica de los personajes y objetos en pantalla
     */
    @Override
    public void actualizarFisica() {
        super.actualizarFisica();
        fondoNubes.mover();
        drYones.move();
        drYones.setRectangulo();
//        caballeroDos.move();
//        caballeroDos.setRectangulo();
        // Control de látigo
        if (dispara) {
            latigo.move();
            if (latigo.getX() > anchoPantalla + latigo.getWidth() || latigo.getX() < 0 - latigo.getWidth()) {
                dispara = false;
                latigo.setPosX(drYones.getRectDrYones().centerX());
            }
        }
        // Mueve la colección de rocas y serpientes
        for (Enemigo e : listaEnemigos) {
            e.move();
            e.setRectangulo();
        }
        // Comprueba hitboxes entre el personaje principal y rocas/serpientes
        for (Enemigo e : listaEnemigos) {
            if (drYones.rectDrYones.intersect(e.rectEnemigo) && !e.isColision()) {
                e.setColision(true);
                this.vidas--;
                if (Opciones.vibracion) {
                    vibrator.vibrate(300);
                }
                if (vidas < 1) {
                    gameOver = true;
                }
            }
        }
        // Mueve la lista de caliz
        for (Caliz c : listaCaliz) {
            c.move();
        }
        // Comprueba hitboxes entre personaje y los caliz
        for (Caliz c : listaCaliz) {
            if (drYones.rectDrYones.intersect(c.rectCaliz) && !c.isColision()) {
                c.setColision(true);
                if (vidas < 3) {
                    this.vidas++;
                }
                if (Opciones.vibracion) {
                    vibrator.vibrate(100);
                }
                puntuacion += 100;
                if (puntuacion > 15000) {
                    ronda++;
                } else if (puntuacion > 30000) {
                    ronda++;
                } else if (puntuacion > 60000) {
                    ronda++;
                    gameWin = true;
                }
            }
        }
    }

    /**
     * Rutina de dibujo, se llamara desde el hilo de juego
     *
     * @param c Canvas de la aplicacion
     */
    public void dibujar(Canvas c) {
        try {
            if (gameOver) {
                pararMusica();
                p.setColor(Color.GREEN);
                c.drawBitmap(bitmapFondo, 0, 0, null);
                c.drawBitmap(lose, anchoPantalla / 2 - lose.getWidth() / 2, altoPantalla / 2 - lose.getHeight() / 2, null);
                if (pusaldo) {
                    c.drawBitmap(volverMenu, 0, proporcionAlto * 0, null);
                }
                listaEnemigos.clear();
                listaCaliz.clear();
            } else if (gameWin) {
                pararMusica();
                p.setColor(Color.GREEN);
                c.drawBitmap(bitmapFondo, 0, 0, null);
                c.drawBitmap(win, anchoPantalla / 2 - lose.getWidth() / 2, altoPantalla / 2 - lose.getHeight() / 2, null);
                c.drawBitmap(volverMenu, 0, proporcionAlto * 0, null);
                if (pusaldo) c.drawRect(rectVolverMenu, p);
                listaEnemigos.clear();
                listaCaliz.clear();
            } else {
                // Prueba aceleración de hardware
                c.drawBitmap(bitmapFondo, 0, 0, null);
//                fondoNubes.dibujar(c);
                c.drawBitmap(heart, proporcionAncho / 2, 0, null);
                p.setColor(Color.GREEN);
                // Botones
                c.drawBitmap(btnA, 0, proporcionAlto * 6, null);
                c.drawBitmap(btnB, anchoPantalla - proporcionAncho - proporcionAncho / 2, proporcionAlto * 6, null);
                c.drawBitmap(btnDisparo, anchoPantalla - proporcionAncho, proporcionAlto * 4, null);
                // Personajes
                drYones.cambiaFrame();
                drYones.dibuja(c);
                if (!caballeroHerido) {
//                    caballeroDos.cambiaFrame();
//                    caballeroDos.dibuja(c);
                }
                for (Enemigo e : listaEnemigos) {
                    e.dibuja(c);
                }
                for (Caliz caliz : listaCaliz) {
                    caliz.dibuja(c);
                }
                if (dispara) {
                    latigo.dibuja(c);
                }
                strPuntuacion = String.valueOf(puntuacion);
                paintTexto.setColor(Color.YELLOW);
                paintTexto.setTextSize(40);
                paintTexto.setTypeface(faw);
                c.drawText(strPuntos + ": " + strPuntuacion + "   " + strRonda + ": " + ronda, anchoPantalla / 2 - proporcionAncho * 2, proporcionAlto - proporcionAlto / 3, paintTexto);

                strVidas = String.valueOf(vidas);
                c.drawText(strVidas, proporcionAncho * 2, proporcionAlto - proporcionAncho / 3, paintTexto);

                if (vidas == 0) {
                    c.drawBitmap(lose, anchoPantalla / 2 - lose.getWidth(), altoPantalla / 2 - lose.getHeight(), null);
                    c.drawText(strRonda + " " + ronda + "Puntos: " + strPuntos, proporcionAncho * 6, 0, paintTexto);
                }
            }
        } catch (NullPointerException e) {
            Log.i("Error al dibujar", e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    // Runnable, lanzo el hilo de backgrounds
    @Override
    public void run() {

    }

    /**
     * Controla los eventos que suceden sobre la pantalla del dispositivo
     *
     * @param event Tipo de evento que sucede
     * @return Devuelve el tipo de evento
     */
    @Override
    public int onTouchEvent(MotionEvent event) {
        int pointerIndex = event.getActionIndex();        //Obtenemos el índice de la acción
        int pointerID = event.getPointerId(pointerIndex); //Obtenemos el Id del pointer asociado a la acción
        int accion = event.getActionMasked();
        switch (accion) {
            case MotionEvent.ACTION_DOWN:
                if (pulsa(rectBtnA, event)) {
                    drYones.enAvance = false;
                    drYones.enRetroceso = true;
                    drYones.seMueve = true;
                }
                if (pulsa(rectBtnB, event)) {
                    drYones.enRetroceso = false;
                    drYones.enAvance = true;
                    drYones.seMueve = true;
                }
                if (pulsa(rectBtnDisparo, event)) {
                    mediaPlayer = MediaPlayer.create(context, R.raw.sonidolatigo);
                    volumen = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setVolume(volumen / 2, volumen / 2);
                    mediaPlayer.start(); // Se arranca la secuencia musical
                    dispara = true;
                }
                if (pulsa(rectVolverMenu, event) && gameOver) {
                    guardarPuntos(puntuacion);
                    pusaldo = true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (pulsa(rectVolverMenu, event) && gameOver) {
                    guardarPuntos(puntuacion);
                    pusaldo = true;
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                pusaldo = false;
                if (pulsa(rectBtnA, event)) {
                    drYones.seMueve = false;
                }
                if (pulsa(rectBtnB, event)) {
                    drYones.seMueve = false;
                }
                if (pulsa(rectBtnDisparo, event)) {
                    dispara = false;
                }
                dispara = false;
                latigo.setPosX(drYones.getRectDrYones().centerX());
                if (pulsa(rectVolverMenu, event) && gameOver) {
                    mediaPlayer.stop();
                    return 0;
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * Funcion para detener la musica
     */
    public void pararMusica() {
        mediaPlayer.pause();
    }

    /**
     * Funcion que reanuda la musica
     */
    public void reanudarMusica() {
        mediaPlayer.start();
    }

    /**
     * Metodo que se encarga de guardar puntos en la base de datos Sqlite
     *
     * @param puntos
     */
    public void guardarPuntos(int puntos) {
        BaseDatos bd = null;
        SQLiteDatabase bdSqlite = null;
        try {
            bd = new BaseDatos(context, "puntuaciones", null, 1);
            bdSqlite = bd.getWritableDatabase();
            String query = "insert into puntuaciones values(null," + puntos + ")";
//            String query = "insert into puntuaciones values(1,234)";
            Log.d("guarda puntos", " " + query);
            bdSqlite.execSQL(query);
            Log.d("guarda puntos", " " + puntos);
        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
            if (bd == null) {
                bd.close();
            }
            if (bdSqlite == null) {
                bdSqlite.close();
            }
        }
    }
}
