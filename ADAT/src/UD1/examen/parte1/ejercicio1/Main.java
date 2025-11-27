package UD1.examen.parte1.ejercicio1;

import UD1.examen.parte1.ejercicio1.logica.GestorAlojamientos;

// Pablo López Couso DNI:77550221V
public class Main {

    public static void main(String[] args) {
        GestorAlojamientos g = new GestorAlojamientos("src/exud1p1pablolc/ejercicio1/xml/AlojamientosHotel.xml");
        g.visualizarAlojamientosReservados();
    }
}
