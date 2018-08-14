package com.example.user.creditcardbilling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Start extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Thread T = new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(SPLASH_TIME_OUT);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent(Start.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        };
        T.start();
    }
}

