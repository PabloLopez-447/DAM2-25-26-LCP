package UD2.empresa25.actividad2.dtos;

public class DepartamentoDTO {

    private int numero;
    private String nombre;

    public DepartamentoDTO(int numero, String nombre) {
        this.numero = numero;
        this.nombre = nombre;
    }

    public int getNumero() {
        return numero;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return numero + " - " + nombre;
    }
}
