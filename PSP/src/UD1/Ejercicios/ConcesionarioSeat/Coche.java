package UD1.Ejercicios.ConcesionarioSeat;

import java.util.Random;

public class Coche {
    static Random rnd = new Random();
    int numCoche, numVisitas;
    String modelo;
    boolean ocupado;

    public Coche(int numCoche, String modelo) {
        this.numCoche = numCoche;
        this.modelo = modelo;
        this.ocupado = false;
        this.numVisitas = 0;
    }

    @Override
    public String toString() {
        return modelo + " " + numCoche;
    }

    public synchronized boolean intentaComprar(Cliente cliente) {
        ocupado = true;

        try {
            Thread.sleep(20);
            if (rnd.nextInt(100) < numVisitas++) {
                System.out.println(cliente + "ha comprado" + this);
                return true;
            } else {
                ocupado = false;
                return false;
            }

        } catch (Exception e) {

        }
        ocupado = false;
        return false;

    }

}