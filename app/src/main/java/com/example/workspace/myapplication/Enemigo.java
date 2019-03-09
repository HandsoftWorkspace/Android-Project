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

    private Paint p;

    public boolean seMueve = true;
    boolean avanza = true;
    public boolean enAvance = false;
    public boolean enRetroceso = false;
    private boolean seDibuja = true;

    public Bitmap frameRoca, frameSerpiente;
    Bitmap bitmaps[];

    public Rect rectEnemigo;

    Context context;
    Utils utils;

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

    public void setRectangulo() {
//        rectEnemigo = new Rect(posX, posY, posX + bitmaps[0].getWidth(), posY + bitmaps[0].getHeight());
        rectEnemigo = new Rect((int) (posX + 0.2 * bitmaps[0].getWidth()), (int) (posY + 0.2 * bitmaps[0].getHeight()), (int) (posX + 0.8 * bitmaps[0].getWidth()), (int) (posY + 0.8 * bitmaps[0].getHeight()));
    }

    public Bitmap move() {
        posY += velocidad;
        if (posY > altoPantalla + bitmaps[0].getHeight()) {
            posY = (int) (Math.random() * -150);
            posX = (int) (anchoPantalla / 18 * Math.random() * 18 + 1);
        }
        this.setRectangulo();
        return null;
    }

    public void actualizarFisica() {
    }

    /**
     * @param c
     */
    public void dibuja(Canvas c) {
        int auxRand = 0;
        c.drawBitmap(bitmaps[0], posX, posY, null);
        p.setColor(Color.RED);
        c.drawRect(rectEnemigo, p);

    }


}
