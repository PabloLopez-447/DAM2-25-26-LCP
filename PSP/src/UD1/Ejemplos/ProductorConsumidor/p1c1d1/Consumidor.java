package UD1.Ejemplos.ProductorConsumidor.p1c1d1;

public class Consumidor extends Thread {
    Buffer buffer;
    VelocidadAcceso velocidad;

    public Consumidor(Buffer buffer, VelocidadAcceso velocidad) {
        this.buffer = buffer;
        this.velocidad = velocidad;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                buffer.get();
                sleep(velocidad.getMilisegundos());
            } catch (InterruptedException e) {
            }
        }
    }
}

