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
        utils = new Utils(context);

        // Frames caballero
        for (int i = 0; i < 10; i++) {
            framesCaballero[i] = utils.getBitmapFromAssets("cabarun/caba" + i + ".png");
            framesCaballero[i] = espejo(framesCaballero[i], true);
        }
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
        if (posX > 0 - frame.getWidth()) {
            c.drawBitmap(this.frame, posX, altoPantalla - proporcionY * 5, null);
        }
    }

    public Bitmap espejo(Bitmap imagen, Boolean horizontal) {
        Matrix matrix = new Matrix();
        if (horizontal) matrix.preScale(-1, 1);
        else matrix.preScale(1, -1);
        return Bitmap.createBitmap(imagen, 0, 0, imagen.getWidth(), imagen.getHeight(), matrix, false);
    }

}
