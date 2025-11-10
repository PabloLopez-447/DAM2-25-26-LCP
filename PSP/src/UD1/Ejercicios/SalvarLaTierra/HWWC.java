package UD1.Ejercicios.SalvarLaTierra;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HWWC {
    private final List<Meteorito> meteoritos;

    public HWWC(int n) {
        meteoritos = new ArrayList<>();
        for (int i = 0; i < n; i++)
            meteoritos.add(new Meteorito());
    }

    public synchronized Meteorito obtenerMeteorito() {
        if (meteoritos.isEmpty()) {
            return null;
        } else {
            return meteoritos.get(new Random().nextInt(meteoritos.size()));

        }

    }

    public synchronized void eliminarMeteorito(Meteorito m) {
        meteoritos.remove(m);
    }

    public synchronized boolean quedanMeteoritos() {
        return !meteoritos.isEmpty();
    }
}
