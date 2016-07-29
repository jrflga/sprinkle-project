package com.android.sprinkleproject.utils;

        import android.content.Context;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.util.AttributeSet;
        import android.view.Gravity;
        import android.view.View;
        import android.widget.RelativeLayout;


public class ZigZagLayout extends RelativeLayout {

    private int mViewMargin = 20;
    private int mRootViewLeftMargin = 200;
    private boolean shouldDrawLines = true;

    private Paint mPaintConnectorLine;

    public ZigZagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setWillNotDraw(false);

        mPaintConnectorLine = new Paint();
        mPaintConnectorLine.setColor(Color.BLACK);
        mPaintConnectorLine.setStrokeWidth(10);
        mPaintConnectorLine.setFlags(Paint.ANTI_ALIAS_FLAG);

        setGravity(Gravity.CENTER_HORIZONTAL);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(shouldDrawLines) {
            drawLines(canvas);
        }
        super.onDraw(canvas);
    }

    private void drawLines(Canvas canvas) {
        int totalChilds = getChildCount();
        View viewFrom, viewTo;
        for(int index = 0; index < totalChilds - 1; index++) {
            viewFrom = getChildAt(index);
            viewTo = getChildAt(index + 1);
            canvas.drawLine(viewFrom.getLeft() + ((viewFrom.getRight() - viewFrom.getLeft()) / 2), viewFrom.getTop() + ((viewFrom.getBottom() - viewFrom.getTop()) / 2),
                    viewTo.getLeft() + ((viewTo.getRight() - viewTo.getLeft()) / 2), viewTo.getTop() + ((viewTo.getBottom() - viewTo.getTop()) / 2),
                    mPaintConnectorLine);
        }
    }

    /**
     * Adds a view to <code>ZigZagLayout</code> in vertical orientation.
     */
    @Override
    public void addView(View child) {
        int childCount = getChildCount();

        LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
        layoutParams.setMargins(mViewMargin, mViewMargin, mViewMargin, mViewMargin);
        if(childCount > 0) {
            int previousViewID = getChildAt(childCount - 1).getId();
            layoutParams.addRule(RelativeLayout.BELOW, previousViewID);
            if(childCount % 2 == 0) {
                layoutParams.addRule(RelativeLayout.RIGHT_OF, previousViewID);
            }
            else {
                layoutParams.addRule(RelativeLayout.LEFT_OF, previousViewID);
            }
            child.setId(childCount + 1);
        } else {
            layoutParams.leftMargin = mRootViewLeftMargin ;
            child.setId(1);
        }

        child.setLayoutParams(layoutParams);

        super.addView(child);
    }

    /**
     * Set the paint's color. Note that the color is an int containing alpha as well as r,g,b. This 32bit value is not premultiplied, meaning that its alpha can be any value, regardless of the values of r,g,b. See the Color class for more details.
     * @param color The new color (including alpha) to set in the paint.
     */
    public void setLineColor(int color) {
        mPaintConnectorLine.setColor(color);
    }

    /**
     * Set the width for stroking. Pass 0 to stroke in hairline mode. Hairlines always draws a single pixel independent of the canva's matrix.
     * @param width set the paint's stroke width, used whenever the paint's style is Stroke or StrokeAndFill.
     */
    public void setLineStrokeWidth(int width) {
        mPaintConnectorLine.setStrokeWidth(width);
    }

    /**
     * Helper to setColor(), that only assigns the color's alpha value, leaving its r,g,b values unchanged. Results are undefined if the alpha value is outside of the range [0..255]
     * @param alpha set the alpha component [0..255] of the paint's color.
     */
    public void setLineAlpha(int alpha) {
        mPaintConnectorLine.setAlpha(alpha);
    }

    /**
     * Set whether connector-lines should be drawn between two adjacent views.
     * @param visibility One of <code>View.VISIBLE</code>, <code>View.INVISIBLE</code>, or <code>View.GONE</code>.
     */
    public void setLineVisibility(int visibility) {
        if(visibility == View.VISIBLE) {
            shouldDrawLines = true;
        }
        else {
            shouldDrawLines = false;
        }
    }

    /**
     * Set margin for the child-views.
     * @param margin the margin size
     */
    public void setViewMargin(int margin) {
        mViewMargin = margin;
    }

    /**
     * Set the start/left margin for the root view (1st view).
     * @param margin the margin size
     */
    public void setRootViewStartMargin(int margin) {
        mRootViewLeftMargin = margin;
    }
}




