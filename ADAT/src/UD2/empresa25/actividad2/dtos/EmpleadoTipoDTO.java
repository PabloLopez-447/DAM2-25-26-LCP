package UD2.empresa25.actividad2.dtos;

public class EmpleadoTipoDTO {

    private String nss;
    private String nombreCompleto;
    private String tipo;

    public EmpleadoTipoDTO(String nss, String nombreCompleto, String tipo) {
        this.nss = nss;
        this.nombreCompleto = nombreCompleto;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return nss + " - " + nombreCompleto + " [" + tipo + "]";
    }
}
