package UD2.clases;

import java.time.LocalDate;

public class EmpleadoTemporal {

    private String nss;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private double costeHora;
    private double numHoras;

    public EmpleadoTemporal(String nss, LocalDate fechaInicio, LocalDate fechaFin,
                            double costeHora, double numHoras) {
        this.nss = nss;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.costeHora = costeHora;
        this.numHoras = numHoras;
    }
}
