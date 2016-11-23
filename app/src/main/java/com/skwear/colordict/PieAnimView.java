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

    Handler handler = new Handler();

    public PieAnimView(Context context) {
        this(context, null);
    }

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

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //TODO radialMenu.getSlicePicked right here

        Log.d("CREATION", "5");
        /*if(Pick.getInstance().getSlicePicked() == Slice.ANGER) {
            Log.d("CREATION", "anger2");
        }*/

        switch (Pick.getInstance().getSlicePicked()) {
            case ANGER: { setImageDrawable(angerAnim); }
            case FEAR: { setImageDrawable(fearAnim); }
            case SURPRISE: { setImageDrawable(surpriseAnim); }
            case HAPPINESS: { setImageDrawable(happinessAnim); }
            case SADNESS: { setImageDrawable(sadnessAnim); }
            case DISGUST: { setImageDrawable(disgustAnim); }
        }

        Animation();
    }

    public void setBackView(ImageView bv) {
        backView = bv;
    }

    public void Animation() {
        AVDWrapper.Callback callback = new AVDWrapper.Callback() {
            @Override
            public void onAnimationDone() {
                //TODO pie animation after action
            }
        };

        Animatable anim = (Animatable) getDrawable();
        Animatable animback = (Animatable) backView.getDrawable();

        AVDWrapper avdWrapper = new AVDWrapper(handler, callback, anim, animback);
        avdWrapper.start(getResources().getInteger(R.integer.animation_duration));
    }
}
