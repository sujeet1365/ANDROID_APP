package com.kpf.sujeet.android_app;

//        GraphView is a library for Android to programmatically create
//        flexible and nice-looking diagrams.
//        For creating this graph I needed a Helper class and added 'com.jjoe64:graphview:4.2.1' into app gradle

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;


public class Triangle extends android.support.v7.widget.AppCompatImageView {

    private double percentage;

    public Triangle(Context context) {
        super(context);
        intialize(context);
    }

    public Triangle(Context context, AttributeSet attrs) {
        super(context, attrs);
        intialize(context);
        intialize(attrs);
    }

    public Triangle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        intialize(context);
        intialize(attrs);
    }

    private void intialize(Context context) {
        this.percentage = 0.8;
        invalidate();
    }

    private void intialize(AttributeSet attrs) {
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int w = getWidth();
        int h = getHeight();

        Paint paint = new Paint();
        paint.setShader(new LinearGradient(0, h/2, w, h/2, Color.GREEN, Color.RED, Shader.TileMode.MIRROR));
        paint.setAntiAlias(true);

        PointF p1 = new PointF(0, h);
        float x = (float) (percentage * w);
        PointF p2 = new PointF(x, h);

        float y = (w * h - h * x) / (float) w;
        PointF p3 = new PointF(x, y);

        Log.e("", p1.toString());
        Log.e("", p2.toString());
        Log.e("", p3.toString());

        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.reset();
        path.moveTo(p1.x, p1.y);
        path.lineTo(p2.x, p2.y);
        path.lineTo(p3.x, p3.y);
        path.close();

        canvas.drawPath(path, paint);
    }
}
