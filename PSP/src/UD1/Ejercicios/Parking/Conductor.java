package UD1.Ejercicios.Parking;

import java.util.Random;

public class Conductor extends Thread {
    int id;
    Parking parking;
    Random rnd = new Random();

    public Conductor() {
    }

    public Conductor(int idConductor, Parking parking) {
        this.id = idConductor;
        this.parking = parking;
    }

    @Override
    public void run() {
        int plaza = -1;

        while (plaza == -1) {
            plaza = parking.ocuparPlaza(id);
        }

        try {
            Thread.sleep((rnd.nextInt(5) + 1) * 1000);
        } catch (InterruptedException e) {
            e.getMessage();
        }

        parking.liberarPlaza(id, plaza);
    }

}
