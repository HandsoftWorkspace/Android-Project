package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;


public class ConfirmacionBorrado extends Escena {
    /**
     * Bitmaps para los botones
     */
    Bitmap btnOk, btnCancel;
    /**
     * Rects asociados a los botones
     */
    Rect rectBtnOk, rectBtnCancel;
    /**
     * Proporciones de pantalla
     */
    int proporcionAncho, proporcionAlto;
    /**
     * Recursos de texto para distintos idiomas
     */
    String strBorra;
    /**
     * Almacena el identificador de la última escena que ha sido cargada
     */
    int idUltimaEscena;

    /**
     * Contructor que inicializa las propiedades de la clase
     *
     * @param context       Contexto de la aplicacion
     * @param idEscena      Numero asociado a la escena
     * @param anchoPantalla Ancho de pantalla del dispositivo
     * @param altoPantalla  Alto de pantalla del dispositivo
     */
    public ConfirmacionBorrado(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);
        this.idUltimaEscena = idEscena;
        this.proporcionAncho = anchoPantalla / 18;
        this.proporcionAlto = altoPantalla / 9;
        // Fondo escena
        bitmapFondo = utils.setFondo(anchoPantalla, altoPantalla, esDeDia);
        // objetos scroll
        fondoNubes = new Fondo(utils.setNubes(anchoPantalla, altoPantalla), anchoPantalla, 6);
        // botones
        btnOk = utils.getBitmapFromAssets("varios/ok.png");
        btnOk = btnOk = Bitmap.createScaledBitmap(btnOk, proporcionAncho * 2, proporcionAlto * 2, false);
        btnCancel = utils.getBitmapFromAssets("varios/cancel.png");
        btnCancel = Bitmap.createScaledBitmap(btnCancel, proporcionAncho * 2, proporcionAlto * 2, false);
        rectBtnOk = new Rect(proporcionAncho * 5, proporcionAlto * 3, proporcionAncho * 7, proporcionAlto * 5);
        rectBtnCancel = new Rect(proporcionAncho * 11, proporcionAlto * 3, proporcionAncho * 13, proporcionAlto * 5);
        // text
        faw = Typeface.createFromAsset(context.getAssets(), "fonts/Moonlight.ttf");
        pTexto.setColor(Color.YELLOW);
        pTexto.setTextSize(80);
        pTexto.setTypeface(faw);
        strBorra = context.getString(R.string.confirmarvaciado);
    }

    /**
     * Se le pasa un boton y un evento, comprueba con una booleana si ha sido comprobado
     *
     * @param boton Rect asociado a un boton
     * @param event Recibe el tipo de evento
     * @return Devuelve una booleana para detectar si se ha pulsado un recto
     */
    @Override
    public boolean pulsa(Rect boton, MotionEvent event) {
        return super.pulsa(boton, event);
    }

    /**
     * Controla los eventos que suceden sobre la pantalla del dispositivo
     *
     * @param event Tipo de evento que sucede
     * @return Devuelve el tipo de evento
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
                    context.deleteDatabase("puntuaciones");
                    Records.listado.clear();
                    return 3;
                } else if (pulsa(rectBtnCancel, event)) {
                    return 3;
                }
        }
        return idEscena;
    }

    /**
     * Actualizamos la fisica de los elementos en pantalla
     */
    public void actualizarFisica() {
        bitmapFondo = utils.setFondo(anchoPantalla, altoPantalla, esDeDia);
        fondoNubes.mover();
    }

    /**
     * Rutina de dibujo en el lienzo. Se le llamara desde el hilo juego
     *
     * @param c Canvas de la aplicación
     */
    public void dibujar(Canvas c) {
        try {
            // Rects
            // Bitmaps
            c.drawBitmap(bitmapFondo, 0, 0, null);
            fondoNubes.dibujar(c);
            c.drawBitmap(btnOk, proporcionAncho * 5, proporcionAlto * 3, null);
            c.drawBitmap(btnCancel, proporcionAncho * 11, proporcionAlto * 3, null);
            // Text

            c.drawText(strBorra, proporcionAncho * 3, proporcionAlto * 2, pTexto);
        } catch (NullPointerException e) {
        }
    }

}
