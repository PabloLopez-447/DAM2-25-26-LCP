package UD2.empresa25.actividad3.clases;

import java.time.LocalDate;

public class VehiculoRenting extends Vehiculo {

    private LocalDate fechaInicio;
    private double precioMensual;
    private int meses;

    public VehiculoRenting(String matricula, String marca, String modelo, String combustible,
                           LocalDate fechaInicio, double precioMensual, int meses) {
        super(matricula, marca, modelo, combustible);
        this.fechaInicio = fechaInicio;
        this.precioMensual = precioMensual;
        this.meses = meses;
    }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public double getPrecioMensual() { return precioMensual; }
    public int getMeses() { return meses; }
}
