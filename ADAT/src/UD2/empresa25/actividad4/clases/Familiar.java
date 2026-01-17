package UD2.empresa25.actividad4.clases;

import java.time.LocalDate;

public class Familiar {
    private String nssEmpleado;
    private int numFam;
    private String nssFam;
    private String nombre;
    private String apellidos;
    private LocalDate fechaNac;
    private String parentesco;
    private char sexo;

    public Familiar(String nssEmpleado, String nssFam, String nombre, String apellidos,
                    LocalDate fechaNac, String parentesco, char sexo) {
        this.nssEmpleado = nssEmpleado;
        this.nssFam = nssFam;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNac = fechaNac;
        this.parentesco = parentesco;
        this.sexo = sexo;
    }

    public String getNssEmpleado() { return nssEmpleado; }
    public int getNumFam() { return numFam; }
    public void setNumFam(int numFam) { this.numFam = numFam; }
    public String getNssFam() { return nssFam; }
    public String getNombre() { return nombre; }
    public String getApellidos() { return apellidos; }
    public LocalDate getFechaNac() { return fechaNac; }
    public String getParentesco() { return parentesco; }
    public char getSexo() { return sexo; }
}
