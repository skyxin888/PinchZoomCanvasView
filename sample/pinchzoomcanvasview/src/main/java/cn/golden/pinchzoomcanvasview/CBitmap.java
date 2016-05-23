package cn.golden.pinchzoomcanvasview;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by antwan on 10/3/2015.
 */
public class CBitmap implements CDrawable {
    private float x, y;
    private int height, width;
    private Bitmap mBitmap;
    private Paint mPaint;
    private int mRotDegree;

    public CBitmap(Bitmap src, int x, int y) {
        this(src, x, y, null);
    }

    public CBitmap(Bitmap src, int x, int y, Paint p) {
        mBitmap = src;
        setXcoords(x);
        setYcoords(y);
        setPaint(p);
    }

    @Override
    public Paint getPaint() {
        return mPaint;
    }

    @Override
    public void setPaint(Paint p) {
        mPaint = p;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public float getXcoords() {
        return x;
    }

    @Override
    public float getYcoords() {
        return y;
    }

    @Override
    public void setXcoords(float x) {
        this.x = x;
    }

    @Override
    public void setYcoords(float y) {
        this.y = y;
    }

    @Override
    public void draw(Canvas canvas) {
        Bitmap nsrc = Bitmap.createScaledBitmap(mBitmap, width, height, true);
        canvas.drawBitmap(nsrc, x, y, mPaint);
    }

    @Override
    public int getRotation() {
        return mRotDegree;
    }

    @Override
    public void setRotation(int degree) {
        mRotDegree = degree;
    }
}
