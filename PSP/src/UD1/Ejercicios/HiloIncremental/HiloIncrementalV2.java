package UD1.Ejercicios.HiloIncremental;

public class HiloIncrementalV2 extends Thread {
    final static int NUM_HILOS = 10;
    final static int NUM_INCREMENTOS = 10;
    Contador contador;

    public HiloIncrementalV2(Contador contador) {
        this.contador = contador;
    }

    public void run() {
        for (int i = 0; i < NUM_INCREMENTOS; i++) {
            contador.aumentar();
        }

    }

    public static void main(String[] args) {

        Contador contador = new Contador();
        HiloIncrementalV2[] hilos = new HiloIncrementalV2[NUM_HILOS];
        HiloIncrementalV2 hilo;

        for (int i = 0; i < NUM_HILOS; i++) {
            hilo = new HiloIncrementalV2(contador);
            hilos[i] = hilo;
            hilo.start();
        }

        for (HiloIncrementalV2 h : hilos) {
            try {
                h.join();
            } catch (Exception e) {
                e.getMessage();
            }
        }

        System.out.println(contador.getContador());

        if (contador.getContador() == NUM_HILOS * NUM_INCREMENTOS) {
            System.out.println("ta bien");
        } else {
            System.out.println("ta mal");
        }

    }

}
