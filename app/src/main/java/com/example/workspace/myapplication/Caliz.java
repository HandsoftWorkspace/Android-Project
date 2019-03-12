package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Caliz extends Personaje {

    private int posX, posY; // posiciones eje X y eje Y
    private int anchoPantalla, altoPantalla; // ancho y alto del dispositivo
    private int proporcionAncho, proporcionAlto; // proporciones de pantalla
    private int velocidad; // velocidad de movimiento del objeto
    private boolean colision = false; // boolean que muestra si existe colisión
    private Paint p; // pincel
    public Bitmap frameArca, frameCaliz; // frames estáticos
    Bitmap bitmaps[]; // array de bitsmaps, donde se almacenarán los frames de los objetos
    public Rect rectCaliz; // hitbox del objeto
    Context context;
    Utils utils;

    /**
     * Contructor que inicializa las propiedades de la clase
     *
     * @param context       Contexto de la aplicacion
     * @param posX          Posición del objeto en el eje X
     * @param posY          Posición del objeto en la clase Y
     * @param anchoPantalla Ancho pantalla del dispositivo
     * @param altoPantalla  Alto pantalla del dispositivo
     * @param velocidad     Velocidad asociada al movimiento del objeto en pantalla
     */
    public Caliz(Context context, int posX, int posY, int anchoPantalla, int altoPantalla, int velocidad) {
        super(context, posX, posY, anchoPantalla, altoPantalla, velocidad);
        this.context = context;
        this.posX = posX;
        this.posY = posY;
        this.velocidad = velocidad;
        this.anchoPantalla = anchoPantalla;
        this.altoPantalla = altoPantalla;

        utils = new Utils(context);
        p = new Paint();

        bitmaps = new Bitmap[2];

        if (Math.random() < 0.5f) {
            frameArca = utils.getBitmapFromAssets("varios/arca.png");
            frameArca = Bitmap.createScaledBitmap(frameArca, anchoPantalla / 18, altoPantalla / 9, false);
            bitmaps[0] = frameArca;
        } else {
            frameCaliz = utils.getBitmapFromAssets("varios/caliz.png");
            frameCaliz = Bitmap.createScaledBitmap(frameCaliz, anchoPantalla / 18, altoPantalla / 9, false);
            bitmaps[0] = frameCaliz;
        }
    }

    /**
     * Se crea y se asocia una rect, que sera el hitbox de nuestro de personaje
     */
    public void setRectangulo() {
//        rectCaliz = new Rect(posX, posY, posX + bitmaps[0].getWidth(), posY + bitmaps[0].getHeight());
        rectCaliz = new Rect((int) (posX + 0.2 * bitmaps[0].getWidth()), (int) (posY + 0.2 * bitmaps[0].getHeight()), (int) (posX + 0.8 * bitmaps[0].getWidth()), (int) (posY + 0.8 * bitmaps[0].getHeight()));
    }

    /**
     * Mueve al objeto, de norte a sur, simulando un efecto de caida
     *
     * @return Devuelve el hitbox del objeto a cada movimiento
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
     * Actualiza fisica de los personajes y objetos en pantalla
     */
    public void actualizarFisica() {
    }

    /**
     * Rutina de dibujo en el lienzo. Se le llamara desde el hilo juego
     *
     * @param c Canvas asociado a la aplicacion
     */
    public void dibuja(Canvas c) {
        c.drawBitmap(bitmaps[0], posX, posY, null);
    }

    /**
     * Indica si el enemigo ha colisionado
     *
     * @return Devuelve el valor de la booleana
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