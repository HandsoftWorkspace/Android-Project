package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;


public class ConfirmacionCierre extends Escena {

    Bitmap btnOk, btnCancel; // botones
    Rect rectBtnOk, rectBtnCancel; // recs para detección de pulsaciones
    int proporcionAncho, proporcionAlto; // proporciones para el dibujado de pantalla
    String strCierra; // recurso de texto para usar distintos idiomas
    int idUltimaEscena; // entero que nos dará id que índica la última escena

    /**
     * Contructor que inicializa las propiedades de la clase
     * @param context Contexto de la aplicacion
     * @param idEscena Numero asociado a la escena
     * @param anchoPantalla Ancho de pantalla del dispositivo
     * @param altoPantalla Alto de pantalla del dispositivo
     */
    public ConfirmacionCierre(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);
        this.idUltimaEscena = idEscena; // se asigna a ultimas escena el id de la escena actual
        // proporciones de pantalla para adaptar los recursos a distintos dispositivos
        this.proporcionAncho = anchoPantalla / 18;
        this.proporcionAlto = altoPantalla / 9;
        // fondo escena
        bitmapFondo = utils.setFondo(anchoPantalla, altoPantalla, esDeDia);
        // objeto fondo para el scroll
        fondoNubes = new Fondo(utils.setNubes(anchoPantalla, altoPantalla), anchoPantalla, 6);
        // botones
        btnOk = utils.getBitmapFromAssets("varios/ok.png");
        btnOk = btnOk = Bitmap.createScaledBitmap(btnOk, proporcionAncho * 2, proporcionAlto * 2, false);
        btnCancel = utils.getBitmapFromAssets("varios/cancel.png");
        btnCancel = Bitmap.createScaledBitmap(btnCancel, proporcionAncho * 2, proporcionAlto * 2, false);
        // rect para detección de pulsaciones de los botones
        rectBtnOk = new Rect(proporcionAncho * 5, proporcionAlto * 3, proporcionAncho * 7, proporcionAlto * 5);
        rectBtnCancel = new Rect(proporcionAncho * 11, proporcionAlto * 3, proporcionAncho * 13, proporcionAlto * 5);
        // Text y ajustes de texto
        faw = Typeface.createFromAsset(context.getAssets(), "fonts/Moonlight.ttf");
        pTexto.setColor(Color.YELLOW);
        pTexto.setTextSize(80);
        pTexto.setTypeface(faw);
        strCierra = context.getString(R.string.confirmarsalida);
        bitmapFondo = utils.setFondo(anchoPantalla, altoPantalla, esDeDia);
    }

    @Override
    public boolean pulsa(Rect boton, MotionEvent event) {
        return super.pulsa(boton, event);
    }

    /**
     * Controla y gestiona las pulsaciones y gestos en la pantalla
     *
     * @param event Tipo de evento tactil que sucede
     * @return Devuelve un entero que indice el número de escena
     */
    @Override
    public int onTouchEvent(MotionEvent event) {
        int pointerIndex = event.getActionIndex();        //Obtenemos el índice de la acción
        int pointerID = event.getPointerId(pointerIndex); //Obtenemos el Id del pointer asociado a la acción
        int accion = event.getActionMasked();
        switch (accion) {
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if (pulsa(rectBtnOk, event)) {
                    System.exit(0);
                    break;
                } else if (pulsa(rectBtnCancel, event)) {
//                    return idUltimaEscena;
                    return 0;
                }
        }
        return idEscena;
    }

    /**
     * Actualizamos la fisica de los elementos en pantalla
     */
    public void actualizarFisica() {
        fondoNubes.mover();
        float aux = fondoNubes.posicion.x;
        Log.d("posX", " " + aux + " velocidad" + fondoNubes.posicion2.x + " " +
                fondoNubes.velocidad);
    }

    /**
     * Rutina de dibujo en el lienzo. Se le llamara desde el hilo juego
     *
     * @param c Canvas de la aplicación
     */
    public void dibujar(Canvas c) {
        try {
            c.drawBitmap(bitmapFondo, 0, 0, null);
            fondoNubes.dibujar(c);
            c.drawBitmap(btnOk, proporcionAncho * 5, proporcionAlto * 3, null);
            c.drawBitmap(btnCancel, proporcionAncho * 11, proporcionAlto * 3, null);
            c.drawText(strCierra, proporcionAncho * 3, proporcionAlto * 2, pTexto);
        } catch (NullPointerException e) {
            Log.d("Error", "Dibujado canvas Opciones");
        }
    }

}
