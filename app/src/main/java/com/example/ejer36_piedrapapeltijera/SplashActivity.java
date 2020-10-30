package com.example.ejer36_piedrapapeltijera;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Vamos a esperar 3 segundos y automaticamente que salte a la pantalla MainActivity:
        Handler retardo = new Handler();
        retardo.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent nueva_pantalla = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(nueva_pantalla);
                finish();
            }
        },3000);
    }
}