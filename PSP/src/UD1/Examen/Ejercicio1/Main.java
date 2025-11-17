package UD1.Examen.Ejercicio1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    final static int NUM_PINTORES = 10;
    final static int TIEMPO_TRABAJO = 4;

    public static void main(String[] args) {
        Casa casa = new Casa();
        Pintor[] pintores = new Pintor[NUM_PINTORES];
        List<Thread> hilos = new ArrayList<>();
        for (int i = 0; i < pintores.length; i++) {
            pintores[i] = new Pintor("" + i, casa, TIEMPO_TRABAJO);
            hilos.add(pintores[i]);
            pintores[i].start();
        }

        Fotografo fotografo = new Fotografo(casa,TIEMPO_TRABAJO);
        hilos.add(fotografo);
        fotografo.start();

        for (Thread t : hilos) {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
