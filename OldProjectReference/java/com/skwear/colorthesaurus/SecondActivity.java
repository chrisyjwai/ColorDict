package com.skwear.colorthesaurus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    ImageView imageView;
    //TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onWindowFocusChanged(true);
        setContentView(R.layout.activity_second);

        imageView = (ImageView) findViewById(R.id.imageView);
        //textView = (TextView) findViewById(R.id.textView);

        Intent intentColor = getIntent();
        final int colorTouched = intentColor.getIntExtra("color",0);

        setDictionary(colorTouched);
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

    public void setDictionary(int pixel) {
        String angercolor = Integer.toHexString(ContextCompat.getColor(this,R.color.colorAnger));
        String happinesscolor = Integer.toHexString(ContextCompat.getColor(this,R.color.colorHappiness));
        String sadnesscolor = Integer.toHexString(ContextCompat.getColor(this,R.color.colorSadness));
        String disgustcolor = Integer.toHexString(ContextCompat.getColor(this,R.color.colorDisgust));
        String surprisecolor = Integer.toHexString(ContextCompat.getColor(this,R.color.colorSurprise));
        String fearcolor = Integer.toHexString(ContextCompat.getColor(this,R.color.colorFear));
        String s = Integer.toHexString(pixel);
        if (s.equals(angercolor)) {
            imageView.setImageDrawable(getDrawable(R.drawable.pie_anger));

        } else if (s.equals(happinesscolor)) {
            imageView.setImageDrawable(getDrawable(R.drawable.pie_happiness));

        } else if (s.equals(sadnesscolor)) {
            imageView.setImageDrawable(getDrawable(R.drawable.pie_sadness));

        } else if (s.equals(disgustcolor)) {
            imageView.setImageDrawable(getDrawable(R.drawable.pie_disgust));

        } else if (s.equals(surprisecolor)) {
            imageView.setImageDrawable(getDrawable(R.drawable.pie_surprise));

        } else if (s.equals(fearcolor)) {
            imageView.setImageDrawable(getDrawable(R.drawable.pie_fear));

        }
    }

    @Override
    public void onBackPressed() {
        int colorTouched = getIntent().getIntExtra("color",0);
        Intent intent = new Intent(this,BackAnimationActivity.class);
        intent.putExtra("color",colorTouched);
        startActivity(intent);
        overridePendingTransition(0,0);
    }

}
