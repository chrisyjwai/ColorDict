package com.skwear.colorthesaurus;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    //TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onWindowFocusChanged(true);
        setContentView(R.layout.activity_main);


        imageView = (ImageView) findViewById(R.id.imageView);
        //textView = (TextView) findViewById(R.id.textView);

        imageView.setOnTouchListener(imageViewOnTouchListener);

    }

    View.OnTouchListener imageViewOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {

            imageView.setDrawingCacheEnabled(true);
            Drawable drawable = ((ImageView)view).getDrawable();
            Bitmap bitmap = imageView.getDrawingCache();

            int x = (int)event.getX();
            int y = (int)event.getY();

            if (x < 0) {
                x = 0;
            } else if (x > bitmap.getWidth() - 1) {
                x = bitmap.getWidth() - 1;
            }

            if (y < 0) {
                y = 0;
            } else if (y > bitmap.getHeight() - 1) {
                y = bitmap.getHeight() - 1;
            }

            int pixel = bitmap.getPixel(x,y);

            String pixelHex = Integer.toHexString(pixel);
            String angercolor = Integer.toHexString(ContextCompat.getColor(getApplicationContext(),R.color.colorAnger));
            String happinesscolor = Integer.toHexString(ContextCompat.getColor(getApplicationContext(),R.color.colorHappiness));
            String sadnesscolor = Integer.toHexString(ContextCompat.getColor(getApplicationContext(),R.color.colorSadness));
            String disgustcolor = Integer.toHexString(ContextCompat.getColor(getApplicationContext(),R.color.colorDisgust));
            String surprisecolor = Integer.toHexString(ContextCompat.getColor(getApplicationContext(),R.color.colorSurprise));
            String fearcolor = Integer.toHexString(ContextCompat.getColor(getApplicationContext(),R.color.colorFear));

            if (event.getAction() == MotionEvent.ACTION_UP) {
                //textView.setText("touched color: " + "#" + pixelHex);
                //textView.setTextColor(pixel);
                if (pixelHex.equals(angercolor) || pixelHex.equals(happinesscolor) ||
                    pixelHex.equals(sadnesscolor) || pixelHex.equals(disgustcolor) ||
                    pixelHex.equals(surprisecolor) || pixelHex.equals(fearcolor)) {
                    goToAnimationActivity(pixel);
                }
                /*
                if (pixelHex.equals("ff000000")) {
                    //open an "iframe" with credits;
                }
                */
            }

            return true;
        }
    };

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

    public void goToAnimationActivity(int pixel) {
        Intent intent = new Intent(this,AnimationActivity.class);
        intent.putExtra("color",pixel);
        startActivity(intent);
        overridePendingTransition(0,0);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
