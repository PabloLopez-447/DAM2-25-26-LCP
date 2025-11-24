package UD1.examen.parte1.ejercicio1.clases;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// Pablo López Couso DNI:77550221V

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Habitacion.class, Suite.class})
@XmlTransient
public abstract class Alojamiento {
    @XmlAttribute(name = "numero")
    String numero;
    @XmlAttribute(name = "estado")
    String estado;
    @XmlAttribute(name = "precio")
    float precio;
    @XmlElementWrapper(name = "Reservas")
    @XmlElement(name = "Reserva")
    List<Reserva> reservas  = new ArrayList<>();

    public Alojamiento(String numero, String estado, float precio, List<Reserva> reservas) {
        this.numero = numero;
        this.estado = estado;
        this.precio = precio;
        this.reservas = reservas;
    }

    public Alojamiento() {
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
}
