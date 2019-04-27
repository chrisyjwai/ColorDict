package com.skwear.colorthesaurus;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class BackAnimationActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onWindowFocusChanged(true);
        setContentView(R.layout.activity_back_animation);

        imageView = (ImageView) findViewById(R.id.imageView);

        Intent intentColor = getIntent();
        final int colorTouched = intentColor.getIntExtra("color",0);

        int duration = (2 * getResources().getInteger(R.integer.animation_duration));

        animate(colorTouched);

        Animatable animatable = (Animatable)imageView.getDrawable();

        Handler mHandler = new Handler();

        AVDWrapper.Callback callback = new AVDWrapper.Callback() {
            @Override
            public void onAnimationDone() {
                goToMainActivity();
            }

            @Override
            public void onAnimationStopped() {
                // Okay
            }
        };

        AVDWrapper avdw = new AVDWrapper(animatable, mHandler, callback);
        animatable.start();
        avdw.start(duration);


    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        View decorView = getWindow().getDecorView();
        if (hasFocus) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);}
    }

    public void animate(int pixel) {
        String angercolor = Integer.toHexString(ContextCompat.getColor(this,R.color.colorAnger));
        String happinesscolor = Integer.toHexString(ContextCompat.getColor(this,R.color.colorHappiness));
        String sadnesscolor = Integer.toHexString(ContextCompat.getColor(this,R.color.colorSadness));
        String disgustcolor = Integer.toHexString(ContextCompat.getColor(this,R.color.colorDisgust));
        String surprisecolor = Integer.toHexString(ContextCompat.getColor(this,R.color.colorSurprise));
        String fearcolor = Integer.toHexString(ContextCompat.getColor(this,R.color.colorFear));
        String s = Integer.toHexString(pixel);
        if (s.equals(angercolor)) {
            imageView.setImageDrawable(getDrawable(R.drawable.ravd_anger));
            //((Animatable) imageView.getDrawable()).start();

        } else if (s.equals(happinesscolor)) {
            imageView.setImageDrawable(getDrawable(R.drawable.ravd_happiness));
            //((Animatable) imageView.getDrawable()).start();

        } else if (s.equals(sadnesscolor)) {
            imageView.setImageDrawable(getDrawable(R.drawable.ravd_sadness));
            //((Animatable) imageView.getDrawable()).start();

        } else if (s.equals(disgustcolor)) {
            imageView.setImageDrawable(getDrawable(R.drawable.ravd_disgust));
            //((Animatable) imageView.getDrawable()).start();

        } else if (s.equals(surprisecolor)) {
            imageView.setImageDrawable(getDrawable(R.drawable.ravd_surprise));
            //((Animatable) imageView.getDrawable()).start();

        } else if (s.equals(fearcolor)) {
            imageView.setImageDrawable(getDrawable(R.drawable.ravd_fear));
            //((Animatable) imageView.getDrawable()).start();

        }
    }

    public void goToMainActivity() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        overridePendingTransition(0,0);
    }

}