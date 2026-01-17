package UD2.empresa25.actividad4.clases;

import java.time.LocalDate;

public class VehiculoPropio extends Vehiculo {

    private LocalDate fechaCompra;
    private double precio;

    public VehiculoPropio(String matricula, String marca, String modelo, String combustible,
                          LocalDate fechaCompra, double precio) {
        super(matricula, marca, modelo, combustible);
        this.fechaCompra = fechaCompra;
        this.precio = precio;
    }

    public LocalDate getFechaCompra() { return fechaCompra; }
    public double getPrecio() { return precio; }
}
