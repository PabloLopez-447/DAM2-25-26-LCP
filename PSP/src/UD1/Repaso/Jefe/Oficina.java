package UD1.Repaso.Jefe;

public class Oficina {
    boolean estaJefe = false;

    public synchronized void llegaEmpleado(Empleado empleado) {
        if (!estaJefe) {
            while (!estaJefe) {
                System.out.println(empleado + ": he llegado. ZZZZZZZZ");
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(empleado + ": ehhh, a trabajar");
            }
        } else {
            System.out.println(empleado + ": he llegado. Hola jefe me pongo a trabajar");
        }
    }

    public synchronized void llegaJefe() {
        System.out.println("Jefe: El jefe ha llegado");
        estaJefe = true;
        notifyAll();
    }
}
