package com.example.ejer36_piedrapapeltijera;

import java.util.Random;

public class JGame {

    //Atributos
    private final String TAG = "Jgame class";
    private int num_aleatorio;
    private int fallos;
    private int puntosAcumulados;
    private int record_puntos;
    private String imagenFile;
    private String resultado;


    //Propiedades

    public String getImagenAleatoria() {
        return imagenFile;
    }

    public int getPuntosAcumulados() {
        return puntosAcumulados;
    }

    public int getFallos() {
        return fallos;
    }

    public String getResultado() {
        return resultado;
    }

    public int getRecord(){
        return record_puntos;
    }

    public void setRecordPuntos(int valor) {
        record_puntos = valor;
    }


    //Funciones

    // Función para generar jugada del teléfono

    public String jugadorAleatorio() {
        // Genero un número aleatorio entre 0 y 2
        Random aleatorio = new Random();
        num_aleatorio = aleatorio.nextInt(3) + 0;
        // Dependiendo del número aleatorio cargaremos una imagen u otra
        switch (num_aleatorio) {
            case 0:
                imagenFile = "ic_rock";
                break;
            case 1:
                imagenFile = "ic_paper";
                break;
            case 2:
                imagenFile = "ic_scissor";
                break;
        }
        return imagenFile;
    }

    // Función que compara la jugada del usuario con la aleatoria

    public int comparaJugada(int eleccion) {
        if (eleccion == num_aleatorio) {
            // empate
            puntosAcumulados = puntosAcumulados + 1;
            resultado = "Empate";
        } else {
            switch (eleccion) {
                case 0: // piedra
                    if (num_aleatorio == 1) { //papel
                        // pierde usuario
                        pierdeUsuario();
                    } else { //tijera
                        // gana usuario
                        ganaUsuario();
                    }
                    break;
                case 1: //papel
                    if (num_aleatorio == 0) { //piedra
                        // gana usuario
                        ganaUsuario();
                    } else {
                        // pierde usuario
                        pierdeUsuario();
                    }
                    break;
                case 2: //tijera
                    if (num_aleatorio == 0) { //piedra
                        // pierde usuario
                        pierdeUsuario();
                    } else {
                        // gana usuario
                        ganaUsuario();
                    }
                    break;
            }
        }
        return puntosAcumulados;
    }


    // Función gana usuario

    public int ganaUsuario() {
        puntosAcumulados = puntosAcumulados + 3;
        resultado = "Victoria!";
        return puntosAcumulados;
    }

    // Función pierde usuario

    public int pierdeUsuario() {
        fallos++;
        resultado = "Derrota";
        return fallos;
    }

    // Función nuevo record

    public boolean nuevoRecord(){
        if (puntosAcumulados>record_puntos){
            record_puntos=puntosAcumulados;
            return true;
        }else{
            return false;
        }
    }

    // Función que reinicia contador de puntos y falos de la partida

    public void reiniciaPartida(){
        puntosAcumulados=0;
        fallos=0;
    }

}
