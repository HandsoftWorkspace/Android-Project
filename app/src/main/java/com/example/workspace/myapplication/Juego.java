package com.example.workspace.myapplication;

import android.graphics.Canvas;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

public class Juego extends SurfaceView implements SurfaceHolder.Callback, SensorEventListener {
    private SurfaceHolder surfaceHolder;      // Interfaz abstracta para manejar la superficie de dibujado
    private Context context;                  // Contexto de la aplicación

    private SensorManager sensorManager;
    private Sensor sensor;

    Utils utils;

    private float luz = -1;

    private int anchoPantalla = 1;              // Ancho de la pantalla, su valor se actualiza en el método surfaceChanged
    private int altoPantalla = 1;               // Alto de la pantalla, su valor se actualiza en el método surfaceChanged
    private Hilo hilo;                        // Hilo encargado de dibujar y actualizar la física
    private boolean funcionando = false;      // Control del hilo
    public Escena escenaActual;

    // Control temporal
    long tiempo = 0;
    int tiempoEspera = 1000;

    boolean[] listaPreferencias;

    private Menu menu;
    private Game game;
    private Opciones opciones;
    private Records records;
    private Ayuda ayuda;
    private Creditos creditos;
    private ConfirmacionCierre cierre;
    private ConfirmacionBorrado borrado;

