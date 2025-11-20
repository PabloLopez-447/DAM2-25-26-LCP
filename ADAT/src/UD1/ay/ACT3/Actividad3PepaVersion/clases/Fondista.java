package UD1.ay.ACT3.Actividad3PepaVersion.clases;

import java.time.LocalDate;
import java.util.List;

public class Fondista extends Corredor {
    private static final long serialVersionUID = 1L;
    private float distanciaMax;

    public Fondista(int dorsal, int equipo, String nombre, LocalDate fechaNacimiento, List<Puntuacion> historial, float distanciaMax) {
        super(dorsal, equipo, nombre, fechaNacimiento, historial);
        this.distanciaMax = distanciaMax;
    }

    public float getDistanciaMax() {
        return distanciaMax;
    }

    public void setDistanciaMax(float distanciaMax) {
        this.distanciaMax = distanciaMax;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(" | Distancia Max ");
        sb.append(String.format("%.3f km", this.distanciaMax));
        return sb.toString();
    }
    
    

}
