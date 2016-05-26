/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package cn.golden.pinchzoomcanvasview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

import java.util.ArrayList;

import uk.co.senab.photoview.IPhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;
import uk.co.senab.photoview.PhotoViewAttacher.OnMatrixChangedListener;
import uk.co.senab.photoview.PhotoViewAttacher.OnPhotoTapListener;
import uk.co.senab.photoview.PhotoViewAttacher.OnViewTapListener;

public class PinchZoomCanvasView extends ImageView implements IPhotoView {

    private PinchZoomCanvasViewAttacher mAttacher;
    private ScaleType mPendingScaleType;

    private Context mContext;

    public PinchZoomCanvasView(Context context) {
        this(context, null);
        init();
    }

    public PinchZoomCanvasView(Context context, AttributeSet attr) {
        this(context, attr, 0);
        init();
    }

    public PinchZoomCanvasView(Context context, AttributeSet attr, int defStyle) {
        super(context, attr, defStyle);
        super.setScaleType(ScaleType.MATRIX);
        mContext = context;
        init();
    }

    protected void init() {
        if (null == mAttacher || null == mAttacher.getImageView()) {
            mAttacher = new PinchZoomCanvasViewAttacher(this);
        }

        if (null != mPendingScaleType) {
            setScaleType(mPendingScaleType);
            mPendingScaleType = null;
        }


        textPaint.setAntiAlias(true);
        textPaint.setTextSize(30F);
        textPaint.setColor(Color.RED);
        textPaint.setStrokeJoin(Paint.Join.MITER);
        textPaint.setTextAlign(Paint.Align.LEFT);
        textPaint.setStrokeWidth(0F);
        textPaint.setTypeface(Typeface.DEFAULT);

    }


    /**
     * @deprecated use {@link #setRotationTo(float)}
     */
    @Override
    public void setPhotoViewRotation(float rotationDegree) {
        mAttacher.setRotationTo(rotationDegree);
    }

    @Override
    public void setRotationTo(float rotationDegree) {
        mAttacher.setRotationTo(rotationDegree);
    }

    @Override
    public void setRotationBy(float rotationDegree) {
        mAttacher.setRotationBy(rotationDegree);
    }

    @Override
    public boolean canZoom() {
        return mAttacher.canZoom();
    }

    @Override
    public RectF getDisplayRect() {
        return mAttacher.getDisplayRect();
    }

    @Override
    public Matrix getDisplayMatrix() {
        return mAttacher.getDisplayMatrix();
    }

    @Override
    public boolean setDisplayMatrix(Matrix finalRectangle) {
        return mAttacher.setDisplayMatrix(finalRectangle);
    }

    @Override
    @Deprecated
    public float getMinScale() {
        return getMinimumScale();
    }

    @Override
    public float getMinimumScale() {
        return mAttacher.getMinimumScale();
    }

    @Override
    @Deprecated
    public float getMidScale() {
        return getMediumScale();
    }

    @Override
    public float getMediumScale() {
        return mAttacher.getMediumScale();
    }

    @Override
    @Deprecated
    public float getMaxScale() {
        return getMaximumScale();
    }

    @Override
    public float getMaximumScale() {
        return mAttacher.getMaximumScale();
    }

    @Override
    public float getScale() {
        return mAttacher.getScale();
    }

    @Override
    public ScaleType getScaleType() {
        return mAttacher.getScaleType();
    }

    @Override
    public void setAllowParentInterceptOnEdge(boolean allow) {
        mAttacher.setAllowParentInterceptOnEdge(allow);
    }

    @Override
    @Deprecated
    public void setMinScale(float minScale) {
        setMinimumScale(minScale);
    }

    @Override
    public void setMinimumScale(float minimumScale) {
        mAttacher.setMinimumScale(minimumScale);
    }

    @Override
    @Deprecated
    public void setMidScale(float midScale) {
        setMediumScale(midScale);
    }

    @Override
    public void setMediumScale(float mediumScale) {
        mAttacher.setMediumScale(mediumScale);
    }

    @Override
    @Deprecated
    public void setMaxScale(float maxScale) {
        setMaximumScale(maxScale);
    }

    @Override
    public void setMaximumScale(float maximumScale) {
        mAttacher.setMaximumScale(maximumScale);
    }

    @Override
    public void setScaleLevels(float minimumScale, float mediumScale, float maximumScale) {
        mAttacher.setScaleLevels(minimumScale, mediumScale, maximumScale);
    }

