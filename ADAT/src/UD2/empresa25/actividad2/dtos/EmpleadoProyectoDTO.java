package UD2.empresa25.actividad2.dtos;

public class EmpleadoProyectoDTO {

    private String nss;
    private String nombreCompleto;
    private double salario;
    private String departamento;

    public EmpleadoProyectoDTO(String nss, String nombreCompleto,
                               double salario, String departamento) {
        this.nss = nss;
        this.nombreCompleto = nombreCompleto;
        this.salario = salario;
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return nss + " - " + nombreCompleto +
                " | " + salario + "€ | " + departamento;
    }
}
