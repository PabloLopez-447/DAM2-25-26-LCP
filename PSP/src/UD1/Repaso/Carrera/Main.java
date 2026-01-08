package UD1.Repaso.Carrera;

public class Main {
    static int NUM_CORREDORES = 5;

    public static void main(String[] args) {
        Thread[] hilos = new Thread[NUM_CORREDORES + 1];
        Pista pista = new Pista();

        // Crear y arrancar corredores
        for (int i = 0; i < NUM_CORREDORES; i++) {
            hilos[i] = new Corredor("Corredor " + i, pista);
            hilos[i].start();
        }

        // Crear y arrancar juez EL ÚLTIMO
        hilos[NUM_CORREDORES] = new Juez(pista);
        hilos[NUM_CORREDORES].start();

        // Esperar a que terminen todos
        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
