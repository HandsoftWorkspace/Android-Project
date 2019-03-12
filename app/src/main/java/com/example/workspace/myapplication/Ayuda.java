package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

public class Ayuda extends Escena {

    Context context;

    private int anchoPantalla, altoPantalla;
    private int proporcionAncho, proporcionAlto;
    private int contador = 0;

    Bitmap fondoTutorial, btnNext, btnBack; // fondo y botones

    Rect rectNext, rectBack; // rect para los respectivos botones


    String ayudaIzq, ayudaDer, ayudaLatigo, ayudaPuntos, ayudaVidasTotales, ayudaRondaActual, ayudaVidas, ayudaPierdeVidas, strLatigo, strAyuda; // direcursos de texto para distintos idiomas

    /**
     * Metodo contructor que inicializa las propiedades de la clase 'ayuda'
     *
     * @param context
     * @param idEscena
     * @param anchoPantalla
     * @param altoPantalla
     */
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

        btnNext = utils.getBitmapFromAssets("varios/next.png");
        btnNext = Bitmap.createScaledBitmap(btnNext, proporcionAncho * 2, proporcionAlto * 2, false);

        btnBack = utils.getBitmapFromAssets("varios/back.png");
        btnBack = Bitmap.createScaledBitmap(btnBack, proporcionAncho * 2, proporcionAlto * 2, false);
        // Rects
        rectVolverMenu = new Rect(0, 0, proporcionAncho * 2, proporcionAlto * 2);
        rectNext = new Rect(anchoPantalla - proporcionAncho * 2, 0, anchoPantalla, proporcionAlto * 2);
        rectBack = new Rect(proporcionAncho * 13, 0, proporcionAncho * 15, proporcionAlto * 2);
        strAyuda = context.getString(R.string.ayuda);

//        ayudaIzq, ayudaDer, ayudaLatigo, ayudaVidasTotales, ayudaRondaActual, ayudaVidas, ayudaPierdeVidas;
        ayudaIzq = context.getString(R.string.ayudaizq);
        ayudaDer = context.getString(R.string.ayudader);
        ayudaLatigo = context.getString(R.string.ayudalatigo);
        ayudaVidasTotales = context.getString(R.string.ayudavidastotales);
        ayudaRondaActual = context.getString(R.string.ayudaronda);
        ayudaVidas = context.getString(R.string.ayudavidas);
        ayudaPierdeVidas = context.getString(R.string.ayudapierdevidas);
        ayudaPuntos = context.getString(R.string.ayudapuntos);
        strLatigo = context.getString(R.string.latigo);

    }

    /**
     * Actualizamos la fisica de los elementos en pantalla
     */
    public void actualizarFisica() {
        fondoNubes.mover();
    }

    /**
     * Rutina de dibujo en el lienzo. Se le llamara desde el hilo juego
     *
     * @param c canvas de la aplicacion
     */
    @Override
    public void dibujar(Canvas c) {
        try {
            super.dibujar(c);
            c.drawBitmap(bitmapFondo, 0, 0, null);
            fondoNubes.dibujar(c);
            c.drawBitmap(fondoTutorial, proporcionAncho * 2, proporcionAlto * 2, null);
            c.drawBitmap(volverMenu, 0, proporcionAlto * 0, null);
            c.drawBitmap(btnNext, anchoPantalla - proporcionAncho * 2, proporcionAlto * 0, null);
            c.drawBitmap(btnBack, proporcionAncho * 13, 0, null);

            paintTexto.setColor(Color.YELLOW); //
            paintTexto.setTypeface(faw);//

            paintTexto.setTextSize(50); //
            c.drawText(strAyuda, proporcionAncho * 2 + proporcionAncho / 3, proporcionAlto + proporcionAlto / 2, paintTexto);
            paintTexto.setTextSize(30); //
            if (contador == 0) {
                c.drawText(ayudaVidasTotales, proporcionAncho * 2, proporcionAlto * 3, paintTexto);
            } else if (contador == 1) {
                c.drawText(ayudaIzq, proporcionAncho * 3 + proporcionAncho / 2, proporcionAlto * 7, paintTexto);
            } else if (contador == 2) {
                c.drawText(ayudaDer, proporcionAncho * 12, proporcionAlto * 7, paintTexto);
            } else if (contador == 3) {
                c.drawText(ayudaPuntos, proporcionAncho * 8, proporcionAlto * 3, paintTexto);
            } else if (contador == 4) {
                c.drawText(ayudaRondaActual, proporcionAncho * 12, proporcionAlto * 3, paintTexto);
            } else if (contador == 5) {
                c.drawText(ayudaLatigo, proporcionAncho * 8 + proporcionAncho / 2, altoPantalla / 2 + proporcionAlto, paintTexto);
            } else if (contador == 6) {
                c.drawText(strLatigo, anchoPantalla / 2, proporcionAlto * 8, paintTexto);
            } else if (contador == 7) {
                c.drawText(ayudaVidas, proporcionAncho * 5 + proporcionAncho / 2, proporcionAlto * 6, paintTexto);
            } else if (contador == 8) {
                c.drawText(ayudaPierdeVidas, proporcionAncho * 8, proporcionAlto * 6, paintTexto);
            }


        } catch (NullPointerException e) {
            Log.d("Error", "Dibujado canvas Opciones");
        }
    }

    /**
     * Controla y gestiona las pulsaciones y gestos en la pantalla
     *
     * @param event Tipo de evento tactil que sucede
     * @return Devuelve un entero que indice el numero de escena
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
                if (pulsa(rectNext, event)) {
                    contador++;
                    if (contador == 8) {
                        contador = 0;
                    }
                }
                if (pulsa(rectBack, event)) {
                    if (contador != -1) {
                        contador--;
                    }
                    if (contador == 0) {
                        contador = 8;
                    }
                }
        }
        return idEscena;
    }

}
