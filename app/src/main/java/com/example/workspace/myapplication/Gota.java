package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Gota {
    int anchoGota, altoGota;
    int proporcionX, proporcionY;
    Bitmap gotita;

    public Gota(Context context, int anchoPantalla, int altoPantalla) {
        this.anchoGota = anchoPantalla / 100;
        this.altoGota = altoPantalla / 80;
        gotita = BitmapFactory.decodeResource(context.getResources(), R.drawable.capa);
        gotita = Bitmap.createScaledBitmap(gotita, anchoPantalla, altoPantalla, false);
    }

}
