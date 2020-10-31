package com.example.ejer36_piedrapapeltijera;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private JGame game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton btnJugar = findViewById(R.id.btnJugar);
        TextView tvPuntos = findViewById(R.id.tvPuntos);
        game = new JGame();

        //Cuando arranca la app, leo de las preferencias a ver si tengo guardado un record:
        SharedPreferences records = MainActivity.this.getSharedPreferences("Datos", 0);
        //record_puntos = taquilla.getInt("record_puntos",0);
        game.setRecordPuntos(records.getInt("record_puntos", 0));
        tvPuntos.setText(String.format(getString(R.string.tv_puntos_record), game.getRecord()));

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