package com.skwear.colordict;

import android.content.Context;
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

    private long i = 0;
    private float f;
    private long starttime;
    private int animdura = 1000;
    private float frate = 60f;
    private float tpf = 1000f / frate; //time per frame milliseconds
    private boolean animstarted = false;
    private Path p = new Path();
    private Paint pnt = new Paint();
    private boolean rotatedone = false;

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
    }

/* NOT NEEDED, BUT KEEP JUST IN CASE
    public PieAnimView(Context context) {
        this(context, null);
    }
*/

/* NOT NEEDED, BUT KEEP JUST IN CASE
    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged(w, h, oldw, oldh);
    }
*/


    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (rotatedone == true) {
            if (animstarted == false) {
                starttime = System.currentTimeMillis();
                animstarted = true;
            }

            i = (long)((System.currentTimeMillis() - starttime) / tpf);

            //TODO check if casts are redundant
            f = (float)(Math.cos((((float)i / 60f) + 1f) * Math.PI) / 2.0f) + 0.5f;

            //TODO draw paths


            canvas.drawPath();

            invalidate();

            p.rewind();
        }
    }


    @Override
    public void onVisibilityChanged(View changedView, int visibility) {
        //super.onVisibilityChanged(pieAnim, VISIBLE);

        slicePicked = Pick.getInstance().getSlicePicked();

        if (visibility == VISIBLE) {
            if(slicePicked == Slice.ANGER) {
                Log.d("CREATION", "anger");
                tImageDrawable(angerAnim);
                //TODO set paint color
            }
            if(slicePicked == Slice.FEAR) {
                Log.d("CREATION", "fear");
                setImageDrawable(fearAnim);
                //TODO set paint color
            }
            if(slicePicked == Slice.SURPRISE) {
                Log.d("CREATION", "surprise");
                setImageDrawable(surpriseAnim);
                //TODO set paint color
            }
            if(slicePicked == Slice.HAPPINESS) {
                Log.d("CREATION", "happiness");
                setImageDrawable(happinessAnim);
                //TODO set paint color
            }
            if(slicePicked == Slice.SADNESS) {
                Log.d("CREATION", "sadness");
                setImageDrawable(sadnessAnim);
                //TODO set paint color
            }
            if(slicePicked == Slice.DISGUST) {
                Log.d("CREATION", "disgust");
                setImageDrawable(disgustAnim);
                //TODO set paint color
            }

            //TODO set path internal structure or default path

            PieRotate();
            Log.d("CREATION", "Animation() called");
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
                //TODO set slice inflate drawable and call SliceInflate()
            }
        };

        Animatable anim = (Animatable) getDrawable();
        Animatable animback = (Animatable) backView.getDrawable();

        AVDWrapper avdWrapper = new AVDWrapper(handler, callback, anim, animback);
        avdWrapper.start(getResources().getInteger(R.integer.animation_duration));
    }

    public void SliceInflate() {
        AVDWrapper.Callback callback = new AVDWrapper.Callback() {
            @Override
            public void onAnimationDone() {
                //TODO layout gone and set next viewgroup visible
            }
        };

        Animatable anim = (Animatable) getDrawable();

        Animatable animaback = (Animatable) backView.getDrawable();

        AVDWrapper avdWrapper = new AVDWrapper(handler, callback, anim, animaback);
        avdWrapper.start(getResources().getInteger(R.integer.animation_duration));
    }
}
