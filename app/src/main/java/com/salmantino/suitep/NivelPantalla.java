package com.salmantino.suitep;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.content.ContextCompat;


public class NivelPantalla extends AppCompatImageView {

    int l;
    int r;
    int rPeq;
    int trazo;

    float[] angulos;

    Bitmap fondo;
    Bitmap burbuja;


    public NivelPantalla(Context contexto, int l) {
        super(contexto);

        this.l = l;
        this.r = l / 2;
        this.rPeq = l / 10;
        this.trazo = l / 100;

        this.angulos = new float[2];

        this.fondo = iniciaFondo();

        BitmapDrawable bola = (BitmapDrawable) ContextCompat.getDrawable(contexto, R.mipmap.burbuja);
        this.burbuja = Bitmap.createScaledBitmap(bola.getBitmap(), rPeq * 2, rPeq * 2, true);
    }


    private Bitmap iniciaFondo() {

        Bitmap.Config conf = Bitmap.Config.ARGB_8888;  // ARGB_4444 está deprecated
        Bitmap fondo = Bitmap.createBitmap(l, l, conf);

        Paint lapiz = new Paint();
        Canvas lienzo = new Canvas(fondo);

        // Borde exterior
        lapiz.setColor(Color.MAGENTA);
        lienzo.drawCircle(r, r, r, lapiz);

        // Interior
        lapiz.setColor(Color.YELLOW);
        lienzo.drawCircle(r, r, r - trazo, lapiz);

        // Centro
        lapiz.setColor(Color.GREEN);
        lienzo.drawCircle(r, r, rPeq + trazo, lapiz);

        // Cruz
        lapiz.setColor(Color.BLUE);
        lapiz.setStrokeWidth(trazo);
        lienzo.drawLine(r, 0, r, l, lapiz);
        lienzo.drawLine(0, r, l, r, lapiz);

        // return lienzo (?)
        return fondo;
    }


    public void angulos(float[] angulos) {
        this.angulos = angulos;
        invalidate();
    }

    protected void onMeasure(int w, int h) {
        super.onMeasure(w, h);
        setMeasuredDimension(l, l);
    }

    protected void onDraw(Canvas lienzo) {
        super.onDraw(lienzo);
        lienzo.drawBitmap(fondo, 0, 0, null);
        int x = r - rPeq + (int)(angulos[0] / 10 * r);
        int y = r - rPeq - (int)(angulos[1] / 10 * r);
        lienzo.drawBitmap(burbuja, x, y, null);
    }

}