    @Override
    // setImageBitmap calls through to this method
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        if (null != mAttacher) {
            mAttacher.update();
        }
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        if (null != mAttacher) {
            mAttacher.update();
        }
    }

    @Override
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        if (null != mAttacher) {
            mAttacher.update();
        }
    }

    @Override
    public void setOnMatrixChangeListener(OnMatrixChangedListener listener) {
        mAttacher.setOnMatrixChangeListener(listener);
    }

    @Override
    public void setOnLongClickListener(OnLongClickListener l) {
        mAttacher.setOnLongClickListener(l);
    }

    @Override
    public void setOnPhotoTapListener(OnPhotoTapListener listener) {
        mAttacher.setOnPhotoTapListener(listener);
    }

    @Override
    public OnPhotoTapListener getOnPhotoTapListener() {
        return mAttacher.getOnPhotoTapListener();
    }

    @Override
    public void setOnViewTapListener(OnViewTapListener listener) {
        mAttacher.setOnViewTapListener(listener);
    }

    @Override
    public OnViewTapListener getOnViewTapListener() {
        return mAttacher.getOnViewTapListener();
    }

    @Override
    public void setScale(float scale) {
        mAttacher.setScale(scale);
    }

    @Override
    public void setScale(float scale, boolean animate) {
        mAttacher.setScale(scale, animate);
    }

    @Override
    public void setScale(float scale, float focalX, float focalY, boolean animate) {
        mAttacher.setScale(scale, focalX, focalY, animate);
    }

    @Override
    public void setScaleType(ScaleType scaleType) {
        if (null != mAttacher) {
            mAttacher.setScaleType(scaleType);
        } else {
            mPendingScaleType = scaleType;
        }
    }

    @Override
    public void setZoomable(boolean zoomable) {
        mAttacher.setZoomable(zoomable);
    }

    @Override
    public Bitmap getVisibleRectangleBitmap() {
        return mAttacher.getVisibleRectangleBitmap();
    }

    @Override
    public void setZoomTransitionDuration(int milliseconds) {
        mAttacher.setZoomTransitionDuration(milliseconds);
    }

    @Override
    public IPhotoView getIPhotoViewImplementation() {
        return mAttacher;
    }

    @Override
    public void setOnDoubleTapListener(GestureDetector.OnDoubleTapListener newOnDoubleTapListener) {
        mAttacher.setOnDoubleTapListener(newOnDoubleTapListener);
    }

    @Override
    public void setOnScaleChangeListener(PhotoViewAttacher.OnScaleChangeListener onScaleChangeListener) {
        mAttacher.setOnScaleChangeListener(onScaleChangeListener);
    }

    @Override
    protected void onDetachedFromWindow() {
        mAttacher.cleanup();
        super.onDetachedFromWindow();
    }

    @Override
    protected void onAttachedToWindow() {
        init();
        super.onAttachedToWindow();
    }


    /**
     * custom for draw
     * @param event
     * @return
     */

    // Canvas interaction modes
    private int mInteractionMode = SELECT_MODE;
    private int penMode = PEN;

    // Interactive Modes
    public static final int DRAW_MODE = 0;
    public static final int SELECT_MODE = 1;
    public static final int ROTATE_MODE = 2;
    public static final int LOCKED_MODE = 3;
    public static final int PEN = 4;
    public static final int RECTANGLE = 5;
    public static final int TEXT = 6;

    // keep track of path and paint being in use
    CPath currentPath;
    CText currentText;
    Paint currentPaint;

    // painting objects and properties
    private ArrayList<CDrawable> mDrawableList = new ArrayList<CDrawable>();


    private float origin[]=new float[2];

    private float res[] = new float[2];
    private float history[] = new float[2];

    private Matrix inver = new Matrix();

    private float startX = 0;
    private float startY = 0;
    private boolean mTextExpectTouch =false;
    // Vars to decrease dirty area and increase performance
    private float lastTouchX, lastTouchY;

    private final RectF dirtyRect = new RectF();

    private int mColor = Color.RED;
    // default style for the library
    private Paint.Style mStyle = Paint.Style.STROKE;
    // default stroke size for the library
    private float mSize = 5f;

    public interface OnKeyBoardListener{
        void onShowKeyBoard(float eventX, float eventY);
    }

    private OnKeyBoardListener onKeyBoardListener;

    private TextPaint textPaint = new TextPaint();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.concat(mAttacher.getDisplayMatrix());

        for (int i = 0; i < mDrawableList.size(); i++) {
            try {
                mDrawableList.get(i).draw(canvas);
            } catch (Exception ex) {

            }
        }
        canvas.restore();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (getInteractionMode() == DRAW_MODE)
            return onTouchDrawMode(event);
        else
            return onTouchSelectMode(event);

    }

    public void setInteractionMode(int mInteractionMode) {
        this.mInteractionMode = mInteractionMode;
        if (mInteractionMode == SELECT_MODE){
            setZoomable(true);
        }else {
            setZoomable(false);
        }
    }

    public void setPenMode(int penMode) {
        setInteractionMode(DRAW_MODE);
        if (penMode == TEXT){
            mTextExpectTouch = true;
            mAttacher.setInEdit(true);
        }else {
            mTextExpectTouch = false;
            mAttacher.setInEdit(true);
        }
        this.penMode = penMode;
    }

    public int getInteractionMode() {
        return mInteractionMode;
    }


    /**
     * Handles the touch input if the mode is set to select
     * @param event the touch event
     */
    private boolean onTouchSelectMode(MotionEvent event) {
        // TODO Implement Method
        return true;
    }

    private boolean onTouchDrawMode(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();
        origin[0]= eventX;
        origin[1]= eventY;
        mAttacher.getDisplayMatrix().invert(inver);
        inver.mapPoints(res,origin);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (penMode != TEXT){
                    // create new path and paint
                    startX = res[0];
                    startY = res[1];
                    currentPath = new CPath();
                    currentPaint = new Paint();
                    currentPaint.setAntiAlias(true);
                    currentPaint.setDither(true);
                    currentPaint.setColor(mColor);
                    currentPaint.setStyle(mStyle);
                    currentPaint.setStrokeJoin(Paint.Join.ROUND);
                    currentPaint.setStrokeCap(Paint.Cap.ROUND);
                    currentPaint.setStrokeWidth(mSize);

                    currentPath.moveTo(startX, startY);
                    currentPath.setPaint(currentPaint);
                    // capture touched locations
                    lastTouchX = res[0];
                    lastTouchY = res[1];
                    mDrawableList.add(currentPath);
                }
                return true;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                if (penMode == RECTANGLE){
                    currentPath.reset();
                    float left;
                    float right;
                    float top;
                    float bottom;
                    if (startX < res[0]){
                        left = startX;
                        right = res[0];
                    }else {
                        left = res[0];
                        right = startX;
                    }
                    if (startY < res[1]){
                        top = startY;
                        bottom = res[1];
                    }else {
                        top = res[1];
                        bottom = startY;
                    }
                    currentPath.addRect(left,top,right,bottom);
                }else if (penMode == TEXT) {
                    if (onKeyBoardListener != null) {
                        if (mTextExpectTouch) {
                            currentText = new CText("", res[0], res[1], textPaint,getTextWidth());
                            mDrawableList.add(currentText);
                        }else {
                            currentText.setXcoords(res[0]);
                            currentText.setYcoords(res[1]);
                        }
                        onKeyBoardListener.onShowKeyBoard(eventX,eventY);

                    }
                }else {
                    int historySize = event.getHistorySize();
                    for (int i = 0; i < historySize; i++) {
                        float historicalX = event.getHistoricalX(i);
                        float historicalY = event.getHistoricalY(i);
                        float[] historyres = {historicalX, historicalY};
                        inver.mapPoints(history,historyres);

                        if (history[0] < dirtyRect.left) {
                            dirtyRect.left = history[0];
                        } else if (history[0] > dirtyRect.right) {
                            dirtyRect.right = history[0];
                        }
                        if (history[1] < dirtyRect.top) {
                            dirtyRect.top = history[1];
                        } else if (history[1] > dirtyRect.bottom) {
                            dirtyRect.bottom = history[1];
                        }
                        currentPath.lineTo(history[0], history[1]);
                    }
                    currentPath.lineTo(res[0], res[1]);
                    cleanDirtyRegion(res[0],res[1]);
                }
                break;
            default:
                return false;

        }
        // Include some padding to ensure nothing is clipped
        invalidate(
                (int) (dirtyRect.left - 20),
                (int) (dirtyRect.top - 20),
                (int) (dirtyRect.right + 20),
                (int) (dirtyRect.bottom + 20));
