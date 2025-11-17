package UD1.ACT5.clases;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;

import java.util.HashSet;
import java.util.Set;

public class Patrocinadores {
    @XmlAttribute(name = "numPatrocinadores",  required = true)
    private int numPatrocinadores;
    @XmlElement(name = "patrocinador", required = true)
    private Set<Patrocinador> patrocinadores = new HashSet<>();

    public Patrocinadores() {
    }

    public int getNumPatrocinadores() {
        return numPatrocinadores;
    }

    public void setNumPatrocinadores(int numPatrocinadores) {
        this.numPatrocinadores = numPatrocinadores;
    }

    public Set<Patrocinador> getPatrocinadores() {
        return patrocinadores;
    }

    public void setPatrocinadores(Set<Patrocinador> patrocinadores) {
        this.patrocinadores = patrocinadores;
    }
}
