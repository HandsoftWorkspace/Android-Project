package com.example.workspace.myapplication;

import android.graphics.Bitmap;
import android.graphics.PointF;

public class Fondo {
    public PointF posicion;
    public Bitmap imagen;
    public Fondo(Bitmap imagen, float x, float y){
        this.imagen=imagen;
        this.posicion=new PointF(x,y);
    }

    public Fondo(Bitmap imagen, int anchoPantalla){
        this(imagen,anchoPantalla-imagen.getWidth(),0);
    }

    public void mover(int velocidad){
        posicion.x-=velocidad;
    }
}
