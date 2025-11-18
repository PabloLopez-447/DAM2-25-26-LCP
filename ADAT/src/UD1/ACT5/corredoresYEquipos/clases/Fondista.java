package UD1.ACT5.corredoresYEquipos.clases;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

import java.time.LocalDate;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)

public class Fondista extends Corredor {
    @XmlElement(name = "distancia_max")
    private float distanciaMax;

    public Fondista(String codigo, int dorsal, String equipo, String nombre, LocalDate fechaNacimiento, List<Puntuacion> historial, float distanciaMax) {
        super(codigo, dorsal, equipo, nombre, fechaNacimiento, historial);
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
