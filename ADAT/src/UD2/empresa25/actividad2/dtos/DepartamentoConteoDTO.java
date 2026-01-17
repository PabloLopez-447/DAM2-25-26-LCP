package UD2.empresa25.actividad2.dtos;

public class DepartamentoConteoDTO {

    private int numero;
    private String nombre;
    private int fijos;
    private int temporales;

    public DepartamentoConteoDTO(int numero, String nombre, int fijos, int temporales) {
        this.numero = numero;
        this.nombre = nombre;
        this.fijos = fijos;
        this.temporales = temporales;
    }

    @Override
    public String toString() {
        return numero + " " + nombre + " → Fijos: " + fijos + " | Temporales: " + temporales;
    }
}
