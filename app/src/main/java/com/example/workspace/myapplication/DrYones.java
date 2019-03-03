package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;

public class DrYones extends Personaje {
    //    private Bitmap[] framesc, framesic, framesp, framesip;
    Bitmap frame;
    private int posX, posY;
    private int anchoPantalla, altoPantalla;
    private int proporcionAncho, proporcionAlto;

    private int velocidad;
    private int tiempoFrame = 100;
    private int tiempoMove = 50;
    private long tFrameAuxm = 0, tMoveAux = 0;
    private int indice = 0;
    private int alfa = 230;

    private Paint p;

    public boolean seMueve = true;
    boolean animado = true;
    boolean avanza = true;
    boolean pulsado = false;
    public boolean enAvance = false;
    public boolean enRetroceso = false;

    private Bitmap[] idle;
    private Bitmap[] idleEspejo;
    private Bitmap[] run;
    private Bitmap[] runEspejo;

    Context context;
    Utils utils = new Utils(context);

    /**
     * @param posX
     * @param posY
     * @param anchoPantalla
     * @param altoPantalla
     * @param velocidad
     */
    public DrYones(Context context, int posX, int posY, int anchoPantalla, int altoPantalla, int velocidad) {
        super(context, posX, posY, velocidad, anchoPantalla, altoPantalla);
        this.context = context;
        this.posX = posX;
        this.posY = posY;
        this.velocidad = velocidad;
        this.anchoPantalla = anchoPantalla;
        this.altoPantalla = altoPantalla;

        utils = new Utils(context);

        idle = new Bitmap[10];
        idleEspejo = new Bitmap[10];
        run = new Bitmap[10];
        runEspejo = new Bitmap[10];

        this.p = new Paint();
        p.setAlpha(alfa);

        // bitmaps idle
        for (int i = 0; i < 10; i++) {
            idle[i] = utils.getBitmapFromAssets("idle/" + "idle" + i + ".png");
            idle[i] = Bitmap.createScaledBitmap(idle[i], anchoPantalla / 18, altoPantalla / 9 * 2, false);
        }
        // idle espejo
        for (int j = 0; j < idle.length; j++) {
            idleEspejo[j] = espejo(idle[j], true);
            idleEspejo[j] = Bitmap.createScaledBitmap(idleEspejo[j], anchoPantalla / 18, altoPantalla / 9 * 2, false);
        }

        // bitmaps run
        for (int k = 0; k < 10; k++) {
            run[k] = utils.getBitmapFromAssets("run/" + "run" + k + ".png");
            run[k] = Bitmap.createScaledBitmap(run[k], anchoPantalla / 18, altoPantalla / 9 * 2, false);
        }
        // run espejo
        for (int l = 0; l < run.length; l++) {
            runEspejo[l] = espejo(run[l], true);
            runEspejo[l] = Bitmap.createScaledBitmap(runEspejo[l], anchoPantalla / 18, altoPantalla / 9 * 2, false);
        }
    }

    /**
     * @param c
     */
    public void dibuja(Canvas c) {
        Log.d("Dibuja", "Dibuja a DrYones");
        if (enAvance) {
            if (seMueve) {
                c.drawBitmap(run[indice], posX, 0 - proporcionAlto, p);
            } else {
                c.drawBitmap(idle[indice], posX, 0 - proporcionAlto, p);
            }
        }
        if (enRetroceso) {
            if (seMueve) {
                c.drawBitmap(runEspejo[indice], posX, altoPantalla / 2 + proporcionAlto, p);
            } else {
                c.drawBitmap(idleEspejo[indice], posX, altoPantalla / 2 + proporcionAlto, p);
            }
        }
//        c.drawBitmap(run[indice], posX, proporcionAlto * 8, p);
//        c.drawBitmap(frame, anchoPantalla / 2, altoPantalla / 2, p);
    }

    /**
     *
     */
    public void cambiaFrame() {
        if (System.currentTimeMillis() - tFrameAuxm > tiempoFrame) {
            indice++;
            if (indice >= idle.length) {
                indice = 0;
            }
            tFrameAuxm = System.currentTimeMillis();
        }
    }

    /**
     *
     */
    public Bitmap move() {
//        if (System.currentTimeMillis() - tFrameAuxm > tiempoFrame) {
//            indice++;
//            if (indice >= idle.length) {
//                indice = 0;
//            }
//            tFrameAuxm = System.currentTimeMillis();
//        }
        if (seMueve && enAvance) {
            if (enAvance) {
                posX += velocidad;
                if (posX > this.anchoPantalla - run[indice].getWidth()) {
                    posX = this.anchoPantalla - run[indice].getWidth();
                    seMueve = false;
                }
                //posX = Math.min(posX, PantallaInicioView.anchoPantalla - framesc[indice].getWidth());
            }
//            tMoveAux = System.currentTimeMillis();
        }
        if (seMueve && enRetroceso) {
            posX -= velocidad;
//                posX = Math.max(posX, 0);
            if (posX < 0) {
                posX = 0;
                seMueve = false;
            }
        }
        return null;
    }

    /**
     * @param pulsado
     */
    public void isPulsado(boolean pulsado) {
//    public void isPulsado(int x, int y) {
        if (!seMueve && pulsado) {
            avanza = !avanza;
            pulsado = false;
        } else if (!seMueve && !pulsado) {
            pulsado = true;
            seMueve = true;
        } else {
            seMueve = false;
            pulsado = true;
        }
    }

    /**
     * @param imagen
     * @param horizontal
     * @return
     */
    public Bitmap espejo(Bitmap imagen, Boolean horizontal) {
        Matrix matrix = new Matrix();
        if (horizontal) matrix.preScale(-1, 1);
        else matrix.preScale(1, -1);
        return Bitmap.createBitmap(imagen, 0, 0, imagen.getWidth(), imagen.getHeight(), matrix, false);
    }

    public void setAlfa(int alfa) {
        this.alfa = alfa;
        p.setAlpha(alfa);
    }

    public long gettFrameAuxm() {
        return tFrameAuxm;
    }

    public void settFrameAuxm(long tFrameAuxm) {
        this.tFrameAuxm = tFrameAuxm;
    }

    public long gettMoveAux() {
        return tMoveAux;
    }

    public void settMoveAux(long tMoveAux) {
        this.tMoveAux = tMoveAux;
    }

    public boolean isSeMueve() {
        return seMueve;
    }

    public void setSeMueve(boolean seMueve) {
        this.seMueve = seMueve;
    }

    public boolean isAnimado() {
        return animado;
    }

    public void setAnimado(boolean animado) {
        this.animado = animado;
    }

    public boolean isAvanza() {
        return avanza;
    }

    public void setAvanza(boolean avanza) {
        this.avanza = avanza;
    }

    public int getAlfa() {
        return alfa;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public int getTiempoFrame() {
        return tiempoFrame;
    }

    public void setTiempoFrame(int tiempoFrame) {
        this.tiempoFrame = tiempoFrame;
    }

    public int getTiempoMove() {
        return tiempoMove;
    }

    public void setTiempoMove(int tiempoMove) {
        this.tiempoMove = tiempoMove;
    }

}
