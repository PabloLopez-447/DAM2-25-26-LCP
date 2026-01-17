package UD2.empresa25.actividad2.dtos;

public class EmpleadoEdadDTO {

    private String nss;
    private String nombre;
    private String apellidos;
    private int edad;

    public EmpleadoEdadDTO(String nss, String nombre, String apellidos, int edad) {
        this.nss = nss;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
    }

    @Override
    public String toString() {
        return nss + " - " + nombre + " " + apellidos + " (" + edad + " anos)";
    }
}
