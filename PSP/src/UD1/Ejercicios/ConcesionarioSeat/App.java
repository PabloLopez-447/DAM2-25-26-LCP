package UD1.Ejercicios.ConcesionarioSeat;

import java.util.ArrayList;
import java.util.List;

public class App {
    static final int NUM_LEON = 10;
    static final int NUM_IBIZA = 7;
    static final int NUM_ATECA = 3;
    static final int NUM_CLIENTES = 50;

    public static void main(String[] args) {
        List<Coche> coches = new ArrayList<>();
        for (int i = 1; i <= NUM_LEON; i++) {
            coches.add(new Coche(i, "Leon"));
        }
        for (int i = 1; i <= NUM_IBIZA; i++) {
            coches.add(new Coche(i, "Leon"));
        }
        for (int i = 1; i <= NUM_ATECA; i++) {
            coches.add(new Coche(i, "Leon"));
        }

        Vendedor v = new Vendedor(coches);
        Cliente[] clientes = new Cliente[NUM_CLIENTES];
        for (int i = 0; i < clientes.length; i++) {
            clientes[i] = new Cliente(v);
            clientes[i].start();
        }

        for (Cliente cliente : clientes) {
            try {
                cliente.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
