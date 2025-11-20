package UD1.ay.ACT4.clases;

import java.time.LocalDate;
import java.util.List;

public class Velocista extends Corredor {
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
