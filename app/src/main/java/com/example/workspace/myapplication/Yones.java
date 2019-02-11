package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Yones extends Personaje {

    Bitmap[] framesIndiana = new Bitmap[10];
    Bitmap frame;
    int cont = 0;
    Paint p;
    int posX, posY;
    int anchoPantalla, altoPantalla;
    int tiempoFrame = 100;
    long tFrameAuxm = 0;
    int indice = 0;
    int proporcionX;
    int proporcionY;

    public Yones(Context context, int posX, int posY, int velocidad, int anchoPantalla, int altoPantalla) {

        this.posX=posX;
        this.posY=posY;
        proporcionX = anchoPantalla / 10;
        proporcionY = altoPantalla / 14;

        framesIndiana[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run0);
        framesIndiana[0] = Bitmap.createScaledBitmap(framesIndiana[0], proporcionX, proporcionY * 5, false);

        framesIndiana[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run1);
        framesIndiana[1] = Bitmap.createScaledBitmap(framesIndiana[1], proporcionX, proporcionY * 5, false);

        framesIndiana[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run2);
        framesIndiana[2] = Bitmap.createScaledBitmap(framesIndiana[2], proporcionX, proporcionY * 5, false);

        framesIndiana[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run3);
        framesIndiana[3] = Bitmap.createScaledBitmap(framesIndiana[3], proporcionX, proporcionY * 5, false);

        framesIndiana[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run4);
        framesIndiana[4] = Bitmap.createScaledBitmap(framesIndiana[4], proporcionX, proporcionY * 5, false);

        framesIndiana[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run5);
        framesIndiana[5] = Bitmap.createScaledBitmap(framesIndiana[5], proporcionX, proporcionY * 5, false);

        framesIndiana[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run6);
        framesIndiana[6] = Bitmap.createScaledBitmap(framesIndiana[6], proporcionX, proporcionY * 5, false);

        framesIndiana[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run7);
        framesIndiana[7] = Bitmap.createScaledBitmap(framesIndiana[7], proporcionX, proporcionY * 5, false);

        framesIndiana[8] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run8);
        framesIndiana[8] = Bitmap.createScaledBitmap(framesIndiana[8], proporcionX, proporcionY * 5, false);

        framesIndiana[9] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run9);
        framesIndiana[9] = Bitmap.createScaledBitmap(framesIndiana[9], proporcionX, proporcionY * 5, false);
    }

    public void mover() {
        if (System.currentTimeMillis() - tFrameAuxm > tiempoFrame) {
            indice++;
            if (indice >= framesIndiana.length) indice = 0;
            tFrameAuxm = System.currentTimeMillis();
        }
        frame = framesIndiana[indice];
    }

    public void dibujar(Canvas c) {
        c.drawBitmap(frame, proporcionX, posY*9, p);
    }

}
