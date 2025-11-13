package UD1.Ejemplos.ProductorConsumidor.p1cndn;

import java.util.ArrayList;
import java.util.Random;

public class Buffer {
    private ArrayList<Integer> lista = new ArrayList<Integer>();
    private final int MAX = 10;
    private Random random = new Random();
    public boolean hayDatos() { return lista.size() > 0; }
    public boolean cabenMasDatos() { return lista.size() < MAX; }
    public synchronized int get(String nombreHilo) throws InterruptedException {
        while (!hayDatos())
            wait();
        int dato = lista.remove(0);
        System.out.print(nombreHilo + " GET " + dato + " ");
        System.out.println(lista);
        notify();
        return dato;
    }
    public synchronized void put() throws InterruptedException {
        while (!cabenMasDatos())
            wait();
        int nuevoDato = random.nextInt(100);
        lista.add(nuevoDato);
        System.out.print("PUT " + nuevoDato + " ");
        System.out.println(lista);
        notify();
    }
}