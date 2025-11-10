package UD1.Ejercicios.Dialogo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Archivo {
    BufferedReader archivo;

    public Archivo(String ruta)
            throws FileNotFoundException {
        archivo = new BufferedReader(new FileReader(ruta));
    }

    public synchronized String getLinea() {
        notify();
        String linea;
        try {
            if ((linea = archivo.readLine()) != null) {
                try {
                    wait();
                } catch (InterruptedException ex) {
                }
                return linea;
            }
        } catch (IOException ex) {
        }
        return null;
    }
}
