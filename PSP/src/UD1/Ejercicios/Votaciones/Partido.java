package UD1.Ejercicios.Votaciones;

public class Partido {
    String nombre;
    int numVotos;

    public Partido(String nombre) {
        this.nombre = nombre;
        this.numVotos = 0;
    }

    public synchronized void votar() {
        try {
            Thread.sleep(25);
            numVotos++;
        } catch (InterruptedException e) {}
    }

    @Override
    public String toString() {
        return "Partido{" +
                "nombre='" + nombre + '\'' +
                ", numVotos=" + numVotos +
                '}';
    }
}
