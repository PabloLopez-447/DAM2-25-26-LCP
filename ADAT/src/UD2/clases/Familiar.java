package UD2.clases;

import java.time.LocalDate;

public class Familiar {

    private String nssEmpleado;
    private int numFamiliar;
    private String nssFamiliar;
    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String parentesco;
    private char sexo;

    public Familiar(String nssEmpleado, int numFamiliar, String nssFamiliar,
                    String nombre, String apellidos, LocalDate fechaNacimiento,
                    String parentesco, char sexo) {
        this.nssEmpleado = nssEmpleado;
        this.numFamiliar = numFamiliar;
        this.nssFamiliar = nssFamiliar;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.parentesco = parentesco;
        this.sexo = sexo;
    }
}

