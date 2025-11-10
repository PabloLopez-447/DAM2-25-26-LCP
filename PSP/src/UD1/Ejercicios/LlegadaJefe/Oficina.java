package UD1.Ejercicios.LlegadaJefe;

public class Oficina {
    private boolean jefePresente = false;

    
    public synchronized void llegaEmpleado(String nombre) {
        System.out.println(nombre + " ha llegado.");

        if (!jefePresente) {
            System.out.println(nombre + ": ZZZZZZ");
            try {
                wait(); 
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println(nombre + " desperezándose, buenos días jefe, aquí estoy trabajando!");
        } else {
            System.out.println(nombre + ": Hola jefe!, me pongo a trabajar...");
        }
    }

    
    public synchronized void llegaJefe() {
        System.out.println("¡¡¡EL JEFE HA LLEGADO!!!");
        jefePresente = true;
        notifyAll();
    }
}