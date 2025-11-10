package UD1.Ejercicios.HiloIncremental;

public class HiloIncremental extends Thread {

    final static int NUM_HILOS = 10;
    final static int NUM_INCREMENTOS = 10;
    static int numerin = 0;

    public void run() {
        for (int i = 0; i < NUM_INCREMENTOS; i++) {
            numerin++;
        }

    }

    public static void main(String[] args) {

        for (int i = 0; i < NUM_HILOS; i++) {
            HiloIncremental hilo = new HiloIncremental();
            hilo.start();

            // Esto esta mal
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
            }
        }

        System.out.println(numerin);

        if (numerin == NUM_HILOS * NUM_INCREMENTOS) {
            System.out.println("ta bien");
        } else {
            System.out.println("ta mal");
        }
    }
}
