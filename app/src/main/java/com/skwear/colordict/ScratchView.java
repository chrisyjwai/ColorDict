package com.skwear.colordict;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by skweartop on 11/27/2016.
 */

public class ScratchView extends AppCompatImageView {

    private Path p = new Path();
    private Paint pnt = new Paint();
    private float len;
    private float hei;

    private float originX;
    private float originY;

    public ScratchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        pnt.setStyle(Paint.Style.FILL);
        pnt.setColor(Color.BLACK);
    }

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged(w, h, oldw, oldh);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            len = getWidth();
            hei = getHeight();
        }
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            len = getHeight();
            hei = getHeight();
        }

        originX =  len / 2f;
        originY =  hei / 2f;

//        innerR = (11f / 96f) * len;
//        outerR = (43f / 96f) * len;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("CREATION", "draw");

        p.moveTo(
                originX + (-0.02083333f * len),
                ((0.04166667f * len))
        );
        p.lineTo(
                ((0.04166667f * len)),
                ((0.04166667f * len))
        );
        p.cubicTo(
                ((0.04166667f * len),
                ((0.34722222f * hei),
                ((0.04166667f * len),
                (0.65277778f * hei)
                ((0.04166667f * len),
                (0.95833333f * hei)
        );
        p.lineTo(
                ((originX + (-0.02083333f * len))),
                (0.95833333f * hei))
        );
        p.cubicTo(
                ((originX + (-0.02083333f * len))),
                (0.95833333f * hei) * f),
                ((originX + (-0.02083333f * len)) * f),
                ((0.65277778f * hei) * f),
                ((originX + (-0.02083333f * len)) * f),
                ((0.34722222f * hei) * f)
        );
        p.close();

        canvas.drawPath(p, pnt);

        invalidate();
    }
}
