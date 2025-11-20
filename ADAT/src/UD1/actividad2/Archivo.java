package UD1.actividad2;

import java.io.File;

public abstract class Archivo {
    String ruta;
    public Archivo(String ruta){
        this.ruta = ruta;
    }

    public boolean comprobarExistencia() {
        File file = new File(ruta);
        return file.exists();
    }

    public boolean borrarArchivo(File archivo) {
        return archivo.delete();
    }

    public boolean renombrarArchivo(File archivo, String nuevoNombre) {
        return archivo.renameTo(new File(nuevoNombre));
    }

    public abstract void abrirArchivo();
    public abstract void cerrarArchivo();
}
