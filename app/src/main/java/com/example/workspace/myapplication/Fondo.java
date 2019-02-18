package com.example.workspace.myapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

public class Fondo {

    public PointF posicion, posicion2;
    public Bitmap imagen;
    Paint p;

    /**
     * @param imagen
     * @param x
     * @param y
     */
    public Fondo(Bitmap imagen, float x, float y) {
        this.imagen = imagen;
        this.posicion = new PointF(x, y);
        this.posicion2 = new PointF(this.posicion.x + imagen.getWidth(), y);
    }

    /**
     * @param imagen
     * @param anchoPantalla
     */
    public Fondo(Bitmap imagen, int anchoPantalla) {
        this(imagen, anchoPantalla - imagen.getWidth(), 0);
    }

    /**
     * @param velocidad
     */
    public void mover(int velocidad) {
        posicion.x -= velocidad;
        posicion2.x -= velocidad;
        if (posicion.x + imagen.getWidth() < 0) {
            posicion.x = posicion2.x + imagen.getWidth();
        }
        if (posicion2.x + imagen.getWidth() < 0) {
            posicion2.x = posicion.x + imagen.getWidth();
        }
    }

    /**
     * @param c
     */
    public void dibujar(Canvas c) {
        c.drawBitmap(imagen, posicion.x, posicion.y, null);
        c.drawBitmap(imagen, posicion2.x, posicion2.y, null);
    }
}
