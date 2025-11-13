package UD1.Ejemplos.ProductorConsumidor.p1cndn;

public class Consumidor extends Thread {
    Buffer buffer;
    VelocidadAcceso velocidad;
    public Consumidor(String nombreHilo, Buffer buffer, VelocidadAcceso velocidad) {
        super(nombreHilo);
        this.buffer = buffer;
        this.velocidad = velocidad;
    }
    @Override
    public void run() {
        for (int i = 0; i < 15; i++) {
            try {
                buffer.get(getName());
                sleep(velocidad.getMilisegundos());
            } catch (InterruptedException e) {
            }
        }
    }
}

