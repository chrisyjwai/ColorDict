package com.skwear.colordict;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    /*----------------------------------------------------------------------------------------------
                                       Declarations
    ----------------------------------------------------------------------------------------------*/
    private FrameLayout splash;

    private ImageView splashpie;
    private ImageView backsplash;

    private Animatable piesplashanim;
    private Animatable backcircleanim;
    //----------------------------------------------------------------------------------------------
    private FrameLayout pie;

    private RadialMenu radialMenu;
    //----------------------------------------------------------------------------------------------
    private FrameLayout pieAnim;

    private PieAnimView pieAnimView;
    private ImageView backAnimArrowView;
    //----------------------------------------------------------------------------------------------
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setFullscreen();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Init();

        Splash();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
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

    private void Splash() {
        AVDWrapper.Callback callback = new AVDWrapper.Callback() {
            @Override
            public void onAnimationDone() {
                splash.setVisibility(ViewGroup.GONE);
                pie.setVisibility(ViewGroup.VISIBLE);

                return;
            }
        };

        AVDWrapper avdWrapper = new AVDWrapper(handler, callback, piesplashanim, backcircleanim);

        avdWrapper.start(getResources().getInteger(R.integer.animation_duration));
    }

    private void Init() {
        /*------------------------------------------------------------------------------------------
                                       Initializations
        ------------------------------------------------------------------------------------------*/
        splash = (FrameLayout) findViewById(R.id.splashGroup);

        splashpie = (ImageView) findViewById(R.id.splash);
        backsplash = (ImageView) findViewById(R.id.back_circle_splash);

        piesplashanim = (Animatable) splashpie.getDrawable();
        backcircleanim = (Animatable)  backsplash.getDrawable();
        //------------------------------------------------------------------------------------------
        pie = (FrameLayout) findViewById(R.id.pieGroup);

        radialMenu = (RadialMenu) findViewById(R.id.pieView);
        //------------------------------------------------------------------------------------------
        pieAnim = (FrameLayout) findViewById(R.id.pieAnim);

        pieAnimView = (PieAnimView) findViewById(R.id.pieAnimView);
        backAnimArrowView = (ImageView) findViewById(R.id.backAnimArrowView);
        //------------------------------------------------------------------------------------------

        /*------------------------------------------------------------------------------------------
                                       set View Methods
        ------------------------------------------------------------------------------------------*/
        radialMenu.setPieAnim(pieAnim);
        radialMenu.setPie(pie);
        //------------------------------------------------------------------------------------------
        pieAnimView.setPieAnim(pieAnim);
        pieAnimView.setBackView(backAnimArrowView);


    }

}
