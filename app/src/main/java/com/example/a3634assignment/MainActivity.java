package com.example.a3634assignment;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //Adds time interval for the Splash Welcome Screen
    private static int SPLASH_TIME = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Adds handler for the Splash Screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splashIntent = new Intent (MainActivity.this, LoginPage.class);
                startActivity(splashIntent);
                finish();
            }
        },SPLASH_TIME);
    }
}
