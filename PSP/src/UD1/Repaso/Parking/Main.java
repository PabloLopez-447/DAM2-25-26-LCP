package UD1.Repaso.Parking;

public class Main {
    static final int NUM_COCHES = 50;

    public static void main(String[] args) {
        Parking aparcamiento = new Parking();
        Coche[] coches = new Coche[NUM_COCHES];
        for (int i = 0; i < coches.length; i++) {
            coches[i] = new Coche(""+(i+1),aparcamiento);
            coches[i].start();
        }
        for (Coche coche : coches) {
            try{ coche.join(); }
            catch (InterruptedException e) { }
        }
        System.out.println("\nFin de la simulación.");
}
}
