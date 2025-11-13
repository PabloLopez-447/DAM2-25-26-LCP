package UD1.Ejemplos.ProductorConsumidor.p1c1dn;

import java.util.Random;

enum VelocidadAcceso {
    LENTO(1000), RAPIDO(100), INMEDIATO(0), ALEATORIO(-1);
    private int milisegundos;
    VelocidadAcceso(int milisegundos) {
        this.milisegundos = milisegundos;
    }
    public int getMilisegundos() {
        if (milisegundos>=0)
            return milisegundos;
        return new Random().nextInt(LENTO.getMilisegundos());
    }
}

