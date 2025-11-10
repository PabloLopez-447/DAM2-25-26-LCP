package UD1.Ejercicios.Parking;

public class AppParking {
    public static void main(String[] args) throws Exception {
        final int NUM_PLAZAS = 10;
        final int NUM_CONDUCTORES = 50;

        Parking parking = new Parking(NUM_PLAZAS);

        for (int i = 1; i <= NUM_CONDUCTORES; i++) {
            new Conductor(i, parking).start();
        }
    }
}
