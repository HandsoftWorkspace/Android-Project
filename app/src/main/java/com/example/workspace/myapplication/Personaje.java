package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Personaje {
    int velocidad;
    Bitmap frame0, frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9;
    private int posX, posY, vel, vida;
    Bitmap[] frames;

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

//    public void obtenerBitmap(Context context, String nombre){
//        BitmapFactory.decodeResource(context.getResources(),R.drawable.nombre);
//    }

}
