package UD1.ficherosDeTexto;

import java.io.*;

public class LecturaTexto extends Archivo{
    private BufferedReader br;

    public LecturaTexto(String ruta) {
        super(ruta);
    }

    @Override
    public void abrirArchivo() {
        try {
            br = new BufferedReader(new FileReader(ruta));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cerrarArchivo() {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String leerLinea(){
        try {
            return br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
