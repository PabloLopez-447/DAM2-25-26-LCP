package UD1.ay.ACT1.excepciones;

public class NoEsDirectorioException extends Exception {
    public NoEsDirectorioException() {
        super("No es un directorio.");
    }

    public NoEsDirectorioException(String mensaje) {
        super(mensaje);
    }
}
