package UD1.Examen.Ejercicio2;

public class Main {
    final static int NUM_CINEFILOS = 130;

    public static void main(String[] args) {
        Cine cine = new Cine();
        Cinefilo[] cinefilos = new Cinefilo[NUM_CINEFILOS];

        for (int i = 0; i < cinefilos.length; i++) {
            cinefilos[i] = new Cinefilo("" + i, cine);
            cinefilos[i].start();
        }

        for(Cinefilo c : cinefilos){
            try {
                c.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        for (Pelicula p : cine.peliculas){
            System.out.println(p.nombre + ": " + p.cinefilos);
        }
    }
}
