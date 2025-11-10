package UD1.Ejercicios.Dialogo;

import java.io.*;

public class Dialogo {
    public static void main(String[] args) {
        String rutaArchivo = "D:\\plopecous\\PSP\\dialogo.txt";
        Archivo archivo = null;
        try {
            archivo = new Archivo(rutaArchivo);
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado.");
        }
        (new HiloDialogo("JORGE", 1, archivo)).start();
        (new HiloDialogo("FRAN", 3000, archivo)).start();
    }
}
