package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

public class CaballeroDos extends Personaje {
    private int posX, posY; // posiciones en eje X y eje Y
    private int anchoPantalla, altoPantalla; // tamaño de pantalla del dispositivo
    private int proporcionAncho, proporcionAlto; // proporciones de pantalla
    private boolean colision = false; // detecta si ha colisionado
    private int velocidad;
    private Paint p;
    private int tiempoFrame = 100; // cada cuanto cambia el frame
    private int tiempoMove = 50; //
    private long tFrameAuxm = 0, tMoveAux = 0;
    private int indice = 0; // índice para recorrer los array de bitmaps
    Bitmap bitmapsCaba[]; // array de bitmaps enemigos
    public Rect rectEnemigo;    // hitbox enemigos
    Context context; // contexto de la aplicación
    Utils utils;

    /**
     * Contructor que inicializa las propiedas de la clase 'enemigo'
     *
     * @param context       contexto de la aplicación
     * @param posX          posición en el eje X
     * @param posY          posición en el eje Y
     * @param anchoPantalla ancho pantalla del dispositivo
     * @param altoPantalla  alto pantalla del dispositivo
     * @param velocidad     velocidad de movimiento en el eje Y del personaje
     */
    public CaballeroDos(Context context, int posX, int posY, int anchoPantalla, int altoPantalla, int velocidad) {
        super(context, posX, posY, anchoPantalla, altoPantalla, velocidad);
        this.context = context;
        this.posX = posX;
        this.posY = posY;
        this.velocidad = velocidad;
        this.anchoPantalla = anchoPantalla;
        this.altoPantalla = altoPantalla;
        proporcionAncho = anchoPantalla / 9;
        proporcionAlto = altoPantalla / 18;
        utils = new Utils(context);
        p = new Paint();
        bitmapsCaba = new Bitmap[9];
        for (int i = 0; i < bitmapsCaba.length; i++) {
            bitmapsCaba[i] = utils.getBitmapFromAssets("cabarun/caba" + i + ".png");
            bitmapsCaba[i] = Bitmap.createScaledBitmap(bitmapsCaba[i], anchoPantalla / 18, altoPantalla / 8 * 2, false);
            bitmapsCaba[i] = espejo(bitmapsCaba[i], true);
        }
    }

    /**
     * Se crea y se asocia una rect, que será el hitbox de nuestro de personaje
     */
    public void setRectangulo() {
        rectEnemigo = new Rect((int) (posX + 0.2 * bitmapsCaba[0].getWidth()), (int) (posY + 0.2 * bitmapsCaba[0].getHeight()), (int) (posX + 0.8 * bitmapsCaba[0].getWidth()), (int) (posY + 0.8 * bitmapsCaba[0].getHeight()));
    }

    /**
     * Comprueba mediante booleanas, si se está moviendo o parado, además de la dirección en la que se muestra
     *
     * @return
     */
    public Bitmap move() {
        posX -= velocidad;
        if (posX < 0 - bitmapsCaba[0].getWidth()) {
            posX = anchoPantalla + bitmapsCaba[0].getWidth();
            colision = false;
        }
        this.setRectangulo();
        return null;
    }

    /**
     * Comprueba que cambia una fracción de tiempo, antes de cambiar el índice
     */
    public void cambiaFrame() {
        if (System.currentTimeMillis() - tFrameAuxm > tiempoFrame) {
            indice++;
            if (indice >= bitmapsCaba.length) {
                indice = 0;
            }
            tFrameAuxm = System.currentTimeMillis();
        }
    }

    /**
     * Actualizamos la física de los elementos en pantalla
     */
    public void actualizarFisica() {

    }

    /**
     * Rutina de dibujo en el lienzo. Se le llamará desde el hilo juego
     *
     * @param c
     */
    public void dibuja(Canvas c) {
        c.drawBitmap(bitmapsCaba[indice], posX, altoPantalla - proporcionAlto * 5, null);
    }

    /**
     * Función que recibe como parámetro un bitmap, el cual será volteado
     *
     * @param imagen     Bitmap que será la imagen a mostrar
     * @param horizontal Índica si se muestra en posición horizontal, en caso de ser true, vértical en caso de ser false
     * @return
     */
    public Bitmap espejo(Bitmap imagen, Boolean horizontal) {
        Matrix matrix = new Matrix();
        if (horizontal) matrix.preScale(-1, 1);
        else matrix.preScale(1, -1);
        return Bitmap.createBitmap(imagen, 0, 0, imagen.getWidth(), imagen.getHeight(), matrix, false);
    }

    /**
     * Índica si el enemigo ha colisionado
     *
     * @return devuelve el valor de la booleana
     */
    public boolean isColision() {
        return colision;
    }

    /**
     * Hace un set a la booleana colision
     *
     * @param colision muestra el valor de si el enemigo está en colisión o no
     */
    public void setColision(boolean colision) {
        this.colision = colision;
    }

}
