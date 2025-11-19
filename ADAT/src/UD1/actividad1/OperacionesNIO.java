package UD1.actividad1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.stream.Stream;

public class OperacionesNIO {
    public static void visualizarContenido(String ruta) {
        SimpleDateFormat formateador =
                new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
        Path path = Paths.get(ruta);

        if (Files.notExists(path)) {
            System.out.println("El archivo no existe");
        }

        if (!Files.isDirectory(path)) {
            System.out.println("No es un directorio");
        }

        try (Stream<Path> s = Files.list(path)) {
            s.forEach(file -> {
                System.out.println(file.getFileName());
                System.out.println(Files.isDirectory(file) ? "<DIR>" : "<FILE>");
                try {
                    System.out.println(!Files.isDirectory(file) ? Files.size(file) / 1024 + "KBs" : "");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                try {
                    System.out.println(formateador.format(Files.getLastModifiedTime(file)));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println();
            });
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
        }
    }

    public static void recorrerRecursivo(String ruta) throws IOException {
        Path path = Paths.get(ruta);

        if (!Files.exists(path)) {
            throw new IOException("ERROR: La ruta no existe");
        }

        if (!Files.isDirectory(path)) {
            throw new IOException("ERROR: No es un directorio");
        }

        try (Stream<Path> s = Files.walk(path)) {
            s.forEach(file -> {
                int nivel = file.relativize(path).getNameCount();
                String indent = "\t".repeat(nivel);

                try {
                    System.out.println(Files.isDirectory(file) ? indent + "<DIR>" + file.getFileName() : indent + "<FILE>" + file.getFileName() + " (" + (Files.size(file) / 1024) + " KB)");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            });
        }
    }


    public static void main(String[] args) {
        OperacionesIO operaciones = new OperacionesIO();
        //operaciones.visualizarContenido("D:\\Datos\\DAM\\DAM2-25-26-LCP\\ADAT\\src\\UD1\\ACT1");
        try {
            recorrerRecursivo("D:\\Datos\\DAM\\DAM2-25-26-LCP\\ADAT\\src\\UD1\\ACT1");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
