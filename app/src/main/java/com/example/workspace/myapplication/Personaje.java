package com.example.workspace.myapplication;

import android.graphics.Bitmap;

public class Personaje {
    int velocidad;
    Bitmap frame0, frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8;

    Bitmap[] frames;

    public Personaje(int posX, int posY){
        frames[0] = frame0;
        frames[1] = frame1;
        frames[2] = frame2;
        frames[3] = frame3;
        frames[4] = frame4;
        frames[5] = frame5;
        frames[6] = frame6;
        frames[7] = frame7;
        frames[8] = frame8;
    }

    public Personaje() {

    }

    public Bitmap move(){
        int aux = 0;
        for (int i = 0; i < frames.length; i++) {
            aux++;
            if(aux/2==0){

                return frames[i];
            }
        }
        return null;
    }

}
