package UD1.ay.ACT5.registroPersonas.clases;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
public class Empresa {

    @XmlAttribute(name="puesto",required=true)
    private String puesto;

    //que sea valor
    @XmlValue
    private String nombreEmpresa;

    public  Empresa() {
    }

    public Empresa(String puesto, String nombreEmpresa) {
        this.puesto = puesto;
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
}
