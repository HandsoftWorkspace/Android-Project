package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class Latigo {
    Context context;
    private Utils utils;
    private Bitmap latigo, latigoEspejo;
    private int posX;
    private int posY;
    private int velocidadLatigo = 13;
    private int anchoPantalla, altoPantalla;
    int proporcionAncho, proporcionAlto;
    public Rect rectLatigo;

    Paint paint = new Paint();

    public Latigo(Context context, int posX, int posY, int anchoPantalla, int altoPantalla) {
        this.posX = posX;
        this.posY = posY;
        Log.i("latigo", "posy "+posY);
        this.altoPantalla = altoPantalla;
        utils = new Utils(context);

        proporcionAncho = anchoPantalla / 9;
        proporcionAlto = altoPantalla / 18;

        latigo = utils.getBitmapFromAssets("varios/latigo.png");
        latigo = Bitmap.createScaledBitmap(latigo, proporcionAncho / 2, proporcionAlto / 2, false);
        /*javi */paint=new Paint();
        /*javi */paint.setColor(Color.RED);
    }

    public void dibuja(Canvas c) {

        c.drawBitmap(latigo, posX, posY, null);
        /*javi */c.drawRect(rectLatigo,paint);

    }

    public void move() {
        if (DrYones.enAvance) {
            posX += velocidadLatigo;
        } else if (DrYones.enRetroceso) {
            posX -= velocidadLatigo;
        }
        this.setRectangulo();
    }

    public void setRectangulo() {
//        float x = posicion.x;
//        float y = posicion.y;
//        float x = posX;
//        float y = posY;
        paint.setColor(Color.GREEN);
   /*javi*/     rectLatigo = new Rect(posX, posY, posX + latigo.getWidth(), posY+latigo.getHeight());
    }

    public int getX() {
        return posX;
    }

    /*javi */  public void setPosX(int posX) {
        /*javi */this.posX = posX;
        /*javi */}

    public int getY() {
        return posY;
    }

    public int getWidth() {
        return latigo.getWidth();
    }

    public int getHeight() {
        return latigo.getHeight();
    }

    public int getVelocidadLatigo() {
        return velocidadLatigo;
    }

    public void setVelocidadLatigo(int velodidad) {
        this.velocidadLatigo = velodidad;
    }

}
