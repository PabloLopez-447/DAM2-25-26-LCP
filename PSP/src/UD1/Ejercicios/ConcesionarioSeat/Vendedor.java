package UD1.Ejercicios.ConcesionarioSeat;

import java.util.List;

public class Vendedor {
    List<Coche> coches;

    public Vendedor(List<Coche> coches) {
        this.coches = coches;
    }

    public synchronized Coche darCoche() {
        for (Coche coche : coches) {
            if (!coche.ocupado) {
                return coche;
            }
        }
        return null;
    }

    public synchronized void removeCoche(Coche coche) {
        coches.remove(coche);
    }

}
