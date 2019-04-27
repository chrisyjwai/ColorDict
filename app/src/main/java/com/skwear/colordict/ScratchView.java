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
            len = getWidth();
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

        float margin = 30;

/*        p.moveTo(
                originX - margin,
                margin
        );
        p.lineTo(
                margin,
                margin
        );
        p.cubicTo(
                margin,
                (0.34722222f * hei),
                margin,
                (0.65277778f * hei),
                margin,
                ((1f * hei) - margin)
        );
        p.lineTo(
                originX - margin,
                ((1f * hei) - margin)
        );
        p.cubicTo(
                originX - margin,
                (0.65277778f * hei),
                originX - margin,
                (0.34722222f * hei),
                originX - margin,
                margin
        );
        p.close();*/

        p.moveTo(
                originX - margin,
                margin
        );
        p.lineTo(
                margin,
                margin
        );
        p.cubicTo(
                margin,
                (0.34722222f * hei),
                margin,
                (0.65277778f * hei),
                margin,
                ((1f * hei) - margin)
        );
        p.lineTo(
                originX - margin,
                ((1f * hei) - margin)
        );
        p.cubicTo(
                originX - margin,
                (0.65277778f * hei),
                originX - margin,
                (0.34722222f * hei),
                originX - margin,
                margin
        );
        p.close();

        canvas.drawPath(p, pnt);

        invalidate();
    }
}
