package UD1.examen.parte1.ejercicio1.clases;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;

import java.util.List;

// Pablo López Couso DNI:77550221V

@XmlAccessorType(XmlAccessType.FIELD)
public class Habitacion extends Alojamiento {
    @XmlAttribute(name = "tipo")
    String tipo;

    public Habitacion(String numero, String estado, float precio, List<Reserva> reservas, String tipo) {
        super(numero, estado, precio, reservas);
        this.tipo = tipo;
    }

    public Habitacion() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Habitacion" + "(" + tipo + "): " + numero + "\n" + reservas.getLast();
    }
}
