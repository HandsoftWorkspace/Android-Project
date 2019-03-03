package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;

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

    private Bitmap[] idle;
    private Bitmap[] idleEspejo;
    private Bitmap[] run;
    private Bitmap[] runEspejo;

    public Rect rectEnemigo;

    Context context;
    Utils utils = new Utils(context);

    public Enemigo(Context context, int posX, int posY, int anchoPantalla, int altoPantalla, int velocidad) {
        super(context, posX, posY, anchoPantalla, altoPantalla, velocidad);
        this.context = context;
        this.posX = posX;
        this.posY = posY;
        this.velocidad = velocidad;
        this.anchoPantalla = anchoPantalla;
        this.altoPantalla = altoPantalla;
    }

//        public Enemigo(Bitmap imagen, float x, float y) {
//            super();
//            this.imagen = imagen;
//            this.posicion = new PointF(x, y);
//            g = new Random();
//        }

    //Establece el movimiento de un enemigo en una pantalla definida por alto y ancho y cierta velocidad
    public void moverEnemigo(int alto, int ancho, int velocidad) {
        posicion.y += velocidad;
        if (posicion.y > alto) {
            posicion.y = 0;
            posicion.x = g.nextFloat() * (ancho - imagen.getWidth());
        }
    }

    public void setRectangulo() {
//        float x = posicion.x;
//        float y = posicion.y;
//        float x = posX;
//        float y = posY;
//        rectDrYones = new Rect((int) (x + 0.2 * run[0].getWidth()), (int) (y + 0.2 * run[0].getHeight()), (int) (x + 0.8 * run[0].getWidth()), (int) (y + 0.8 * run[0].getHeight()));
//        rectDrYones = new Rect((int) x, (int) y, (int) x + run[0].getWidth(), (int) y + run[0].getWidth());
        rectEnemigo = new Rect(posX, posY, posX + run[0].getWidth(), altoPantalla - proporcionAlto * 5);

    }

}
