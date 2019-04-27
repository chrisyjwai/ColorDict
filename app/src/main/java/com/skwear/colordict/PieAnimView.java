package com.skwear.colordict;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by skweartop on 11/18/2016.
 */

public class PieAnimView extends AppCompatImageView {

    private ImageView backView;

    private Drawable angerAnim;
    private Drawable fearAnim;
    private Drawable surpriseAnim;
    private Drawable happinessAnim;
    private Drawable sadnessAnim;
    private Drawable disgustAnim;

    private FrameLayout pieAnim;

    private Slice slicePicked;

    private float i = 0f;
    private float f;
    private long starttime;
    private int animdura = 500;
    private float frate = 60f;
    private float tpf = 1000f / frate; //time per frame in milliseconds
    private boolean animstarted = false;
    private Path p = new Path();
    private Paint pnt = new Paint();
    private boolean rotatedone = false;
    private float len;
    private float hei;
    private float rad;
    private float margin = 30;

    private float originX;
    private float originY;

//    private float innerR;
//    private float outerR;

    private float deg2Rad = (float)Math.PI / 180f;
    private float innerArc = 44.37388215f * deg2Rad;
    //private float outerArc = 56.00261914f * deg2Rad;

    private int angerCol;
    private int fearCol;
    private int surpriseCol;
    private int happinessCol;
    private int sadnessCol;
    private int disgustCol;

    Handler handler = new Handler();

    public PieAnimView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);

        //Log.d("CREATION", "pieAnim Created");

        angerAnim = ContextCompat.getDrawable(context, R.drawable.avd_anger);
        fearAnim = ContextCompat.getDrawable(context, R.drawable.avd_fear);
        surpriseAnim = ContextCompat.getDrawable(context, R.drawable.avd_surprise);
        happinessAnim = ContextCompat.getDrawable(context, R.drawable.avd_happiness);
        sadnessAnim = ContextCompat.getDrawable(context, R.drawable.avd_sadness);
        disgustAnim = ContextCompat.getDrawable(context, R.drawable.avd_disgust);

        angerCol = ContextCompat.getColor(context, R.color.colorAnger);
        fearCol = ContextCompat.getColor(context, R.color.colorFear);
        surpriseCol = ContextCompat.getColor(context, R.color.colorSurprise);
        happinessCol = ContextCompat.getColor(context, R.color.colorHappiness);
        sadnessCol = ContextCompat.getColor(context, R.color.colorSadness);
        disgustCol = ContextCompat.getColor(context, R.color.colorDisgust);

        pnt.setStyle(Paint.Style.FILL);
    }

