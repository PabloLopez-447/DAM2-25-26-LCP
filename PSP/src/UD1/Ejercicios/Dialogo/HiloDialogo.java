package UD1.Ejercicios.Dialogo;

public class HiloDialogo extends Thread {
    Archivo archivo;
    long retraso;

    public HiloDialogo(String nombre, long retraso, Archivo archivo) {
        super(nombre);
        this.retraso = retraso;
        this.archivo = archivo;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(retraso);
        } catch (InterruptedException ex) {
        }
        String linea = archivo.getLinea();
        while (linea != null) {
            System.out.println(getName() + ": " + linea);
            try {
                Thread.sleep(retraso);
            } catch (InterruptedException ex) {
            }
            linea = archivo.getLinea();
        }
    }

}
