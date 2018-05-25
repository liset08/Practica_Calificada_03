package com.cayhualla.practica_calificada_03;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cayhualla.practica_calificada_03.Activities.LoginActivity;

public class SplachActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splach);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(SplachActivity.this, LoginActivity.class));
            }
        }, 3000);


    }
}
