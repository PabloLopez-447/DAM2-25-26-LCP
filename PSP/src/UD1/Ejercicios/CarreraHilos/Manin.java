package UD1.Ejercicios.CarreraHilos;

public class Manin extends Thread {
    Pista pista;

    public Manin(Pista pista) {
        this.pista = pista;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        pista.darSalida();
    }
}
