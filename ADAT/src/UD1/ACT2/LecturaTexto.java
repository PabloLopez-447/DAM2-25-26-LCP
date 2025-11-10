package UD1.ACT2;

import java.io.*;

public class LecturaTexto extends Archivo {
    private BufferedReader br;

    public LecturaTexto(String ruta) {
        super(ruta);
    }

    @Override
    public void abrirArchivo() {
        try {
            br = new BufferedReader(new FileReader(ruta));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void cerrarArchivo() {
        try {
            if (br != null)
                br.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public String leerLinea() {
        try {
            return br.readLine();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
