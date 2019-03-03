package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Vibrator;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.MissingResourceException;

public class Game extends Escena implements Runnable {

    private int proporcionAncho, proporcionAlto;
    private ArrayList<Fondo> parallax; // Array de objetos 'fondo' para realizar el parallax
    Paint p = new Paint();
    Utils utils = new Utils(context);

    Bitmap noche0, noche1, noche2, noche3, noche4, noche5;

    Bitmap btnA, btnB;

    Enemigo enemigo; // Personaje secundario
    ArrayList<Personaje> personajes = new ArrayList<>();

    Rect rectYones;
    Rect rectBtnA, rectBtnB;

    int random;

    DrYones drYones = new DrYones(context, anchoPantalla / 2, altoPantalla * 8, anchoPantalla, altoPantalla, 5);
    //    Yones yones = new Yones(context, proporcionAncho, proporcionAlto * 8, 2, anchoPantalla, altoPantalla);
    //    Caballero caballero = new Caballero(context, proporcionAncho * 18, proporcionAlto * 6, 4, anchoPantalla, altoPantalla);
    Caballero caballero = new Caballero(context, anchoPantalla, proporcionAlto * 6, 4, anchoPantalla, altoPantalla);
//    Caballero caballero2 = new Caballero(context, proporcionAncho, proporcionAlto * 6, 4, anchoPantalla, altoPantalla);

    /**
     * @param context       application
     * @param idEscena      scene run in this moment
     * @param anchoPantalla width screen this device
     * @param altoPantalla  heigth screen this device
     */
    public Game(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);

        this.proporcionAncho = anchoPantalla / 18;
        this.proporcionAlto = altoPantalla / 9;

        // segundo parallax
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 2;

        // Se asocia cada bitmap a una imagen png
        fondo = BitmapFactory.decodeResource(context.getResources(), R.drawable.backgroundnight);
        fondo = Bitmap.createScaledBitmap(fondo, anchoPantalla, altoPantalla, false);
//
//        noche0 = BitmapFactory.decodeResource(context.getResources(), R.drawable.noche0);
//        noche0 = Bitmap.createScaledBitmap(noche0, anchoPantalla, altoPantalla, false);
//
//        noche1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.noche1);
//        noche1 = Bitmap.createScaledBitmap(noche1, anchoPantalla, altoPantalla, false);
//
//        noche2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.noche2);
//        noche2 = Bitmap.createScaledBitmap(noche2, anchoPantalla, altoPantalla, false);
//
//        noche3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.noche3);
//        noche3 = Bitmap.createScaledBitmap(noche3, anchoPantalla, altoPantalla, false);
//
//        noche4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.noche4);
//        noche4 = Bitmap.createScaledBitmap(noche4, anchoPantalla, altoPantalla, false);
//
//        noche5 = BitmapFactory.decodeResource(context.getResources(), R.drawable.noche5);
//        noche5 = Bitmap.createScaledBitmap(noche5, anchoPantalla, altoPantalla, false);

        // Colección
//        parallax = new ArrayList<>(); // Se inicia la coleccion;
//        parallax.add(new Fondo(noche0, 0, 0, 4));
//        parallax.add(new Fondo(noche1, 0, 0, 6));
//        parallax.add(new Fondo(noche2, 0, 0, 6));
//        parallax.add(new Fondo(noche3, 0, 0, 8));
//        parallax.add(new Fondo(noche4, 0, 0, 10));
//        parallax.add(new Fondo(noche5, 0, 0, 12));

        // Botones
        btnA = BitmapFactory.decodeResource(context.getResources(), R.drawable.btn);
        btnA = Bitmap.createScaledBitmap(btnA, proporcionAncho * 1, proporcionAlto * 1, false);

        btnB = BitmapFactory.decodeResource(context.getResources(), R.drawable.btn);
        btnB = Bitmap.createScaledBitmap(btnB, proporcionAncho * 1, proporcionAlto * 1, false);

        rectBtnA = new Rect(0, proporcionAlto * 6, proporcionAncho * 1, proporcionAlto * 7);
        rectBtnB = new Rect(proporcionAncho * 17, proporcionAlto * 6, proporcionAncho * 18, proporcionAlto * 7);

        // PERSONAJES

        p.setColor(ContextCompat.getColor(context, R.color.colorBackGr));
        drYones.enAvance = true;
        drYones.seMueve = false;
//        personajes.add(caballero);
    }

    /**
     *
     */
    @Override
    public void actualizarFisica() {
        super.actualizarFisica();
//        for (Fondo f : parallax) {
//            f.mover();
//        }
        drYones.move();

    }

    /**
     * @param c canvas this application
     */
    public void dibujar(Canvas c) {
        try {
//            Log.d("hard", String.valueOf(c.isHardwareAccelerated()));
            // Parallax background
            // Se recorre la lista de imagen de la colección y se dibuja
//            for (Fondo f : parallax) {
//                f.dibujar(c);
//            }

            c.drawBitmap(fondo, 0, 0, null);
            p.setColor(Color.GREEN);
            c.drawRect(rectBtnA, p);
            c.drawRect(rectBtnB, p);


            // Botones
            c.drawBitmap(btnA, 0, proporcionAlto * 6, null);
            c.drawBitmap(btnB, anchoPantalla - proporcionAncho, proporcionAlto * 6, null);

            // Personajes
            //enemigo.moverEnemigo(altoPantalla,anchoPantalla,10);
            drYones.cambiaFrame();
            drYones.dibuja(c);
        } catch (MissingResourceException e) {
            Log.i("Error al dibujar", e.getLocalizedMessage());
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
            case MotionEvent.ACTION_DOWN:
                if (pulsa(rectBtnA, event)) {
                    drYones.enAvance = false;
                    drYones.enRetroceso = true;
                    drYones.seMueve = true;
                }
                if (pulsa(rectBtnB, event)) {
                    drYones.enRetroceso = false;
                    drYones.enAvance = true;
                    drYones.seMueve = true;
                }
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if (pulsa(rectBtnA, event)) {
                    drYones.seMueve = false;
                }
                if (pulsa(rectBtnB, event)) {
                    drYones.seMueve = false;
                }
                break;
            case MotionEvent.ACTION_MOVE: // Se mueve alguno de los dedos
                break;
        }
        return super.onTouchEvent(event);
    }
}
