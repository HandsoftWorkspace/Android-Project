package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

public class Opciones extends Escena {
    Canvas c;

    RectF rectVolumen, rectVibracion;
    float x;
    public Opciones(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);
//        c.drawText("OPCIONES",altoPantalla/2,altoPantalla/2,anchoPantalla/2,altoPantalla/2,pBoton);
//        pBoton.setColor(Color.BLACK);
        rectVolumen = new RectF(anchoPantalla / 7 * 3, altoPantalla / 16, anchoPantalla / 7 * 4, altoPantalla / 10 * 2);
        rectVibracion = new RectF(anchoPantalla / 7 * 3, altoPantalla / 9 * 2, anchoPantalla / 7 * 4, altoPantalla / 11 * 4);
    }

//    @Override
//    public int onTouchEvent(MotionEvent event) {
//        int idNuevo = super.onTouchEvent(event);
//        int accion = event.getActionMasked();             //Obtenemos el tipo de pulsación
//        switch (accion) {
//            case MotionEvent.ACTION_DOWN:           // Primer dedo toca
//            case MotionEvent.ACTION_POINTER_DOWN:  // Segundo y siguientes tocan
//                break;
//            case MotionEvent.ACTION_UP:                     // Al levantar el último dedo
//            case MotionEvent.ACTION_POINTER_UP:  // Al levantar un dedo que no es el último
//                if (pulsa(rectVolumen, event)) {
//                    volumen = !volumen;
//                    return 95;
//                } else if (pulsa(rectVibracion, event)) vibracion = !vibracion;
//                else if (pulsa(volverMenu, event)) return 0;
//                break;
//            case MotionEvent.ACTION_MOVE: // Se mueve alguno de los dedos
//                break;
//            default:
//                Log.i("Otra acción", "Acción no definida: " + accion);
//        }
//        if (idNuevo != idEscena) {
//            return idNuevo;
//        } else {
//            return idEscena;
//        }
//    }

//    @Override
//    public void dibujar(Canvas c) {
//        try {
//            super.dibujar(c);
////        c.drawRect(rectVolumen, pBoton);
////        c.drawRect(rectVibracion, pBoton);
//            c.drawText(context.getText(R.string.volumen).toString(), anchoPantalla / 7, altoPantalla / 6, paintIconos);
//            c.drawText(context.getText(R.string.vibrar).toString(), anchoPantalla / 7, altoPantalla / 6 * 2, paintIconos);
//            x = anchoPantalla / 21 * 10;
//            if (volumen) {
//                c.drawText(context.getText(R.string.volumenON).toString(), x, altoPantalla / 6, paintIconos);
//            } else {
//                c.drawText(context.getText(R.string.volumenOFF).toString(), x, altoPantalla / 6, paintIconos);
//            }
//            if (vibracion) {
//                c.drawText(context.getText(R.string.vibrarON).toString(), x, altoPantalla / 6 * 2, paintIconos);
//            } else {
//                c.drawText(context.getText(R.string.vibrarOFF).toString(), x / 30 * 29, altoPantalla / 6 * 2, paintIconos);
//            }
//        } catch (Exception e) {
//        }
//    }

}