    /**
     * * Contructor que inicializa las propiedas de la clase
     *
     * @param context contexto de la aplicación
     */
    public Juego(Context context) {
        super(context);
        this.surfaceHolder = getHolder();       // Se obtiene el holder
        this.surfaceHolder.addCallback(this);   // Se indica donde van las funciones callback
        this.context = context;                 // Obtenemos el contexto

        hilo = new Hilo();                      // Inicializamos el hilo
        setFocusable(true);                     // Aseguramos que reciba eventos de toque

        utils = new Utils(context);

        // Sensor luz
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        if (sensor != null) {
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    /**
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        synchronized (surfaceHolder) {
            // Control de escenas
            int nuevaEscena = escenaActual.onTouchEvent(event);
            if (nuevaEscena != escenaActual.idEscena) {
                switch (nuevaEscena) {
                    case 0:
                        escenaActual = menu;
                        break;
                    case 1:
                        escenaActual = new Game(context, 1, anchoPantalla, altoPantalla);
                        break;
                    case 2:
                        escenaActual = opciones;
                        break;
                    case 3:
                        escenaActual = records;
                        break;
                    case 4:
                        escenaActual = ayuda;
                        break;
                    case 5:
                        escenaActual = creditos;
                        break;
                    case 6:
                        escenaActual = cierre;
                        break;
//                    case 7:
//                        escenaActual = borrado;
//                        break;
                }
            }
        }
        return true;
    }

    /**
     * @param surfaceHolder
     */
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
    }

    /**
     * @param surfaceHolder
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
//        if (escenaActual == game) {
//            game.pararMusica();
//        }
        opciones.guardarPreferencias(); // Guardo preferencias opciones
        hilo.setFuncionando(false);  // Se para el hilo
        try {
            menu.pararMusica(); // método que para la música del menú
            hilo.join();   // Se espera a que finalize
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param holder
     * @param format
     * @param width
     * @param height
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        anchoPantalla = width;               // se establece el nuevo ancho de pantalla
        altoPantalla = height;               // se establece el nuevo alto de pantalla

        if (escenaActual != game) {
            game.pararMusica();
        }
        if (menu == null) {
            menu = new Menu(context, 0, anchoPantalla, altoPantalla);
        }
        if (game == null) {
            opciones = new Opciones(context, 2, anchoPantalla, altoPantalla);
            opciones.cargarPreferencias();
        }
        if (records == null) {
            records = new Records(context, 3, anchoPantalla, altoPantalla);
        }
        if (ayuda == null) {
            ayuda = new Ayuda(context, 4, anchoPantalla, altoPantalla);
        }
        if (creditos == null) {
            creditos = new Creditos(context, 5, anchoPantalla, altoPantalla);
        }
        if (cierre == null) {
            cierre = new ConfirmacionCierre(context, 6, anchoPantalla, altoPantalla);
        }
        // TODO
//        if (borrado == null) {
//            borrado = new ConfirmacionBorrado(context, 7, anchoPantalla, altoPantalla);
//        }

        escenaActual = menu;
        hilo.setSurfaceSize(width, height);   // se establece el nuevo ancho y alto de pantalla en el hilo
        hilo.setFuncionando(true); // Se le indica al hilo que puede arrancar
        if (hilo.getState() == Thread.State.NEW)
            hilo.start(); // si el hilo no ha sido creado se crea;
        if (hilo.getState() == Thread.State.TERMINATED) {      // si el hilo ha sido finalizado se crea de nuevo;
            hilo = new Hilo();
            hilo.start(); // se arranca el hilo
        }
        if (escenaActual == menu) {
            menu.reanudarMusica();
        }
        if (escenaActual != menu) {
            menu.pararMusica();
        }
    }

    /**
     * Hilo en la cual implementamos el método de dibujo (y física) para que se haga en paralelo con la gestión de la interfaz de usuario
     */
    // Clase Hilo en la cual implementamos el método de dibujo (y física) para que se haga en paralelo con la gestión de la interfaz de usuario
    class Hilo extends Thread {
        public Hilo() {

        }

        @Override
        public void run() {
            while (funcionando) {
                Canvas c = null; //Necesario repintar todo el lienzo
                try {

                    if (!surfaceHolder.getSurface().isValid())
                        continue; // si la superficie no está preparada repetimos
                    c = surfaceHolder.lockCanvas(); // Obtenemos el lienzo.  La sincronización es necesaria por ser recurso común
                    synchronized (surfaceHolder) {
                        escenaActual.actualizarFisica();
                        escenaActual.dibujar(c);
                    }
                    tiempo = System.currentTimeMillis();

                    try {
                        Thread.sleep(System.currentTimeMillis() - tiempo);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {  // Haya o no excepción, hay que liberar el lienzo
                    if (c != null) {
                        surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
        }

        // Activa o desactiva el funcionamiento del hilo
        void setFuncionando(boolean flag) {
            funcionando = flag;
        }

        // Función es llamada si cambia el tamaño de la pantall o la orientación
        public void setSurfaceSize(int width, int height) {
            synchronized (surfaceHolder) {  // Se recomienda realizarlo de forma atómica

            }
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
     * Establece el nivel de luz del dispositivo
     *
     * @param luz nivel de luz expresado en unidades de medida lux
     */
    public void setLuz(float luz) {
        this.luz = luz;
        if (luz < 4) {
            menu.esDeDia = false;
            opciones.esDeDia = false;
            records.esDeDia = false;
            ayuda.esDeDia = false;
            creditos.esDeDia = false;
            cierre.esDeDia = false;
        } else {
            menu.esDeDia = true;
            opciones.esDeDia = true;
            records.esDeDia = true;
            ayuda.esDeDia = true;
            creditos.esDeDia = true;
            cierre.esDeDia = true;
        }
//        menu.bitmapFondo = utils.setFondo(anchoPantalla, altoPantalla, menu.esDeDia);
//        opciones.bitmapFondo = utils.setFondo(anchoPantalla, altoPantalla, menu.esDeDia);
//        records.bitmapFondo = utils.setFondo(anchoPantalla, altoPantalla, menu.esDeDia);
//        ayuda.bitmapFondo = utils.setFondo(anchoPantalla, altoPantalla, menu.esDeDia);
//        creditos.bitmapFondo = utils.setFondo(anchoPantalla, altoPantalla, menu.esDeDia);
    }

    /**
     * Detecta cúando se produce un cambio de valor en el sensor de luz
     *
     * @param event devuelve los eventos del sensor de luz
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        luz = event.values[0];
        setLuz(luz);
        Log.i("LUX JUEGO", "" + luz);
        if (sensor == null) {
            luz = (float) Math.random() * 4;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}




