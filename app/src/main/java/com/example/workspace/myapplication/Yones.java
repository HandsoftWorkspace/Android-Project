package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

import static com.example.workspace.myapplication.Game.enSalto;
import static com.example.workspace.myapplication.Game.enSlide;

public class Yones extends Personaje {

    private Bitmap[] framesIndiana = new Bitmap[10]; // Array de carrera
    private Bitmap[] saltosIndiana = new Bitmap[10]; // Array de salto
    private Bitmap[] slidesIndiana = new Bitmap[10]; // Array de slides
    private Bitmap frame; // Frame a devolver a cada instante

    private int cont = 0; // Contador para reiniciar el bucle de imagenes
    private Paint p;
    private int posX, posY; // Posiciones vertical y horizontal del personaje en pantalla, X será estática
    private int anchoPantalla, altoPantalla; // Tamaños de pantalla que conseguimos mediante el constructor
    private int tiempoFrame = 20; // Tiempo que tardar en actualizar cada frames, en milisegundos
    private long tFrameAuxm = 0; // Tiempo auxiliar que ayuda a gestionar el cambio de frame
    private int indice = 0; // Asociado al array, para acceder a la posición del mismo
    private int proporcionX; // Divide la pantalla para adaptarnos a distintas resoluciones de pantalla
    private int proporcionY; // Divide la pantalla para adaptarnos a distintas resoluciones de pantalla

    // Box del personaje
    public Rect rectYones;

    /**
     * @param context
     * @param posX
     * @param posY
     * @param velocidad
     * @param anchoPantalla
     * @param altoPantalla
     */
    public Yones(Context context, int posX, int posY, int velocidad, int anchoPantalla, int altoPantalla) {

        this.posX = posX;
        this.posY = posY;
        proporcionX = anchoPantalla / 18;
        proporcionY = altoPantalla / 9;

        //(Bitmap, width,height,bool)
        // Frames personaje principal, asociado a su imagen y relativo al tamaño proporcional de la pantalla
        framesIndiana[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run0);
        framesIndiana[0] = Bitmap.createScaledBitmap(framesIndiana[0], proporcionX, proporcionY * 2, false);

        framesIndiana[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run1);
        framesIndiana[1] = Bitmap.createScaledBitmap(framesIndiana[1], proporcionX, proporcionY * 2, false);

        framesIndiana[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run2);
        framesIndiana[2] = Bitmap.createScaledBitmap(framesIndiana[2], proporcionX, proporcionY * 2, false);

        framesIndiana[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run3);
        framesIndiana[3] = Bitmap.createScaledBitmap(framesIndiana[3], proporcionX, proporcionY * 2, false);

        framesIndiana[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run4);
        framesIndiana[4] = Bitmap.createScaledBitmap(framesIndiana[4], proporcionX, proporcionY * 2, false);

        framesIndiana[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run5);
        framesIndiana[5] = Bitmap.createScaledBitmap(framesIndiana[5], proporcionX, proporcionY * 2, false);

        framesIndiana[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run6);
        framesIndiana[6] = Bitmap.createScaledBitmap(framesIndiana[6], proporcionX, proporcionY * 2, false);

        framesIndiana[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run7);
        framesIndiana[7] = Bitmap.createScaledBitmap(framesIndiana[7], proporcionX, proporcionY * 2, false);

        framesIndiana[8] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run8);
        framesIndiana[8] = Bitmap.createScaledBitmap(framesIndiana[8], proporcionX, proporcionY * 2, false);

