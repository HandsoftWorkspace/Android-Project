package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public class Game extends Escena implements Runnable {

    Fondo[] parallax;
    Paint pincel;
    // Fondos bitmaps
//    Bitmap capa1,capa2,capa3,capa4;

    // Fondos clase propia backgrounds
    Background a1, a2;
    Background b1, b2;
    Background c1, c2;
    Background d1, d2;

    Enemigo enemigo;
    Bitmap bitmapEnemigo;

    Rect rectYones;

    Yones yones = new Yones(context, 10, 80, 2, anchoPantalla, altoPantalla);

    /**
     * @param context       application
     * @param idEscena      scene run in this moment
     * @param anchoPantalla width screen this
     * @param altoPantalla  heigth screen
     */
    public Game(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);
        //fondo = BitmapFactory.decodeResource(context.getResources(), R.drawable.prueba);
        //fondo = Bitmap.createScaledBitmap(fondo, anchoPantalla, altoPantalla, false);

        capa = BitmapFactory.decodeResource(context.getResources(), R.drawable.capa);
        capa = Bitmap.createScaledBitmap(capa, anchoPantalla, altoPantalla, false);

        capa1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.capa1);
        capa1 = Bitmap.createScaledBitmap(capa1, anchoPantalla, altoPantalla, false);

        capa2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.capa2);
        capa2 = Bitmap.createScaledBitmap(capa2, anchoPantalla, altoPantalla, false);

        capa3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.capa3);
        capa3 = Bitmap.createScaledBitmap(capa3, anchoPantalla, altoPantalla, false);

        capa4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.capa4);
        capa4 = Bitmap.createScaledBitmap(capa4, anchoPantalla, altoPantalla, false);

        capa5 = BitmapFactory.decodeResource(context.getResources(), R.drawable.capa4);
        capa5 = Bitmap.createScaledBitmap(capa4, anchoPantalla, altoPantalla, false);

        capa6 = BitmapFactory.decodeResource(context.getResources(), R.drawable.capa4);
        capa6 = Bitmap.createScaledBitmap(capa4, anchoPantalla, altoPantalla, false);

        capa7 = BitmapFactory.decodeResource(context.getResources(), R.drawable.capa4);
        capa7 = Bitmap.createScaledBitmap(capa4, anchoPantalla, altoPantalla, false);

        capa8 = BitmapFactory.decodeResource(context.getResources(), R.drawable.capa4);
        capa8 = Bitmap.createScaledBitmap(capa4, anchoPantalla, altoPantalla, false);

        capa9 = BitmapFactory.decodeResource(context.getResources(), R.drawable.capa4);
        capa9 = Bitmap.createScaledBitmap(capa4, anchoPantalla, altoPantalla, false);

        capa10 = BitmapFactory.decodeResource(context.getResources(), R.drawable.capa4);
        capa10 = Bitmap.createScaledBitmap(capa4, anchoPantalla, altoPantalla, false);
//
        parallax = new Fondo[10];

