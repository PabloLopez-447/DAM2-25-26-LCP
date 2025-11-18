package UD1.ACT5.registroPersonas.clases;

import UD1.ACT5.registroPersonas.util.LocalDateAdapter;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;
import java.util.List;

@XmlRootElement(name = "Registro")
@XmlAccessorType(XmlAccessType.FIELD)
public class Registro {
    @XmlAttribute(name = "version", required = true)
    private int version;
    @XmlAttribute(name = "fechaCreacion", required = true)
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechaCreacion;
    @XmlElement(name = "Categorias")
    List<Categoria> categorias;
    @XmlElementWrapper(name = "Personas")
    @XmlElement()
    List<Persona> personas;
}
