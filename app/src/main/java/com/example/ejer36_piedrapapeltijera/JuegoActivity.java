package com.example.ejer36_piedrapapeltijera;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class JuegoActivity extends AppCompatActivity {

    private JGame game;

    ImageView imgAleatoria;

    TextView tvFallos;
    TextView tvResultado;
    TextView tvPuntosAcumulados;
    TextView tvNuevoRecord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        game=new JGame();

        ImageView imgPiedra = findViewById(R.id.imgPiedra);
        ImageView imgPapel = findViewById(R.id.imgPapel);
        ImageView imgTijera = findViewById(R.id.imgTijera);
        imgAleatoria = findViewById(R.id.imgAleatoria);

        tvFallos = findViewById(R.id.tvFallos);
        tvResultado = findViewById(R.id.tvResultado);
        tvPuntosAcumulados = findViewById(R.id.tvPuntosAcumulados);
        tvNuevoRecord = findViewById(R.id.tvNuevoRecord);

        ImageButton btnNuevaPartida = findViewById(R.id.btnNuevaPartida);

        imgPiedra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eleccionUsuario();
            }
        });

        imgPapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eleccionUsuario();
            }
        });
        imgTijera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eleccionUsuario();
            }
        });


    }

    private void eleccionUsuario(){
        game.jugadaMovil();
        String txt=game.getImagenAleatoria();
        int resId = getResources().getIdentifier(txt,"drawable",getPackageName());
        imgAleatoria.setImageResource(resId);
    }
}