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
    int proporcionAncho, proporcionAlto;
    private Bitmap one, two, three, table, star1, star2, star3, btnBorrado;
    private static ArrayList<Integer> listado = new ArrayList<>();
    private String[] strPuntos;
    private String strRecords;

    Rect rectBorrado;

    /**
     *
     * @param context
     * @param idEscena
     * @param anchoPantalla
     * @param altoPantalla
     */
    public Records(Context context, int idEscena, int anchoPantalla, int altoPantalla) {
        super(context, idEscena, anchoPantalla, altoPantalla);
        this.proporcionAncho = anchoPantalla / 18;
        this.proporcionAlto = altoPantalla / 9;

        bitmapFondo = utils.setFondo(anchoPantalla, altoPantalla, esDeDia);
        fondoNubes = new Fondo(utils.setNubes(anchoPantalla, altoPantalla), anchoPantalla, 6);

        volverMenu = BitmapFactory.decodeResource(context.getResources(), R.drawable.close2);
        volverMenu = Bitmap.createScaledBitmap(volverMenu, proporcionAncho * 2, proporcionAlto * 2, false);

        btnBorrado = utils.getBitmapFromAssets("varios/borrado.png");
        btnBorrado = Bitmap.createScaledBitmap(btnBorrado, proporcionAncho * 2, proporcionAlto * 2, false);

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
        rectBorrado = new Rect(anchoPantalla - proporcionAncho * 2, 0, anchoPantalla, proporcionAlto * 2);

        faw = Typeface.createFromAsset(context.getAssets(), "fonts/Moonlight.ttf");
        strRecords = context.getString(R.string.logros);

    }

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

    public void cargarPuntuaciones() {
        BaseDatos bd = null;
        SQLiteDatabase bdSqlite = null;
        try {
            bd = new BaseDatos(context, "puntuaciones", null, 1);
            bdSqlite = bd.getReadableDatabase();
            String query = "SELECT puntos from puntuaciones order by puntos DESC LIMIT 3";
            Log.d("puntps", "kkk " + query);
            Cursor cursor = bdSqlite.rawQuery(query, null);
            Log.d("puntps", "qqqq " + query + " " + cursor.moveToFirst());

            if (cursor.moveToFirst()) {
                int puntos;
                do {
                    Log.d("puntps", "fff " + query);
                    puntos = cursor.getInt(0);
                    Log.d("puntps", "" + puntos);
                    listado.add(puntos);
                    Log.d("puntps", "lis 1 " + listado.size());
                } while (cursor.moveToNext());
                Log.d("puntps", "lis 2 " + listado.size());
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