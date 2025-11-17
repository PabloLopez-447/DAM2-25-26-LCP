package UD1.Ejercicios.CarreraHilos;

import java.util.Random;

public class Pista {

    private final int metros;
    //private boolean salidaDada = false;
    private final Random rnd = new Random();

    public Pista(int metros) {
        this.metros = metros;
    }


    public void esperarSalida() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " en sus puestos...");
            //while (!salidaDada) {
                try {
                    wait();
                } catch (InterruptedException e) {}
            //}
        }
    }


    public void darSalida() {
        synchronized (this) {
            System.out.println("¡BANG! Salida dada.");
            //salidaDada = true;
            notifyAll();
        }
    }


    public void correr() {
        int distancia = 0;
        while (distancia < metros) {
            try {
                Thread.sleep(rnd.nextInt(500));
            } catch (InterruptedException e) {}
            distancia += 10;
        }
        System.out.println(Thread.currentThread().getName() + " terminó!");
    }
}
