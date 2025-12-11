package com.example.eva;

public class Partida {
    int dificultad;
    int nTiradas;
    int rachaMax;

    public Partida() {
    }

    public Partida(int dificultad, int nTiradas, int rachaMax) {
        this.dificultad = dificultad;
        this.nTiradas = nTiradas;
        this.rachaMax = rachaMax;
    }

    public int getDificultad() {
        return dificultad;
    }

    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }

    public int getnTiradas() {
        return nTiradas;
    }

    public void setnTiradas(int nTiradas) {
        this.nTiradas = nTiradas;
    }

    public int getRachaMax() {
        return rachaMax;
    }

    public void setRachaMax(int rachaMax) {
        this.rachaMax = rachaMax;
    }

    @Override
    public String toString() {
        return "Partida{" +
                "dificultad=" + dificultad +
                ", nTiradas=" + nTiradas +
                ", rachaMax=" + rachaMax +
                '}';
    }
}

