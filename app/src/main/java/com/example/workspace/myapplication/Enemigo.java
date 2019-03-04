package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Log;

import java.util.Random;

public class Enemigo extends Personaje {

    public PointF posicion;
    public Bitmap imagen;
    private Random g;

    private int posX, posY;
    private int anchoPantalla, altoPantalla;
    private int proporcionAncho, proporcionAlto;

    int vidas;

    private int velocidad;
    private int tiempoFrame = 100;
    private int tiempoMove = 50;
    private long tFrameAuxm = 0, tMoveAux = 0;
    private int indice = 0;
    private int alfa = 230;

    private Paint p;

    public boolean seMueve = true;
    boolean avanza = true;
    public boolean enAvance = false;
    public boolean enRetroceso = false;
    private boolean seDibuja = true;

    Bitmap frameEnemigo;
    Bitmap bitmaps[];
    private Bitmap[] idle;
    private Bitmap[] idleEspejo;
    private Bitmap[] run;
    private Bitmap[] runEspejo;

    public Rect rectEnemigo;

    Context context;
    Utils utils;

    int rand;

    public Enemigo(Context context, int posX, int posY, int anchoPantalla, int altoPantalla, int velocidad) {
        super(context, posX, posY, anchoPantalla, altoPantalla, velocidad);
        this.context = context;
        this.posX = posX;
        this.posY = posY;
        this.velocidad = velocidad;
        this.anchoPantalla = anchoPantalla;
        this.altoPantalla = altoPantalla;

        utils = new Utils(context);

        bitmaps = new Bitmap[5];

        rand = (int) Math.random() * 2 + 1;

        switch (rand) {
            case 1:
                frameEnemigo = utils.getBitmapFromAssets("varios/rock.png");
                frameEnemigo = Bitmap.createScaledBitmap(frameEnemigo, anchoPantalla / 18, altoPantalla / 9, false);
                bitmaps[0] = frameEnemigo;
                break;
            case 2:
                frameEnemigo = utils.getBitmapFromAssets("varios/snake.png");
                frameEnemigo = Bitmap.createScaledBitmap(frameEnemigo, anchoPantalla / 18, altoPantalla / 9, false);
                bitmaps[1] = frameEnemigo;
                break;
        }
    }

    /**
     *
     */
    public void cambiaFrame() {
        if (System.currentTimeMillis() - tFrameAuxm > tiempoFrame) {
            indice++;
            if (indice >= idle.length) {
                indice = 0;
            }
            tFrameAuxm = System.currentTimeMillis();
        }
    }

    public void setRectangulo() {
        rectEnemigo = new Rect(posX, posY, posX + run[0].getWidth(), altoPantalla - proporcionAlto * 5);
    }

    public Bitmap move() {
        posY += velocidad;
        if (posY > altoPantalla + frameEnemigo.getHeight()) {
            seDibuja = false;
        }
//        this.setRectangulo();
        return null;
    }

    /**
     * @param c
     */
    public void dibuja(Canvas c) {
        if (seDibuja) {
            c.drawBitmap(frameEnemigo, posX, posY, null);
        }
        p.setColor(Color.GREEN);
        p.setStyle(Paint.Style.STROKE);
        c.drawRect(rectEnemigo, p);
    }


}
