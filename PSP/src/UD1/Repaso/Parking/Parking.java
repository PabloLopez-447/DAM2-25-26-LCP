package UD1.Repaso.Parking;

import java.util.ArrayList;
import java.util.List;

public class Parking {
    final int NUM_PLAZAS=10;
    List<Plaza> plazas = new ArrayList<Plaza>();

    public Parking(){
        for(int i=0;i<NUM_PLAZAS;i++){
            plazas.add(new Plaza());
        }
    }

    public synchronized void aparcar(Coche coche){
        Plaza plaza;
        while ((plaza = getPlazaLibre()) == null){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        plaza.aparcarCoche(coche);
    }

    public synchronized void salir(Coche coche){
        for(Plaza plaza : plazas){
            if(plaza.coche == coche){
                plaza.cocheSale();
                break;
            }
        }
        notifyAll();
    }

    public Plaza getPlazaLibre(){
        for(Plaza plaza:plazas){
            if (plaza.estaLibre()){
                return plaza;
            }
        }
        return null;
    }

}