//        invalidate();

        // register most recent touch locations
        lastTouchX = res[0];
        lastTouchY = res[1];
        return true;

    }

    private float getTextWidth() {
        float drawableWidth = getDrawable().getIntrinsicWidth();
        float totalWidth = drawableWidth/getDisplayRect().width()*getWidth();
        float width = (totalWidth - drawableWidth)/2 +drawableWidth;
        return width;
    }

    /**
     * Retrieve the region needing to be redrawn
     * @param eventX The current x location of the touch
     * @param eventY the current y location of the touch
     */
    private void cleanDirtyRegion(float eventX, float eventY) {
        // figure out the sides of the dirty region
        dirtyRect.left = Math.min(lastTouchX, eventX);
        dirtyRect.right = Math.max(lastTouchX, eventX);
        dirtyRect.top = Math.min(lastTouchY, eventY);
        dirtyRect.bottom = Math.max(lastTouchY, eventY);
    }

    public void setOnKeyBoardListener(OnKeyBoardListener listener){
        onKeyBoardListener = listener;
    }

    public void drawText(String content  ){
        currentText.setText(content);
        invalidate();
    }

    public void setTextExpectTouch(boolean b){
        mTextExpectTouch = b;
    }
    public void undo(){
        if (mDrawableList.size() > 0){
            mDrawableList.remove(mDrawableList.size() - 1);
            invalidate();
        }
    }

    public void reset(){
        mDrawableList.clear();
        invalidate();
    }

    /**
     * Gets what has been drawn on the canvas so far as a bitmap
     * @return Bitmap of the canvas.
     */
    public Bitmap getCanvasBitmap()
    {
        // build drawing cache of the canvas, use it to create a new bitmap, then destroy it.
        buildDrawingCache();
        Bitmap src = getDrawingCache();
        Matrix restore = new Matrix();

        mAttacher.getDisplayMatrix().invert(restore);
        Bitmap mCanvasBitmap = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(),restore, true);

        destroyDrawingCache();
        // return the created bitmap.

        return mCanvasBitmap;
    }
}