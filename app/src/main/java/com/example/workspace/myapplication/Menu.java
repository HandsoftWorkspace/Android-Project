package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.example.workspace.myapplication.MainActivity;import com.example.workspace.myapplication.R;

public class Menu extends Escena {

    Rect ayuda, opciones, juego, game, records;
    int alto, ancho;

    public Menu(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);
        fondo = BitmapFactory.decodeResource(context.getResources(), R.drawable.fondo);
        fondo = Bitmap.createScaledBitmap(fondo, anchoPantalla, altoPantalla, false);
        alto = altoPantalla / 7;
        ancho = anchoPantalla / 5;

        juego = new Rect(ancho, alto, ancho * 4, alto * 3);
        game = new Rect(ancho,alto,ancho*4,alto*4);
        ayuda = new Rect(ancho, alto, ancho * 4, alto * 3);
        opciones = new Rect(ancho, alto, ancho * 4, alto * 3);
        records = new Rect(ancho, alto, ancho * 4, alto * 3);
    }

    // Actualizamos la física de los elementos en pantalla
    public void actualizarFisica() {

    }

    // Rutina de dibujo en el lienzo. Se le llamará desde el hilo
    public void dibujar(Canvas c) {
        try {
            c.drawBitmap(fondo, 0, 0, null);
            c.drawRect(juego, pBoton);
            c.drawText("Jugar", juego.centerX(), juego.centerY() + alto / 5, pTexto);
            c.drawText("Menú", anchoPantalla / 2, altoPantalla / 5, pTexto);
            c.drawText("Menú", anchoPantalla / 2, altoPantalla / 5 + 30, pTexto2);
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

            case MotionEvent.ACTION_UP:                     // Al levantar el último dedo
            case MotionEvent.ACTION_POINTER_UP:  // Al levantar un dedo que no es el último
                if(pulsa(juego,event)){
                    return 1;
                }
                else if(pulsa(opciones,event)){
                    return 99;
                }
                else if(pulsa(game,event)){
                    return 98;
                }
                else if(pulsa(records,event)){
                    return 97;
                }
                break;

            case MotionEvent.ACTION_MOVE: // Se mueve alguno de los dedos

                break;
            default:
                Log.i("Otra acción", "Acción no definida: " + accion);
        }


        return idEscena;
    }

}
