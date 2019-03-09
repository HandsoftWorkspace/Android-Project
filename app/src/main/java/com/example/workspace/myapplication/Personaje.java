package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Personaje {
    private int posX, posY, vel, vida;
    Bitmap[] frames;
    Bitmap frame;
    Rect rectPersonaje;

    private int velocidad;
    private int tiempoFrame = 100;
    private int tiempoMove = 50;
    private long tFrameAuxm = 0, tMoveAux = 0;
    private int indice = 0;
    private int alfa = 230;

    public Personaje(Context context, int posX, int posY, int velocidad, int anchoPantalla, int altoPantalla) {

    }

    public Bitmap move() {
        int aux = 0;
        for (int i = 0; i < frames.length; i++) {
            aux++;
            if (aux / 2 == 0) {
                return frames[i];
            }
        }
        return null;
    }

    public void setRectangulo() {
        rectPersonaje = new Rect((int) (posX + 0.2 * frame.getWidth()), (int) (posY + 0.2 * frame.getHeight()), (int) (posX + 0.8 * frame.getWidth()), (int) (posY + 0.8 * frame.getHeight()));
    }

//    public void obtenerBitmap(Context context, String nombre){
//        BitmapFactory.decodeResource(context.getResources(),R.drawable.nombre);
//    }

}
