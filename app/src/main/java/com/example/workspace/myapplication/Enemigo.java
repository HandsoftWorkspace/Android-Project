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
    private int pintadoRandom = 1;

    private Paint p;

    public boolean seMueve = true;
    boolean avanza = true;
    public boolean enAvance = false;
    public boolean enRetroceso = false;
    private boolean seDibuja = true;

    public Bitmap frameEnemigo, frameRoca, frameSerpiente;
    Bitmap bitmaps[];
    private Bitmap[] idle;
    private Bitmap[] idleEspejo;
    private Bitmap[] run;
    private Bitmap[] runEspejo;

    /*javi*/ //    Enemigo[] arrayEnemigos;

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
        p = new Paint();

        /*javi */
        bitmaps = new Bitmap[5];

        /*javi *///rand = (int)(Math.random() * 2 + 1);

        if (Math.random() < 0.5f) {
            frameRoca = utils.getBitmapFromAssets("varios/rock.png");
            frameRoca = Bitmap.createScaledBitmap(frameRoca, anchoPantalla / 18, altoPantalla / 9, false);
            /*javi*/
            bitmaps[0] = frameRoca;
        } else {
            frameSerpiente = utils.getBitmapFromAssets("varios/snake.png");
            frameSerpiente = Bitmap.createScaledBitmap(frameSerpiente, anchoPantalla / 18, altoPantalla / 9, false);
            /*javi*/
            bitmaps[0] = frameSerpiente;
        }

        // /*javi*/     switch (rand) {
        // /*javi*/         case 1:
        // /*javi*/        frameRoca = utils.getBitmapFromAssets("varios/rock.png");
        // /*javi*/        frameRoca = Bitmap.createScaledBitmap(frameRoca, anchoPantalla / 18, altoPantalla / 9, false);
        // /*javi*/         /*javi*/                bitmaps[0] = frameRoca;
        // /*javi*/        break;
        // /*javi*/    case 2:
        // /*javi*/        frameSerpiente = utils.getBitmapFromAssets("varios/snake.png");
        // /*javi*/        frameSerpiente = Bitmap.createScaledBitmap(frameSerpiente, anchoPantalla / 18, altoPantalla / 9, false);
        // /*javi*/         /*javi*/        bitmaps[0] = frameSerpiente;
        // /*javi*/        break;
        // /*javi*/  }
    }

    public void setRectangulo() {
//        rectEnemigo = new Rect(posX, posY, posX + bitmaps[0].getWidth(), altoPantalla - proporcionAlto * 5);
        rectEnemigo = new Rect(posX, posY, posX + bitmaps[0].getWidth(), posY + bitmaps[0].getHeight());
    }

    public Bitmap move() {
        posY += velocidad;
        /*javi*/
        if (posY > altoPantalla + bitmaps[0].getHeight()) {
            /*javi*/
            posY = (int) (Math.random() * -150);
            /*javi*/
            posX = (int) (anchoPantalla / 18 * Math.random() * 18 + 1);
//            seDibuja = false;
        }
        this.setRectangulo();
        return null;
    }

    public void actualizarFisica() {
        /*javi*/ /*       for (int i = 0; i < arrayEnemigos.length; i++) {
            int auxPosX = proporcionAncho * (int) Math.random() * 18 + 1;
            int auxVelocidad = (int) Math.random() * 40 + 15;
//            if (i < 5) {
//                frameEnemigo = utils.getBitmapFromAssets("varios/roca.png");
//                frameEnemigo = Bitmap.createScaledBitmap(frameEnemigo, anchoPantalla / 20, altoPantalla / 20, false);
//            }
//            else{
//                frameEnemigo = utils.getBitmapFromAssets("varios/roca.png");
//                frameEnemigo = Bitmap.createScaledBitmap(frameEnemigo, anchoPantalla / 20, altoPantalla / 20, false);
//            }
            arrayEnemigos[i] = new Enemigo(context, auxPosX, 0 - proporcionAlto, anchoPantalla, altoPantalla, auxVelocidad);
        }
/*javi*/
    }

    /**
     * @param c
     */
    public void dibuja(Canvas c) {
        int auxRand = 0;
        c.drawBitmap(bitmaps[0], posX, posY, null);
//        c.drawRect(rectEnemigo, p);

        /*javi*/  //  for (int i = 0; i < arrayEnemigos.length; i++) {
        /*javi*/ //arrayEnemigos[i].dibuja(c);
        /*javi*/ //}

//        if (pintadoRandom == 1 && seDibuja) {
//            c.drawBitmap(bitmaps[0], posX, posY, null);
//          //  p.setColor(Color.GREEN);
//          //  p.setStyle(Paint.Style.STROKE);
//            c.drawRect(rectEnemigo, p);
//        } else {
//            pintadoRandom = (int) Math.random() * 2 + 1;
//            if (pintadoRandom == 1) {
//                seDibuja = true;
//            } else {
//                seDibuja = false;
//            }
//        }
    }


}
