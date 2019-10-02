package com.aa.a.reviewtalent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.airbnb.lottie.LottieAnimationView;

public class SplashActivity extends AppCompatActivity {
    private Handler handler;
    private LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        lottieAnimationView= findViewById(R.id.glass_dance);
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                lottieAnimationView.playAnimation();
                finish();
            }
        },3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        lottieAnimationView.cancelAnimation();
    }
}
