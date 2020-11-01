package com.example.ejer36_piedrapapeltijera;

import java.util.Random;

public class JGame {

    /**********************************************************************************************
    Atributos
    **********************************************************************************************/

    private final String TAG = "Jgame class";
    private int num_aleatorio;
    private int fallos;
    private int puntosAcumulados;
    private int record_puntos;
    private String imagenFile;
    private String resultado;
    private int sonido;

    /**********************************************************************************************
     Propiedades
     **********************************************************************************************/

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

    public int getRecord() {
        return record_puntos;
    }

    public int getSonido(){
        return sonido;
    }

    public void setRecordPuntos(int valor) {
        record_puntos = valor;
    }

    /**********************************************************************************************
     Funciones
     **********************************************************************************************/

    // --------------------------------------------------------------------------------------------
    // jugadorAleatorio() - Número aleatorio para la jugada de la app
    // --------------------------------------------------------------------------------------------

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

    // --------------------------------------------------------------------------------------------
    // comparaJugada() - Compara la jugada del usuario con la de la app
    // --------------------------------------------------------------------------------------------

    public int comparaJugada(int eleccion) {

        // 0 - Piedra (rock)
        // 1 - Papel (paper)
        // 2 - Tijera (tijera)

        // Gestionamos el empate
        if (eleccion == num_aleatorio) {
            empataUsuario();
        // Si no hay empate, gestionamos quién gana o pierde
        } else {
            switch (eleccion) {
                case 0:
                    if (num_aleatorio == 1) {
                        pierdeUsuario();
                    } else {
                        ganaUsuario();
                    }
                    break;
                case 1:
                    if (num_aleatorio == 0) {
                        ganaUsuario();
                    } else {
                        pierdeUsuario();
                    }
                    break;
                case 2:
                    if (num_aleatorio == 0) {
                        pierdeUsuario();
                    } else {
                        ganaUsuario();
                    }
                    break;
            }
        }
        return puntosAcumulados;
    }

    // --------------------------------------------------------------------------------------------
    // empataUsuario() - Gestionamos que sucede cuando el usuario empata la jugada
    // --------------------------------------------------------------------------------------------

    public int empataUsuario(){
        puntosAcumulados = puntosAcumulados + 1;
        resultado = "Empate";
        sonido=0;
        return puntosAcumulados;
    }

    // --------------------------------------------------------------------------------------------
    // ganaUsuario() - Gestionamos que sucede cuando el usuario gana la jugada
    // --------------------------------------------------------------------------------------------

    public int ganaUsuario() {
        puntosAcumulados = puntosAcumulados + 3;
        resultado = "Victoria!";
        sonido=2;
        return puntosAcumulados;
    }

    // --------------------------------------------------------------------------------------------
    // pierdeUsuario() - Gestionamos que sucede cuando el usuario pierde la jugada
    // --------------------------------------------------------------------------------------------

    public int pierdeUsuario() {
        fallos++;
        resultado = "Derrota";
        sonido=1;
        return fallos;
    }

    // --------------------------------------------------------------------------------------------
    // nuevoRecord() - Comprobamos si hay nuevo record cuando termina la partida
    // --------------------------------------------------------------------------------------------

    public boolean nuevoRecord() {
        if (puntosAcumulados > record_puntos) {
            record_puntos = puntosAcumulados;
            return true;
        } else {
            return false;
        }
    }

    // --------------------------------------------------------------------------------------------
    // iniciaContadores() - Reiniciamos valores al comienzo de cada partida
    // --------------------------------------------------------------------------------------------

    public void iniciaContadores() {
        puntosAcumulados = 0;
        fallos = 0;
    }

}
