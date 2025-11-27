package UD1.examen.parte2;

import UD1.examen.parte2.logica.GestorFotografos;

//Pablo López Couso DNI:77550221V
public class Main {

    public static void main(String[] args) {
        GestorFotografos g = new GestorFotografos("src/ficheros/fotografos.dat");

        g.mostrarFotografos();

        g.escribirFotografosPorEstudio();
        System.out.println();
        System.out.println();
        System.out.println("=======================ACTUALIZADO=======================");
        g.procesarActualizaciones("src/ficheros/UpdateFotografos.xml");
        g.mostrarFotografos();
    }
}
