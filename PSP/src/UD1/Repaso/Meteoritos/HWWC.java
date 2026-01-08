package UD1.Repaso.Meteoritos;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HWWC {
    List<Meteorito> meteoritos = new ArrayList<Meteorito>();

    public HWWC(int nMeteoritos) {
        for (int i = 0; i < nMeteoritos; i++) {
            meteoritos.add(new Meteorito());
        }
    }

    public synchronized boolean hayMeteorito() {
        return !meteoritos.isEmpty();
    }

    public synchronized Meteorito getMeteorito() {

        if (hayMeteorito()) {
            Random rand = new Random();
            return meteoritos.get(rand.nextInt(meteoritos.size()));
        } else {
            return null;
        }
    }

    public synchronized void eliminarMeteorito(Meteorito meteorito) {
        meteoritos.remove(meteorito);
    }

    public void Taladrar() {
        Meteorito meteorito;
        synchronized(meteoritos) {
            if(meteoritos.isEmpty()) return;
            int posMeteorito = new Random().nextInt(meteoritos.size());
            meteorito = meteoritos.get(posMeteorito);
        }
        meteorito.taladrar();
    }

    public void BOOM() {
        synchronized(meteoritos) {
            if(meteoritos.isEmpty()) return;
            int posMeteorito = new Random().nextInt(meteoritos.size());
            Meteorito meteorito = meteoritos.get(posMeteorito);
            if(meteorito.explotaRepostar()) meteoritos.remove(meteorito);
        }
    }

}
