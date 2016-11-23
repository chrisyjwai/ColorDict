package com.skwear.colordict;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by skweartop on 11/4/2016.
 */

public class RadialMenu extends AppCompatImageView {

    private float outerR; //outer radius of touch zone
    private float innerR; //inner radius of touch zone
    private float centerR;

    private float originX; //defines the center of the circle
    private float originY;

    private FrameLayout pieAnim;
    private FrameLayout pie;

    public RadialMenu(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        //Log.d("CREATION","created");
    }

    public RadialMenu(Context context) {
        this(context, null);
    }

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged(w, h, oldw, oldh);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Log.d("CREATION","touch");
        float currX = event.getX();
        float currY = event.getY();

        //Log.d("CREATION","currX: " + String.valueOf(currX));
        //Log.d("CREATION","currY: " + String.valueOf(currY));

        //Log.d("CREATION","originX: " + String.valueOf(originX));
        //Log.d("CREATION","originY: " + String.valueOf(originY));

        float dx = currX - originX;
        float dy = originY - currY;
        float r = (float)Math.sqrt((dx * dx) + (dy * dy));
        int deg = (int) (Math.atan2(dy, dx) * (180f / Math.PI));

        if (event.getAction() == MotionEvent.ACTION_UP) {
            //Log.d("CREATION","r: " + String.valueOf(r));
            if (r > innerR && r < outerR) {
                //Log.d("CREATION","in region");
                if (deg > -30 && deg < 30) {
                    //Log.d("CREATION","anger");
                    Pick.getInstance().setSlicePicked(Slice.ANGER);
                }
                else if (deg > 30 && deg < 90) {
                    //Log.d("CREATION","fear");
                    Pick.getInstance().setSlicePicked(Slice.FEAR);
                }
                else if (deg > 90 && deg < 150) {
                    //Log.d("CREATION","surprise");
                    Pick.getInstance().setSlicePicked(Slice.SURPRISE);
                }
                else if ((deg > 150 && deg < 180) || (deg > -180 && deg < -150)) {
                    //Log.d("CREATION","happiness");
                    Pick.getInstance().setSlicePicked(Slice.HAPPINESS);
                }
                else if (deg > -150 && deg < -90 ) {
                    //Log.d("CREATION","sadness");
                    Pick.getInstance().setSlicePicked(Slice.SADNESS);
                }
                else if (deg > -90 && deg < -30 ) {
                    //Log.d("CREATION","disgust");
                    Pick.getInstance().setSlicePicked(Slice.DISGUST);
                }
            }
            if (r < centerR) {
                //TODO About box for center credits button
            }
            //TODO pass to pie anim view
            Log.d("CREATION", "1");
            pie.setVisibility(ViewGroup.GONE);
            Log.d("CREATION", "2");
            pieAnim.setVisibility(ViewGroup.VISIBLE);
            Log.d("CREATION", "3");
        }
        return true;
    }

    @Override
    public void onDraw(Canvas canvas) {
        originX = getHeight() / 2;
        originY = getWidth() / 2;
        innerR = (11f / 96f) * getWidth();
        outerR = (43f / 96f) * getWidth();
        centerR = (1f / 12f) * getWidth();
        Log.d("CREATION", "drawn");
        super.onDraw(canvas);
    }

    public void setPieAnim(FrameLayout pa) {
        pieAnim = pa;
    }

    public void setPie (FrameLayout p) {
        pie = p;
    }
}
