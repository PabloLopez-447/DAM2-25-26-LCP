package UD1.stax.corredoresYEquipos.clases.corredores;

import java.time.LocalDate;
import java.util.List;

public class Velocista extends Corredor {
    private float velocidadMedia;

    public Velocista(String codigo, int dorsal, String equipo, String nombre, LocalDate fechaNacimiento, List<Puntuacion> historial,
            float velocidadMedia) {
        super(codigo, dorsal, equipo, nombre, fechaNacimiento, historial);
        this.velocidadMedia = velocidadMedia;
    }

    public Velocista(int dorsal, String codigo, String nombre, String equipo, LocalDate fechaNacimiento, float velocidadMedia) {
        super(dorsal, codigo, nombre, equipo, fechaNacimiento);
        this.velocidadMedia = velocidadMedia;
    }

    public Velocista(){
        
    }

    public float getVelocidadMedia() {
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