//        parallax[0] = new Fondo(capa, anchoPantalla);
//        parallax[1] = new Fondo(capa1, anchoPantalla);
//        parallax[2] = new Fondo(capa2, anchoPantalla);
//        parallax[3] = new Fondo(capa3, anchoPantalla);
//        parallax[4] = new Fondo(capa4, anchoPantalla);
//        parallax[5] = new Fondo(capa5, parallax[0].posicion.x + capa.getWidth(), 0);
//        parallax[6] = new Fondo(capa6, parallax[1].posicion.x + capa1.getWidth(), 0);
//        parallax[7] = new Fondo(capa7, parallax[2].posicion.x + capa2.getWidth(), 0);
//        parallax[8] = new Fondo(capa8, parallax[3].posicion.x + capa3.getWidth(), 0);
//        parallax[9] = new Fondo(capa9, parallax[4].posicion.x + capa4.getWidth(), 0);


        parallax[0] = new Fondo(capa, anchoPantalla);
        parallax[1] = new Fondo(capa1, parallax[0].posicion.x + capa1.getWidth(), 0);
        parallax[2] = new Fondo(capa2, anchoPantalla);
        parallax[3] = new Fondo(capa3, parallax[2].posicion.x + capa1.getWidth(), 0);
        parallax[4] = new Fondo(capa4, anchoPantalla);
        parallax[5] = new Fondo(capa5, parallax[0].posicion.x + capa1.getWidth(), 0);
        parallax[6] = new Fondo(capa6, anchoPantalla);
        parallax[7] = new Fondo(capa7, parallax[0].posicion.x + capa1.getWidth(), 0);
        parallax[8] = new Fondo(capa8, anchoPantalla);
        parallax[9] = new Fondo(capa9, parallax[0].posicion.x + capa1.getWidth(), 0);

        // PERSONAJES
        //bitmapEnemigo = new Bitmap.createScaledBitmap(bitmapEnemigo,10,10,true);
    }

    /**
     * @param c canvas this application
     */
    public void dibujar(Canvas c) {
        try {
            //Aquí dibujo el array de bitmaps
            c.drawBitmap(parallax[0].imagen, parallax[0].posicion.x, parallax[0].posicion.y, null);
            c.drawBitmap(parallax[1].imagen, parallax[1].posicion.x, parallax[0].posicion.y, null);
            c.drawBitmap(parallax[2].imagen, parallax[2].posicion.x, parallax[0].posicion.y, null);
            c.drawBitmap(parallax[3].imagen, parallax[3].posicion.x, parallax[0].posicion.y, null);
            c.drawBitmap(parallax[4].imagen, parallax[4].posicion.x, parallax[0].posicion.y, null);

            c.drawBitmap(parallax[5].imagen, parallax[0].posicion.x, parallax[0].posicion.y, null);
            c.drawBitmap(parallax[6].imagen, parallax[0].posicion.x, parallax[0].posicion.y, null);
            c.drawBitmap(parallax[7].imagen, parallax[0].posicion.x, parallax[0].posicion.y, null);
            c.drawBitmap(parallax[8].imagen, parallax[0].posicion.x, parallax[0].posicion.y, null);
            c.drawBitmap(parallax[9].imagen, parallax[0].posicion.x, parallax[0].posicion.y, null);
//            c.drawBitmap(capa10,0,0,null);


            // Movemos
            parallax[0].mover(1);
            parallax[1].mover(3);
            parallax[2].mover(6);
            parallax[3].mover(8);
            parallax[4].mover(12);

            parallax[5].mover(4);
            parallax[6].mover(3);
            parallax[7].mover(4);
            parallax[8].mover(8);
            parallax[9].mover(8);

            // Comprobamos que se sobrepase la pantalla y reiniciamos
            if (parallax[0].posicion.x == 0) {
//                parallax[0].posicion.x = parallax[0].posicion.x - parallax[0].imagen.getWidth();
                parallax[0].posicion.x = anchoPantalla;
                System.err.println("CAPA 0");
            }
            if (parallax[1].posicion.x == 0) {
//                parallax[1].posicion.x = parallax[1].posicion.x - parallax[1].imagen.getWidth();
                parallax[1].posicion.x = anchoPantalla;
                System.err.println("CAPA 1");
            }
            if (parallax[2].posicion.x == 0) {
//                parallax[2].posicion.x = parallax[2].posicion.x - parallax[2].imagen.getWidth();
                parallax[2].posicion.x = anchoPantalla;
                System.err.println("CAPA 2");
            }
            if (parallax[3].posicion.x == 0) {
//                parallax[3].posicion.x = parallax[3].posicion.x - parallax[3].imagen.getWidth();
                parallax[3].posicion.x = anchoPantalla;
                System.err.println("CAPA 3");
            }
            if (parallax[4].posicion.x == 0) {
//                parallax[4].posicion.x = parallax[4].posicion.x - parallax[4].imagen.getWidth();
                parallax[4].posicion.x = anchoPantalla;
                System.err.println("CAPA 4");
            }

            if (parallax[5].posicion.x > anchoPantalla) {
//                parallax[5].posicion.x = parallax[5].posicion.x - parallax[5].imagen.getWidth();
                parallax[5].posicion.x = 0;
                System.err.println("CAPA 5");
            }
            if (parallax[6].posicion.x > anchoPantalla) {
//                parallax[6].posicion.x = parallax[6].posicion.x - parallax[6].imagen.getWidth();
                parallax[6].posicion.x = 0;
                System.err.println("CAPA 6");
            }
            if (parallax[7].posicion.x > anchoPantalla) {
//                parallax[7].posicion.x = parallax[7].posicion.x - parallax[6].imagen.getWidth();
                parallax[7].posicion.x = 0;
                System.err.println("CAPA 7");
            }
            if (parallax[8].posicion.x > anchoPantalla) {
//                parallax[8].posicion.x = parallax[8].posicion.x - parallax[6].imagen.getWidth();
                parallax[8].posicion.x = 0;
                System.err.println("CAPA 8");
            }
            if (parallax[9].posicion.x > anchoPantalla) {
//                parallax[9].posicion.x = parallax[9].posicion.x - parallax[6].imagen.getWidth();
                parallax[9].posicion.x = 0;
                System.err.println("CAPA 9");
            }
            // Personajes
            //enemigo.moverEnemigo(altoPantalla,anchoPantalla,10);
            yones.mover();
            yones.dibujar(c);
        } catch (Exception e) {
            Log.i("Error al dibujar", e.getLocalizedMessage());
        }
    }

    // Runnable, lanzo el hilo de backgrounds
    @Override
    public void run() {

    }

}
