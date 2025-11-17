package UD1.Examen.Ejercicio1;

public class Pintor extends Thread {
    int tiempoTrabajo;
    Casa casa;
    String colorFavorito;
    int nVecesPintado = 0;

    public Pintor(String name, Casa casa, int tiempoTrabajo) {
        super(name);
        this.casa = casa;
        this.colorFavorito = "Color " + name;
        this.tiempoTrabajo = tiempoTrabajo;
    }

    @Override
    public void run() {
        for (int i = 0; i < tiempoTrabajo; i++) {
            casa.pintar(this);
        }
    }

    @Override
    public String toString() {
        return getName();
    }
}
