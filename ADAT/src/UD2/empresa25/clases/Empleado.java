package UD2.empresa25.clases;

import java.time.LocalDate;

public class Empleado {

    private String nss;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private char sexo;
    private LocalDate fechaNacimiento;
    private Integer numDepartamento;

    public Empleado(String nss, String nombre, String apellido1, String apellido2,
                    char sexo, LocalDate fechaNacimiento, Integer numDepartamento) {
        this.nss = nss;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.numDepartamento = numDepartamento;
    }

    public String getNss() { return nss; }
    public String getNombre() { return nombre; }
}
