package UD1.Ejercicios.Parking;

public class Parking {
    int[] plazas;

    public Parking(int numPlazas) {
        plazas = new int[numPlazas];

        for (int i = 0; i < plazas.length; i++) {
            plazas[i] = 0;
        }
    }

    public synchronized int ocuparPlaza(int coche) {
        for (int i = 0; i < plazas.length; i++) {
            if (plazas[i] == 0) {
                plazas[i] = coche;
                System.out.println("coche " + coche + " en plaza " + i);
                return i;
            }
        }

        return -1;
    }

    public synchronized void liberarPlaza(int coche, int plaza) {
        plazas[plaza] = 0;
        System.out.println("coche " + coche + " fuera de plaza " + plaza);
    }
}
