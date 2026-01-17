package UD2.empresa25.actividad2.dtos;

public class EmpleadoFijoDTO {

    private String nss;
    private String nombreCompleto;
    private double salario;
    private String departamento;

    public EmpleadoFijoDTO(String nss, String nombreCompleto,
                           double salario) {
        this.nss = nss;
        this.nombreCompleto = nombreCompleto;
        this.salario = salario;
    }

    @Override
    public String toString() {
        return nss + " - " + nombreCompleto +
                " | " + salario + "€ | " + departamento;
    }
}
