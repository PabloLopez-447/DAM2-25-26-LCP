package UD1.Repaso.Meteoritos;

public class Meteorito {
    boolean taladrado = false;

    public synchronized void taladrar() {
        if (!taladrado) {
            System.out.println("Taladrando meteorito ");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            taladrado = true;
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Meteorito taladrado, NaveA saliendo");
        }
    }

    public synchronized boolean explotaRepostar() {
        if (taladrado) {
            System.out.println("Repostando nave A");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            notify();
            System.out.println("Poniendo Bomba, NaveBS saliendo");
            return true;
        } else {
            return false;
        }
    }
}
