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

import static com.example.workspace.myapplication.Menu.mediaPlayer;

public class ConfirmacionCierre extends Escena {

    Bitmap btnVolverMenu, btnOk, btnCancel;

    Rect rectVolverMenu, rectBtnOk, rectBtnCancel;

    boolean prueba;

    int proporcionAncho, proporcionAlto;

    int idUltimaEscena;

    Paint paint;
    Utils utils;

    // TODO al crear el constructor no se ve nada en pantalla
    public ConfirmacionCierre(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);
        this.idUltimaEscena = idEscena;
        this.proporcionAncho = anchoPantalla / 18;
        this.proporcionAlto = altoPantalla / 9;

        utils = new Utils(context);
        paint = new Paint();

        fondo = BitmapFactory.decodeResource(context.getResources(), R.drawable.backgroundmountains);
        fondo = Bitmap.createScaledBitmap(fondo, anchoPantalla, altoPantalla, false);

        btnOk = utils.getBitmapFromAssets("varios/ok.png");
        btnOk = btnOk = Bitmap.createScaledBitmap(btnOk, proporcionAncho * 2, proporcionAlto * 2, false);

        btnCancel = utils.getBitmapFromAssets("varios/cancel.png");
        btnCancel = Bitmap.createScaledBitmap(btnCancel, proporcionAncho * 2, proporcionAlto * 2, false);

        rectBtnOk = new Rect(proporcionAncho * 5, proporcionAlto * 3, proporcionAncho * 7, proporcionAlto * 5);
        rectBtnCancel = new Rect(proporcionAncho * 11, proporcionAlto * 3, proporcionAncho * 13, proporcionAlto * 5);
    }

    @Override
    public boolean pulsa(Rect boton, MotionEvent event) {
        return super.pulsa(boton, event);
    }

    @Override
    public int onTouchEvent(MotionEvent event) {
        int pointerIndex = event.getActionIndex();        //Obtenemos el índice de la acción
        int pointerID = event.getPointerId(pointerIndex); //Obtenemos el Id del pointer asociado a la acción
        int accion = event.getActionMasked();
        switch (accion) {
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if (pulsa(rectBtnOk, event)) {
                    //System.exit(0);
                    break;
                } else if (pulsa(rectBtnCancel, event)) {
                    return idUltimaEscena;
                }
        }
//        return super.onTouchEvent(event);
        return idEscena;
    }

    public void dibujar(Canvas c) {
        try {
//            super.dibujar(c);
            // Bitmaps
            c.drawBitmap(fondo, 0, 0, null);
            c.drawBitmap(btnOk, proporcionAncho * 5, proporcionAlto * 3, null);
            c.drawBitmap(btnCancel, proporcionAncho * 11, proporcionAlto * 3, null);
            // Rects
//            p.setColor(Color.GREEN);
            c.drawRect(rectBtnOk, paint);
            c.drawRect(rectBtnCancel, paint);
            // Text
            c.drawText(R.string.confirmarsalida + "", proporcionAncho * 6, proporcionAlto * 1, p);
        } catch (NullPointerException e) {
            Log.d("Error", "Dibujado canvas Opciones");
        }
    }

}
