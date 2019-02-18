package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.MissingResourceException;

public class Game extends Escena implements Runnable {

    private ArrayList<Fondo> parallax; // Array de objetos 'fondo' para realizar el parallax
    Paint pincel;

    Bitmap capa, capa1, capa2, capa3, capa4, capa5, capa6, capa7, capa8, capa9, capa10;

    Enemigo enemigo; // Personaje secundario

    Rect rectYones;

    public static boolean enSalto;
    public static boolean enSlide;

    int random;

    Yones yones = new Yones(context, 10, 80, 2, anchoPantalla, altoPantalla);
    Caballero caballero = new Caballero(context, anchoPantalla, altoPantalla, 4, anchoPantalla, altoPantalla);

    /**
     * @param context       application
     * @param idEscena      scene run in this moment
     * @param anchoPantalla width screen this device
     * @param altoPantalla  heigth screen this device
     */
    public Game(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);

        // Se asocia cada bitmap a una imagen png
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

        parallax = new ArrayList<>(); // Se inicia la coleccion;

        // Se añade la imagen a cada posición de la colección
//        parallax.add(new Fondo(capa, 0, 0));
//        parallax.add(new Fondo(capa1, 0, 0));
//        parallax.add(new Fondo(capa2, 0, 0));
//        parallax.add(new Fondo(capa3, 0, 0));
//        parallax.add(new Fondo(capa4, 0, 0));

//        segundo parallax
        capa = BitmapFactory.decodeResource(context.getResources(), R.drawable.layer1);
        capa = Bitmap.createScaledBitmap(capa, anchoPantalla, altoPantalla, false);

        capa1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.layer2);
        capa1 = Bitmap.createScaledBitmap(capa1, anchoPantalla, altoPantalla, false);

        capa2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.layer3);
        capa2 = Bitmap.createScaledBitmap(capa2, anchoPantalla, altoPantalla, false);

        capa3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.layer4);
        capa3 = Bitmap.createScaledBitmap(capa3, anchoPantalla, altoPantalla, false);

        capa4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.layer5);
        capa4 = Bitmap.createScaledBitmap(capa4, anchoPantalla, altoPantalla, false);

        capa5 = BitmapFactory.decodeResource(context.getResources(), R.drawable.layer6);
        capa5 = Bitmap.createScaledBitmap(capa5, anchoPantalla, altoPantalla, false);

        capa6 = BitmapFactory.decodeResource(context.getResources(), R.drawable.layer7);
        capa6 = Bitmap.createScaledBitmap(capa6, anchoPantalla, altoPantalla, false);

        capa7 = BitmapFactory.decodeResource(context.getResources(), R.drawable.layer8);
        capa7 = Bitmap.createScaledBitmap(capa7, anchoPantalla, altoPantalla, false);

        capa8 = BitmapFactory.decodeResource(context.getResources(), R.drawable.layer9);
        capa8 = Bitmap.createScaledBitmap(capa8, anchoPantalla, altoPantalla, false);

        capa9 = BitmapFactory.decodeResource(context.getResources(), R.drawable.layer10);
        capa9 = Bitmap.createScaledBitmap(capa9, anchoPantalla, altoPantalla, false);

        capa10 = BitmapFactory.decodeResource(context.getResources(), R.drawable.layer11);
        capa10 = Bitmap.createScaledBitmap(capa10, anchoPantalla, altoPantalla, false);

        parallax.add(new Fondo(capa, 0, 0));
        parallax.add(new Fondo(capa1, 0, 0));
        parallax.add(new Fondo(capa2, 0, 0));
        parallax.add(new Fondo(capa3, 0, 0));
        parallax.add(new Fondo(capa4, 0, 0));
        parallax.add(new Fondo(capa5, 0, 0));
        parallax.add(new Fondo(capa6, 0, 0));
        parallax.add(new Fondo(capa7, 0, 0));
        parallax.add(new Fondo(capa8, 0, 0));
        parallax.add(new Fondo(capa9, 0, 0));
        parallax.add(new Fondo(capa10, 0, 0));

        // PERSONAJES
        enSalto = false;
        enSlide = false;
    }

    /**
     *
     */
    @Override
    public void actualizarFisica() {
        super.actualizarFisica();

        parallax.get(0).mover(4);
        parallax.get(1).mover(6);
        parallax.get(2).mover(8);
        parallax.get(3).mover(10);
        parallax.get(4).mover(12);
        parallax.get(5).mover(14);
        parallax.get(6).mover(16);
        parallax.get(7).mover(18);
        parallax.get(8).mover(20);
        parallax.get(9).mover(22);
        parallax.get(10).mover(24);

    }

    /**
     * @param c canvas this application
     */
    public void dibujar(Canvas c) {
        try {
            // Se recorre la lista de imagen de la colección y se dibuja
            for (Fondo f : parallax) {
                f.dibujar(c);
            }

            // Personajes
            //enemigo.moverEnemigo(altoPantalla,anchoPantalla,10);
//            if (enSlide) {
//                yones.dibujar(c);
//                enSlide = false;
//            } else if (enSalto) {
//                yones.dibujar(c);
//                enSalto = false;
//            } else {
            yones.mover();
            yones.dibujar(c); // Se llama a dibujar de la clase
//            }
//            random = (int) (Math.random() * 3) + 1;
//            if (random == 1) {
            //caballero.mover();
            //caballero.dibujar(c);
//            }
        } catch (MissingResourceException e) {
            //Log.i("Error al dibujar", e.getLocalizedMessage());
        }
    }

    // Runnable, lanzo el hilo de backgrounds
    @Override
    public void run() {

    }

    /**
     * @param event
     * @return
     */
    @Override
    public int onTouchEvent(MotionEvent event) {
        int pointerIndex = event.getActionIndex();        //Obtenemos el índice de la acción
        int pointerID = event.getPointerId(pointerIndex); //Obtenemos el Id del pointer asociado a la acción
        int accion = event.getActionMasked();

        switch (accion) {
            case MotionEvent.ACTION_DOWN:           // Primer dedo toca
                enSalto = true;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:  // Segundo y siguientes tocan
                break;
            case MotionEvent.ACTION_UP:                     // Al levantar el últ
                break;
            case MotionEvent.ACTION_POINTER_UP:  // Al levantar un dedo que no es
                break;
            case MotionEvent.ACTION_MOVE: // Se mueve alguno de los dedos
                enSlide = true;
                break;
        }
        return super.onTouchEvent(event);
    }
}
