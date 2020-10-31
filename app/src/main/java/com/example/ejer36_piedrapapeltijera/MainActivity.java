package com.example.ejer36_piedrapapeltijera;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declaramos un objeto de la clase JGame

    private JGame game;

    /**********************************************************************************************
     onCreate
     **********************************************************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    /**********************************************************************************************
     Objetos de la clase
     **********************************************************************************************/

        // -----------------------------------------------------------------------------------------
        // Instanciamos el objeto game de la clase JGame
        // -----------------------------------------------------------------------------------------

        game = new JGame();

        // -----------------------------------------------------------------------------------------
        // Enlazamos con la vista
        // -----------------------------------------------------------------------------------------

        ImageButton btnJugar = findViewById(R.id.btnJugar);
        TextView tvPuntos = findViewById(R.id.tvPuntos);

        // -----------------------------------------------------------------------------------------
        // Si hay record en las Sharedpreferences, lo leemos y se lo pasamos al atributo record de la clase JGame
        // -----------------------------------------------------------------------------------------

        SharedPreferences records = MainActivity.this.getSharedPreferences("Datos", 0);
        game.setRecordPuntos(records.getInt("record_puntos", 0));

        // Mostramos en pantalla el record almacenado en las Sharereferences

        tvPuntos.setText(String.format(getString(R.string.tv_puntos_record), game.getRecord()));

        // -----------------------------------------------------------------------------------------
        // Programamos el click en el botón para lanzar JuegoActivity e iniciar la partida
        // -----------------------------------------------------------------------------------------

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