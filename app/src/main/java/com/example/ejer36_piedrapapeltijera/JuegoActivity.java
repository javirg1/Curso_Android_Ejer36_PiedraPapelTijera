package com.example.ejer36_piedrapapeltijera;

import android.content.SharedPreferences;
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
    ImageView imgPiedra;
    ImageView imgPapel;
    ImageView imgTijera;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        game=new JGame();

        imgPiedra = findViewById(R.id.imgPiedra);
        imgPapel = findViewById(R.id.imgPapel);
        imgTijera = findViewById(R.id.imgTijera);
        imgAleatoria = findViewById(R.id.imgAleatoria);

        tvFallos = findViewById(R.id.tvFallos);
        tvResultado = findViewById(R.id.tvResultado);
        tvPuntosAcumulados = findViewById(R.id.tvPuntosAcumulados);
        tvNuevoRecord = findViewById(R.id.tvNuevoRecord);

        ImageButton btnNuevaPartida = findViewById(R.id.btnNuevaPartida);

        nuevaPartida();

        imgPiedra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                jugada(0);
            }
        });

        imgPapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                jugada(1);


            }
        });
        imgTijera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                jugada(2);

            }
        });

        btnNuevaPartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevaPartida();
            }
        });


    }



    private void jugada(int uEleccion) {
        // Llamo a la función de la clase. Devuelve un string con el nombre de la imagen que muestra aleatoriamente
        if (game.getFallos()<3){
            game.jugadorAleatorio();
            // Gestiono el string que devuelve y lo convierto en el número id del recurso
            String txt = game.getImagenAleatoria();
            int resId = getResources().getIdentifier(txt, "drawable", getPackageName());
            imgAleatoria.setImageResource(resId);
            // Declaro la variable que contiene la elección del usuario
            int usuarioEleccion = uEleccion;
            // comparo la jugada dek usuario con la jugada aleatoria
            game.comparaJugada(usuarioEleccion);
            String puntos = String.format(getString(R.string.tv_puntos_acumulados),
                    game.getPuntosAcumulados());
            tvPuntosAcumulados.setText(puntos);
            String resultado = String.format(getString(R.string.tv_resultado),
                    game.getResultado());
            tvResultado.setText(resultado);
            String fallos = String.format(getString(R.string.tv_fallos),
                    game.getFallos());
            tvFallos.setText(fallos);

        }else{

            imgPiedra.setEnabled(false);
            imgPapel.setEnabled(false);
            imgTijera.setEnabled(false);

        }
        if (game.nuevoRecord()){
            tvNuevoRecord.setText(String.format(getString(R.string.tv_nuevo_record), game.getRecord()));
            SharedPreferences records = JuegoActivity.this.getSharedPreferences("Datos", 0);
            // 0 - for private mode
            SharedPreferences.Editor editor = records.edit();
            //Ahora ya guardo variable - valor:
            editor.putInt("record_puntos", game.getRecord());
            //Aplicamos los cambios:
            editor.apply(); //Equivalente a editor.commit();
        }
    }

    private void nuevaPartida(){
        game.reiniciaPartida();
        imgPiedra.setEnabled(true);
        imgPapel.setEnabled(true);
        imgTijera.setEnabled(true);
        tvFallos.setText("");
        tvResultado.setText("");
        tvNuevoRecord.setText("");
        tvPuntosAcumulados.setText("");
    }




}