package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Gota {
    int anchoGota, altoGota;
    //int velocidad;
    int proporcionX, proporcionY;
    Bitmap gotita;

    float posX, posY, velocidad;

    public Gota(Context context, int anchoPantalla, int altoPantalla, float velocidad) {
        this.anchoGota = anchoPantalla / 100;
        this.altoGota = altoPantalla / 80;
        this.velocidad = velocidad;
        gotita = BitmapFactory.decodeResource(context.getResources(), R.drawable.capa);
        gotita = Bitmap.createScaledBitmap(gotita, anchoPantalla, altoPantalla, false);
    }

}
