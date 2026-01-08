package UD1.Repaso.Parking;

public class Plaza {
    Coche coche = null;

    public boolean estaLibre() {
        return coche == null;
    }

    public void aparcarCoche(Coche coche) {
        this.coche = coche;
        System.out.println("Aparco el coche");
    }

    public void cocheSale() {
        this.coche = null;
        System.out.println("Coche sale");
    }
}

