package UD1.Ejercicios.CarreraHilos;

public class Main {
    static final int NUM_CALLES = 8;

    public static void main(String[] args) {
        Pista pista = new Pista(100);
        Manin manin = new Manin(pista);
        Corredor[] corredores = new Corredor[NUM_CALLES];

        for (int i = 0; i < NUM_CALLES; i++) {
            corredores[i] = new Corredor("Corredor" + i, pista);
            corredores[i].start();
        }

        manin.start();

        for(Corredor c : corredores) {
            try {
                c.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
