package UD1.ay.ACT3.Actividad3PepaVersion.persistencia;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import UD1.ay.ACT3.Actividad3Corregido.persistencia.Archivo;

public class CorredorWrite extends Archivo {

    private ObjectOutputStream oos;

    public CorredorWrite(File ruta) {
        super(ruta);
    }

    @Override
    public void abrirArchivo() {
        boolean appendmode = existe() && ruta.length() > 0;
        try {
            if (appendmode) {
                oos = new AppendObjectOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream(ruta, appendmode)));
            } else {
                oos = new ObjectOutputStream(
                        new BufferedOutputStream(
                                new FileOutputStream(ruta, appendmode)));
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void cerrarArchivo() {
        if (oos != null) {
            try {
                oos.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private boolean verificarEquipoValido(int idEquipo){
        return false;
    }

}
