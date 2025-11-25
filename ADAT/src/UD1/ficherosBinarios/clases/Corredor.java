package UD1.ficherosBinarios.clases;

import UD1.ay.ACT3.Actividad3PepaVersion.utilidades.UtilidadesFechas;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Corredor implements Serializable {
    private static final long serialVersionUID = 1L;
    private int dorsal, equipo;
    private String nombre;
    private LocalDate fechaNacimiento;
    private List<Puntuacion> historial;

    public Corredor(int dorsal, int equipo, String nombre, LocalDate fechaNacimiento, List<Puntuacion> historial) {
        this.dorsal = dorsal;
        this.equipo = equipo;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.historial = historial;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public int getEquipo() {
        return equipo;
    }

    public void setEquipo(int equipo) {
        this.equipo = equipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<Puntuacion> getHistorial() {
        return historial;
    }

    public void setHistorial(List<Puntuacion> historial) {
        if (historial != null) {
            historial.sort((p1, p2) -> Integer.compare(p1.getAnio(), p2.getAnio()));
        }
        this.historial = historial;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Nombre: ").append(this.nombre);
        sb.append(" | FechaNacimiento: ").append(UtilidadesFechas.formatearLargo(this.fechaNacimiento));
        sb.append(" | Equipo: ").append(this.equipo);
        return sb.toString();
    }

}
