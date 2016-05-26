package cn.golden.pinchzoomcanvasview;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;

/**
 * Created by antwan on 10/3/2015.
 */
public class CText implements CDrawable {
    private String mText;
    private TextPaint mTextPaint;
    private float x, y,mWidth;
    private int mRotDegree;

    private int mPeddingtoRight = 10;

    public CText(String s, float x, float y, TextPaint p ,float width ) {
        setText(s);
        setYcoords(y);
        setXcoords(x);
        setTextPaint(p);
        setWidth(width);
    }

    public void setText(String t) {
        mText = t;
    }

    public String getText() {
        return mText;
    }

    @Override
    public Paint getPaint() {
        return null;
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

    }

    @Override
    public void draw(Canvas canvas) {

        StaticLayout mTextLayout = new StaticLayout(mText, mTextPaint,(int)(getWidth()-getXcoords() - mPeddingtoRight),
                Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
        canvas.save();
        canvas.translate(getXcoords(), getYcoords());
        mTextLayout.draw(canvas);
        canvas.restore();

    }

    @Override
    public int getRotation() {
        return mRotDegree;
    }

    @Override
    public void setRotation(int degree) {
        mRotDegree = degree;
    }

    public void setTextPaint(TextPaint mTextPaint) {
        this.mTextPaint = mTextPaint;
    }

    public android.text.TextPaint getTextPaint() {
        return mTextPaint;
    }

    public float getWidth() {
        return mWidth;
    }

    public void setWidth(float width) {
        this.mWidth = width;
    }
}
