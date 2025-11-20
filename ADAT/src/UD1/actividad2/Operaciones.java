package UD1.actividad2;

import java.io.File;

public class Operaciones {


    public static void crearDirectorios(String archivoEntrada, String log) {
        UD1.ACT2.LecturaTexto lector = new UD1.ACT2.LecturaTexto(archivoEntrada);
        lector.abrirArchivo();

        UD1.ACT2.EscribirTexto escritor = new UD1.ACT2.EscribirTexto(log);
        escritor.abrirArchivo();

        String linea;
        while ((linea = lector.leerLinea()) != null) {
            String[] partes = linea.split("/");
            if (partes.length < 3) {
                escritor.escribirLinea("Formato incorrecto: " + linea);
                continue;
            }

            String curso = partes[0];
            String alumno = partes[2];

            File dirCurso = new File(curso);
            if (!dirCurso.exists()) {
                if (dirCurso.mkdir()) {
                    escritor.escribirLinea("Directorio curso creado: " + curso);
                } else {
                    escritor.escribirLinea("Error creando directorio curso: " + curso);
                }
            }

            File dirAlumno = new File(dirCurso, alumno);
            if (!dirAlumno.exists()) {
                if (dirAlumno.mkdir()) {
                    escritor.escribirLinea("Directorio alumno creado: " + alumno + " en curso " + curso);
                } else {
                    escritor.escribirLinea("Error creando directorio alumno: " + alumno + " en curso " + curso);
                }
            }
        }

        lector.cerrarArchivo();
        escritor.cerrarArchivo();
    }

    public static void contarPalabras(String archivoEntrada, String palabra, String archivoSalida) {
        UD1.ACT2.LecturaTexto lector = new UD1.ACT2.LecturaTexto(archivoEntrada);
        lector.abrirArchivo();

        UD1.ACT2.EscribirTexto escritor = new UD1.ACT2.EscribirTexto(archivoSalida);
        escritor.abrirArchivo();

        int total = 0;
        int numLinea = 1;
        String linea;

        while ((linea = lector.leerLinea()) != null) {
            int contador = 0;
            String[] palabras = linea.split("\\s+");
            for (String p : palabras) {
                if (p.equalsIgnoreCase(palabra)) {
                    contador++;
                }
            }
            total += contador;
            escritor.escribirLinea("Línea " + numLinea + ": " + contador + " coincidencias");
            numLinea++;
        }

        escritor.escribirLinea("TOTAL: " + total + " coincidencias de la palabra '" + palabra + "'");

        lector.cerrarArchivo();
        escritor.cerrarArchivo();
    }

    public void ContarLineas(String[] archivos) {
        EscribirTexto et = new EscribirTexto("D:\\Datos\\DAM\\DAM2-25-26-LCP\\ADAT\\src\\UD1\\actividad2\\archivos\\salida.txt");

        for (String archivo : archivos) {
            LecturaTexto lt = new LecturaTexto(archivo);
            lt.abrirArchivo();
            String linea;
            int contador = 0;

            if (!lt.comprobarExistencia()) {
                System.out.println("No existe el archivo");
                lt.cerrarArchivo();
            }

            while ((linea = lt.leerLinea()) != null) {
                contador++;
            }

            et.escribirLinea(archivo + " " + contador);
            lt.cerrarArchivo();
        }
        et.cerrarArchivo();
    }
}
