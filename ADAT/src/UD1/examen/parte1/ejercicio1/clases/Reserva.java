package UD1.examen.parte1.ejercicio1.clases;

import UD1.examen.parte1.ejercicio1.persistenciaJAXB.LocalDateAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;

// Pablo López Couso DNI:77550221V

@XmlAccessorType(XmlAccessType.FIELD)
public class Reserva {
    @XmlAttribute(name = "codigo")
    String codigo;
    @XmlElement(name = "Usuario")
    Usuario usuario;
    @XmlElement(name = "FechaInicio")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    LocalDate fechaInicio;
    @XmlElement(name = "FechaFin")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    LocalDate fechaFin;

    public Reserva() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return "Reserva: " + codigo + ", FechaInicio: "  + fechaInicio + ", Usuario: "+ usuario;
    }
}
