package UD1.Repaso.Jefe;

public class Jefe extends Thread{
    Oficina oficina;

    public Jefe(String name, Oficina oficina) {
        super(name);
        this.oficina = oficina;
    }

    @Override
    public void run() {
        oficina.llegaJefe();
    }
}
