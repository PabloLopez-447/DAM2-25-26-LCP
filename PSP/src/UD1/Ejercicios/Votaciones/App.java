package UD1.Ejercicios.Votaciones;

import java.util.ArrayList;
import java.util.List;

public class App {
    static final int CENSO = 100;

    public static void main(String[] args) {
        List<Partido> partidos = new ArrayList<Partido>();
        partidos.add(new Partido("Partido 1"));
        partidos.add(new Partido("Partido 2"));
        partidos.add(new Partido("Partido 3"));
        partidos.add(new Partido("Partido 4"));

        Urna urna = new Urna(partidos);
        Votante[] votantes = new Votante[CENSO];

        for (int i = 0; i < CENSO; i++) {
            votantes[i] = new Votante(urna);
            votantes[i].start();
        }

        for (Votante v : votantes) {
            try {
                v.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        for (Partido p : partidos) {
            System.out.println(p);
        }
    }
}
