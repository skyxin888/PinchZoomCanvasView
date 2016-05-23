package cn.golden.pinchzoomcanvasview;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by antwan on 10/3/2015.
 */
public class CPath  implements CDrawable {
    private float x, y, height, width;
    private Path mPath;
    private Paint mPaint;
    private int mRotDegree;
    public CPath() {
        mPath = new Path();
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
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public int getRotation() {
        return mRotDegree;
    }

    @Override
    public void setRotation(int degree) {
        mRotDegree = degree;
    }

    public void lineTo(float eventX, float eventY) {
        mPath.lineTo(eventX, eventY);
    }

    public void moveTo(float eventX, float eventY) {
        mPath.moveTo(eventX, eventY);
    }
    public void addRect(float left, float top,float right,float bottom) {
        mPath.addRect(left,top,right,bottom, Path.Direction.CCW);
    }
    public void reset() {
        mPath.reset();
    }
}

