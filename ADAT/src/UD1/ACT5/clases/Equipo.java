package UD1.ACT5.clases;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;

public class Equipo {

    @XmlAttribute(name = "id", required = true)
    private String idEquipo;
    @XmlAttribute(name = "nombre", required = true)
    private String nombre;
    @XmlElement(name = "patrocinadores", required = true)
    Patrocinadores patrocinadores;

    public Equipo() {
    }

    public String getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(String idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Patrocinadores getPatrocinadores() {
        return patrocinadores;
    }

    public void setPatrocinadores(Patrocinadores patrocinadores) {
        this.patrocinadores = patrocinadores;
    }
}
