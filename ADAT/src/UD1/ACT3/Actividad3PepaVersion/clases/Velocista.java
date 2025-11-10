package UD1.ACT3.Actividad3PepaVersion.clases;

import java.time.LocalDate;
import java.util.List;

public class Velocista extends Corredor {
    private static final long serialVersionUID = 1L;
    private float velocidadMedia;

    public Velocista(int dorsal, int equipo, String nombre, LocalDate fechaNacimiento, List<Puntuacion> historial,
            float velocidadMedia) {
        super(dorsal, equipo, nombre, fechaNacimiento, historial);
        this.velocidadMedia = velocidadMedia;
    }

    public float getDistanciaMax() {
        return velocidadMedia;
    }

    public void setDistanciaMax(float velocidadMedia) {
        this.velocidadMedia = velocidadMedia;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(" | Velocidad Media: ");
        sb.append(String.format("%.2f km/h", this.velocidadMedia));
        return sb.toString();
    }

}
