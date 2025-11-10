package UD1.Ejercicios.LlegadaJefe;

public class Empleado extends Thread {
    private final Oficina oficina;
    private final String nombre;

    public Empleado(String nombre, Oficina oficina) {
        this.nombre = nombre;
        this.oficina = oficina;
    }

    @Override
    public void run() {
        try {
            Thread.sleep((int) (Math.random() * 5000));
        } catch (Exception e) {
            System.out.println(e.getMessage());;
        }
        oficina.llegaEmpleado(nombre);
    }
}
