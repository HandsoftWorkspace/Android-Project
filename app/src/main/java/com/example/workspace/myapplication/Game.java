package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

public class Game extends Escena{
    // Variables
    private static BackgroundB bg1, bg2;

    public Game(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);
        fondo = BitmapFactory.decodeResource(context.getResources(), R.drawable.fondo);
        fondo = Bitmap.createScaledBitmap(fondo, anchoPantalla, altoPantalla, false);

        bg1 = new BackgroundB(0, 0);
        bg2 = new BackgroundB(2160, 0);
    }

    public void dibujar(Canvas c){
        try{

        c.drawBitmap(fondo,0,0,null);
        super.dibujar(c);
        }catch (Exception e){
            Log.i("Error al dibujar",e.getLocalizedMessage());
        }
    }

    // Juego prueba

}
