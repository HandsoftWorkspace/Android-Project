package com.example.workspace.myapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

public class DrYones {
    Bitmap[] framesc, framesic, framesp, framesip;
    int posX, posY;
    int anchoPantalla, altoPantalla;

    int velocidad;
    int tiempoFrame = 100;
    int tiempoMove = 50;
    long tFrameAuxm = 0, tMoveAux = 0;
    int indice = 0;
    int alfa = 230;
    Paint p;
    boolean seMueve = true;
    boolean animado = true;
    boolean avanza = true;
    boolean pulsado = false;

    public DrYones(Bitmap[] framesc, Bitmap[] framesp, int posX, int posY, int anchoPantalla, int altoPantalla, int velocidad) {
        this.posX = posX;
        this.posY = posY;
        this.velocidad = velocidad;
        this.anchoPantalla = anchoPantalla;
        this.altoPantalla = altoPantalla;

        this.p = new Paint();
        p.setAlpha(alfa);

        this.framesc = framesc;
        framesic = new Bitmap[framesc.length];
        for (int k = 0; k < framesc.length; k++) framesic[k] = espejo(framesc[k], true);

        this.framesp = framesp;
        framesip = new Bitmap[framesp.length];
        for (int k = 0; k < framesp.length; k++) framesip[k] = espejo(framesp[k], true);
    }

    public void dibuja(Canvas c) {
        if (avanza) {
            if (seMueve) c.drawBitmap(framesc[indice], posX, posY, p);
            else c.drawBitmap(framesp[indice], posX, posY, p);
        } else if (seMueve) c.drawBitmap(framesic[indice], posX, posY, p);
        else c.drawBitmap(framesip[indice], posX, posY, p);
    }

    public void cambiaFrame() {
        if (System.currentTimeMillis() - tFrameAuxm > tiempoFrame) {
            indice++;
            if (indice >= framesc.length) indice = 0;
            tFrameAuxm = System.currentTimeMillis();
        }
    }

    public void move() {
        if (seMueve) {
            if (System.currentTimeMillis() - tMoveAux > tiempoMove) {
                if (avanza) {
                    posX += velocidad;
                    if (posX > this.anchoPantalla - framesc[indice].getWidth()) {
                        posX = this.anchoPantalla - framesc[indice].getWidth();
                        seMueve = false;
                    }
                    //posX = Math.min(posX, PantallaInicioView.anchoPantalla - framesc[indice].getWidth());
                } else {
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

    public void isPulsado(int x, int y) {
        if (x > posX && x < posX + framesc[indice].getWidth() &&
                y > posY && y < posY + framesc[indice].getHeight()) {
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
        } else pulsado = false;
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

    public Bitmap espejo(Bitmap imagen, Boolean horizontal) {
        Matrix matrix = new Matrix();
        if (horizontal) matrix.preScale(-1, 1);
        else matrix.preScale(1, -1);
        return Bitmap.createBitmap(imagen, 0, 0, imagen.getWidth(), imagen.getHeight(), matrix, false);
    }
}
