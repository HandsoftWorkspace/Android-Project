package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import static com.example.workspace.myapplication.Menu.mediaPlayer;

public class Ayuda extends Escena {

    Context context;

    int anchoPantalla, altoPantalla;
    int proporcionAncho, proporcionAlto;

    Bitmap fondoTutorial;

    String strAyuda;

    public Ayuda(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);

        this.context = context;
        this.anchoPantalla = anchoPantalla;
        this.altoPantalla = altoPantalla;

        this.proporcionAncho = anchoPantalla / 18;
        this.proporcionAlto = altoPantalla / 9;

        // Bitmaps
        bitmapFondo = utils.setFondo(anchoPantalla, altoPantalla, esDeDia);
        fondoNubes = new Fondo(utils.setNubes(anchoPantalla, altoPantalla), anchoPantalla, 6);

        fondoTutorial = utils.getBitmapFromAssets("varios/fondotutorial.png");
        fondoTutorial = Bitmap.createScaledBitmap(fondoTutorial, anchoPantalla - proporcionAncho * 2 - proporcionAncho / 2, altoPantalla - proporcionAlto * 2 - proporcionAlto / 2, false);

        volverMenu = BitmapFactory.decodeResource(context.getResources(), R.drawable.close2);
        volverMenu = Bitmap.createScaledBitmap(volverMenu, proporcionAncho * 2, proporcionAlto * 2, false);

        // Rects
        rectVolverMenu = new Rect(0, 0, proporcionAncho * 2, proporcionAlto * 2);

        strAyuda = context.getString(R.string.ayuda);
    }

    /**
     * Actualizamos la física de los elementos en pantalla
     */
    public void actualizarFisica() {
        fondoNubes.mover();
    }

    /**
     * @param c
     */
    @Override
    public void dibujar(Canvas c) {
        try {
            super.dibujar(c);
            c.drawBitmap(bitmapFondo, 0, 0, null);
            fondoNubes.dibujar(c);
            c.drawBitmap(volverMenu, 0, proporcionAlto * 0, null);
            c.drawBitmap(fondoTutorial, proporcionAncho * 2, proporcionAlto * 2, null);

            paintTexto.setColor(Color.YELLOW); //
            paintTexto.setTextSize(50); //
            paintTexto.setTypeface(faw);//
//            c.drawText(nombreOpciones + "", proporcionAncho * 4, proporcionAlto * 2, paintTexto); //
            c.drawText(strAyuda, proporcionAncho * 2 + proporcionAncho / 3, proporcionAlto, paintTexto);

        } catch (NullPointerException e) {
            Log.d("Error", "Dibujado canvas Opciones");
        }
    }

    /**
     * @param event
     * @return
     */
    @Override
    public int onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        int pointerIndex = event.getActionIndex();        //Obtenemos el índice de la acción
        int pointerID = event.getPointerId(pointerIndex); //Obtenemos el Id del pointer asociado a la acción
        int accion = event.getActionMasked();

        switch (accion) {
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if (pulsa(rectVolverMenu, event)) {
                    return 0;
                }
        }
        return idEscena;
    }

}
