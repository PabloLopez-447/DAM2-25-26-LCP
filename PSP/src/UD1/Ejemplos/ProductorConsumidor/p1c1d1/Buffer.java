package UD1.Ejemplos.ProductorConsumidor.p1c1d1;

public class Buffer {
    private String str;
    private boolean disponible = false;

    public synchronized String get() throws InterruptedException {
        while (!disponible)
            wait();
        notify();
        disponible = false;
        System.out.println("GET " + str);
        return str;
    }

    public synchronized void put(String str) throws InterruptedException {
        while (disponible)
            wait();
        this.str = str;
        System.out.println("PUT " + str);
        disponible = true;
        notify();
    }
}
