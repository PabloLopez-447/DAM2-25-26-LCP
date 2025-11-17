package UD1.Examen.Ejercicio1;

import java.util.Random;

public class Casa {
    final static int NUM_TABIQUES = 25;
    final static int TIEMPO_MAXIMO_DESCANSO = 3;
    final static Random rnd = new Random();
    String colorActual = "color";

    public synchronized void pintar(Pintor pintor) {
        try {
            notifyAll();
            for (int i = 0; i < NUM_TABIQUES; i++) {
                Thread.sleep(rnd.nextInt(TIEMPO_MAXIMO_DESCANSO));
            }
            System.out.println(pintor + " pinto de " + pintor.colorFavorito);
            colorActual = pintor.colorFavorito;
            pintor.nVecesPintado++;
            wait();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void fotografiar() {
        try {
            notifyAll();
            System.out.println("Foto sacada la casa estaba de color: " + colorActual);
            wait();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
