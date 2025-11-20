package UD1.ay.ACT1.excepciones;

public class DirectorioNoExisteException extends Exception {
    public DirectorioNoExisteException() {
        super("El directorio no existe");
    }

    public DirectorioNoExisteException(String mensaje) {
        super(mensaje);
    }
};
