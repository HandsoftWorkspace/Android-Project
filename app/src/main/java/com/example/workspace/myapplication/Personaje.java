package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Personaje {
    private int posX, posY, vel, vida; // posiciones ejex X/Y, velocidad y vidas
    Bitmap[] frames; // array de frames
    Bitmap frame; // bitmap actual
    Rect rectPersonaje; // rect personajes
    Utils utils;
    private int velocidad; // velocidad
    private int tiempoFrame = 100;
    private int tiempoMove = 50;
    private long tFrameAuxm = 0, tMoveAux = 0;
    private int indice = 0;
    private int alfa = 230; // transparencia

    /**
     * Contructor que inicializa las propiedas de la clase 'personaje'
     *
     * @param context       Contexto de la aplicación
     * @param posX          Posición en el eje X del látigo
     * @param posY          Posicion en el eje Y del látigo
     * @param anchoPantalla Ancho de pantalla del dispositivo
     * @param altoPantalla  Alto de pantalla del dispositivo
     */
    public Personaje(Context context, int posX, int posY, int velocidad, int anchoPantalla, int altoPantalla) {
    }

    /**
     * Comprueba mediante booleanas, si se está moviendo o parado, además de la dirección en la que se muestra
     *
     * @return
     */
    public Bitmap move() {
        int aux = 0;
        for (int i = 0; i < frames.length; i++) {
            aux++;
            if (aux / 2 == 0) {
                return frames[i];
            }
        }
        return null;
    }

    /**
     * Se crea y se asocia una rect, que será el hitbox de nuestro de personaje
     */
    public void setRectangulo() {
        rectPersonaje = new Rect((int) (posX + 0.2 * frame.getWidth()), (int) (posY + 0.2 * frame.getHeight()), (int) (posX + 0.8 * frame.getWidth()), (int) (posY + 0.8 * frame.getHeight()));
    }

//    public void obtenerBitmap(Context context, String nombre){
//        BitmapFactory.decodeResource(context.getResources(),R.drawable.nombre);
//    }

}
