package UD1.Ejercicios.Votaciones;

import java.util.List;
import java.util.Random;

public class Urna {
    List<Partido> partidos;
    Random rand = new Random();

    public Urna(List<Partido> partidos) {
        this.partidos = partidos;
    }

    public synchronized Partido darPartido() {
        return partidos.get(rand.nextInt(partidos.size()));
    }
}
