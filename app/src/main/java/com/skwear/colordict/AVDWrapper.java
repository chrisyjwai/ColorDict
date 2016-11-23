package com.skwear.colordict;

import android.graphics.drawable.Animatable;
import android.os.Handler;

/**
 * Created by skweartop on 11/15/2016.
 */

public class AVDWrapper {

    private Handler mHandler;
    private Animatable[] mDrawable;
    private Callback mCallback;
    private Runnable mAnimationDoneRunnable = new Runnable() {
        @Override
        public void run() {
            if (mCallback != null)
                mCallback.onAnimationDone();
        }
    };

    public interface Callback {
        public void onAnimationDone();
        //public void onAnimationStopped();
    }

    public AVDWrapper(Handler handler, Callback callback, Animatable... drawable) {
        mDrawable = drawable;
        mHandler = handler;
        mCallback = callback;
    }

    // Duration of the animation
    public void start(long duration) {
        for (int i = 0; i < mDrawable.length; i++) {
            mDrawable[i].start();
        }
        mHandler.postDelayed(mAnimationDoneRunnable, duration);
    }

    /*public void stop() {
        mDrawable.stop();
        mHandler.removeCallbacks(mAnimationDoneRunnable);

        if (mCallback != null)
            mCallback.onAnimationStopped();
    }*/
}
