package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;

public class Caballero extends Personaje {
    Context context;
    int ancho, alto;
    int velocidad;

    private int posX, posY; // Posiciones vertical y horizontal del personaje en pantalla, X será estática
    Bitmap[] framesCaballero = new Bitmap[10]; // Secuencia de fotogramas para conseguir el movimiento del caballero

    private Bitmap frame; // Frame a devolver a cada instante

    private int anchoPantalla, altoPantalla; // Tamaños de pantalla que conseguimos mediante el constructor
    private int tiempoFrame = 20; // Tiempo que tardar en actualizar cada frames, en milisegundos
    private long tFrameAuxm = 0; // Tiempo auxiliar que ayuda a gestionar el cambio de frame
    private int indice = 0; // Asociado al array, para acceder a la posición del mismo
    private int proporcionX; // Divide la pantalla para adaptarnos a distintas resoluciones de pantalla
    private int proporcionY; // Divide la pantalla para adaptarnos a distintas resoluciones de pantalla

    //public Personaje(Context context, int posX, int posY, int velocidad, int anchoPantalla, int altoPantalla) {

    public Caballero(Context context, int posX, int posY, int velocidad, int anchoPantalla, int altoPantalla) {
        super(context, posX, posY, velocidad, anchoPantalla, altoPantalla);
        this.context = context;
        this.posX = posX;
        this.posY = posY;
        this.ancho = anchoPantalla;
        this.alto = altoPantalla;
        proporcionX = anchoPantalla / 18;
        proporcionY = altoPantalla / 9;

        // Frames caballero
        framesCaballero[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.caba0);
        framesCaballero[0] = Bitmap.createScaledBitmap(framesCaballero[0], proporcionX, proporcionY * 2, false);
        framesCaballero[0] = espejo(framesCaballero[0], true);

        framesCaballero[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.caba1);
        framesCaballero[1] = Bitmap.createScaledBitmap(framesCaballero[1], proporcionX, proporcionY * 2, false);
        framesCaballero[1] = espejo(framesCaballero[1], true);

        framesCaballero[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.caba2);
        framesCaballero[2] = Bitmap.createScaledBitmap(framesCaballero[2], proporcionX, proporcionY * 2, false);
        framesCaballero[2] = espejo(framesCaballero[2], true);

        framesCaballero[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.caba3);
        framesCaballero[3] = Bitmap.createScaledBitmap(framesCaballero[3], proporcionX, proporcionY * 2, false);
        framesCaballero[3] = espejo(framesCaballero[3], true);

        framesCaballero[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.caba4);
        framesCaballero[4] = Bitmap.createScaledBitmap(framesCaballero[4], proporcionX, proporcionY * 2, false);
        framesCaballero[4] = espejo(framesCaballero[4], true);

        framesCaballero[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.caba5);
        framesCaballero[5] = Bitmap.createScaledBitmap(framesCaballero[5], proporcionX, proporcionY * 2, false);
        framesCaballero[5] = espejo(framesCaballero[5], true);

        framesCaballero[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.caba6);
        framesCaballero[6] = Bitmap.createScaledBitmap(framesCaballero[6], proporcionX, proporcionY * 2, false);
        framesCaballero[6] = espejo(framesCaballero[6], true);

        framesCaballero[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.caba7);
        framesCaballero[7] = Bitmap.createScaledBitmap(framesCaballero[7], proporcionX, proporcionY * 2, false);
        framesCaballero[7] = espejo(framesCaballero[7], true);

        framesCaballero[8] = BitmapFactory.decodeResource(context.getResources(), R.drawable.caba8);
        framesCaballero[8] = Bitmap.createScaledBitmap(framesCaballero[8], proporcionX, proporcionY * 2, false);
        framesCaballero[8] = espejo(framesCaballero[8], true);

        framesCaballero[9] = BitmapFactory.decodeResource(context.getResources(), R.drawable.caba9);
        framesCaballero[9] = Bitmap.createScaledBitmap(framesCaballero[9], proporcionX, proporcionY * 2, false);
        framesCaballero[9] = espejo(framesCaballero[9], true);
    }

    public void mover() {
        if (System.currentTimeMillis() - tFrameAuxm > tiempoFrame) {
            indice++;
            this.posX -= velocidad;
            if (indice >= framesCaballero.length) indice = 0;
            tFrameAuxm = System.currentTimeMillis();
        }
        this.frame = framesCaballero[indice];
    }

    public void dibujar(Canvas c) {
        c.drawBitmap(this.frame, posX, (proporcionY * 6) + proporcionY / 2, null);
    }

    public Bitmap espejo(Bitmap imagen, Boolean horizontal) {
        Matrix matrix = new Matrix();
        if (horizontal) matrix.preScale(-1, 1);
        else matrix.preScale(1, -1);
        return Bitmap.createBitmap(imagen, 0, 0, imagen.getWidth(), imagen.getHeight(), matrix, false);
    }

}
