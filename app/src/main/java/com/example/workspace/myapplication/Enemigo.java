package com.example.workspace.myapplication;

import android.graphics.Bitmap;
import android.graphics.PointF;

import java.util.Random;

public class Enemigo extends Personaje {

        public PointF posicion;
        public Bitmap imagen;
        private Random g;
        public Enemigo(Bitmap imagen, float x, float y) {
            super();
            this.imagen = imagen;
            this.posicion = new PointF(x, y);
            g = new Random();
        }

        //Establece el movimiento de un enemigo en una pantalla definida por alto y ancho y cierta velocidad
        public void moverEnemigo(int alto, int ancho, int velocidad) {
            posicion.y += velocidad;
            if (posicion.y > alto) {
                posicion.y = 0;
                posicion.x = g.nextFloat() * (ancho - imagen.getWidth());
            }
        }

}
