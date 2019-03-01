package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

public class DrYones {
    private Bitmap[] framesc, framesic, framesp, framesip;
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
    Utils utils = new Utils();

    /**
     * @param posX
     * @param posY
     * @param anchoPantalla
     * @param altoPantalla
     * @param velocidad
     */
    public DrYones(int posX, int posY, int proporcionX, int proporcionY, int anchoPantalla, int altoPantalla, int velocidad) {
        this.posX = posX;
        this.posY = posY;
        this.velocidad = velocidad;
        this.anchoPantalla = anchoPantalla;
        this.altoPantalla = altoPantalla;
        this.proporcionAncho = proporcionX;
        this.proporcionAlto = proporcionY;

        idle = new Bitmap[10];
        idleEspejo = new Bitmap[10];
        run = new Bitmap[10];
        runEspejo = new Bitmap[10];

        this.p = new Paint();
        p.setAlpha(alfa);

        idle[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.idle0);
        idle[0] = Bitmap.createScaledBitmap(idle[0], proporcionX, proporcionY * 2, false);

        idle[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.idle1);
        idle[1] = Bitmap.createScaledBitmap(idle[1], proporcionX, proporcionY * 2, false);

        idle[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.idle2);
        idle[2] = Bitmap.createScaledBitmap(idle[2], proporcionX, proporcionY * 2, false);

        idle[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.idle3);
        idle[3] = Bitmap.createScaledBitmap(idle[3], proporcionX, proporcionY * 2, false);

        idle[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.idle4);
        idle[4] = Bitmap.createScaledBitmap(idle[4], proporcionX, proporcionY * 2, false);

        idle[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.idle5);
        idle[5] = Bitmap.createScaledBitmap(idle[5], proporcionX, proporcionY * 2, false);

        idle[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.idle6);
        idle[6] = Bitmap.createScaledBitmap(idle[6], proporcionX, proporcionY * 2, false);

        idle[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.idle7);
        idle[7] = Bitmap.createScaledBitmap(idle[7], proporcionX, proporcionY * 2, false);

        idle[8] = BitmapFactory.decodeResource(context.getResources(), R.drawable.idle8);
        idle[8] = Bitmap.createScaledBitmap(idle[8], proporcionX, proporcionY * 2, false);

        idle[9] = BitmapFactory.decodeResource(context.getResources(), R.drawable.idle9);
        idle[9] = Bitmap.createScaledBitmap(idle[9], proporcionX, proporcionY * 2, false);

//        for (int i = 0; i < idle.length; i++) {
//            idleEspejo[i] = espejo(idle[i], true);
//        }

        run[0] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run0);
        run[0] = Bitmap.createScaledBitmap(run[0], proporcionX, proporcionY * 2, false);

        run[1] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run1);
        run[1] = Bitmap.createScaledBitmap(run[1], proporcionX, proporcionY * 2, false);

        run[2] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run2);
        run[2] = Bitmap.createScaledBitmap(run[2], proporcionX, proporcionY * 2, false);

        run[3] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run3);
        run[3] = Bitmap.createScaledBitmap(run[3], proporcionX, proporcionY * 2, false);

        run[4] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run4);
        run[4] = Bitmap.createScaledBitmap(run[4], proporcionX, proporcionY * 2, false);

        run[5] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run5);
        run[5] = Bitmap.createScaledBitmap(run[5], proporcionX, proporcionY * 2, false);

        run[6] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run6);
        run[6] = Bitmap.createScaledBitmap(run[6], proporcionX, proporcionY * 2, false);

        run[7] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run7);
        run[7] = Bitmap.createScaledBitmap(run[7], proporcionX, proporcionY * 2, false);

        run[8] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run8);
        run[8] = Bitmap.createScaledBitmap(run[8], proporcionX, proporcionY * 2, false);

        run[9] = BitmapFactory.decodeResource(context.getResources(), R.drawable.run9);
        run[9] = Bitmap.createScaledBitmap(run[9], proporcionX, proporcionY * 2, false);

//        for (int i = 0; i < run.length; i++) {
//            runEspejo[i] = espejo(run[i], true);
//        }

    }

    /**
     * @param c
     */
    public void dibuja(Canvas c) {
        if (enAvance) {
            if (seMueve) {
                c.drawBitmap(run[indice], posX, posY, p);
            } else {
                c.drawBitmap(idle[indice], posX, posY, p);
            }
        }  if (seMueve) {
            c.drawBitmap(runEspejo[indice], posX, posY, p);
        } else {
            c.drawBitmap(idleEspejo[indice], posX, posY, p);
        }
    }

    /**
     *
     */
    public void cambiaFrame() {
        if (System.currentTimeMillis() - tFrameAuxm > tiempoFrame) {
            indice++;
            if (indice >= framesc.length) {
                indice = 0;
            }
            tFrameAuxm = System.currentTimeMillis();
        }
    }

    /**
     *
     */
    public void move() {
        if (seMueve) {
            if (System.currentTimeMillis() - tMoveAux > tiempoMove) {
                if (enAvance) {
                    posX += velocidad;
                    if (posX > this.anchoPantalla - framesc[indice].getWidth()) {
                        posX = this.anchoPantalla - framesc[indice].getWidth();
                        seMueve = false;
                    }
                    //posX = Math.min(posX, PantallaInicioView.anchoPantalla - framesc[indice].getWidth());
                }
                if (enRetroceso) {
                    posX -= velocidad;
                    //posX = Math.max(posX, 0);
                    if (posX < 0) {
                        posX = 0;
                        seMueve = false;
                    }
                }
                tMoveAux = System.currentTimeMillis();
            }
        }
    }

    /**
     * @param x
     * @param y
     */
//    public void isPulsado(int x, int y) {
//        if (x > posX && x < posX + framesc[indice].getWidth() &&
//                y > posY && y < posY + framesc[indice].getHeight()) {
//            if (!seMueve && pulsado) {
//                avanza = !avanza;
//                pulsado = false;
//            } else if (!seMueve && !pulsado) {
//                pulsado = true;
//                seMueve = true;
//            } else {
//                seMueve = false;
//                pulsado = true;
//            }
//        } else pulsado = false;
//    }

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

    public Bitmap[] getFramesc() {
        return framesc;
    }

    public void setFramesc(Bitmap[] framesc) {
        this.framesc = framesc;
    }

    public Bitmap[] getFramesp() {
        return framesp;
    }

    public void setFramesp(Bitmap[] framesp) {
        this.framesp = framesp;
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
