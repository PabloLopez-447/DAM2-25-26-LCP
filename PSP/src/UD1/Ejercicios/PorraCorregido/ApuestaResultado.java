package UD1.Ejercicios.PorraCorregido;

import java.util.Random;

public class ApuestaResultado {
    String resultado;
    int importe;
    int numApuestas;

    public ApuestaResultado(String resultado) {
        this.resultado = resultado;
        this.importe = 0;
        this.numApuestas = 0;
    }

    public synchronized void apuesta(int importe) {
        int importeHastaAhora = this.importe;
        try {
            Thread.sleep(new Random().nextInt(100));
        } catch (InterruptedException ex) {
        }
        this.importe = importeHastaAhora + importe;
        this.numApuestas++;
    }

    @Override
    public String toString() {
        return "{" + numApuestas + " apuestas, que suman " + importe + "€ }";
    }
}