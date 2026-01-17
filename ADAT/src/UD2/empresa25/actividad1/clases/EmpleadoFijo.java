package UD2.empresa25.actividad1.clases;

import java.time.LocalDate;

public class EmpleadoFijo {

    private String nss;
    private double salario;
    private LocalDate fechaAlta;
    private String categoria;

    public EmpleadoFijo(String nss, double salario, LocalDate fechaAlta, String categoria) {
        this.nss = nss;
        this.salario = salario;
        this.fechaAlta = fechaAlta;
        this.categoria = categoria;
    }
}
