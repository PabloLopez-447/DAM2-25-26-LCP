package UD1.Examen.Ejercicio1;

public class Fotografo extends Thread {
    Casa casa;
    int tiempoTrabajo;

    public Fotografo(Casa casa, int tiempoTrabajo) {
        this.casa = casa;
        this.tiempoTrabajo = tiempoTrabajo;
    }

    @Override
    public void run() {
        for (int i = 0; i < tiempoTrabajo; i++) {
            casa.fotografiar();
        }
    }
}
