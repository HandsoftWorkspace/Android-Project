package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;

import static com.example.workspace.myapplication.Menu.mediaPlayer;

public class ConfirmacionBorrado extends Escena {

    Bitmap btnOk, btnCancel; // bitmaps botones
    Rect rectBtnOk, rectBtnCancel; // rects asociados a los botones
    int proporcionAncho, proporcionAlto; // proporciones de pantalla
    String strBorra; // recursos de texto para distintos idiomas
    int idUltimaEscena; // almacena el identificador de la última escena que ha sido cargada

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
        strBorra = context.getString(R.string.confirmarvaciado);
    }

    /**
     * Se le pasa un botón y un evento, comprueba con una booleana si ha sido comprobado
     *
     * @param boton
     * @param event
     * @return
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
                    return 3;
                } else if (pulsa(rectBtnCancel, event)) {
                    return 3;
                }
        }
        return idEscena;
    }

    /**
     * Actualizamos la física de los elementos en pantalla
     */
    public void actualizarFisica() {
        bitmapFondo = utils.setFondo(anchoPantalla, altoPantalla, esDeDia);
        fondoNubes.mover();
    }

    /**
     * Rutina de dibujo en el lienzo. Se le llamará desde el hilo juego
     *
     * @param c canvas de la aplicación
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
            pTexto.setTypeface(faw);
            c.drawText(strBorra, proporcionAncho * 3, proporcionAlto * 2, pTexto);
        } catch (NullPointerException e) {
            Log.d("Error", "Dibujado canvas Opciones");
        }
    }

}
