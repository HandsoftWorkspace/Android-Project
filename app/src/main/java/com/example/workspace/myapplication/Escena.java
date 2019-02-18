package com.example.workspace.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

public class Escena {
    Context context;
    int idEscena;
    static int anchoPantalla, altoPantalla;
    Bitmap fondo;
    //Bitmap capa,capa1,capa2,capa3,capa4,capa5,capa6,capa7,capa8,capa9,capa10;
    public Paint p, pTexto2, pBoton;

    public Escena(Context context, int idEscena, int anchoPantalla, int altoPantalla){
        this.context=context;
        this.idEscena=idEscena;
        this.anchoPantalla=anchoPantalla;
        this.altoPantalla = altoPantalla;

//        pTexto = new Paint();
//        pTexto2 = new Paint();
//
//        pTexto.setColor(Color.RED);
//        pTexto.setTextAlign(Paint.Align.CENTER);
//        pTexto.setTextSize(altoPantalla/5);
//
//        pTexto2.setColor(Color.RED);
//        pTexto2.setTextAlign(Paint.Align.CENTER);
//        pTexto2.setTextSize(altoPantalla/5);
//
//        pBoton = new Paint();
//        pBoton.setColor(Color.GREEN);
    }

    // Actualizamos la física de los elementos en pantalla
    public void actualizarFisica(){

    }

    // Rutina de dibujo en el lienzo. Se le llamará desde el hilo
    public void dibujar(Canvas c) {
        try {
            //if(idEscena!=0 &&)
        } catch (Exception e) {
            Log.i("Error al dibujar",e.getLocalizedMessage());
        }
    }

    public boolean pulsa(Rect boton, MotionEvent event){
        if(boton.contains((int)event.getX(),(int)event.getY())){
            return true;
        }
        return false;
    }

//    public boolean pulsa(int x, int y, MotionEvent event){
//
//        return false;
//    }

    public int onTouchEvent(MotionEvent event) {
        //synchronized (surfaceHolder) {
        int pointerIndex = event.getActionIndex();        //Obtenemos el índice de la acción
        int pointerID = event.getPointerId(pointerIndex); //Obtenemos el Id del pointer asociado a la acción
        int accion = event.getActionMasked();             //Obtenemos el tipo de pulsación
        switch (accion) {
            case MotionEvent.ACTION_DOWN:           // Primer dedo toca
            case MotionEvent.ACTION_POINTER_DOWN:  // Segundo y siguientes tocan
                break;

            case MotionEvent.ACTION_UP:                     // Al levantar el último dedo
            case MotionEvent.ACTION_POINTER_UP:  // Al levantar un dedo que no es el último
                break;

            case MotionEvent.ACTION_MOVE: // Se mueve alguno de los dedos

                break;
            default:
                Log.i("Otra acción", "Acción no definida: " + accion);
        }
        return idEscena;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getIdEscena() {
        return idEscena;
    }

    public void setIdEscena(int idEscena) {
        this.idEscena = idEscena;
    }

    public int getAnchoPantalla() {
        return anchoPantalla;
    }

    public void setAnchoPantalla(int anchoPantalla) {
        this.anchoPantalla = anchoPantalla;
    }

    public int getAltoPantalla() {
        return altoPantalla;
    }

    public void setAltoPantalla(int altoPantalla) {
        this.altoPantalla = altoPantalla;
    }

    public Bitmap getFondo() {
        return fondo;
    }

    public void setFondo(Bitmap fondo) {
        this.fondo = fondo;
    }
}
