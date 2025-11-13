package UD1.Ejemplos.ProductorConsumidor.p1cndn;

public class Main {
    public static void main(String[] args) {
        final int NUM_CONSUMIDORES = 3;
        Buffer datos = new Buffer();
        Productor productor = new Productor(datos, VelocidadAcceso.RAPIDO);
        productor.start();
        for (int i = 0; i < NUM_CONSUMIDORES; i++)
            new Consumidor("H" + i, datos, VelocidadAcceso.ALEATORIO).start();
    }
}
