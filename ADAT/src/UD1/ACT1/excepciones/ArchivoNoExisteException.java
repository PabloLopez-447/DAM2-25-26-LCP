package UD1.ACT1.excepciones;

public class ArchivoNoExisteException extends Exception {
    public ArchivoNoExisteException() {
        super("El archivo no existe");
    }

    public ArchivoNoExisteException(String mensaje) {
        super(mensaje);
    }
}
