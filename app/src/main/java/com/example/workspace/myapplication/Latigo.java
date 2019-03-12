package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class Latigo {

    /**
     * Contexto de la aplicacion
     */
    Context context; // contexto de la aplicación
    /**
     * Objeto de la clase utils
     */
    private Utils utils;
    /**
     * Bitmap estatico para el objeto
     */
    private Bitmap latigo;
    /**
     * Posicion eje X del personaje
     */
    private int posX;
    /**
     * Posicion eje Y del personaje
     */
    private int posY;
    /**
     * Velocidad de movimiento del latigo
     */
    private int velocidadLatigo = 13;
    /**
     * Tamaños de pantalla del dispositivo
     */
    private int anchoPantalla, altoPantalla;
    /**
     * Proporciones para el dibujado adaptativo
     */
    int proporcionAncho, proporcionAlto;
    /**
     * Hitbox del látigo
     */
    public Rect rectLatigo;
    /**
     * Pincel
     */
    Paint paint; // pincel

    /**
     * Contructor que inicializa las propiedas de la clase
     *
     * @param context       Contexto de la aplicacion
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
        paint = new Paint();
        paint.setColor(Color.RED);
    }

    /**
     * Rutina de dibujo
     *
     * @param c Es el canvas asociado a la aplicación
     */
    public void dibuja(Canvas c) {
        c.drawBitmap(latigo, posX, posY, null);
    }

    /**
     * Mueve al objeto latigo en posicion horizontal en direccion 'X' negativo y 'X' positivo
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
     * Comprueba mediante booleanas, si se está moviendo o parado, ademas de la direccion en la que se muestra
     */
    public void setRectangulo() {
        paint.setColor(Color.GREEN);
        rectLatigo = new Rect(posX, posY, posX + latigo.getWidth(), posY + latigo.getHeight());
    }

    public int getX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
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
