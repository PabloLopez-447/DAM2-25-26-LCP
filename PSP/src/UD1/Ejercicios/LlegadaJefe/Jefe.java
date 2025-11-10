package UD1.Ejercicios.LlegadaJefe;

public class Jefe extends Thread {
    private final Oficina oficina;

    public Jefe(Oficina oficina) {
        this.oficina = oficina;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000 + (int) (Math.random() * 3000)); 
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        oficina.llegaJefe();
    }
}