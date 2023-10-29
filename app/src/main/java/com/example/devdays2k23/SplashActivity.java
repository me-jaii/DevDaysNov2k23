package com.example.devdays2k23;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Handler;
public class SplashActivity extends AppCompatActivity {

    private static int SPLASH = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(() -> {
            Intent intent=new Intent(SplashActivity.this, IntroActivity.class);
            startActivity(intent);
            finish();
        },SPLASH);
    }
}