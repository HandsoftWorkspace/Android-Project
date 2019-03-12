package com.example.workspace.myapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import com.example.workspace.myapplication.Juego;

public class Fondo {

    /**
     * Numero de coma flotante para definir las posiciones de la imagen
     */
    public PointF posicion, posicion2;
    /**
     * Bitmap a la que se le asocia una imagen
     */
    public Bitmap imagen;
    /**
     * Velocidad de movimiento en el eje X
     */
    int velocidad = 0;

    /**
     * Constructo que inicializa los parametros de clase
     * @param imagen Bitmap asociado a una imagen
     * @param x Posicion eje X
     * @param y Posicion posicion eje Y
     */
    public Fondo(Bitmap imagen, float x, float y, int velocidad) {
        this.imagen = imagen;
        this.velocidad=velocidad;
        this.posicion = new PointF(x, y);
        this.posicion2 = new PointF(this.posicion.x + imagen.getWidth(), y);
    }

    /**
     * @param imagen
     * @param anchoPantalla
     */
    public Fondo(Bitmap imagen, int anchoPantalla, int velocidad) {
        this(imagen, anchoPantalla - imagen.getWidth(), 0, velocidad);
    }

    /**
     * Mueve en el eje X la imagen, la reinicia cuando traspasa los límites de la pantalla, creando un efecto de scroll
     */
    public void mover() {
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
     * Rutina de dibujo en el lienzo. Se le llamara desde el hilo juego
     *
     * @param c
     */
    public void dibujar(Canvas c) {
        c.drawBitmap(imagen, posicion.x, posicion.y, null);
        c.drawBitmap(imagen, posicion2.x, posicion2.y, null);
    }
}
