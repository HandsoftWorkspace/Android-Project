package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

public class Records extends Escena {
    int proporcionAncho, proporcionAlto;

    private Rect rectVolverMenu;

    private Bitmap volverMenu;

    Paint p;

    public Records(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);
        this.proporcionAncho = anchoPantalla / 18;
        this.proporcionAlto = altoPantalla / 9;

        fondo = BitmapFactory.decodeResource(context.getResources(), R.drawable.fondo);
        fondo = Bitmap.createScaledBitmap(fondo, anchoPantalla, altoPantalla, false);

        volverMenu = BitmapFactory.decodeResource(context.getResources(), R.drawable.close2);
        volverMenu = Bitmap.createScaledBitmap(volverMenu, proporcionAncho * 2, proporcionAlto * 2, false);

        rectVolverMenu = new Rect(0, 0, proporcionAncho * 2, proporcionAlto * 2);

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
            case MotionEvent.ACTION_POINTER_UP:  // Al levantar un dedo que no es
                if (pulsa(rectVolverMenu, event)) {
                    Juego juego = new Juego(context);
                    juego.setKeepScreenOn(true);
                    return 0;
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void dibujar(Canvas c) {
        super.dibujar(c);
        c.drawBitmap(fondo, 0, 0, null);

        c.drawBitmap(volverMenu, 0, proporcionAlto * 0, null);
        p.setColor(Color.GREEN);
        c.drawRect(rectVolverMenu, p);
    }
}