package UD1.Ejercicios.Votaciones;

import java.util.Arrays;
import java.util.Random;

public class Votante extends Thread {
    static final int CENSO = 10000;
    static final int NUM_PARTIDOS = 5;
    static Urna urna = new Urna(NUM_PARTIDOS);
    static Random rnd = new Random();

    public void run() {
        try {
            Thread.sleep(rnd.nextLong(501));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Votante.votar();
    }

    public static void main(String[] args) {
        
        Votante hilo;
        Votante[] hilos = new Votante[CENSO];

        for (int i = 0; i < CENSO; i++) {
            hilo = new Votante();
            hilos[i] = hilo;
            hilo.start();
        }

        for (Votante h : hilos) {
            try {
                h.join();
            } catch (Exception e) {
                e.getMessage();
            }
        }

        System.out.println(Arrays.toString(urna.votosPartido));
    }

    public static synchronized void votar(){
        urna.votosPartido[rnd.nextInt(NUM_PARTIDOS)]++;

    }
}
