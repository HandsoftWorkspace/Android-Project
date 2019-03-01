package com.example.workspace.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.io.IOException;
import java.io.InputStream;

public class Utils {

    Context context;

    public Utils(){

    }

    public Utils(Context context) {
        this.context = context;
    }

    int getPixels(float dp) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().
                getMetrics(metrics);
        return (int) (dp * metrics.density);
    }

    public Bitmap escalaAnchura(int res, int nuevoAncho) {
        Bitmap bitmapAux = BitmapFactory.decodeResource(context.getResources(), res);
        if (nuevoAncho == bitmapAux.getWidth()) return bitmapAux;
        return bitmapAux.createScaledBitmap(bitmapAux, nuevoAncho, (bitmapAux.getHeight() * nuevoAncho) /
                bitmapAux.getWidth(), true);
    }

    public Bitmap escalaAltura(int res, int nuevoAlto) {
        Bitmap bitmapAux = BitmapFactory.decodeResource(context.getResources(), res);
        if (nuevoAlto == bitmapAux.getHeight()) return bitmapAux;
        return bitmapAux.createScaledBitmap(bitmapAux, (bitmapAux.getWidth() * nuevoAlto) / bitmapAux.getHeight(),
                nuevoAlto, true);
    }

    public Bitmap escalaAnchura(String fichero, int nuevoAncho) {
        Bitmap bitmapAux = getBitmapFromAssets(fichero);
        if (nuevoAncho == bitmapAux.getWidth()) return bitmapAux;
        return bitmapAux.createScaledBitmap(bitmapAux, nuevoAncho, (bitmapAux.getHeight() * nuevoAncho) /
                bitmapAux.getWidth(), true);
    }

    public Bitmap escalaAltura(String fichero, int nuevoAlto) {
        Bitmap bitmapAux = getBitmapFromAssets(fichero);
        if (nuevoAlto == bitmapAux.getHeight()) return bitmapAux;
        return bitmapAux.createScaledBitmap(bitmapAux, (bitmapAux.getWidth() * nuevoAlto) / bitmapAux.getHeight(),
                nuevoAlto, true);
    }

    public Bitmap[] getFrames(int numImg, String dir, String tag, int width) {
        Bitmap[] aux = new Bitmap[numImg];
        for (int i = 0; i < numImg; i++)
            aux[i] = escalaAnchura(dir + "/" + tag + " (" + (i + 1) + ").png", width);
        return aux;
    }


    public Bitmap getBitmapFromRes(Bitmap bitmap, String file, int proporcionX, int proporcionY) {
        bitmap = Bitmap.createScaledBitmap(bitmap, proporcionX, proporcionY, false);
        //bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.);
        return bitmap;
    }

    public Bitmap getBitmapFromAssets(String fichero) {
        try {
            InputStream is = context.getAssets().open(fichero);
            return BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            return null;
        }
    }

    // 1280x775
    public int getDpW(int pixels) {
        return (int) ((pixels / 12.8) * Menu.anchoPantalla) / 100;
    }

    // 1280x775
    public int getDpH(int pixels) {
        return (int) ((pixels / 7.75) * Menu.altoPantalla) / 100;
    }

}
