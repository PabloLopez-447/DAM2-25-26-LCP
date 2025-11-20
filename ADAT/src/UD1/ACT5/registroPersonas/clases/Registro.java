package UD1.ACT5.registroPersonas.clases;

import UD1.ACT5.registroPersonas.utiles.LocalDateAdapter;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//<Registro version="1.0" fechaCreacion="17-11-25">

@XmlRootElement (name = "Registro")
public class Registro {

    @XmlAttribute (name ="version", required = true)
    private String version;

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    @XmlAttribute(name = "fechaCreacion", required = true)
    private LocalDate fechaCreacion;

    @XmlElement (name ="Categorias")
    @XmlList
    private List<String> categorias = new ArrayList<>();
    //esto se declara aqui y se añade desde el gestor Estudios, Trabajo, Deporte


    @XmlElementWrapper(name = "Personas")
    @XmlElements({
            @XmlElement (name = "Trabajador" ,type = Trabajador.class),
            @XmlElement (name = "Estudiante" ,type = Estudiante.class)
    })
    private List<Persona> personas = new ArrayList<>();

    public Registro() {
    }


    @XmlTransient
    public List<String> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<String> categorias) {
        this.categorias = categorias;
    }
    @XmlTransient
    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    @XmlTransient
    public String getVersion() {
        return version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    @XmlTransient
    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }
}
