package com.cayhualla.practica_calificada_03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {
    private static final int REGISTER_FORM_REQUEST = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        public void showRegister(View view){
            startActivityForResult(new Intent(this, RegisterUusarioActivity.class), REGISTER_FORM_REQUEST);
        }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REGISTER_FORM_REQUEST) {
            // refresh data
            initialize();
        }
    }

    private void initialize() {
    }


}

