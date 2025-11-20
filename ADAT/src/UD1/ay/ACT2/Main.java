package UD1.ay.ACT2;

public class Main {
    public static void main(String[] args) {
        System.out.println("Proyecto de gestión de ficheros en Java");
        // Ejemplo de prueba del Ejercicio 3
        Ejercicio3.ejecutar("texto.txt", "java");
    }
}

class Ejercicio1 {
    public static void ejecutar(String[] args) {
        Operaciones.contarLineas(args, "Salida.txt");
    }
}

class Ejercicio2 {
    public static void ejecutar(String archivoEntrada) {
        Operaciones.crearDirectorios(archivoEntrada, "ficherolog.txt");
    }
}

class Ejercicio3 {
    public static void ejecutar(String archivoEntrada, String palabra) {
        Operaciones.contarPalabras(archivoEntrada, palabra, "resultado.txt");
    }

}