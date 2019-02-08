package com.example.workspace.myapplication;

import android.graphics.Bitmap;

// Clase que gestiona la transición de las capas de los fondos del  juego
public class Background implements Runnable{

    // Propiedas, coordenadas X, Y. velocidad de transición
    private int bgX, bgY, speedX;
    // Se le pasa un bitmap con el que va a trabajar
    private Bitmap image;
    // Booleana control inicio
    boolean isRunning = true;
    // Hilo
    Thread thread;

    // Constructo al que se le pasa solamente las coordenadas
    public Background(int x, int y, Bitmap image){
        bgX = x;
        bgY = y;
        this.image=image;
        speedX = 10;
        thread.start();
    }

    public Background(int x, int y, Bitmap image, int speedX){
        bgX = x;
        bgY = y;
        this.image=image;
        this.speedX = speedX;
        thread.start();
    }

    // Método actualizar, trabaja sobre las coordenadas donde se encuentre el background
    public void update() {
        bgX += speedX;

        // Cúando esto se cumpla, reinicia la posición x del fondo
        if (bgX <= -2160){
            bgX += 4320;
        }
    }

    // Setters y getters
    public int getBgX() {
        return bgX;
    }

    public int getBgY() {
        return bgY;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setBgX(int bgX) {
        this.bgX = bgX;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public void setBgY(int bgY) {
        this.bgY = bgY;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    @Override
    public void run() {
        while(isRunning){
            //Scroll
            bgY += 1;
            try {
                //this.postInvalidate();
                thread.sleep(10);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}
