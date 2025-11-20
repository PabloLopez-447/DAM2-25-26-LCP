package UD1.ay.ACT5.registroPersonas.clases;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;

//@XmlAccessorType(XmlAccessType.FIELD)
public class Telefono {
    @XmlValue
    private String numero;

    //@XmlElementWrapper(name ="Telefonos") no se puede usar wrapper porque no siempre habra telefonos, hay clase telefonos
    @XmlAttribute (name="tipo")
    private String tipo;
    //
    //@XmlElement (name="Telefono")

    public Telefono() {}

    public Telefono(String tipo) {
        this.tipo = tipo;
    }

    public Telefono(String tipo, String numero) {
        this.tipo = tipo;
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
}
