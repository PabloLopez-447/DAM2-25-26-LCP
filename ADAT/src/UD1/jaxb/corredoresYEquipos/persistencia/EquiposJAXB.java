package UD1.jaxb.corredoresYEquipos.persistencia;

import UD1.jaxb.XMLJAXBUtils;
import UD1.jaxb.corredoresYEquipos.clases.equipos.Equipo;
import UD1.jaxb.corredoresYEquipos.clases.equipos.Equipos;
import jakarta.xml.bind.JAXBException;

import java.util.ArrayList;
import java.util.List;

public class EquiposJAXB {
    public List<Equipo> listarEquipos(String xml){
        try {
            Equipos equipos = XMLJAXBUtils.unmarshal(Equipos.class, xml);
            return equipos.getEquipo();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }
}
