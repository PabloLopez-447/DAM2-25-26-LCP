package UD1.stax.corredoresYEquipos.clases.corredores;

import java.time.LocalDate;
import java.util.List;

public class Fondista extends Corredor {
    private float distanciaMax;

    public Fondista(String codigo, int dorsal, String equipo, String nombre, LocalDate fechaNacimiento, List<Puntuacion> historial, float distanciaMax) {
        super(codigo, dorsal, equipo, nombre, fechaNacimiento, historial);
        this.distanciaMax = distanciaMax;
    }

    public Fondista(int dorsal, String codigo, String nombre, String equipo, LocalDate fechaNacimiento, float distanciaMax) {
        super(dorsal, codigo, nombre, equipo, fechaNacimiento);
        this.distanciaMax = distanciaMax;
    }

    public Fondista(){
        
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
