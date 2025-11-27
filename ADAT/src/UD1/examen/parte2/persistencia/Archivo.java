package UD1.examen.parte2.persistencia;

import java.io.File;

//Pablo López Couso DNI:77550221V

public abstract class Archivo {
    String ruta;
    public Archivo(String ruta){
        this.ruta = ruta;
    }

    public boolean comprobarExistencia() {
        File file = new File(ruta);
        return file.exists();
    }

    public abstract void abrirArchivo();
    public abstract void cerrarArchivo();
}
