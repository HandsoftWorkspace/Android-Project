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

    Bitmap capa, capa1, capa2, capa3, capa4, capa5, capa6, capa7, capa8, capa9, capa10;

    Bitmap btnA, btnB;

    Enemigo enemigo; // Personaje secundario
    ArrayList<Personaje> personajes = new ArrayList<>();

    Rect rectYones;
    Rect rectBtnA, rectBtnB;

    public static boolean enSalto;
    public static boolean enSlide;

    int random;

//    DrYones drYones=new DrYones();
//    DrYones drYones = new DrYones(anchoPantalla / 2, altoPantalla * 7, proporcionAncho, proporcionAlto, anchoPantalla, altoPantalla, 30);
    Yones yones = new Yones(context, proporcionAncho, proporcionAlto * 8, 2, anchoPantalla, altoPantalla);
    Caballero caballero = new Caballero(context, proporcionAncho * 18, proporcionAlto * 6, 4, anchoPantalla, altoPantalla);
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

        // Colección
        parallax = new ArrayList<>(); // Se inicia la coleccion;
//        parallax.add(new Fondo(capa, 0, 0));
        parallax.add(new Fondo(capa1, 0, 0, 4));
        parallax.add(new Fondo(capa2, 0, 0, 6));
        parallax.add(new Fondo(capa3, 0, 6));
        parallax.add(new Fondo(capa4, 0, 8));
        parallax.add(new Fondo(capa5, 0, 10));
        parallax.add(new Fondo(capa6, 0, 12));
        parallax.add(new Fondo(capa7, 0, 14));
//        parallax.add(new Fondo(capa8, 0, 16));
//        parallax.add(new Fondo(capa9, 0, 18));
//        parallax.add(new Fondo(capa10, 0, 20));

        // Botones
        btnA = BitmapFactory.decodeResource(context.getResources(), R.drawable.btn);
        btnA = Bitmap.createScaledBitmap(btnA, proporcionAncho * 1, proporcionAlto * 1, false);

        btnB = BitmapFactory.decodeResource(context.getResources(), R.drawable.btn);
        btnB = Bitmap.createScaledBitmap(btnB, proporcionAncho * 1, proporcionAlto * 1, false);

        rectBtnA = new Rect(0, proporcionAlto * 2, proporcionAncho * 1, proporcionAlto * 3);
        rectBtnB = new Rect(0, proporcionAlto * 4, proporcionAncho * 1, proporcionAlto * 5);

        // PERSONAJES
        enSalto = false;
        enSlide = false;

        p.setColor(ContextCompat.getColor(context, R.color.colorBackGr));

//        personajes.add(caballero);

    }

    /**
     *
     */
    @Override
    public void actualizarFisica() {
        super.actualizarFisica();
        for (Fondo f : parallax) {
            f.mover();
        }

        //drYones.move();
        //drYones.cambiaFrame();

//        yones.mover();
//        caballero.mover();
    }

    /**
     * @param c canvas this application
     */
    public void dibujar(Canvas c) {
        try {
            Log.d("hard", String.valueOf(c.isHardwareAccelerated()));

            // Parallax background
            // Se recorre la lista de imagen de la colección y se dibuja
            for (Fondo f : parallax) {
                f.dibujar(c);
            }

            // Botones
            c.drawBitmap(btnA, 0, proporcionAlto * 2, null);
            c.drawBitmap(btnB, 0, proporcionAlto * 4, null);

            // Personajes
            //enemigo.moverEnemigo(altoPantalla,anchoPantalla,10);
//            if (enSlide) {
//                yones.dibujar(c);
//
//            } else if (enSalto) {
//                yones.dibujar(c);
//                enSalto = false;
//            } else {

//            yones.dibujar(c); // Se llama a dibujar de la clase
//            }
//            random = (int) (Math.random() * 3) + 1;
//            if (random == 1) {

//            caballero.dibujar(c);
//            }
//            drYones.dibuja(c);
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
            case MotionEvent.ACTION_DOWN:
                if (pulsa(rectBtnA, event)) {
                    enSalto = true; //yones
//                    drYones.seMueve = true; // dryones
//                    drYones.enRetroceso = true;
                }
                if (pulsa(rectBtnB, event)) {
                    enSlide = true; //yones
//                    drYones.seMueve = true; // dryones
//                    drYones.enAvance = true; // dryones
                }
                //drYones.isPulsado ((int) x, (int) y);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if (pulsa(rectBtnA, event)) {
//                    drYones.seMueve = false;
//                    drYones.enRetroceso = false;
                }
                if (pulsa(rectBtnB, event)) {
//                    drYones.seMueve = false;
//                    drYones.enAvance = false;
                }
                break;
            case MotionEvent.ACTION_MOVE: // Se mueve alguno de los dedos
                break;
        }
        return super.onTouchEvent(event);
    }
}
