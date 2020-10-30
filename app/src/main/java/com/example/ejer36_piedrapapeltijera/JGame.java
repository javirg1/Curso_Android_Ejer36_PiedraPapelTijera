package com.example.ejer36_piedrapapeltijera;

import java.util.Random;

public class JGame {

    //Atributos
    private int num_aleatorio;
    private int fallos;
    private int puntosAcumulados;
    private int record;
    private String imagenFile;


    //Propiedades

    public String getImagenAleatoria(){
        return imagenFile;
    }


    //Funciones

    // Función para generar jugada del teléfono

    public String jugadaMovil (){
        // Genero un número aleatorio entre 0 y 2
        Random aleatorio = new Random();
        num_aleatorio = aleatorio.nextInt(3) + 0;
        switch (num_aleatorio){
            case 0:
                imagenFile="ic_rock";
                break;
            case 1:
                imagenFile="ic_paper";
                break;
            case 2:
                imagenFile="ic_scissor";
                break;
        }
        return imagenFile;


    }
}
