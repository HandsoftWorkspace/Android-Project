package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

public class CaballeroDos extends Personaje {
    /**
     * Posiciones en eje X y eje Y
     */
    private int posX, posY;
    /**
     * Tamaño de pantalla del dispositivo
     */
    private int anchoPantalla, altoPantalla;
    /**
     * Proporciones de pantalla
     */
    private int proporcionAncho, proporcionAlto;
    /**
     * Detecta si ha colisionado
     */
    private boolean colision = false;
    /**
     * Velocidad del personaje
     */
    private int velocidad;
    /**
     * Pincel
     */
    private Paint p;
    /**
     * Cada cuanto cambia el frame
     */
    private int tiempoFrame = 100, tiempoMove = 50;
    /**
     * Tiempo para controlar cambios de frame
     */
    private long tFrameAuxm = 0, tMoveAux = 0;
    /**
     * Indice para recorrer los array de bitmaps
     */
    private int indice = 0;
    /**
     * Array de bitmaps enemigos
     */
    Bitmap bitmapsCaba[];
    /**
     * Hitbox del personaje
     */
    public Rect rectEnemigo;
    /**
     * Contexto de la aplicacion
     */
    Context context;
    /**
     * Objeto de la clase utils
     */
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
