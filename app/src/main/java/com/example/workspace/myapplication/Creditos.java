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

public class Creditos extends Escena {

    Context context;

    int anchoPantalla, altoPantalla;
    int proporcionAncho, proporcionAlto;

    // Fuentes
    String nombreProyecto, proyecto, nombre, hechoPor, nombreJuego, fuentesRecursos, strCreditos, strMusica, strFuente, strCreadoPor;

    public Creditos(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);
        this.context = context;
        this.anchoPantalla = anchoPantalla;
        this.altoPantalla = altoPantalla;

        this.proporcionAncho = anchoPantalla / 18;
        this.proporcionAlto = altoPantalla / 9;

        // Bitmaps
        bitmapFondo = utils.setFondo(anchoPantalla, altoPantalla, esDeDia);
        fondoNubes = new Fondo(utils.setNubes(anchoPantalla, altoPantalla), anchoPantalla, 6);

        volverMenu = BitmapFactory.decodeResource(context.getResources(), R.drawable.close2);
        volverMenu = Bitmap.createScaledBitmap(volverMenu, proporcionAncho * 2, proporcionAlto * 2, false);

        // Rects
        rectVolverMenu = new Rect(0, 0, proporcionAncho * 2, proporcionAlto * 2);

        nombreProyecto = context.getString(R.string.projectname);
        proyecto = context.getString(R.string.proyecto);
        nombre = context.getString(R.string.name);
        hechoPor = context.getString(R.string.hechopor);
        nombreJuego = context.getString(R.string.app_name);
        fuentesRecursos = context.getString(R.string.fuentes);
        strCreditos = context.getString(R.string.creditos);
        strMusica = context.getString(R.string.musica);
        strFuente = context.getString(R.string.fuente);
        strCreadoPor = context.getString(R.string.hechopor);
//        fuentesRecursos;
    }

    /**
     * Actualizamos la física de los elementos en pantalla
     */
    public void actualizarFisica() {
        fondoNubes.mover();
    }

    @Override
    public void dibujar(Canvas c) {
        super.dibujar(c);
        // Bitmaps
        c.drawBitmap(bitmapFondo, 0, 0, null);
        fondoNubes.dibujar(c);
        c.drawBitmap(volverMenu, 0, 0, null);

        // Textos

//        p.setColor(Color.WHITE);
        p.setColor(Color.YELLOW);
        p.setTextSize(50);
        p.setTypeface(faw);
        c.drawText(strCreditos, proporcionAncho * 2 + proporcionAncho / 3, proporcionAlto, p);
        p.setTextSize(30);
        c.drawText(nombreProyecto + ": " + proyecto + " final Android", proporcionAncho * 2, proporcionAlto * 2 + proporcionAlto / 2, p);
        c.drawText(nombre + ": " + nombreJuego, proporcionAncho * 2, proporcionAlto * 3 + proporcionAlto / 2, p);
        c.drawText(hechoPor + ": Daniel Vázquez Rodríguez", proporcionAncho * 2, proporcionAlto * 4 + proporcionAlto / 2, p);
        c.drawText(fuentesRecursos + ": Icons Icons, game2Art, craftFix", proporcionAncho * 2, proporcionAlto * 5 + proporcionAlto / 2, p);
        c.drawText(strMusica + ": SoundBible", proporcionAncho * 2, proporcionAlto * 6 + proporcionAlto / 2, p);
        c.drawText(strFuente + " " + strCreadoPor + ": Darrell Flood", proporcionAncho * 2, proporcionAlto * 7 + proporcionAlto / 2, p);

    }

    @Override
    public int onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);
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
}


