package UD1.ACT1.servicios;

import java.io.File;
import java.util.Comparator;

public class ComparaNombres implements Comparator<File> {

    private boolean descendente;

    public ComparaNombres(boolean descendente) {
        this.descendente = descendente;
    }

    @Override
    public int compare(File f1, File f2) {
        String nombre1 = f1.getName().toLowerCase();
        String nombre2 = f2.getName().toLowerCase();
        return descendente ? nombre2.compareTo(nombre1) : nombre1.compareTo(nombre2);
    }
}