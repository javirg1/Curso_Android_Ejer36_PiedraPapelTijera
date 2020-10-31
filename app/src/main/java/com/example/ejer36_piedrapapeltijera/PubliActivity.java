package com.example.ejer36_piedrapapeltijera;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class PubliActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publi);
    }

    /**********************************************************************************************
     onDestroy
     **********************************************************************************************/

    // ---------------------------------------------------------------------------------------------
    // Automaticamente saltamos a la pantalla MainActivity
    // ---------------------------------------------------------------------------------------------

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Intent nueva_pantalla = new Intent(PubliActivity.this, MainActivity.class);
        startActivity(nueva_pantalla);
        finish();
    }
}