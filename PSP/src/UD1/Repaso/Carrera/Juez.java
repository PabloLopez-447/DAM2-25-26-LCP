package UD1.Repaso.Carrera;

public class Juez extends Thread {
    Pista pista;

    public Juez(Pista pista) {
        this.pista = pista;
    }
    @Override
    public void run() {
        pista.disparar();
    }
}
