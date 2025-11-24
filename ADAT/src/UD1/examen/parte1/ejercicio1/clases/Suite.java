package UD1.examen.parte1.ejercicio1.clases;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;

import java.util.List;

// Pablo López Couso DNI:77550221V

@XmlAccessorType(XmlAccessType.FIELD)
public class Suite extends Alojamiento {
    @XmlAttribute(name = "terraza")
    String terraza;
    @XmlAttribute(name = "categoria")
    String categoria;

    public Suite(String numero, String estado, float precio, List<Reserva> reservas, String terraza, String categoria) {
        super(numero, estado, precio, reservas);
        this.terraza = terraza;
        this.categoria = categoria;
    }

    public Suite() {}

    public String getTerraza() {
        return terraza;
    }

    public void setTerraza(String terraza) {
        this.terraza = terraza;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return  "Suit" + "(" + categoria + "): " + numero + "\n" + reservas.getLast();
    }
}
