package UD1.ay.ACT2;

import java.io.*;

public class EscribirTexto extends Archivo {
    private BufferedWriter bw;

    public EscribirTexto(String ruta) {
        super(ruta);
    }

    @Override
    public void abrirArchivo() {
        try {
            bw = new BufferedWriter(new FileWriter(ruta, true));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void cerrarArchivo() {
        try {
            if (bw != null)
                bw.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void escribirLinea(String linea) {
        try {
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}