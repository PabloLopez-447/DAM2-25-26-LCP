package UD1.ay.ACT2;

import java.io.*;

public class Operaciones {
    public static void contarLineas(String[] archivos, String salida) {
        EscribirTexto escritor = new EscribirTexto(salida);
        escritor.abrirArchivo();

        for (String archivo : archivos) {
            LecturaTexto lector = new LecturaTexto(archivo);
            lector.abrirArchivo();

            if (!lector.existe()) {
                escritor.escribirLinea("Error al leer archivo: " + archivo);
                lector.cerrarArchivo();
            }

            int contador = 0;
            String linea;
            while ((linea = lector.leerLinea()) != null) {
                contador++;
            }

            escritor.escribirLinea(archivo + ": " + contador + " líneas");

            lector.cerrarArchivo();
        }

        escritor.cerrarArchivo();
    }

    public static void crearDirectorios(String archivoEntrada, String log) {
        LecturaTexto lector = new LecturaTexto(archivoEntrada);
        lector.abrirArchivo();

        EscribirTexto escritor = new EscribirTexto(log);
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
        LecturaTexto lector = new LecturaTexto(archivoEntrada);
        lector.abrirArchivo();

        EscribirTexto escritor = new EscribirTexto(archivoSalida);
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
}