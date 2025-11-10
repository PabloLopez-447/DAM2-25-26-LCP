package UD1.ACT2;

import java.io.*;

public abstract class Archivo {
    protected String ruta;

    public Archivo(String ruta) {
        this.ruta = ruta;
    }

    public boolean existe() {
        return new File(ruta).exists();
    }

    public boolean borrar() {
        return new File(ruta).delete();
    }

    public boolean renombrar(String nuevoNombre) {
        File f = new File(ruta);
        return f.renameTo(new File(nuevoNombre));
    }

    public abstract void abrirArchivo();

    public abstract void cerrarArchivo();
}
