package com.example.ejer36_piedrapapeltijera;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton btnJugar = findViewById(R.id.btnJugar);

        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nueva_pantalla = new Intent(MainActivity.this, JuegoActivity.class);
                startActivity(nueva_pantalla);
                finish();
            }
        });
    }
}