package cn.golden.pinchzoomcanvasview;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by antwan on 10/3/2015.
 */
public class CText implements CDrawable {
    private String mText;
    private Paint mPaint;
    private float x, y;
    private int mRotDegree;

    public CText(String s, float x, float y, Paint p) {
        setText(s);
        setYcoords(y);
        setXcoords(x);
        setPaint(p);
    }

    public void setText(String t) {
        mText = t;
    }

    public String getText() {
        return mText;
    }

    @Override
    public Paint getPaint() {
        return mPaint;
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
    public void setPaint(Paint p) {
        mPaint = p;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawText(getText(), getXcoords(), getYcoords(), mPaint);
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
