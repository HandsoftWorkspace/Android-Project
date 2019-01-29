package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import org.w3c.dom.Text;

public class Menu extends Escena {

    Rect ayuda, opciones, juego, game, records;
    int alto, ancho;
    Text jugar;
    Paint brocha = new Paint();

     public Menu(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);
//        fondo = BitmapFactory.decodeResource(context.getResources(), R.drawable.capa1);
//        fondo = Bitmap.createScaledBitmap(fondo,anchoPantalla, altoPantalla, false);

        capa1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.capa1);
        capa1 = Bitmap.createScaledBitmap(capa1,anchoPantalla, altoPantalla, false);

        capa2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.capa2);
        capa2 = Bitmap.createScaledBitmap(capa2,anchoPantalla, altoPantalla, false);

        capa3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.capa3);
        capa3 = Bitmap.createScaledBitmap(capa3,anchoPantalla, altoPantalla, false);

        capa4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.capa4);
        capa4 = Bitmap.createScaledBitmap(capa4,anchoPantalla, altoPantalla, false);


//         capa1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.capa1largo);
//         capa1 = Bitmap.createScaledBitmap(capa1,anchoPantalla, altoPantalla, false);
//
//         capa2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.capa2largo);
//         capa2 = Bitmap.createScaledBitmap(capa2,anchoPantalla, altoPantalla, false);

//         capa3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.capa3largo);
//         capa3 = Bitmap.createScaledBitmap(capa3,anchoPantalla, altoPantalla, false);

//         capa4 = BitmapFactory.decodeResource(context.getResources(), R.drawable.capa4largo);
//         capa4 = Bitmap.createScaledBitmap(capa4,anchoPantalla, altoPantalla, false);


        idioma = BitmapFactory.decodeResource(context.getResources(),R.drawable.menues);
        idioma = Bitmap.createScaledBitmap(idioma, anchoPantalla,altoPantalla,false);

        alto = altoPantalla / 7;
        ancho = anchoPantalla / 5;

        // anchoPantalla/2, altoPantalla/2
        juego = new Rect(anchoPantalla*1/3, altoPantalla*1/3, anchoPantalla *2/3, altoPantalla  *2/3);
        //juego = new Rect(anchoPantalla/3,altoPantalla*3,anchoPantalla/3,altoPantalla*(2/3));
        //juego = new Rect(anchoPantalla,anchoPantalla,anchoPantalla/2,altoPantalla/2);
         //game = new Rect(ancho,alto,ancho*4,alto*4);
        //ayuda = new Rect(ancho, alto, ancho * 4, alto * 3);
        //opciones = new Rect(ancho, alto, ancho * 4, alto * 3);
        //records = new Rect(ancho, alto, ancho * 4, alto * 3);
    }

    // Actualizamos la física de los elementos en pantalla
    public void actualizarFisica() {

    }

    // Rutina de dibujo en el lienzo. Se le llamará desde el hilo
    public void dibujar(Canvas c) {
        try {
            // Fondos
            // Cappas de fondo menú
            c.drawBitmap(capa1, 0, 0, brocha);
            brocha.isAntiAlias();
            brocha.setAntiAlias(true);

            c.drawBitmap(capa2, 0, 0, brocha);
            brocha.isAntiAlias();
            brocha.setAntiAlias(true);

            c.drawBitmap(capa3, 0, 0, brocha);
            brocha.isAntiAlias();
            brocha.setAntiAlias(true);

            c.drawBitmap(capa4, 0, 0, brocha);
            brocha.isAntiAlias();
            brocha.setAntiAlias(true);

            //c.drawBitmap(idioma,0,0,null);

            // Rects menú
            // rect Juego
            c.drawRect(juego, pBoton);
            //c.drawRect(juego,brocha);
            brocha.setColor(Color.YELLOW);

            // rect Opciones

            // rect Ayuda

            // rect Records

            // Textos menú
            // text jugar
            c.drawText("Jugar", anchoPantalla/2, altoPantalla/2, pTexto);
            pTexto.isAntiAlias();
            pTexto.setAntiAlias(true);
            // text opciones
            //c.drawText("Opciones",anchoPantalla-anchoPantalla+400,altoPantalla-altoPantalla+110,pTexto);

            //c.drawText("Menú", anchoPantalla / 2, altoPantalla / 5, pTexto2);
            //c.drawText("Menú", anchoPantalla / 2, altoPantalla / 5 + 30, pTexto2);
        } catch (Exception e) {
            Log.i("Error al dibujar", e.getLocalizedMessage());
        }
    }

    @Override
    public int onTouchEvent(MotionEvent event) {
        //synchronized (surfaceHolder) {
        int pointerIndex = event.getActionIndex();        //Obtenemos el índice de la acción
        int pointerID = event.getPointerId(pointerIndex); //Obtenemos el Id del pointer asociado a la acción
        int accion = event.getActionMasked();             //Obtenemos el tipo de pulsación
        switch (accion) {
            case MotionEvent.ACTION_DOWN:           // Primer dedo toca
            case MotionEvent.ACTION_POINTER_DOWN:  // Segundo y siguientes tocan
                break;

//            case MotionEvent.ACTION_UP:                  /   // Al levantar el último dedo
            case MotionEvent.ACTION_POINTER_UP:  // Al levan22222222222222222222222222222/2222222222222222222222222222222/tar un dedo que no es el último
                if(pulsa(juego,event)){
                        return 1;
                }
//                else if(pulsa(juego,event)){
//                    return 99;
//                }
//                else if(pulsa(game,event)){
//                    return 98;
//                }
//                else if(pulsa(records,event)){
//                    return 97;
//                }
                break;

            case MotionEvent.ACTION_MOVE: // Se mueve alguno de los dedos

                break;
            default:
                Log.i("Otra acción", "Acción no definida: " + accion);
        }


        return idEscena;
    }

}
