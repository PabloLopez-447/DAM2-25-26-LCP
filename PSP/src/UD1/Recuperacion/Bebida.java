package UD1.Recuperacion;

import java.util.Random;

public class Bebida {
    static Random rand = new Random();

    int numero;
    boolean tieneAlcohol;

    public Bebida(int numero) {
        this.numero = numero;

        int i = rand.nextInt(2);

        if (i == 0) {
            tieneAlcohol = true;
        } else {
            tieneAlcohol = false;
        }
    }

    public boolean esAlcoholica() {
        return tieneAlcohol;
    }

    @Override
    public String toString() {
        return "Bebida " +  numero + (tieneAlcohol ? " alcoholica" : " sin alcohol");
    }
}
