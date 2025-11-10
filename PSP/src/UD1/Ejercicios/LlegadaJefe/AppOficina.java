package UD1.Ejercicios.LlegadaJefe;

public class AppOficina {
    public static void main(String[] args) {
        Oficina oficina = new Oficina();

        Thread e1 = new Empleado("Ana", oficina);
        Thread e2 = new Empleado("Luis", oficina);
        Thread e3 = new Empleado("Marta", oficina);
        Thread jefe = new Jefe(oficina);

        e1.start();
        e2.start();
        e3.start();
        jefe.start();
    }
}
