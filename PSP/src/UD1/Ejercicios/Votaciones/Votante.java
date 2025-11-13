package UD1.Ejercicios.Votaciones;

public class Votante extends Thread {
    Urna urna;

    public Votante(Urna urna) {
        this.urna = urna;
    }

    @Override
    public void run() {
        Partido p = urna.darPartido();
        p.votar();
    }
}
