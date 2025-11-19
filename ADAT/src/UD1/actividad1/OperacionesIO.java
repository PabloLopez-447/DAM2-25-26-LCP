package UD1.actividad1;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class OperacionesIO {

    public void visualizarContenido(String ruta) {
        SimpleDateFormat formateador =
                new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es", "ES"));

        File dir = new File(ruta);

        if (!dir.exists()) {
            System.out.println("El archivo no existe");
        }
        if (!dir.isDirectory()) {
            System.out.println("No es un directorio");
        }

        for (File file : dir.listFiles()) {
            System.out.println(file.getName());
            System.out.println(file.isDirectory() ? "<DIR>" : "<FILE>");
            if (file.isFile()) {
                System.out.println(file.length() / 1024 + "KBs");
            }
            System.out.println(formateador.format(file.lastModified()));
            System.out.println();
        }

    }

    public static void recorrerRecursivo(String ruta) throws Exception {
        File dir = new File(ruta);

        if (!dir.exists()) {
            throw new Exception("ERROR: La ruta no existe");
        }

        if (!dir.isDirectory()) {
            throw new Exception("ERROR: No es un directorio");
        }

        recorrer(dir, 0);
    }

    private static void recorrer(File file, int nivel) {
        String indent = "\t".repeat(nivel);

        File[] lista = file.listFiles();
        if (lista == null) return;

        for (File f : lista) {
            if (f.isDirectory()) {
                System.out.println(indent + "[DIR]  " + f.getName());
                recorrer(f, nivel + 1);   // recursión
            } else {
                long sizeKB = f.length() / 1024;
                System.out.println(indent + "FILE   " + f.getName() + " (" + sizeKB + " KB)");
            }
        }
    }

    public static void main(String[] args) {
        OperacionesIO operaciones = new OperacionesIO();
        //operaciones.visualizarContenido("D:\\Datos\\DAM\\DAM2-25-26-LCP\\ADAT\\src\\UD1\\ACT1");

        try {
            recorrerRecursivo("D:\\Datos\\DAM\\DAM2-25-26-LCP\\ADAT\\src\\UD1\\ACT1");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
