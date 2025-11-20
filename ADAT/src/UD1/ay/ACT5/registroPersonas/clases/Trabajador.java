package UD1.ay.ACT5.registroPersonas.clases;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)

public class Trabajador extends Persona {

    @XmlElement (name = "Empresa",required = true)
    private Empresa empresa;

    @XmlElement (name = "Salario",required = true)
    private float salario;

    @XmlElement(name="Telefonos")
    private Telefonos telefonos;

    public Trabajador() {}
    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }
}
