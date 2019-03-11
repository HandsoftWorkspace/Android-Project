package com.example.workspace.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;

public class Records extends Escena {
    int proporcionAncho, proporcionAlto; // proporciones que se utilizarán para el pintado en diferentes tamaños de pantalla
    private Bitmap one, two, three, table, star1, star2, star3, btnBorrado; // dinstintos bitmaps de la clae records
    private static ArrayList<Integer> listado = new ArrayList<>(); // colección donde se guardan las puntuaciones obtenida de la base de datos 'puntuaciones'
    private String strRecords; // recurso string para distintos idiomas
    Rect rectBorrado; // rect para detecar un evento en una zona de pantalla

    /**
     * Método contructor que inicializa las propiedades de la clase crétidos
     *
     * @param context
     * @param idEscena
     * @param anchoPantalla
     * @param altoPantalla
     */
    public Records(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);
        // proporciones de pantalla
        this.proporcionAncho = anchoPantalla / 18;
        this.proporcionAlto = altoPantalla / 9;
        // fondo escena records
        bitmapFondo = utils.setFondo(anchoPantalla, altoPantalla, esDeDia);
        // objetos de tipo fondo, hace efecto scroll
        fondoNubes = new Fondo(utils.setNubes(anchoPantalla, altoPantalla), anchoPantalla, 6);
        // bitmaps de botones
        volverMenu = BitmapFactory.decodeResource(context.getResources(), R.drawable.close2);
        volverMenu = Bitmap.createScaledBitmap(volverMenu, proporcionAncho * 2, proporcionAlto * 2, false);
        btnBorrado = utils.getBitmapFromAssets("varios/borrado.png");
        btnBorrado = Bitmap.createScaledBitmap(btnBorrado, proporcionAncho * 2, proporcionAlto * 2, false);
        // bitmaps para interfaz de usuario
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
        // rect de botones
        rectVolverMenu = new Rect(0, 0, proporcionAncho * 2, proporcionAlto * 2);
        rectBorrado = new Rect(anchoPantalla - proporcionAncho * 2, 0, anchoPantalla, proporcionAlto * 2);
        // tipo de fuentes
        faw = Typeface.createFromAsset(context.getAssets(), "fonts/Moonlight.ttf");
        // recurso para multiples idiomas
        strRecords = context.getString(R.string.logros);

    }

    /**
     * Controla y gestiona las pulsaciones y gestos en la pantalla
     *
     * @param event Tipo de evento táctil que sucede
     * @return Devuelve un entero que índice el número de escena
     */
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
                } else if (pulsa(rectBorrado, event)) {
                    return 7;
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * Actualizamos la física de los elementos en pantalla
     */
    public void actualizarFisica() {
        fondoNubes.mover();
    }

    /**
     * Se encargará de pintar dentro de la clase records
     *
     * @param c canvas de la aplicación
     */
    @Override
    public void dibujar(Canvas c) {
        super.dibujar(c);
        paintTexto.setColor(Color.YELLOW);
        paintTexto.setTextSize(50);
        paintTexto.setTypeface(faw);
        Log.d("debug", (c == null) + " " + (p == null) + " " + (rectVolverMenu == null));
        c.drawRect(rectVolverMenu, p);
        c.drawBitmap(bitmapFondo, 0, 0, null);
        fondoNubes.dibujar(c);
        c.drawBitmap(volverMenu, 0, proporcionAlto * 0, null);
        c.drawBitmap(btnBorrado, anchoPantalla - proporcionAncho * 2, proporcionAlto * 0, null);

        c.drawBitmap(one, proporcionAncho * 4, proporcionAlto * 2, null);
        c.drawBitmap(table, proporcionAncho * 6, proporcionAlto * 2, null);
        c.drawBitmap(star3, proporcionAncho * 11, proporcionAlto * 2, null);

        c.drawBitmap(two, proporcionAncho * 4, proporcionAlto * 4, null);
        c.drawBitmap(table, proporcionAncho * 6, proporcionAlto * 4, null);
        c.drawBitmap(star2, proporcionAncho * 11, proporcionAlto * 4, null);

        c.drawBitmap(three, proporcionAncho * 4, proporcionAlto * 6, null);
        c.drawBitmap(table, proporcionAncho * 6, proporcionAlto * 6, null);
        c.drawBitmap(star1, proporcionAncho * 11, proporcionAlto * 6, null);

        c.drawText(strRecords, proporcionAncho * 2 + proporcionAncho / 3, proporcionAlto, paintTexto);

        for (int i = 0; i < 3 && i < listado.size(); i++) {
            Log.d("listado", "listame " + listado.size() + " " + listado.get(i) + "");
            if (i == 0) {
                c.drawText(listado.get(i) + "", proporcionAncho * 6, proporcionAlto * 2 + proporcionAncho / 2, paintTexto);
            } else if (i == 1) {
                c.drawText(listado.get(i) + "", proporcionAncho * 6, proporcionAlto * 4 + proporcionAncho / 2, paintTexto);
            } else if (i == 2) {
                c.drawText(listado.get(i) + "", proporcionAncho * 6, proporcionAlto * 6 + proporcionAncho / 2, paintTexto);
            }
//            c.drawText(listado.get(i) + "", proporcionAncho * 6, (proporcionAlto * 2) * (i + 1) * 2, paintTexto);
//        c.drawText(listado.get(1).toString(), proporcionAncho * 6, proporcionAlto * 4, pTexto);
//        c.drawText(listado.get(2).toString(), proporcionAncho * 6, proporcionAlto * 6, pTexto);
        }

    }

    /**
     * Carga las puntuaciones obtenidas en la base de datos Sqlite, las almacena en una colección para su posterior gestión
     */
    public void cargarPuntuaciones() {
        BaseDatos bd = null;
        SQLiteDatabase bdSqlite = null;
        try {
            bd = new BaseDatos(context, "puntuaciones", null, 1);
            bdSqlite = bd.getReadableDatabase();
            String query = "SELECT puntos from puntuaciones order by puntos DESC LIMIT 3";
            Cursor cursor = bdSqlite.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                int puntos;
                do {
                    puntos = cursor.getInt(0);
                    listado.add(puntos);
                } while (cursor.moveToNext());
            }
        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
            if (bd == null) {
                bd.close();
            }
            if (bdSqlite == null) {
                bdSqlite.close();
            }
        }
    }
}