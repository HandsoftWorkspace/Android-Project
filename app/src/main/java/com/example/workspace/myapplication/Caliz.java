package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;

import java.util.Random;

public class Caliz extends Personaje {

    private int posX, posY;
    private int anchoPantalla, altoPantalla;
    private int proporcionAncho, proporcionAlto;

    private int velocidad;

    private boolean colision = false;

    private Paint p;

    public Bitmap frameArca, frameCaliz;
    Bitmap bitmaps[];

    public Rect rectCaliz;

    Context context;
    Utils utils;

    /**
     * Contructor que inicializa las propiedades de la clase
     *
     * @param context       contexto de la aplicación
     * @param posX          posición del objeto en el eje X
     * @param posY          posición del objeto en la clase Y
     * @param anchoPantalla ancho pantalla del dispositivo
     * @param altoPantalla  alto pantalla del dispositivo
     * @param velocidad     velocidad asociada al movimiento del objeto en pantalla
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
     * Se crea y se asocia una rect, que será el hitbox de nuestro de personaje
     */
    public void setRectangulo() {
//        rectCaliz = new Rect(posX, posY, posX + bitmaps[0].getWidth(), posY + bitmaps[0].getHeight());
        rectCaliz = new Rect((int) (posX + 0.2 * bitmaps[0].getWidth()), (int) (posY + 0.2 * bitmaps[0].getHeight()), (int) (posX + 0.8 * bitmaps[0].getWidth()), (int) (posY + 0.8 * bitmaps[0].getHeight()));
    }

    /**
     * Mueve al objeto, de norte a sur, simulando un efecto de caida
     *
     * @return devuelve el hitbox del objeto a cada movimiento
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
     * Actualiza física de los personajes y objetos en pantalla
     */
    public void actualizarFisica() {
    }

    /**
     * Rutina de dibujo en el lienzo. Se le llamará desde el hilo juego
     *
     * @param c canvas asociado a la aplicación
     */
    public void dibuja(Canvas c) {
        c.drawBitmap(bitmaps[0], posX, posY, null);
    }

    /**
     * Índica si el enemigo ha colisionado
     *
     * @return devuelve el valor de la booleana
     */
    public boolean isColision() {
        return colision;
    }

    /**
     * Hace un set a la booleana colision
     *
     * @param colision muestra el valor de si el enemigo está en colisión o no
     */
    public void setColision(boolean colision) {
        this.colision = colision;
    }

}