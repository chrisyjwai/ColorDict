package com.skwear.colordict;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class scratch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setFullscreen();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scratch);
    }

    private void setFullscreen() {
        //------------------------------------------------------------------------------------------
        //                      Set fullscreen based on API Version
        //------------------------------------------------------------------------------------------
        if (Build.VERSION.SDK_INT >= 19) {
            onWindowFocusChanged(true);
        }

        // If the Android version is lower than Jellybean, use this call to hide
        // the status bar.
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        if (Build.VERSION.SDK_INT >= 16 || Build.VERSION.SDK_INT < 19 ) {
            View decorView = getWindow().getDecorView();
            // Hide both the navigation bar and the status bar.
            // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
            // a general rule, you should design your app to hide the status bar whenever you
            // hide the navigation bar.
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
        //------------------------------------------------------------------------------------------
    }
}
