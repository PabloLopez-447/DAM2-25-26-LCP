package UD1.ay.ACT5.corredoresYEquipos.clases;

import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "corredores")
@XmlAccessorType(XmlAccessType.FIELD)
public class Corredores {
    @XmlElements({
            @XmlElement(name = "velocista", type = Velocista.class),
            @XmlElement(name = "fondista", type = Fondista.class)
    })
    private List<Corredor> corredores = new ArrayList<>();

    public List<Corredor> getCorredores() {
        return this.corredores;
    }

    public void setCorredores(List<Corredor> corredores) {
        this.corredores = corredores;
    }
    @Override
    public String toString() {
        return "Corredores{" +
                "corredores=" + corredores +
                '}';
    }
}
