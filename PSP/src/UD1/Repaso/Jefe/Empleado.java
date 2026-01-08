package UD1.Repaso.Jefe;

public class Empleado extends Thread {
    Oficina oficina;

    public Empleado(String name, Oficina oficina) {
        super(name);
        this.oficina = oficina;
    }

    @Override
    public void run() {
        oficina.llegaEmpleado(this);
    }
}
