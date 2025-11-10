package UD1.Ejercicios.HiloIncremental;

public class Contador {
    public int contador = 0;

    public int getContador() {
        return contador;
    }

    public void aumentar() {

        synchronized (this) {
            this.contador++;
        }
    }

}
