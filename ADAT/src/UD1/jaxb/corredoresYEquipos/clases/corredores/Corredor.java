package UD1.jaxb.corredoresYEquipos.clases.corredores;

import UD1.ay.ACT3.Actividad3PepaVersion.utilidades.UtilidadesFechas;
import UD1.ay.ACT5.corredoresYEquipos.util.LocalDateAdapter;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Velocista.class, Fondista.class})
@XmlTransient
public class Corredor {
    @XmlAttribute(name = "dorsal")
    private int dorsal;
    @XmlElement(name = "nombre")
    private String nombre;
    @XmlAttribute(name = "codigo")
    private String codigo;
    @XmlAttribute(name = "equipo")
    private String equipo;
    @XmlElement(name = "fecha_nacimiento")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechaNacimiento;
    @XmlElementWrapper(name = "historial")
    @XmlElement(name = "puntuacion")
    private List<Puntuacion> historial = new ArrayList<>();

    public Corredor(String codigo, int dorsal, String equipo, String nombre, LocalDate fechaNacimiento, List<Puntuacion> historial) {
        this.codigo = codigo;
        this.dorsal = dorsal;
        this.equipo = equipo;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.historial = historial;
    }

    public Corredor() {

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<Puntuacion> getHistorial() {
        return historial;
    }

    public void setHistorial(List<Puntuacion> historial) {
        this.historial = historial;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Nombre: ").append(this.nombre);
        sb.append(" | FechaNacimiento: ").append(UtilidadesFechas.formatearLargo(this.fechaNacimiento));
        sb.append(" | Equipo: ").append(this.equipo);
        return sb.toString();
    }

}
