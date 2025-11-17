package UD1.Examen.Ejercicio2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pelicula {
    final static Random rnd = new Random();

    int aforoSala;
    String nombre;
    int sitiosCogidos = 0;
    List<Cinefilo> cinefilos = new ArrayList<>();

    public Pelicula(String nombre, int aforoSala) {
        this.nombre = nombre;
        this.aforoSala = aforoSala;
    }

    public synchronized boolean comprarEntrada(Cinefilo cinefilo){
        try {
            System.out.println(cinefilo + " llega a la taquilla de: " + nombre);
            Thread.sleep(rnd.nextInt(20));
            if (rnd.nextInt(2) == 1){
                System.out.println(cinefilo + " ha comprado una entrada para: " + nombre);
                sitiosCogidos++;
                cinefilos.add(cinefilo);
                return true;
            } else {
                System.out.println(cinefilo + " se va de la cola de: " + nombre);
                return false;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
