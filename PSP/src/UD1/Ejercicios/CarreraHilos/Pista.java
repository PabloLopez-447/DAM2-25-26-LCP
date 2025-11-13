package UD1.Ejercicios.CarreraHilos;

import java.util.Random;

public class Pista {

    private final int metros;
    private boolean salidaDada = false;
    private final Random rnd = new Random();

    public Pista(int metros) {
        this.metros = metros;
    }

    // Los corredores esperan aquí
    public void esperarSalida() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " en sus puestos...");
            while (!salidaDada) {    // usar while SIEMPRE
                try {
                    wait();
                } catch (InterruptedException e) {}
            }
        }
    }

    // Llamado por el juez
    public void darSalida() {
        synchronized (this) {
            System.out.println("¡BANG! Salida dada.");
            salidaDada = true;
            notifyAll();
        }
    }

    // La carrera se ejecuta sin synchronized
    public void correr() {
        int distancia = 0;
        while (distancia < metros) {
            try {
                Thread.sleep(rnd.nextInt(500)); // retraso por vallas
            } catch (InterruptedException e) {}
            distancia += 10;
        }
        System.out.println(Thread.currentThread().getName() + " terminó!");
    }
}
