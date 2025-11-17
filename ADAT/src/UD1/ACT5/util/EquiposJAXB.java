package UD1.ACT5.util;

import UD1.ACT5.clases.Equipos;
import jakarta.xml.bind.JAXBException;

public class EquiposJAXB {
    public Equipos leerEquipos(String rutaXML) {
        try {
            return XMLJAXBUtils.unmarshal(Equipos.class, rutaXML);
        } catch (JAXBException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
