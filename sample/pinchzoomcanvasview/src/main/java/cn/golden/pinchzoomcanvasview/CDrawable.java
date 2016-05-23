package cn.golden.pinchzoomcanvasview;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by antwan on 10/3/2015.
 */
public interface CDrawable {
    Paint getPaint();

    float getXcoords();

    float getYcoords();

    void setXcoords(float x);

    void setYcoords(float y);

    void setPaint(Paint p);

    void draw(Canvas canvas);

    int getRotation();

    void setRotation(int degree);
}
