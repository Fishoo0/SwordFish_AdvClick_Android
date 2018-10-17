package com.acmes.simpleandroid.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class TriangleView extends View {

    static final String TAG = TriangleView.class.getSimpleName();

    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int RIGHT = 3;
    public static final int LEFT = 4;

    private int mDirection = UP;

    private Paint mPaint;
    private Point[] mPoints;
    private Path mPath;

    public TriangleView(Context context) {
        super(context);
        init(context, null);
    }

    public TriangleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public TriangleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    private void init(Context context, AttributeSet attrs) {
        mPaint = new Paint();
        mPaint.setColor(0xCC000000);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setAntiAlias(true);

        mPoints = new Point[3];
        mPath = new Path();

        for (int i = 0; i < mPoints.length; i++) {
            mPoints[i] = new Point();
        }
    }


    /**
     * Setting direction of this triangle
     *
     * @param direction
     */
    public void setDirection(int direction) {
        Log.v(TAG, "setDirection -> " + direction);
        mDirection = direction;
        invalidate();
    }


    /**
     * Setting color of this triangle
     *
     * @param color
     */
    public void setColor(int color) {
        mPaint.setColor(color);
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.v(TAG, "onMeasure");
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.v(TAG, "onSizeChanged");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.v(TAG, "onLayout");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.v(TAG, "onDraw");
        final Path path = mPath;
        final Paint paint = mPaint;

        int w = getWidth();
        int h = getHeight();
        switch (mDirection) {
            case UP:
                mPoints[0].x = trimWidthPadding(0);
                mPoints[0].y = trimHeightPadding(h);

                mPoints[1].x = trimWidthPadding(w);
                mPoints[1].y = trimHeightPadding(h);

                mPoints[2].x = trimWidthPadding(w / 2);
                mPoints[2].y = trimHeightPadding(0);
                break;

            case DOWN:
                mPoints[0].x = trimWidthPadding(0);
                mPoints[0].y = trimHeightPadding(0);

                mPoints[1].x = trimWidthPadding(w);
                mPoints[1].y = trimHeightPadding(0);

                mPoints[2].x = trimWidthPadding(w / 2);
                mPoints[2].y = trimHeightPadding(h);
                break;

            case RIGHT:
                mPoints[0].x = trimWidthPadding(0);
                mPoints[0].y = trimHeightPadding(0);

                mPoints[1].x = trimWidthPadding(0);
                mPoints[1].y = trimHeightPadding(h);

                mPoints[2].x = trimWidthPadding(w);
                mPoints[2].y = trimHeightPadding(h / 2);
                break;

            case LEFT:
                mPoints[0].x = trimWidthPadding(w);
                mPoints[0].y = trimHeightPadding(0);

                mPoints[1].x = trimWidthPadding(w);
                mPoints[1].y = trimHeightPadding(h);

                mPoints[2].x = trimWidthPadding(0);
                mPoints[2].y = trimHeightPadding(h / 2);
                break;
        }

        path.reset();
        path.moveTo(mPoints[0].x, mPoints[0].y);
        path.lineTo(mPoints[1].x, mPoints[1].y);
        path.lineTo(mPoints[2].x, mPoints[2].y);
        path.lineTo(mPoints[0].x, mPoints[0].y);
        path.close();

        canvas.drawPath(path, paint);
    }


    protected final int trimWidthPadding(int value) {
        return trim(getPaddingLeft(), getWidth() - getPaddingRight(), value);
    }

    protected final int trimHeightPadding(int value) {
        return trim(getPaddingTop(), getHeight() - getPaddingBottom(), value);
    }

    static final int trim(int min, int max, int value) {
        if (value < min) {
            return min;
        } else if (value > max) {
            return max;
        } else {
            return value;
        }
    }

}