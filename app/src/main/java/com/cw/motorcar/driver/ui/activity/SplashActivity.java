package com.cw.motorcar.driver.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.cw.motorcar.R;
import com.cw.motorcar.storage.SharedPrefManager;

public class SplashActivity extends AppCompatActivity {
    private Handler handler = new Handler();
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context = SplashActivity.this;
        handler.postDelayed(mTask, 1000);
    }

    Runnable mTask = new Runnable() {
        @Override
        public void run() {
            if (SharedPrefManager.getInstance(context).isLoggedIn()) {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
            } else {
                startActivity(new Intent(SplashActivity.this, WelcomeActivity.class));
            }
            finish();

        }
    };
}