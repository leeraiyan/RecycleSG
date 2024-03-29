package com.example.recyclesg;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EasySplashScreen config = new EasySplashScreen(SplashScreenActivity.this)
                .withFullScreen()
                .withTargetActivity(NewHomePage.class)
                .withSplashTimeOut(3000)
                .withBackgroundColor(Color.parseColor("#95CEE0"))
                .withAfterLogoText(" Recycling,\nMade Easy")
                .withLogo(R.mipmap.ic_launcher_round);

        config.getAfterLogoTextView().setTextColor(Color.WHITE);
        View easySplashScreen = config.create();
        setContentView(easySplashScreen);
    }
}
