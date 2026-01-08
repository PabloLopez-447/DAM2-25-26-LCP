package UD1.Repaso.Parking;

import java.util.Random;

public class Coche extends Thread {
    Parking parking;

    public Coche(String s, Parking aparcamiento) {
        this.parking = aparcamiento;
    }

    @Override
    public void run() {
        super.run();

        parking.aparcar(this);
        try {
            sleep(new Random().nextInt(100) + 1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        parking.salir(this);
    }
}
