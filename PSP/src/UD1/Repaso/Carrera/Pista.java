package UD1.Repaso.Carrera;

import java.util.Random;

public class Pista {
    boolean empezo = false;

    public synchronized void prepararCorredor(Corredor corredor) {
        Random random = new Random();
        while(!empezo) {
            System.out.println(corredor + "en sus marcas");
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(corredor + "empieza a correr");

        try {
            Thread.sleep(random.nextInt(5000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(corredor + "termino");
    }

    public synchronized void disparar(){
        System.out.println("Empieza la movida");
        empezo = true;
        notifyAll();
    }
}
