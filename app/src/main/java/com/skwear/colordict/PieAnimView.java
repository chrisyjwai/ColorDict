package com.skwear.colordict;

import android.content.Context;
import android.graphics.Canvas;
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

/* NOT NEEDED, BUT KEEP JUST IN CASE
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
*/

    @Override
    public void onVisibilityChanged(View changedView, int visibility) {
        //super.onVisibilityChanged(pieAnim, VISIBLE);

        slicePicked = Pick.getInstance().getSlicePicked();

        if (visibility == VISIBLE) {
            if(slicePicked == Slice.ANGER) {
                Log.d("CREATION", "anger");
                setImageDrawable(angerAnim);
            }
            if(slicePicked == Slice.FEAR) {
                Log.d("CREATION", "fear");
                setImageDrawable(fearAnim);
            }
            if(slicePicked == Slice.SURPRISE) {
                Log.d("CREATION", "surprise");
                setImageDrawable(surpriseAnim);
            }
            if(slicePicked == Slice.HAPPINESS) {
                Log.d("CREATION", "happiness");
                setImageDrawable(happinessAnim);
            }
            if(slicePicked == Slice.SADNESS) {
                Log.d("CREATION", "sadness");
                setImageDrawable(sadnessAnim);
            }
            if(slicePicked == Slice.DISGUST) {
                Log.d("CREATION", "disgust");
                setImageDrawable(disgustAnim);
            }

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