        framesIndiana[9] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run9);
        framesIndiana[9] = Bitmap.createScaledBitmap(framesIndiana[9], proporcionX, proporcionY * 2, false);

        // Frames de indiana, en la acción de salto
        saltosIndiana[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.jump0);
        saltosIndiana[0] = Bitmap.createScaledBitmap(saltosIndiana[0], proporcionX, proporcionY * 2, false);

        saltosIndiana[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.jump1);
        saltosIndiana[1] = Bitmap.createScaledBitmap(saltosIndiana[1], proporcionX, proporcionY * 2, false);

        saltosIndiana[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.jump2);
        saltosIndiana[2] = Bitmap.createScaledBitmap(saltosIndiana[2], proporcionX, proporcionY * 2, false);

        saltosIndiana[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.jump3);
        saltosIndiana[3] = Bitmap.createScaledBitmap(saltosIndiana[3], proporcionX, proporcionY * 2, false);

        saltosIndiana[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.jump4);
        saltosIndiana[4] = Bitmap.createScaledBitmap(saltosIndiana[4], proporcionX, proporcionY * 2, false);

        saltosIndiana[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.jump5);
        saltosIndiana[5] = Bitmap.createScaledBitmap(saltosIndiana[5], proporcionX, proporcionY * 2, false);

        saltosIndiana[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.jump6);
        saltosIndiana[6] = Bitmap.createScaledBitmap(saltosIndiana[6], proporcionX, proporcionY * 2, false);

        saltosIndiana[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.jump7);
        saltosIndiana[7] = Bitmap.createScaledBitmap(saltosIndiana[7], proporcionX, proporcionY * 2, false);

        saltosIndiana[8] = BitmapFactory.decodeResource(context.getResources(), R.drawable.jump8);
        saltosIndiana[8] = Bitmap.createScaledBitmap(saltosIndiana[8], proporcionX, proporcionY * 2, false);

        saltosIndiana[9] = BitmapFactory.decodeResource(context.getResources(), R.drawable.jump9);
        saltosIndiana[9] = Bitmap.createScaledBitmap(saltosIndiana[9], proporcionX, proporcionY * 2, false);

        // Arrays de slides indiana
        slidesIndiana[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.slide0);
        slidesIndiana[0] = Bitmap.createScaledBitmap(slidesIndiana[0], proporcionX, proporcionY * 2, false);

        slidesIndiana[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.slide1);
        slidesIndiana[1] = Bitmap.createScaledBitmap(slidesIndiana[1], proporcionX, proporcionY * 2, false);

        slidesIndiana[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.slide2);
        slidesIndiana[2] = Bitmap.createScaledBitmap(slidesIndiana[2], proporcionX, proporcionY * 2, false);

        slidesIndiana[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.slide3);
        slidesIndiana[3] = Bitmap.createScaledBitmap(slidesIndiana[3], proporcionX, proporcionY * 2, false);

        slidesIndiana[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.slide4);
        slidesIndiana[4] = Bitmap.createScaledBitmap(slidesIndiana[4], proporcionX, proporcionY * 2, false);

        slidesIndiana[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.slide5);
        slidesIndiana[5] = Bitmap.createScaledBitmap(slidesIndiana[5], proporcionX, proporcionY * 2, false);

        slidesIndiana[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.slide6);
        slidesIndiana[6] = Bitmap.createScaledBitmap(slidesIndiana[6], proporcionX, proporcionY * 2, false);

        slidesIndiana[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.slide7);
        slidesIndiana[7] = Bitmap.createScaledBitmap(slidesIndiana[7], proporcionX, proporcionY * 2, false);

        slidesIndiana[8] = BitmapFactory.decodeResource(context.getResources(), R.drawable.slide8);
        slidesIndiana[8] = Bitmap.createScaledBitmap(slidesIndiana[8], proporcionX, proporcionY * 2, false);

        slidesIndiana[9] = BitmapFactory.decodeResource(context.getResources(), R.drawable.slide9);
        slidesIndiana[9] = Bitmap.createScaledBitmap(slidesIndiana[9], proporcionX, proporcionY * 2, false);
    }

    public void mover() {
        if (enSalto) {
            for (int i = 0; i < saltosIndiana.length; i++) {
//                if (System.currentTimeMillis() - tFrameAuxm > tiempoFrame) {
//                    //this.posY -= 1; TODO
//                    indice++;
//                    if (indice >= saltosIndiana.length) indice = 0;
//                    this.posY = proporcionY * 8;
//                    tFrameAuxm = System.currentTimeMillis();
//                }
                frame = saltosIndiana[indice];
            }
            enSalto = false;
            indice = 0;
        } else if (enSlide) {
            for (int i = 0; i < slidesIndiana.length; i++) {
                if (System.currentTimeMillis() - tFrameAuxm > tiempoFrame) {
                    indice++;
                    if (indice >= slidesIndiana.length) indice = 0;
                    tFrameAuxm = System.currentTimeMillis();
                }
                frame = slidesIndiana[indice];
            }
            enSlide = false;
            indice = 0;
        } else if (enSalto == false && enSlide == false) {
            if (System.currentTimeMillis() - tFrameAuxm > tiempoFrame) {
                indice++;
                if (indice >= framesIndiana.length) indice = 0;
                tFrameAuxm = System.currentTimeMillis();
            }
            frame = framesIndiana[indice];
        }
    }

    public void moverRun() {
        if (System.currentTimeMillis() - tFrameAuxm > tiempoFrame) {
            indice++;
            if (indice >= framesIndiana.length) indice = 0;
            tFrameAuxm = System.currentTimeMillis();
        }
        frame = framesIndiana[indice];
    }

    //
    public void moverSalto() {
        for (int i = 0; i <saltosIndiana.length; i++) {
            if (System.currentTimeMillis() - tFrameAuxm > tiempoFrame) {
                indice++;
                if (indice >= saltosIndiana.length) indice = 0;
                tFrameAuxm = System.currentTimeMillis();
            }
            frame = saltosIndiana[indice];
        }
    }

    public void moverSlide() {
        for (int i = 0; i < slidesIndiana.length; i++) {

            if (System.currentTimeMillis() - tFrameAuxm > tiempoFrame) {
                indice++;
                if (indice >= slidesIndiana.length) indice = 0;
                tFrameAuxm = System.currentTimeMillis();
            }
            frame = slidesIndiana[indice];
        }
    }

    public void dibujar(Canvas c) {
        if (enSlide) {
            c.drawBitmap(frame, proporcionX, posY * 7, p);
        } else if (enSalto) {
//            for (int i = 0; i < saltosIndiana.length; i++) {
//                c.drawBitmap(saltosIndiana[i], proporcionX, posY, p);
//            }
            c.drawBitmap(frame, proporcionX, posY * ((int) 5), p);
        } else if (!enSlide) {
            c.drawBitmap(frame, proporcionX, (proporcionY * 6) + proporcionY / 2, p);
        }
    }


}
