package UD1.actividad2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class EscribirTexto extends Archivo{
    BufferedWriter bw;

    public EscribirTexto(String ruta) {
        super(ruta);
    }

    @Override
    public void abrirArchivo() {
        try {
            bw = new BufferedWriter(new FileWriter(ruta));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cerrarArchivo() {
        if (bw != null) {
            try {
                bw.close();
            } catch (IOException e) {}
        }
    }

    public void escribirLinea(String linea){
        try {
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {}
    }

}
