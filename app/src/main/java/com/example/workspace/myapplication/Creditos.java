package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.MotionEvent;


public class Creditos extends Escena {
    /**
     * Contexto de la aplicacion
     */
    Context context;
    /**
     * Alto y ancho de la pantalla del dispositivo
     */
    int anchoPantalla, altoPantalla;
    /**
     * Divisores del tamaño de la pantalla, para adaptar los distintos objetos a diferentes resoluciones
     */
    int proporcionAncho, proporcionAlto;
    /**
     * Fuentes que se utilizan para gestionar diferentes idiomas en la aplicación
     */
    String nombreProyecto, proyecto, nombre, hechoPor, nombreJuego, fuentesRecursos, strCreditos, strMusica, strFuente, strCreadoPor;

    /**
     * Contructor que inicializa las propiedades de la clase
     *
     * @param context       Contexto de la aplicacion
     * @param idEscena      Numero asociado a la escena
     * @param anchoPantalla Ancho de pantalla del dispositivo
     * @param altoPantalla  Alto de pantalla del dispositivo
     */
    public Creditos(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);
        this.context = context;
        // tamaños de pantalla
        this.anchoPantalla = anchoPantalla;
        this.altoPantalla = altoPantalla;
        // proporciones de pantalla
        this.proporcionAncho = anchoPantalla / 18;
        this.proporcionAlto = altoPantalla / 9;
        // Bitmaps
        bitmapFondo = utils.setFondo(anchoPantalla, altoPantalla, esDeDia);
        fondoNubes = new Fondo(utils.setNubes(anchoPantalla, altoPantalla), anchoPantalla, 6); // objeto de tipo 'Fondo' que será un scroll
        // btn para volver al menú de la aplicación
        volverMenu = BitmapFactory.decodeResource(context.getResources(), R.drawable.close2);
        volverMenu = Bitmap.createScaledBitmap(volverMenu, proporcionAncho * 2, proporcionAlto * 2, false);
        // Rects
        rectVolverMenu = new Rect(0, 0, proporcionAncho * 2, proporcionAlto * 2);
        // string para idiomas
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
        // gestión de los ajustes de pintado de texto
        p.setColor(Color.YELLOW);
        p.setTypeface(faw);
    }

    /**
     * Actualizamos la fisica de los elementos en pantalla
     */
    public void actualizarFisica() {
        fondoNubes.mover(); // mueve el scroll
    }

    /**
     * Rutina de dibujo en el lienzo. Se le llamara desde el hilo juego
     *
     * @param c Canvas de la aplicacion
     */
    @Override
    public void dibujar(Canvas c) {
        super.dibujar(c);
        // Bitmaps
        c.drawBitmap(bitmapFondo, 0, 0, null); // fondo
        fondoNubes.dibujar(c); // objeto tipo 'Fondo'
        c.drawBitmap(volverMenu, 0, 0, null); // botón para volver al menú
        // Textos
        p.setTextSize(50);
        c.drawText(strCreditos, proporcionAncho * 2 + proporcionAncho / 3, proporcionAlto, p);
        p.setTextSize(30);
        // textos
        c.drawText(nombreProyecto + ": " + proyecto + " final Android, Colegio Vivas", proporcionAncho * 2, proporcionAlto * 2 + proporcionAlto / 2, p);
        c.drawText(nombre + ": " + nombreJuego, proporcionAncho * 2, proporcionAlto * 3 + proporcionAlto / 2, p);
        c.drawText(hechoPor + ": Daniel Vazquez Rodriguez", proporcionAncho * 2, proporcionAlto * 4 + proporcionAlto / 2, p);
        c.drawText(fuentesRecursos + ": Icons Icons, game2Art, craftFix", proporcionAncho * 2, proporcionAlto * 5 + proporcionAlto / 2, p);
        c.drawText(strMusica + ": SoundBible", proporcionAncho * 2, proporcionAlto * 6 + proporcionAlto / 2, p);
        c.drawText(strFuente + " " + strCreadoPor + ": Darrell Flood", proporcionAncho * 2, proporcionAlto * 7 + proporcionAlto / 2, p);

    }

    /**
     * Controla y gestiona las pulsaciones y gestos en la pantalla
     *
     * @param event Tipo de evento tactil que sucede
     * @return Devuelve un entero que indice el numero de escena
     */
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


