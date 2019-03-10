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

    /**
     * Contructor que inicializa las propiedas de la clase 'Latigo'
     *
     * @param context       Contexto de la aplicación
     * @param posX          Posición en el eje X del látigo
     * @param posY          Posicion en el eje Y del látigo
     * @param anchoPantalla Ancho de pantalla del dispositivo
     * @param altoPantalla  Alto de pantalla del dispositivo
     */
    public Latigo(Context context, int posX, int posY, int anchoPantalla, int altoPantalla) {
        this.posX = posX;
        this.posY = posY;
        Log.i("latigo", "posy " + posY);
        this.altoPantalla = altoPantalla;
        utils = new Utils(context);

        proporcionAncho = anchoPantalla / 9;
        proporcionAlto = altoPantalla / 18;

        latigo = utils.getBitmapFromAssets("varios/latigo.png");
        latigo = Bitmap.createScaledBitmap(latigo, proporcionAncho / 2, proporcionAlto / 2, false);
        /*javi */
        paint = new Paint();
        /*javi */
        paint.setColor(Color.RED);
    }

    /**
     * Dibuja el objeto de la clase látigo
     *
     * @param c Es el canvas asociado a la aplicación
     */
    public void dibuja(Canvas c) {
        c.drawBitmap(latigo, posX, posY, null);
//        c.drawRect(rectLatigo, paint);
    }

    /**
     * Mueve al objeto látigo en posición horizontal en dirección 'X' negativo y 'X' positivo
     */
    public void move() {
        if (DrYones.enAvance) {
            posX += velocidadLatigo;
        } else if (DrYones.enRetroceso) {
            posX -= velocidadLatigo;
        }
        this.setRectangulo();
    }

    /**
     *
     */
    public void setRectangulo() {
        paint.setColor(Color.GREEN);
        rectLatigo = new Rect(posX, posY, posX + latigo.getWidth(), posY + latigo.getHeight());
    }

    public int getX() {
        return posX;
    }

    /*javi */
    public void setPosX(int posX) {
        /*javi */
        this.posX = posX;
        /*javi */
    }

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
