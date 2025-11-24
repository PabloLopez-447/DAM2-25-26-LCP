package UD1.examen.parte1.ejercicio1.clases;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// Pablo López Couso DNI:77550221V

@XmlRootElement(name = "Alojamientos")
@XmlAccessorType(XmlAccessType.FIELD)
public class Alojamientos {
    @XmlElements({
            @XmlElement(name = "Habitacion", type = Habitacion.class),
            @XmlElement(name = "Suite", type = Suite.class)
    })
    List<Alojamiento> alojamientos = new ArrayList<>();



    public Alojamientos() {
    }

    public List<Alojamiento> getAlojamientos() {
        return alojamientos;
    }

    public void setAlojamientos(List<Alojamiento> alojamientos) {
        this.alojamientos = alojamientos;
    }
}
