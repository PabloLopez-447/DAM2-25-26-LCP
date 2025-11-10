package UD1.ACT1.servicios;

import java.io.File;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;

import UD1.ACT1.excepciones.DirectorioNoExisteException;
import UD1.ACT1.excepciones.NoEsDirectorioException;

public class Utilidades {

    // formato fecha
    public static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    // segundos desde una fecha dada desde la zona del sistema
    public static String formatearFecha(long millis) {
        return dtf.format(Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()));
    }

    public static void validarDirectorio(File dir) throws DirectorioNoExisteException, NoEsDirectorioException {
        if (!dir.exists()) {
            throw new DirectorioNoExisteException("La ruta no existe");
        }
        ;

        if (!dir.isDirectory()) {
            throw new NoEsDirectorioException("La ruta no es un directorio");
        }

    }

    public static void mostrarInfo(File f, String sangria) {
        NumberFormat nf = NumberFormat.getInstance();

        String tipoDir = f.isDirectory() ? "<DIR>" : "<FICHERO>";
        String fecha = formatearFecha(f.lastModified());

        if (f.isFile()) {
            String detalle = String.format("%s %s KB %s", tipoDir, nf.format(f.length() / 1024), fecha);
            System.out.printf("%s - %s %s %n", sangria, detalle, f.getName());
        } else {
            System.out.printf("%s - %s %s %s%n", sangria, tipoDir, f.getName(), fecha);
        }
    }
};
