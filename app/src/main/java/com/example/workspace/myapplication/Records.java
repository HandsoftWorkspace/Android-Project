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

    private Bitmap volverMenu, one, two, three, table, star1, star2, star3;

    Paint p = new Paint();

    public Records(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);
        this.proporcionAncho = anchoPantalla / 18;
        this.proporcionAlto = altoPantalla / 9;

        fondo = BitmapFactory.decodeResource(context.getResources(), R.drawable.backgroundmountains);
        fondo = Bitmap.createScaledBitmap(fondo, anchoPantalla, altoPantalla, false);

        volverMenu = BitmapFactory.decodeResource(context.getResources(), R.drawable.close2);
        volverMenu = Bitmap.createScaledBitmap(volverMenu, proporcionAncho * 2, proporcionAlto * 2, false);

        one = BitmapFactory.decodeResource(context.getResources(), R.drawable.one);
        one = Bitmap.createScaledBitmap(one, proporcionAncho, proporcionAlto, false);

        two = BitmapFactory.decodeResource(context.getResources(), R.drawable.two);
        two = Bitmap.createScaledBitmap(two, proporcionAncho, proporcionAlto, false);

        three = BitmapFactory.decodeResource(context.getResources(), R.drawable.three);
        three = Bitmap.createScaledBitmap(three, proporcionAncho, proporcionAlto, false);

        table = BitmapFactory.decodeResource(context.getResources(), R.drawable.table);
        table = Bitmap.createScaledBitmap(table, proporcionAncho * 4, proporcionAlto, false);

        star1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.star1);
        star1 = Bitmap.createScaledBitmap(star1, proporcionAncho, proporcionAlto, false);

        star2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.star2);
        star2 = Bitmap.createScaledBitmap(star2, proporcionAncho * 2, proporcionAlto, false);

        star3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.star3);
        star3 = Bitmap.createScaledBitmap(star3, proporcionAncho * 3, proporcionAlto, false);

        p.setColor(Color.TRANSPARENT);
        rectVolverMenu = new Rect(0, 0, proporcionAncho * 2, proporcionAlto * 2);

    }

//    @Override
//    public boolean pulsa(Rect boton, MotionEvent event) {
//        return super.pulsa(boton, event);
//    }

    @Override
    public int onTouchEvent(MotionEvent event) {
        int pointerIndex = event.getActionIndex();        //Obtenemos el índice de la acción
        int pointerID = event.getPointerId(pointerIndex); //Obtenemos el Id del pointer asociado a la acción
        int accion = event.getActionMasked();

        switch (accion) {
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:  // Al levantar un dedo que no es
                if (pulsa(rectVolverMenu, event)) {
                    return 0;
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void dibujar(Canvas c) {
        super.dibujar(c);
        c.drawRect(rectVolverMenu, p);
        c.drawBitmap(fondo, 0, 0, null);

        c.drawBitmap(volverMenu, 0, proporcionAlto * 0, null);

        c.drawBitmap(one, proporcionAncho * 4, proporcionAlto * 2, null);
        c.drawBitmap(table, proporcionAncho * 6, proporcionAlto * 2, null);
        c.drawBitmap(star1, proporcionAncho * 11, proporcionAlto * 2, null);

        c.drawBitmap(two, proporcionAncho * 4, proporcionAlto * 4, null);
        c.drawBitmap(table, proporcionAncho * 6, proporcionAlto * 4, null);
        c.drawBitmap(star2, proporcionAncho * 11, proporcionAlto * 4, null);

        c.drawBitmap(three, proporcionAncho * 4, proporcionAlto * 6, null);
        c.drawBitmap(table, proporcionAncho * 6, proporcionAlto * 6, null);
        c.drawBitmap(star3, proporcionAncho * 11, proporcionAlto * 6, null);
    }
}