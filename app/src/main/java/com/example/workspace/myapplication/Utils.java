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

    public Utils(Context context) {
        this.context = context;
    }

    public Bitmap getBitmapFromAssets(String fichero) {
        try {
            InputStream is = context.getAssets().open(fichero);
            return BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            return null;
        }
    }

    public Bitmap[] tiempo(int tiempo) {
        switch (tiempo) {
//            case 0:
//                return Game.listaNumeros[0];
//            case 1:
//                return Game.listaNumeros[1];
//            case 2:
//                return Game.listaNumeros[2];
//            case 3:
//                return Game.listaNumeros[3];
//            case 4:
//                return Game.listaNumeros[4];
//            case 5:
//                return Game.listaNumeros[5];
//            case 6:
//                return Game.listaNumeros[6];
//            case 7:
//                return Game.listaNumeros[7];
//            case 8:
//                return Game.listaNumeros[8];
//            case 9:
//                return Game.listaNumeros[9];
//            case 10:
//                return Game.listaNumeros[10];
//            case 11:
//                return Game.listaNumeros[0];
//            case 12:
//                return Game.listaNumeros[0];
//            case 13:
//                return Game.listaNumeros[0];
//            case 14:
//                return Game.listaNumeros[0];
//            case 15:
//                return Game.listaNumeros[0];
//            case 16:
//                return Game.listaNumeros[0];
//            case 17:
//                return Game.listaNumeros[0];
//            case 18:
//                return Game.listaNumeros[0];

        }
        return null;
    }

}
