package UD1.ay.ACT3.Actividad3PepaVersion.clases;

import java.time.LocalDate;

public class Patrocinador {
    String nombre;
    private float donacion;
    private LocalDate fechaInicio;

    public Patrocinador(String nombre, float donacion, LocalDate fechaInicio) {
        this.nombre = nombre;
        this.donacion = donacion;
        this.fechaInicio = fechaInicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getDonacion() {
        return donacion;
    }

    public void setDonacion(float donacion) {
        this.donacion = donacion;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    
}
