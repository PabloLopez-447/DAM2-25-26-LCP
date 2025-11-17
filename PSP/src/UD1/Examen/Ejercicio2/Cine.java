package UD1.Examen.Ejercicio2;

import java.util.Random;

public class Cine {
    final static int NUM_PELICULAS = 5;
    final static int AFORO_SALA = 20;
    final static Random rnd = new Random();

    Pelicula[] peliculas = new Pelicula[NUM_PELICULAS];

    public Cine() {
        for (int i = 0; i < NUM_PELICULAS; i++) {
            peliculas[i] = new Pelicula("Pelicula " + i, AFORO_SALA);
        }
    }

    public synchronized Pelicula getPeliculaRnd() {
        int n = rnd.nextInt(peliculas.length);
        if (peliculas[n].sitiosCogidos < peliculas[n].aforoSala){
            return peliculas[n];
        }
        return null;
    }


}
