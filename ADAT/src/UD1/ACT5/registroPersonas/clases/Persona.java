package UD1.ACT5.registroPersonas.clases;

import UD1.ACT5.corredoresYEquipos.util.LocalDateAdapter;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({Trabajador.class, Estudiante.class})
@XmlTransient
public abstract class Persona {

    @XmlElement(name = "Nombre",required = true)
    private String nombre;

    @XmlElement (name = "FechaNacimiento",required = true)
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechaNacimiento;

    @XmlElements({
            @XmlElement (name = "Telefonos", type = Telefonos.class),
            @XmlElement (name = "Email", type = Email.class)
    })
    private Contacto contacto;


    public Persona() {}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Contacto getContacto() {
        return contacto;
    }

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

}