/* NOT NEEDED, BUT KEEP JUST IN CASE
    public PieAnimView(Context context) {
        this(context, null);
    }
*/

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged(w, h, oldw, oldh);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            len = getWidth();
            hei = getHeight();
            rad = getWidth();
        }
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            len = getWidth();
            hei = getHeight();
            rad = getHeight();
        }

        originX =  len / 2f;
        originY =  hei / 2f;
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //Log.d("CREATION", "draw");

        if (rotatedone == true) {
            if (animstarted == false) {
                starttime = System.currentTimeMillis();
                animstarted = true;
            }
            if (i * tpf < animdura) {
                i = (System.currentTimeMillis() - starttime) / tpf;

                f = (float)(Math.cos((((i * tpf) / animdura) + 1f) * Math.PI) / 2.0f) + 0.5f;

                p.moveTo(
                        ((originX + (-0.10607121f * rad)) * (1f - f))
                                + ((originX - margin) * f),
                        ((originY + (-0.04319825f * rad)) * (1f - f)) + (margin * f)
                );
                p.lineTo(
                        ((originX + (-0.39477826f * rad)) * (1f - f)) + (margin * f),
                        ((originY + (-0.20988358f * rad)) * (1f - f)) + (margin * f)
                );
                p.cubicTo(
                        ((originX + (-0.46469098f * rad)) * (1f - f)) + (margin * f),
                        ((originY + (-0.07840415f * rad)) * (1f - f)) + ((originY - (0.15277778f * hei)) * f),
                        ((originX + (-0.46495548f * rad)) * (1f - f)) + (margin * f),
                        ((originY + (0.07855642f * rad)) * (1f - f)) + ((originY + (0.15277778f * hei)) * f),
                        ((originX + (-0.39504277f * rad)) * (1f - f)) + (margin * f),
                        ((originY + (0.21003585f * rad)) * (1f - f)) + (((1f * hei) - margin) * f)
                );
                p.lineTo(
                        ((originX + (-0.10600081f * rad)) * (1f - f))
                                + ((originX - margin) * f),
                        ((originY + (0.04315740f * rad)) * (1f - f)) + (((1f * hei) - margin) * f)
                );
                p.cubicTo(
                        ((originX + (-0.11731298f * rad)) * (1f - f))
                                + ((originX - margin) * f),
                        ((originY + (0.01541969f * rad)) * (1f - f)) + ((originY + (0.15277778f * hei)) * f),
                        ((originX + (-0.11738338f * rad)) * (1f - f))
                                + ((originX - margin) * f),
                        ((originY + (-0.01546054f * rad)) * (1f - f)) + ((originY - (0.15277778f * hei)) * f),
                        ((originX + (-0.10607121f * rad)) * (1f - f))
                                + ((originX - margin) * f),
                        ((originY + (-0.04319825f * rad)) * (1f - f)) + (margin * f)
                );
                p.close();

                canvas.drawPath(p, pnt);

                invalidate();
                //Log.d("CREATION", "path drawn");

                p.rewind();
            }
            else {
                //TODO call next view

            }
        }
    }


    @Override
    public void onVisibilityChanged(View changedView, int visibility) {
        //super.onVisibilityChanged(pieAnim, VISIBLE);

        slicePicked = Pick.getInstance().getSlicePicked();

        if (visibility == VISIBLE) {
            if(slicePicked == Slice.ANGER) {
                //Log.d("CREATION", "anger");
                setImageDrawable(angerAnim);
                pnt.setColor(angerCol);
            }
            if(slicePicked == Slice.FEAR) {
                //Log.d("CREATION", "fear");
                setImageDrawable(fearAnim);
                pnt.setColor(fearCol);
            }
            if(slicePicked == Slice.SURPRISE) {
                //Log.d("CREATION", "surprise");
                setImageDrawable(surpriseAnim);
                pnt.setColor(surpriseCol);
            }
            if(slicePicked == Slice.HAPPINESS) {
                //Log.d("CREATION", "happiness");
                setImageDrawable(happinessAnim);
                pnt.setColor(happinessCol);
            }
            if(slicePicked == Slice.SADNESS) {
                //Log.d("CREATION", "sadness");
                setImageDrawable(sadnessAnim);
                pnt.setColor(sadnessCol);
            }
            if(slicePicked == Slice.DISGUST) {
                //Log.d("CREATION", "disgust");
                setImageDrawable(disgustAnim);
                pnt.setColor(disgustCol);
            }

            PieRotate();
            //Log.d("CREATION", "Animation() called");
        }
    }

    public void setBackView(ImageView bv) {
        backView = bv;
    }

    public void setPieAnim(FrameLayout pa) {
        pieAnim = pa;
    }

    public void PieRotate() {
        AVDWrapper.Callback callback = new AVDWrapper.Callback() {
            @Override
            public void onAnimationDone() {
                setImageResource(android.R.color.transparent);
                rotatedone = true;
                invalidate();
            }
        };

        Animatable anim = (Animatable) getDrawable();
        Animatable animback = (Animatable) backView.getDrawable();

        AVDWrapper avdWrapper = new AVDWrapper(handler, callback, anim, animback);
        avdWrapper.start(getResources().getInteger(R.integer.animation_duration));
    }
}
