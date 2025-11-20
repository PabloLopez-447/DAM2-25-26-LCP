package UD1.actividad1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import java.util.Iterator;

import UD1.ACT1.excepciones.DirectorioNoExisteException;
import UD1.ACT1.excepciones.NoEsDirectorioException;

public class OperacionesNIO {
    // 1. visualizarContenido(String ruta)

    public static void visualizarContenido(String ruta) throws DirectorioNoExisteException, NoEsDirectorioException {
        Path dir = Paths.get(ruta);

        Utilidades.validarDirectorio(dir.toFile());

        try (Stream<Path> stream = Files.list(dir)) {
            stream.forEach(System.out::println);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    // 2. recorrerRecursivo(String ruta)

    public static void recorrerRecursivo(String ruta) throws DirectorioNoExisteException, NoEsDirectorioException {
        Path dir = Paths.get(ruta);
        try (Stream<Path> stream = Files.walk(dir)) {// para recorrerlo
            Iterator<Path> it = stream.iterator();
            while (it.hasNext()) {
                Path p = it.next();
                System.out.println(p);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
