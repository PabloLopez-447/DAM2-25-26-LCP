package UD1.ay.ACT5.corredoresYEquipos.clases;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "equipos")
@XmlAccessorType(XmlAccessType.FIELD)
public class Equipos {
    @XmlElement(name = "equipo", required = true)
    private List<Equipo> equipo = new ArrayList<>();

    public Equipos() {
    }

    public List<Equipo> getEquipo() {
        return equipo;
    }

    public void setEquipo(List<Equipo> equipo) {
        this.equipo = equipo;
    }

    @Override
    public String toString() {
        return "Equipos{" +
                "equipo=" + equipo +
                '}';
    }
}
