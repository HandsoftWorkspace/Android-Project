package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;

public class Personaje {

    /**
     * Enteros de posiciones eje X y posicion eje Y
     */
    private int posX, posY;
    /**
     * Posiciones ejex X/Y, velocidad y vidas
     */
    int vel, vida;
    /**
     * Array de bitmaps para la gestion de movimiento del personaje
     */
    Bitmap[] frames; // array de frames
    /**
     * Bitmap que almacena la imagen actual
     */
    Bitmap frame; // bitmap actual
    /**
     * Rect hitbox del personaje
     */
    Rect rectPersonaje;
    /**
     * Objeto de la clase utils
     */
    Utils utils;
    /**
     * Entero que representa la velocidad del personaje
     */
    private int velocidad;
    /**
     * Cambio de frame
     */
    private int tiempoFrame = 100;
    /**
     * Cada cuantos milisegundos cambia de frame
     */
    private int tiempoMove = 50;
    private long tFrameAuxm = 0, tMoveAux = 0;
    private int indice = 0;
    private int alfa = 230;

    /**
     * Contructor que inicializa las propiedas de la clase
     *
     * @param context       Contexto de la aplicacion
     * @param posX          Posicion en el eje X del látigo
     * @param posY          Posicion en el eje Y del látigo
     * @param anchoPantalla Ancho de pantalla del dispositivo
     * @param altoPantalla  Alto de pantalla del dispositivo
     */
    public Personaje(Context context, int posX, int posY, int velocidad, int anchoPantalla, int altoPantalla) {
    }

    /**
     * Comprueba mediante booleanas, si se está moviendo o parado, además de la direccion en la que se muestra
     *
     * @return Devuelve null
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
     * Se crea y se asocia una rect, que sera el hitbox de nuestro de personaje
     */
    public void setRectangulo() {
        rectPersonaje = new Rect((int) (posX + 0.2 * frame.getWidth()), (int) (posY + 0.2 * frame.getHeight()), (int) (posX + 0.8 * frame.getWidth()), (int) (posY + 0.8 * frame.getHeight()));
    }

}
