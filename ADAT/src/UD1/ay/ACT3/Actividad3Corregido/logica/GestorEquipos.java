package UD1.ay.ACT3.Actividad3Corregido.logica;

import java.io.File;
import java.io.IOException;

import UD1.ay.ACT3.Actividad3Corregido.clases.Equipo;
import UD1.ay.ACT3.Actividad3Corregido.persistencia.RandomFile;

public class GestorEquipos {
    private RandomFile randomFile;

    public GestorEquipos(String ruta) {
        this.randomFile = new RandomFile(new File(ruta));
    }

    public void guardarEquipo(Equipo e) throws IOException {
        randomFile.abrirArchivo();

        e.setIdEquipo(randomFile.cogerIdUltimoEquipo()-1);

        randomFile.guardarEquipo(e);



        randomFile.cerrarArchivo();
    }
    

}
