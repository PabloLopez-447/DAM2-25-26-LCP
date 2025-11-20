package UD1.ACT5.registroPersonas.clases;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Estudiante extends Persona {

    private Contacto contacto;

    @XmlElement(name = "Universidad")
    private String universidad;

    @XmlElement(name = "Carrera")
    private String carrera;

    public Estudiante() {}

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
}
