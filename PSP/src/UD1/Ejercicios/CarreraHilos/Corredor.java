package UD1.Ejercicios.CarreraHilos;

public class Corredor extends Thread {
    Pista pista;

    public Corredor(String name, Pista pista) {
        super(name);
        this.pista = pista;
    }

    @Override
    public void run() {
        pista.esperarSalida();
        pista.correr();
    }

    @Override
    public String toString() {
        return getName();
    }
}
