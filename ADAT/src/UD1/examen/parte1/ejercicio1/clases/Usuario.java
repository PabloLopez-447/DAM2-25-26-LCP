package UD1.examen.parte1.ejercicio1.clases;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;

// Pablo López Couso DNI:77550221V

@XmlAccessorType(XmlAccessType.FIELD)
public class Usuario {
    @XmlAttribute(name = "dni")
    String dni;
    @XmlValue
    String nombre;

    public Usuario() {
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre + "(" +  dni + ")";
    }
}
