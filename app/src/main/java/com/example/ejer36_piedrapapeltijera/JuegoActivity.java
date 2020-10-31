package com.example.ejer36_piedrapapeltijera;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class JuegoActivity extends AppCompatActivity {

    /**********************************************************************************************
     Funciones de la clase
     **********************************************************************************************/

    // --------------------------------------------------------------------------------------------
    // jugada() - Realiza las operaciones en cada jugada
    // --------------------------------------------------------------------------------------------

    private void jugada(int uEleccion) {

        // Si todavía no hemos perdido tres veces (3 fallos acumulados)

        if (game.getFallos()<3){
            // Creamos la jugada aleatoria de la app llamando al método del objeto de la clase JGame
            game.jugadorAleatorio();
            // Gestionamos el string que devuelve y lo convertimos en el número de id del recurso
            String txt = game.getImagenAleatoria();
            int resId = getResources().getIdentifier(txt, "drawable", getPackageName());
            imgAleatoria.setImageResource(resId);
            // Declaramos la variable que contiene la elección del usuario
            int usuarioEleccion = uEleccion;
            // Comparamos la jugada del usuario con la jugada aleatoria
            game.comparaJugada(usuarioEleccion);
            // Mostramos en pantallalos puntos acumulados
            String puntos = String.format(getString(R.string.tv_puntos_acumulados),game.getPuntosAcumulados());
            tvPuntosAcumulados.setText(puntos);
            // Mostramos en pantalla el resultado de la jugada (Empate / Victoria / Derrota
            String resultado = String.format(getString(R.string.tv_resultado),game.getResultado());
            tvResultado.setText(resultado);
            // Mostramos en pantalla los fallos acumulados
            String fallos = String.format(getString(R.string.tv_fallos),game.getFallos());
            tvFallos.setText(fallos);

        // Si ya hemos perdido tres veces (3 fallos acumulados)

        }else{

            // Desabilitamos las imágenes para que el usuario no siga jugando

            imgPiedra.setEnabled(false);
            imgPapel.setEnabled(false);
            imgTijera.setEnabled(false);
        }

        // Comprobamos si hay nuevo record y lo mostramos
        if (game.nuevoRecord()){
            tvNuevoRecord.setText(String.format(getString(R.string.tv_nuevo_record), game.getRecord()));
            grabarRecord();
        }
    }

    // --------------------------------------------------------------------------------------------
    // nuevaPartida() - Realiza las operaciones al inicio de cada partida
    // --------------------------------------------------------------------------------------------

    private void nuevaPartida(){

        // Leemos el record que está en las Sharedpreferences

        leerRecord();

        // Ponemos a cero los puntos acumulados y los fallos

        game.iniciaContadores();

        // Habilitamos las imágenes para que el jugador pueda utilizarlas haciendo click en ellas

        imgPiedra.setEnabled(true);
        imgPapel.setEnabled(true);
        imgTijera.setEnabled(true);

        // Limpamos los Textview de la vista

        tvFallos.setText("");
        tvResultado.setText("");
        tvNuevoRecord.setText("");
        tvPuntosAcumulados.setText("");
    }

    // --------------------------------------------------------------------------------------------
    // leerRecord() - Si hay record en las Sharedpreferences, lo leemos y se lo pasamos al atributo record de la clase JGame
    // --------------------------------------------------------------------------------------------

    private void leerRecord(){
        SharedPreferences records = JuegoActivity.this.getSharedPreferences("Datos", 0);
        game.setRecordPuntos(records.getInt("record_puntos", 0));
    }

    // --------------------------------------------------------------------------------------------
    // grabarRecord() - Si hay nuevo record, lo guardamos en las Sharedpreferences para mostrarlo cuando iniciamos la aplicación en el MainActivity
    // --------------------------------------------------------------------------------------------

    private void grabarRecord(){
        SharedPreferences records = JuegoActivity.this.getSharedPreferences("Datos", 0);
        SharedPreferences.Editor editor = records.edit();
        editor.putInt("record_puntos", game.getRecord());
        editor.apply(); //Equivalente a editor.commit();
    }

    /**********************************************************************************************
     Objetos de la clase
     **********************************************************************************************/

    // ---------------------------------------------------------------------------------------------
    // Declaramos un objeto de la clase JGame
    // ---------------------------------------------------------------------------------------------

    private JGame game;

    // ---------------------------------------------------------------------------------------------
    // Declaramos objetos de la vista
    // ---------------------------------------------------------------------------------------------

    TextView tvFallos;
    TextView tvResultado;
    TextView tvPuntosAcumulados;
    TextView tvNuevoRecord;
    ImageView imgAleatoria;
    ImageView imgPiedra;
    ImageView imgPapel;
    ImageView imgTijera;

    /**********************************************************************************************
     onCreate
     **********************************************************************************************/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        // -----------------------------------------------------------------------------------------
        // Instanciamos el objeto game de la clase JGame
        // -----------------------------------------------------------------------------------------

        game=new JGame();

        // -----------------------------------------------------------------------------------------
        // Enlazamos con la vista
        // -----------------------------------------------------------------------------------------

        imgPiedra = findViewById(R.id.imgPiedra);
        imgPapel = findViewById(R.id.imgPapel);
        imgTijera = findViewById(R.id.imgTijera);
        imgAleatoria = findViewById(R.id.imgAleatoria);
        ImageButton btnNuevaPartida = findViewById(R.id.btnNuevaPartida);

        tvFallos = findViewById(R.id.tvFallos);
        tvResultado = findViewById(R.id.tvResultado);
        tvPuntosAcumulados = findViewById(R.id.tvPuntosAcumulados);
        tvNuevoRecord = findViewById(R.id.tvNuevoRecord);

        // -----------------------------------------------------------------------------------------
        // Empieza una nueva partida llamando a la función
        // -----------------------------------------------------------------------------------------

        nuevaPartida();

        // -----------------------------------------------------------------------------------------
        // Programamos los clicks en cada imagen (piedra, papel o tijera)
        // -----------------------------------------------------------------------------------------

        imgPiedra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Gestionamos la jugada llamando a la función jugada()
                // Pasamos parámetro 0 para decirle que hemos hecho click en "piedra"
                jugada(0);
            }
        });

        imgPapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Gestionamos la jugada llamando a la función jugada()
                // Pasamos parámetro 1 para decirle que hemos hecho click en "papel"
                jugada(1);
            }
        });

        imgTijera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Gestionamos la jugada llamando a la función jugada()
                // Pasmos parámetro 2 para decirle que hemos hecho click en "tijera"
                jugada(2);
            }
        });

        // -----------------------------------------------------------------------------------------
        // Programamos el click en el botón nuevaPartida
        // -----------------------------------------------------------------------------------------

        btnNuevaPartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Llamamos a la función que realiza las tareas para comenzar una nueva partida
                nuevaPartida();
            }
        });
    }

    /**********************************************************************************************
     onPause
     **********************************************************************************************/

    // ---------------------------------------------------------------------------------------------
    // Automaticamente saltamos a la pantalla PubliActivity
    // ---------------------------------------------------------------------------------------------

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Intent nueva_pantalla = new Intent(JuegoActivity.this, PubliActivity.class);
        startActivity(nueva_pantalla);
        finish();
    }
}