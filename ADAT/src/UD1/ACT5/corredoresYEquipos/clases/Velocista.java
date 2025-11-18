package UD1.ACT5.corredoresYEquipos.clases;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

import java.time.LocalDate;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)

public class Velocista extends Corredor {
    @XmlElement(name = "velocidad_media")
    private float velocidadMedia;

    public Velocista(String codigo, int dorsal, String equipo, String nombre, LocalDate fechaNacimiento, List<Puntuacion> historial,
            float velocidadMedia) {
        super(codigo, dorsal, equipo, nombre, fechaNacimiento, historial);
        this.velocidadMedia = velocidadMedia;
    }
    public Velocista(){
        
    }

    public float getDistanciaMax() {
        return velocidadMedia;
    }

    public void setVelocidadMedia(float velocidadMedia) {
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
