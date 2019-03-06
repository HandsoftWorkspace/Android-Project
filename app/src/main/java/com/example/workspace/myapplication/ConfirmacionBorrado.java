package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

import static com.example.workspace.myapplication.Menu.mediaPlayer;

public class ConfirmacionBorrado extends Escena {

    Bitmap btnOk, btnCancel;

    Rect rectBtnOk, rectBtnCancel;

    int idUltimaEscena;
    int proporcionAncho, proporcionAlto;

    Utils utils;

    public ConfirmacionBorrado(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);
        this.idUltimaEscena = idEscena;

        this.proporcionAncho = altoPantalla / 18;
        this.proporcionAlto = altoPantalla / 9;

        utils = new Utils(context);

        btnOk = utils.getBitmapFromAssets("varios/ok");
        btnOk = btnOk = Bitmap.createScaledBitmap(btnOk, proporcionAncho * 2, proporcionAlto * 2, false);

        btnCancel = utils.getBitmapFromAssets("varios/cancel");
        btnCancel = Bitmap.createScaledBitmap(btnCancel, proporcionAncho * 2, proporcionAlto * 2, false);
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
                    return 0;
                } else if (pulsa(rectBtnOk, event)) {

                    break;
                } else if (pulsa(rectBtnCancel, event)) {
                    return idUltimaEscena;
                }
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void dibujar(Canvas c) {
        super.dibujar(c);
        // Bitmaps
        c.drawBitmap(btnOk, proporcionAncho * 3, altoPantalla / 2 - btnOk.getHeight(), null);
        c.drawBitmap(btnCancel, proporcionAncho * 11, altoPantalla / 2 - btnCancel.getHeight(), null);
    }

}
