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
    private int indice = 0; // indice para recorrer los array de bitmaps
    Bitmap bitmapsCaba[]; // array de bitmaps enemigos
    public Rect rectEnemigo;    // hitbox enemigos
    Context context; // contexto de la aplicacion
    Utils utils;

    /**
     * Contructor que inicializa las propiedas de la clase 'enemigo'
     *
     * @param context       Contexto de la aplicacion
     * @param posX          Posicion en el eje X
     * @param posY          Posicion en el eje Y
     * @param anchoPantalla Ancho pantalla del dispositivo
     * @param altoPantalla  Alto pantalla del dispositivo
     * @param velocidad     Velocidad de movimiento en el eje Y del personaje
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
     * Se crea y se asocia una rect, que sera el hitbox de nuestro de personaje
     */
    public void setRectangulo() {
        rectEnemigo = new Rect((int) (posX + 0.2 * bitmapsCaba[0].getWidth()), (int) (posY + 0.2 * bitmapsCaba[0].getHeight()), (int) (posX + 0.8 * bitmapsCaba[0].getWidth()), (int) (posY + 0.8 * bitmapsCaba[0].getHeight()));
    }

    /**
     * Comprueba mediante booleanas, si se esta moviendo o parado, ademas de la direccion en la que se muestra
     *
     * @return Devuelve null
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
     * Comprueba que cambia una fraccion de tiempo, antes de cambiar el indice
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
     * Actualizamos la fisica de los elementos en pantalla
     */
    public void actualizarFisica() {

    }

    /**
     * Rutina de dibujo en el lienzo. Se le llamara desde el hilo juego
     *
     * @param c Canvas de la aplicacion
     */
    public void dibuja(Canvas c) {
        c.drawBitmap(bitmapsCaba[indice], posX, altoPantalla - proporcionAlto * 5, null);
    }

    /**
     * Funcion que recibe como parametro un bitmap, el cual será volteado
     *
     * @param imagen     Bitmap que sera la imagen a mostrar
     * @param horizontal Indica si se muestra en posición horizontal, en caso de ser true, vertical en caso de ser false
     * @return Devuelve un bitmap aplicando efecto espejo
     */
    public Bitmap espejo(Bitmap imagen, Boolean horizontal) {
        Matrix matrix = new Matrix();
        if (horizontal) matrix.preScale(-1, 1);
        else matrix.preScale(1, -1);
        return Bitmap.createBitmap(imagen, 0, 0, imagen.getWidth(), imagen.getHeight(), matrix, false);
    }

    /**
     * Indica si el enemigo ha colisionado
     *
     * @return Devuelve true en caso de colision
     */
    public boolean isColision() {
        return colision;
    }

    /**
     * Hace un set a la booleana colision
     *
     * @param colision Muestra el valor si el enemigo esta en colisian o no
     */
    public void setColision(boolean colision) {
        this.colision = colision;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

}
