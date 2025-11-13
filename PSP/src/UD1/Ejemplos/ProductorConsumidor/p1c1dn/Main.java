package UD1.Ejemplos.ProductorConsumidor.p1c1dn;

public class Main {
    public static void main(String[] args) {
        Buffer datos = new Buffer();
        Productor productor = new Productor(datos, VelocidadAcceso.ALEATORIO);
        Consumidor consumidor = new Consumidor(datos, VelocidadAcceso.ALEATORIO);
        productor.start();
        consumidor.start();
    }
}
