package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Enemigo extends Personaje {

    private int posX, posY; // posiciones en eje X y eje Y
    private int anchoPantalla, altoPantalla;
    private int proporcionAncho, proporcionAlto;
    private boolean colision = false; // detecta si ha colisionado
    private int velocidad;
    private Paint p;
    public Bitmap frameRoca, frameSerpiente;
    Bitmap bitmaps[]; // array de bitmaps enemigos
    public Rect rectEnemigo;    // hitbox enemigos
    Context context; // contexto de la aplicación
    Utils utils;

    /**
     * Contructor que inicializa las propiedas de la clase
     *
     * @param context       Contexto de la aplicacion
     * @param posX          Posición en el eje X
     * @param posY          Posición en el eje Y
     * @param anchoPantalla Ancho pantalla del dispositivo
     * @param altoPantalla  Alto pantalla del dispositivo
     * @param velocidad     Velocidad de movimiento en el eje Y del personaje
     */
    public Enemigo(Context context, int posX, int posY, int anchoPantalla, int altoPantalla, int velocidad) {
        super(context, posX, posY, anchoPantalla, altoPantalla, velocidad);
        this.context = context;
        this.posX = posX;
        this.posY = posY;
        this.velocidad = velocidad;
        this.anchoPantalla = anchoPantalla;
        this.altoPantalla = altoPantalla;
        utils = new Utils(context);
        p = new Paint();
        bitmaps = new Bitmap[4];
        // crea una aleatoriedad a la hora de escoger un enemigo u otro
        if (Math.random() < 0.5f) {
            frameRoca = utils.getBitmapFromAssets("varios/rock.png");
            frameRoca = Bitmap.createScaledBitmap(frameRoca, anchoPantalla / 18, altoPantalla / 9, false);
            bitmaps[0] = frameRoca;
        } else {
            frameSerpiente = utils.getBitmapFromAssets("varios/snake.png");
            frameSerpiente = Bitmap.createScaledBitmap(frameSerpiente, anchoPantalla / 18, altoPantalla / 9, false);
            bitmaps[0] = frameSerpiente;
        }
    }

    /**
     * Se crea y se asocia una rect, que sera el hitbox de nuestro de personaje
     */
    public void setRectangulo() {
        rectEnemigo = new Rect((int) (posX + 0.2 * bitmaps[0].getWidth()), (int) (posY + 0.2 * bitmaps[0].getHeight()), (int) (posX + 0.8 * bitmaps[0].getWidth()), (int) (posY + 0.8 * bitmaps[0].getHeight()));
    }

    /**
     * Comprueba mediante booleanas, si se está moviendo o parado, ademas de la direccion en la que se muestra
     *
     * @return Devuelve null
     */
    public Bitmap move() {
        posY += velocidad;
        if (posY > altoPantalla + bitmaps[0].getHeight()) {
            posY = (int) (Math.random() * -150);
            posX = (int) (anchoPantalla / 18 * Math.random() * 18 + 1);
            colision = false;
        }
        this.setRectangulo();
        return null;
    }

    /**
     * Actualizamos la fisica de los elementos en pantalla
     */
    public void actualizarFisica() {

    }

    /**
     * Rutina de dibujo en el lienzo. Se le llamara desde el hilo juego
     *
     * @param c Canvas de la aplicacion
     */
    public void dibuja(Canvas c) {
        c.drawBitmap(bitmaps[0], posX, posY, null);

    }

    /**
     * Indica si el enemigo ha colisionado
     *
     * @return Devuelve el valor true o false
     */
    public boolean isColision() {
        return colision;
    }

    /**
     * Hace un set a la booleana colision
     *
     * @param colision Muestra el valor de si el enemigo esta en colision o no
     */
    public void setColision(boolean colision) {
        this.colision = colision;
    }

}
