package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;

public class Yones extends Personaje {

    private Bitmap[] framesIndiana = new Bitmap[10]; // Array de carrera
    private Bitmap[] saltosIndiana = new Bitmap[10]; // Array de salto
    private Bitmap[] slidesIndiana = new Bitmap[10]; // Array de slides
    private Bitmap frame; // Frame a devolver a cada instante

    private Bitmap[] idle;
    private Bitmap[] idleEspejo;
    private Bitmap[] run;
    private Bitmap[] runEspejo;

    private int cont = 0; // Contador para reiniciar el bucle de imagenes
    private Paint p;
    private int posX, posY; // Posiciones vertical y horizontal del personaje en pantalla, X será estática
    private int anchoPantalla, altoPantalla; // Tamaños de pantalla que conseguimos mediante el constructor
    private int tiempoFrame = 20; // Tiempo que tardar en actualizar cada frames, en milisegundos
    private long tFrameAuxm = 0; // Tiempo auxiliar que ayuda a gestionar el cambio de frame
    private int indice = 0; // Asociado al array, para acceder a la posición del mismo
    private int proporcionX; // Divide la pantalla para adaptarnos a distintas resoluciones de pantalla
    private int proporcionY; // Divide la pantalla para adaptarnos a distintas resoluciones de pantalla

    public boolean seMueve = true;
    boolean animado = true;
    boolean avanza = true;
    boolean pulsado = false;
    public boolean enAvance = false;
    public boolean enRetroceso = false;

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
    public Yones(Context context, int posX, int posY, int anchoPantalla, int altoPantalla, int velocidad) {
        super(context, posX, posY, velocidad, anchoPantalla, altoPantalla);
        this.posX = posX;
        this.posY = posY;
        proporcionX = anchoPantalla / 18;
        proporcionY = altoPantalla / 9;
        this.velocidad = velocidad;

        //(Bitmap, width,height,bool)
        // Frames personaje principal, asociado a su imagen y relativo al tamaño proporcional de la pantalla

        idle[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.idle0);
        idle[0] = Bitmap.createScaledBitmap(idle[0], anchoPantalla / 9, anchoPantalla / 18 * 2, false);

        idle[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.idle1);
        idle[1] = Bitmap.createScaledBitmap(idle[1], anchoPantalla / 9, anchoPantalla / 18 * 2, false);

        idle[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.idle2);
        idle[2] = Bitmap.createScaledBitmap(idle[2], anchoPantalla / 9, anchoPantalla / 18 * 2, false);

        idle[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.idle3);
        idle[3] = Bitmap.createScaledBitmap(idle[3], anchoPantalla / 9, anchoPantalla / 18 * 2, false);

        idle[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.idle4);
        idle[4] = Bitmap.createScaledBitmap(idle[4], anchoPantalla / 9, anchoPantalla / 18 * 2, false);

        idle[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.idle5);
        idle[5] = Bitmap.createScaledBitmap(idle[5], anchoPantalla / 9, anchoPantalla / 18 * 2, false);

        idle[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.idle6);
        idle[6] = Bitmap.createScaledBitmap(idle[6], anchoPantalla / 9, anchoPantalla / 18 * 2, false);

        idle[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.idle7);
        idle[7] = Bitmap.createScaledBitmap(idle[7], anchoPantalla / 9, anchoPantalla / 18 * 2, false);

        idle[8] = BitmapFactory.decodeResource(context.getResources(), R.drawable.idle8);
        idle[8] = Bitmap.createScaledBitmap(idle[8], anchoPantalla / 9, altoPantalla / 18 * 2, false);

        idle[9] = BitmapFactory.decodeResource(context.getResources(), R.drawable.idle9);
        idle[9] = Bitmap.createScaledBitmap(idle[9], anchoPantalla / 9, altoPantalla / 18 * 2, false);

        // Frames de indiana, en la acción de salto
        run[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run0);
        run[0] = Bitmap.createScaledBitmap(run[0], anchoPantalla / 9, altoPantalla / 18 * 2, false);

        run[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run1);
        run[1] = Bitmap.createScaledBitmap(run[1], anchoPantalla / 9, altoPantalla / 18 * 2, false);

        run[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run2);
        run[2] = Bitmap.createScaledBitmap(run[2], anchoPantalla / 9, altoPantalla / 18 * 2, false);

        run[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run3);
        run[3] = Bitmap.createScaledBitmap(run[3], anchoPantalla / 9, altoPantalla / 18 * 2, false);

        run[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run4);
        run[4] = Bitmap.createScaledBitmap(run[4], anchoPantalla / 9, altoPantalla / 18 * 2, false);

        run[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run5);
        run[5] = Bitmap.createScaledBitmap(run[5], anchoPantalla / 9, altoPantalla / 18 * 2, false);

        run[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run6);
        run[6] = Bitmap.createScaledBitmap(run[6], anchoPantalla / 9, altoPantalla / 18 * 2, false);

        run[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run7);
        run[7] = Bitmap.createScaledBitmap(run[7], anchoPantalla / 9, altoPantalla / 18 * 2, false);

        run[8] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run8);
        run[8] = Bitmap.createScaledBitmap(run[8], anchoPantalla / 9, altoPantalla / 18 * 2, false);

        run[9] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run9);
        run[9] = Bitmap.createScaledBitmap(run[9], anchoPantalla / 9, altoPantalla / 18 * 2, false);

        this.posY = proporcionY * 6 + proporcionY + 2;
    }

    public void cambiaFrame() {
        if (System.currentTimeMillis() - tFrameAuxm > tiempoFrame) {
            indice++;
            if (indice >= idle.length) {
                indice = 0;
            }
            tFrameAuxm = System.currentTimeMillis();
        }
    }

    public void mover() {
//         Gestiona el cambio de frame
//        if (seMueve) {
//            if (enAvance) {
//                posX += velocidad;
//                if (posX > this.anchoPantalla - run[indice].getWidth()) {
//                    posX = this.anchoPantalla - run[indice].getWidth();
//                    seMueve = false;
//                }
//                //posX = Math.min(posX, PantallaInicioView.anchoPantalla - framesc[indice].getWidth());
//            }
//            if (enRetroceso) {
//                posX -= velocidad;
//                //posX = Math.max(posX, 0);
//                if (posX < 0) {
//                    posX = 0;
//                    seMueve = false;
//                }
//            }
//            tMoveAux = System.currentTimeMillis();
//        }
    }

    public void dibujar(Canvas c) {
        Log.d("Dibuja", "Dibuja a DrYones");
        if (enAvance) {
            if (seMueve) {
                c.drawBitmap(run[indice], posX, proporcionY * 6, p);
            } else {
                c.drawBitmap(idle[indice], posX, proporcionY * 6, p);
            }
        }
        if (enRetroceso) {
            if (seMueve) {
                c.drawBitmap(runEspejo[indice], posX, proporcionY * 6, p);
            } else {
                c.drawBitmap(idleEspejo[indice], posX, proporcionY * 6, p);
            }
        }
//        c.drawBitmap(run[indice], anchoPantalla / 2, altoPantalla / 2, p);
        c.drawBitmap(frame, anchoPantalla / 2, altoPantalla / 2, p);

    }


}
