package UD1.ay.ACT5.registroPersonas.clases;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

import java.util.ArrayList;
import java.util.List;

@XmlAccessorType (XmlAccessType.FIELD)
public class Telefonos extends Contacto {

    @XmlElement(name="Telefono") //la etiqueta la nombras aqui
    private List<Telefono> telefonos;

    public Telefonos() {
        this.telefonos = new ArrayList<>();
    }
    public List<Telefono> getTelefonos() {
        return telefonos;
    }
    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos;
    }
}
