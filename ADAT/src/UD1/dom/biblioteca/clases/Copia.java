package UD1.dom.biblioteca.clases;

public class Copia {
    int numero;
    String estado;

    public Copia(int numero, String estado) {
        this.numero = numero;
        this.estado = estado;
    }

    public Copia() {
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Copia{" +
                "numero=" + numero +
                ", estado=" + estado +
                '}';
    }
}
