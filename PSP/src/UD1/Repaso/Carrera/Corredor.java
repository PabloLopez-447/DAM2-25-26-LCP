package UD1.Repaso.Carrera;

public class Corredor extends Thread {
    Pista pista;

    public Corredor(String name, Pista pista) {
        super(name);
        this.pista = pista;
    }

    @Override
    public void run() {
        pista.prepararCorredor(this);
    }
}
