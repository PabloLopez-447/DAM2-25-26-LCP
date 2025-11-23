package UD1.stax.corredoresYEquipos.clases.equipos;

import java.time.LocalDate;

public class ActualizacionPatrocinador {
    private String idEquipo;
    private String nombreEquipo;
    private String nombrePatrocinador;
    private float donacion;
    private LocalDate fecha;

    public String getIdEquipo() { return idEquipo; }
    public void setIdEquipo(String idEquipo) { this.idEquipo = idEquipo; }

    public String getNombreEquipo() { return nombreEquipo; }
    public void setNombreEquipo(String nombreEquipo) { this.nombreEquipo = nombreEquipo; }

    public String getNombrePatrocinador() { return nombrePatrocinador; }
    public void setNombrePatrocinador(String nombrePatrocinador) { this.nombrePatrocinador = nombrePatrocinador; }

    public float getDonacion() { return donacion; }
    public void setDonacion(float donacion) { this.donacion = donacion; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    @Override
    public String toString() {
        return idEquipo + " | " + nombrePatrocinador + " | " + donacion + " | " + fecha;
    }
}
